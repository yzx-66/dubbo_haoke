const jwt = require('jsonwebtoken')
const Joi = require('joi')
module.exports = options => {
  return {
    method: 'post',
    auth: true,
    schema: Joi.object().keys({
      question: Joi.string().required().label('问题不能为空'),
    }),
    action: async(ctx) => {
      const {question} = ctx.request.body;
      const token = jwt.decode(ctx.header.authorization.substr(7));
      const {InfoController} = ctx.controllers;
      let list = null;
      let ret = await InfoController.addQuestion(question, token.uId);
      return ctx.sendResult({
        ret
      }, 200, '提问成功')
    }
  }
}
