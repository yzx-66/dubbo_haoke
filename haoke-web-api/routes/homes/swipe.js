/*
  获取轮播图数据
*/
module.exports = options => {
  // const baseUrl = 'http://localhost/'
  return {
    method: 'post',
    auth: false,
    action: async(ctx) => {
      console.log(options)
      const {HomeController} = ctx.controllers;
      let list = await HomeController.swipeList();
      list = list.map((item)=>{
        return {
          original: options.config.upload_base_url + item.original_path
        }
      })
      return ctx.sendResult({list}, 200, '测试数据')
    }
  }
}
