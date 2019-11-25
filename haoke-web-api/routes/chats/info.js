const Joi = require('joi')
module.exports = options => {
  return {
    /** 请求方式 */
    method: 'post',
    auth: true,
    /** 表单验证 */
    schema: Joi.object().keys({
      to_user: Joi.number().required().label('to用户ID不能为空'),
      from_user: Joi.number().required().label('from用户ID不能为空')
    }),
    /** 业务逻辑代码 */
    action: async(ctx) => {
      const {to_user,from_user} = ctx.request.body;
      const {ChatInfoController} = ctx.controllers;
      const list = await ChatInfoController.chatInfo(to_user,from_user);
      return ctx.sendResult({
        list
      }, 200, '用户数据')
    }
  }
}
