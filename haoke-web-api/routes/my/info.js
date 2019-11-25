const Joi = require('joi')
module.exports = options => {
  return {
    method: 'post',
    auth: false,
    schema: Joi.object().keys({
      user_id: Joi.number().required().label('用户ID不能为空'),
    }),
    action: async(ctx) => {
      // 获取参数数据
      const {user_id} = ctx.request.body;
      const {UserController} = ctx.controllers
      const user = await UserController.getInfo(user_id)
      return ctx.sendResult(user, 200, '获取数据成功')
    }
  }
}
