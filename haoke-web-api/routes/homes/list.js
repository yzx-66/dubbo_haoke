/*
  获取房屋列表
*/
module.exports = options => {
  return {
    method: 'post',
    auth: true,
    action: async(ctx) => {
      const {home_type} = ctx.request.body;
      console.log(home_type)
      const {HomeController} = ctx.controllers;
      let list = await HomeController.listByType(home_type);
      return ctx.sendResult(list, 200, '房屋列表数据')
    }
  }
}
