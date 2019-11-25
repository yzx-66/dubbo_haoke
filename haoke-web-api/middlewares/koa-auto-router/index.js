const fs = require('fs')
const path = require('path')
// 加载中央路由中间件模块
const Router = require('koa-router')
// 把多个中间件合成一个中间件
const compose = require('koa-compose')
/**
 * 自动化路由中间件
 * @param {Object} options 上层传入参数
 */
module.exports = options => {
  /**
   * 实现自动化路由规则基于 koa-router
   * 实现自动化路由之前，定义规则：
   * 1. 规范路由文件夹和文件的定义，文件系统与路径之间的映射关系：在路由目录下，
        文件夹的名称表示路由的路径，在文件夹中的文件，表示请求具体动作
   * 2. 定义路由的具体执行文件格式的内容
   * 2.1 模块根函数携带 options
   * 2.2 定义返回值 json，json 内部定义了整个请求的参数
   */

  // 1. 创建中央路由对象
  const rootRouter = new Router()
  // 2. 通过路由路径获取路由文件夹列表
  // 2.1 获取路由文件存储的路径
  const routesPath = path.join(process.cwd(), options.config.routes_path)
    // 2.2 获取文件列表
  fs.readdirSync(routesPath)
    // 2.3 过滤不需要的文件
    .filter(routeName => {
      // 2.3.1 过滤 index.js
      return routeName !== 'index.js' &&
        // 2.3.2 过滤所有的文件必须是文件夹
        fs.statSync(path.join(routesPath, routeName)).isDirectory() &&
        // 2.3.3 过滤以'.'符号开头的文件
        routeName.indexOf('.') !== 0
    })
    .forEach(routeName => {
      // 读取到了支持的文件夹
      // 3. 通过文件夹的名称创建子路由并且添加到根路由中
      // 3.1 创建子路由对象
      const subRouter = new Router()
        // 3.2 读取具体执行文件对象，并且读取配置信息生成具体路由访问代码
        // 3.2.1 获取所有的执行文件列表过滤掉不符合规范的文件
      fs.readdirSync(path.join(routesPath, routeName))
        // 3.2.2 过滤不符合条件的文件
        .filter(actionName => {
          // 3.2.2.1 过滤 index.js
          return actionName !== 'index.js' &&
            // 3.2.2.2 过滤必须是一个文件而不是文件夹
            fs.statSync(path.join(routesPath, routeName, actionName)).isFile() &&
            // 3.2.2.3 过滤以 '.' 开头的文件
            actionName.indexOf('.') !== 0 &&
            // 3.2.3.4 必须是 '.js' 做为后缀的文件
            actionName.endsWith('.js')
        })
        // 3.2.3 去除 .js 文件的后缀并且小写化
        .map(actionName => {
          return actionName.slice(0, -3).toLowerCase()
        })
        .forEach(actionName => {
          // 提取完成了整个需要获取的路由的路径和具体执行的动作的名称
          // 4. 通过路径获取具体执行文件的 json 配置信息，
          //    通过 json 配置来生成具体的路由的动作的代码
          // 4.1 获取执行文件的 json 配置
          // 4.1.2 导入执行文件模块
          const actionConfigFn = require(path.join(routesPath, routeName, actionName + ".js"))
            // 4.1.3 验证模块的格式
          if (actionConfigFn && typeof(actionConfigFn) === 'function') {
            // 4.1.4 执行模块函数获取配置对象
            let actionConfig = actionConfigFn(options)
            // 4.1.5 验证配置对象
            if (
              actionConfig &&
              actionConfig['action'] &&
              typeof(actionConfig['action']) === 'function'
            ) {
              let method = actionConfig['method'] || 'get'
              method = method.toLowerCase()
              // 过滤路由是否有这个方案
              if (subRouter[method]) {
                // 需要集成 jwt 模块
                // 动作容器
                const actions = []
                // 判断是否需要验证,如果配置需要验证就把验证中间件添加到动作列表中
                if (actionConfig.auth) {
                  actions.push(options.jwt)
                }
                // 解析上传文件中间件
                if (actionConfig.upload) {
                  actions.push(options.upload)
                }
                // 表单验证逻辑
                // 获取表单验证规则
                const schema = actionConfig["schema"]
                if (schema && typeof(schema) === 'object') {
                  // 动态添加表单验证中间件
                  actions.push(async(ctx, next) => {
                    // 表单验证代码
                    // 构建表单数据
                    const formData = {}
                    if (method === 'post' || method === 'put' || method === 'patch' || method === 'delete') {
                      Object.assign(formData, ctx.request.body)
                    } else if (method === 'get') {
                      Object.assign(formData, ctx.request.query)
                    }
                    // 验证表单数据
                    ctx.joi(formData, schema)
                    await next()
                  })
                }
                // 把最后业务逻辑动作对象添加到动作容器中
                actions.push(actionConfig['action'])
                // 通过请求方式构建路由
                subRouter[method](`/${actionName}`, compose(actions))
              }
            }
          }
        })
      // 把子路由添加到根路由中
      rootRouter.use(`/${routeName}`, subRouter.routes())
    })
  // 返回中央路由中间件
  return rootRouter.routes()
}
