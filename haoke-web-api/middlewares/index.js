const path = require('path')
// 引入中间件合成模块
const compose = require('koa-compose')
/**
 * 统一中间件的加载机制
 * @param {Object} options 外层统一传入的参数
 */
module.exports = (options, db) => {
  // 创建所需中间件的容器
  const middlewares = []
  /*
    日志中间件
    安装日志中间件 npm install --save koa-logger
    引入日志中间件模块,通过日志开关判断是否加载日志中间件
  */
  if (options.config.logging) {
    middlewares.push(require('koa-logger')())
  }
  
  // 静态资源服务
  middlewares.push(require('./koa-static-folders')(options))

  // 加载统一格式化输出中间件
  middlewares.push(require('./koa-fmt-response')(options))

  // 加载统一异常处理中间件,注意必须放在统一格式化输入中间件下面，因为异常处理中间件的输出依赖于统一格式化输出中间件
  middlewares.push(require('./koa-error-handler')(options))

  // 加载joi中间件
  middlewares.push(require('./koa-joi')(options))

  // 之后的所有中间件如果抛出异常，那么将会被统一异常处理中间件接获，
  middlewares.push(require('koa-bodyparser')())

  // 引入自动化加载业务逻辑中间件
  middlewares.push(require('./koa-controllers')(options, db))

  // 引入上传中间件模块
  const Multer = require('koa-multer')
  // 创建上传对象专门负责解析上传文件
  const upload = Multer({
    // 设置上传文件目录
    dest: path.join(process.cwd(), options.config.upload)
  })
  // 加载自动化路由中间件
  // 因为自动化路由中间件需要验证模块，所以让 jwt 验证模块集成进自动化路由中间件 
  const ops = Object.assign(options, {
    jwt: require('koa-jwt')({secret: options.config.jwt.secret}),
    upload: upload.single('file')
  })
  middlewares.push(require('./koa-auto-router')(ops))

  // 最后添加一个测试中间件
  middlewares.push(async(ctx, next) => {
    // 未来所有的调用返回值都使用 sendResult 进行输出
    // ctx.sendResult("Hello World",200,"成功")
    ctx.throw(404, "Not Found")
  })

  // 合成一个中间件返回
  return compose(middlewares)
}
