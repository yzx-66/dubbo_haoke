const path = require('path')
const fs = require('fs')
const Sequelize = require('sequelize')
/**
* 数据库模块:基于 Sequelize 的ORM框架
* @param {Object} options 外层提供参数
*/
module.exports = options => {
  // 1. 创建 ORM框架对象
  const orm = new Sequelize(options.config.db.database, options.config.db.username, options.config.db.password, {
    host: options.config.db.host,
    port: options.config.db.port,
    /** orm 底层使用什么数据库类型 */
    dialect: options.config.db.type,
    pool: options.config.db.pool,
    logging: options.config.db.logging,
    operatorsAliases: false
  })

  // 2. 定义模型的集合容器,通过ORM框架对象加载模型
  const models = {}

  // 读取模型配置文件
  const modelsConfig = require(path.join(process.cwd(), options.config.db.models_path))

  // 通过模型配置文件加载模型对象
  Object.keys(modelsConfig)
    // 过滤如果模型名称没有对应的模型文件就过滤
    .filter(modelName => {
      return fs.existsSync(path.join(process.cwd(), options.config.db.models_path, modelsConfig[modelName]))
    })
    // 通过 orm 对象加载模型,并且把模型对象放入模型集合容器中
    .forEach(modelName => {
      models[modelName] = orm.import(path.join(process.cwd(), options.config.db.models_path, modelsConfig[modelName]))
    })
    // 返回模型集合
  return {
    orm,
    models,
    Sequelize
  }
  // 3. 如果想对数据库进行操作仅仅只需要调用模型对象的方法即可
}
