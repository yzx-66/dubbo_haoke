// 导入验证框架
const Joi = require('joi')
// 导入 jwt 模块
const jwt = require('jsonwebtoken')
module.exports = options => {
  return {
    /** 请求方式 */
    method: 'post',
    auth: true,
    /** 表单验证 */
    // schema: Joi.object().keys({
    //   user_id: Joi.number().required().label('用户ID不能为空')
    // }),
    /** 业务逻辑代码 */
    action: async(ctx) => {
      const token = jwt.decode(ctx.header.authorization.substr(7));
      const {ChatInfoController} = ctx.controllers;
      const list = await ChatInfoController.chatList(token.uId);
      return ctx.sendResult({
        list
      }, 200, '用户列表数据')
    }
  }
}
