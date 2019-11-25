/* 1. 获取环境变量的值 */
const env = process.env.NODE_ENV || 'production'

/* 2. 根据环境变量的值去读取配置文件并且返回 */
module.exports = require(`./${env}.js`)