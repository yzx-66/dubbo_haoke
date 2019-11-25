const path = require('path')
// 中间件合成
const compose = require('koa-compose')
// 导入静态服务模块
const staticServe = require('koa-static')
// 导入自定义路由中间件模块
const mount = require('koa-mount')
/**
* 设置静态服务目录
* @param {Object} options 上层传递的参数
*/
module.exports = options => {
  // 通过 options 获取配置信息
  const staticFolders = options.config.static_folders || {}
  // 通过 koa-static 和 koa-mount 配合使用来制作静态服务
  // staticServe 函数参数是一个本机的物理路径
  // staticServe 仅仅只能映射一个目录，而且只是根目录
  // mount 自定义一个虚拟目录
  return compose(
    Object.keys(staticFolders).map(key => {
      // 这里返回的就是 自定义静态目录服务的中间件对象
      return mount(key, staticServe(path.join(process.cwd(), staticFolders[key])))
    })
  )
}
