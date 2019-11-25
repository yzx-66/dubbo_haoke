const jwt = require('jsonwebtoken')
const sha1 = require('node-sha1')
const Controller = require('./Controller.js')

/**
 * 用户相关的业务逻辑
 */
class UserController extends Controller {
  async login(uname, pwd) {
    const {User} = this.db.models
    const user = await User.findOne({
        // 设置查询条件
        where:{username: uname,password: pwd}
    })
    if(user == null) {
        throw new Error('用户名或者密码错误')
    }
    return {
      id: user.id,
      username: user.username,
      password: user.password
    };
  }
  async getInfo(userId) {
    const {User} = this.db.models
    const user = await User.findOne({
      where:{id: userId}
    })
    if(user == null) {
      throw new Error('获取用户信息失败')
    }
    return user;
  }
  async updateAvatar(avatar,uId) {
    const Op = this.db.Sequelize.Op
    const {User} = this.db.models
    const ret = await User.update({
      avatar: avatar
    }, {
      where: {
        id: {
          [Op.eq]: uId
        }
      }
    })
    if(ret == null) {
      throw new Error('用户头像更新失败')
    }
    return ret;
  }
}

module.exports = (options, db) => {
  return new UserController(options, db)
}
