const Joi = require('joi')
const jwt = require('jsonwebtoken')
module.exports = options => {
  return {
    method: 'post',
    auth: false,
    schema: Joi.object().keys({
      avatar: Joi.string().required().label('头像不能为空'),
    }),
    action: async(ctx) => {
      // 获取参数数据
      const {avatar} = ctx.request.body;
      const token = jwt.decode(ctx.header.authorization.substr(7));
      const {UserController} = ctx.controllers
      const ret = await UserController.updateAvatar(avatar,token.uId);
      return ctx.sendResult(ret, 200, '更新头像成功')
    }
  }
}
