// 读取启动环境 
const env = process.env.NODE_ENV
// 导入模块
const Koa = new require('koa')
const cors = require('koa2-cors')
// 创建 Web 应用
const app = new Koa()

// 初始化options参数
const options = {
  config: require('./config')
}

// 引入数据库模块
const Database = require('./commons/db')

// 初始化数据库
const db = Database(options)

// 引入中间件统一模块
const Middlewares = require('./middlewares')

// 解决跨域问题
app.use(cors())

// 应用中间件
app.use(Middlewares(options,db))

// 启动 Web 应用
app.listen(8086)

/* 启动环境配置用来做什么 */
/* 如何使用环境配置读取不同的配置文件 */
if (env === 'development') {
  console.log("程序启动成功")
}

/**
 * 启动通讯模块
 */
global.wscons = new Map();
const IMServer = require("./commons/chat/index.js")
const server = new IMServer(8087,db)
server.startService()

