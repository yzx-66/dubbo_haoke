// 导入验证框架
const Joi = require('joi')
// 导入 jwt 模块
const jwt = require('jsonwebtoken')
module.exports = options => {
  return {
    /** 请求方式 */
    method: 'post',
    auth: false,
    /** 表单验证 */
    schema: Joi.object().keys({
      uname: Joi.string().required().label('用户名不能为空'),
      pwd: Joi.string().required().label('密码不能为空')
    }),
    /** 业务逻辑代码 */
    action: async(ctx) => {
      // 获取表单数据
      const {uname,pwd} = ctx.request.body;
      // 获取业务逻辑控制器，并且调用业务逻辑代码
      const {UserController} = ctx.controllers
      // 通过用户业务逻辑对象执行小程序登录获取用户对象
      const user = await UserController.login(uname, pwd)
      const token = jwt.sign({uId:user.id},options.config.jwt.secret,{
        // 设置有效期
        expiresIn: options.config.jwt.expiresIn || '7d'
      })
      return ctx.sendResult({
        uid: user.id,
        uname: uname,
        token: 'Bearer ' + token
      }, 200, '登录成功')
    }
  }
}
