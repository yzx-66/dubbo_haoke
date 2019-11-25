/**
 * 统一格式化输出
 * @param {Object} options 上层传递的全局参数
 */
module.exports = options => {
  return async(ctx, next) => {
    // 在 ctx 对象上 挂载一个 sendResult 方法
    // 未来所有的调用返回值都使用 sendResult 进行输出
    ctx.sendResult = (data, errCode, errMsg) => {
      ctx.body = {
        data: data,
        meta: {
          status: errCode,
          msg: errMsg
        }
      }
    }
    // 让中间件继续往下执行
    await next()
  }
}
