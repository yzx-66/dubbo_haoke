/*
  获取地图列表
*/
module.exports = options => {
  return {
    method: 'post',
    auth: false,
    action: async(ctx) => {
      const {type} = ctx.request.body;
      const {HomeController} = ctx.controllers;
      let list = await HomeController.mapList(type);
      return ctx.sendResult(list, 200, '地图列表数据')
    }
  }
}
