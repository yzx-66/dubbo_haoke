const fs = require('fs')
const path = require('path')
/**
 * 自动化加载业务逻辑对象
 * @param {Object} options 上层参数
 */
module.exports = (options, db) => {
  // 实现自动化创建业务逻辑对象并且挂载在ctx上
  // 业务逻辑对象容器
  const controllers = {}

  // 设计业务逻辑 XXXController 代码结构
  // 通过配置文件去获取所有的业务逻辑文件
  const controllersPath = options.config.controllers_path || '/controllers'

  // 读取业务逻辑文件夹中的文件并且自动化初始化
  fs.readdirSync(path.join(process.cwd(), controllersPath))
    // 过滤业务逻辑文件
    .filter(controllerName => {
      // 过滤以 '.' 开头的文件，过滤只能是文件不能是文件夹,文件必须是 '.js'后缀
      return controllerName.indexOf('.') !== 0 &&
        fs.statSync(path.join(process.cwd(), controllersPath, controllerName)).isFile &&
        controllerName.endsWith('.js') &&
        controllerName !== 'Controller.js' // 过滤超类
    })
    .map(controllerName => {
      // 去除文件后缀 ‘.js’
      return controllerName.slice(0, -3)
    })
    .forEach(controllerName => {
      // 自动化构建业务逻辑对象
      controllers[controllerName] = require(path.join(process.cwd(), controllersPath, controllerName + ".js"))(options, db)
    })
  // console.log("业务逻辑对象:", controllers)
  // 把controllers 挂载在context对象上 
  return async(ctx, next) => {
    ctx.controllers = controllers
    await next()
  }
}
