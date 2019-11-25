/**
 * 统一异常处理
 * @param {Object} options 上层传递给模块的参数对象
 */
module.exports = options => {
  return async(ctx, next) => {
    // 异常处理捕获
    try {
      await next()
    } catch (error) {
      console.log(error)
      // 交给统一格式化中间件去处理输出
      return ctx.sendResult(null, error.statusCode || 400, error.message)
    }
  }
}
