const Sequelize = require('sequelize');
const Op = Sequelize.Op;
const sequelize = new Sequelize('myshop', 'root', '', {
  host: 'localhost',
  dialect: 'mysql',
  pool: {
    max: 5,
    min: 0,
    acquire: 30000,
    idle: 10000
  },
  // http://docs.sequelizejs.com/manual/tutorial/querying.html#operators
  operatorsAliases: false
});

const Account = sequelize.define('account', {
  username: Sequelize.STRING,
  password: Sequelize.STRING,
  user_detail: Sequelize.STRING
}, {
  tableName: 'account',
  timestamps: false
});

// -------------------------------------------------
// 查询操作
// Account.findById(1).then(result => {
//   console.log(result.username)
// }).finally(()=>{
//   sequelize.close();
// })
// --------------------------------------------------
Account.findAll().then(result => {
  result.forEach(item => {
    console.log(item.user_detail)
  })
}).finally(()=>{
  sequelize.close();
})
// --------------------------------------------------
// 更新操作
// Account.update({
//   username: 'jerry',
// }, {
//   where: {
//     id: {
//       [Op.eq]: 1
//     }
//   }
// }).then(result => {
//   sequelize.close();
// })
// ----------------------------------------------------
// 插入操作
// Account.create({
//   username: 'spike',
//   password: 123456
// }).then(result => {
//   sequelize.close();
// })
// ----------------------------------------------------
// 删除操作
// Account.destroy({
//   where: {
//     id: 4
//   }
// }).then(result => {
//   sequelize.close();
// })


