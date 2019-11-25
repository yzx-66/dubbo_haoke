const Joi = require('joi')
/**
* 表单验证中间件
* 为 context 挂载一个 joi 验证函数
*/
module.exports = options => {
  return async(ctx, next) => {
    /**
     * 在 context 对象上挂载一个 验证表单的函数
     * @param {Object} form 表单内容
     * @param {Object} schema 表单验证规则
     */
    ctx.joi = (form, schema) => {
      // if (!form) {
      if(JSON.stringify(form) === '{}'){
        return ctx.throw('表单不能为空')
      }
      const output = Joi.validate(form, schema)
      if (output.error !== null) {
        return ctx.throw(output.error.details[0].context.label || output.error.details[0].context.message)
      }
    }
    await next()
  }
}
