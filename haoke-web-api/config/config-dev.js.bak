module.exports = {
  env: "development",
  /* 日志开关负责是否打印日志 */
  logging: true,
  /* 配置静态服务目录 */
  static_folders: {
    /* "web访问的虚拟路径":"物理路径" */
    "/public": "/public",
    "/data/upload": '/data/upload',
    '/data/qrs': '/data/qrs'
  },
  /* 配置路由文件夹路径 */
  routes_path: '/routes',
  /* 配置 jwt 所需参数*/
  jwt: {
    secret: 'qwer1234',
    expireIn: '7d'
  },
  /** 配置上传文件路径 */
  upload: '/data/upload',
  /** 业务逻辑层的代码目录 */
  controllers_path: '/controllers',
  /** 访问的地址 */
  upload_base_url: 'http://127.0.0.1:8086/',
  /** 数据库配置 */
  db: {
    /** 模型文件路径 */
    models_path: '/models',
    /** 数据库主机IP */
    host: '172.16.55.185',
    /** 数据库的端口号 */
    port: 3306,
    /** 数据库类型 */
    type: 'mysql',
    /** 数据库登录用户名 */
    username: 'root',
    /** 数据库密码 */
    password: 'root',
    /** 数据库名称 */
    database: 'myhome',
    /** 是否显示数据库日志 */
    logging: console.log,// false 为禁用日志
    /** 配置数据库连接池 */
    pool: {
      max: 5,
      min: 0,
      charset: 'utf8',
      idle: 30000
    }
  }
}
