// 导入验证框架
const Joi = require('joi')
module.exports = options => {
  return {
    /** 请求方式 */
    method: 'post',
    auth: false,
    /** 表单验证 */
    schema: Joi.object().keys({
      type: Joi.number().required().label('类型不能为空'),
      pagenum: Joi.number().required().label('页码不能为空'),
      pagesize: Joi.number().required().label('每页条数不能为空'),
    }),
    /** 业务逻辑代码 */
    action: async(ctx) => {
      // 获取参数数据
      const {pagenum,pagesize,type} = ctx.request.body;
      const {InfoController} = ctx.controllers;
      let list = null;
      if(type == 3) {
        // 资讯列表
        list = await InfoController.questionList(pagenum,pagesize);
      }else{
        // 问答列表
        list = await InfoController.infoList(pagenum,pagesize,type)
      }
      return ctx.sendResult({
        list
      }, 200, '获取数据成功')
    }
  }
}
