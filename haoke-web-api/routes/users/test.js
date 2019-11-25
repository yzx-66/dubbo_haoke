// 导入验证框架
// const Joi = require('joi')
// 导入 jwt 模块
// const jwt = require('jsonwebtoken')
module.exports = options => {
  return {
    /** 请求方式 */
    method: 'post',
    auth: true,
    /** 表单验证 */
    // schema: Joi.object().keys({
    //   uname: Joi.string().required().label('用户名不能为空'),
    //   pwd: Joi.string().required().label('密码不能为空')
    // }),
    /** 业务逻辑代码 */
    action: async(ctx) => {
      console.log(123)
      return ctx.sendResult({
        uname: 'test data',
      }, 200, '测试数据')
    }
  }
}
