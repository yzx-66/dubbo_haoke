/*
  获取菜单数据
*/
module.exports = options => {
  return {
    method: 'post',
    auth: true,
    action: async(ctx) => {
      const {HomeController} = ctx.controllers;
      const list = await HomeController.menuList();
      return ctx.sendResult({list}, 200, '测试数据')
    }
  }
}
