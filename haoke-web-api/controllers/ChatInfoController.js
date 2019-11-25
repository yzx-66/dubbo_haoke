const jwt = require('jsonwebtoken')
const sha1 = require('node-sha1')
const Controller = require('./Controller.js')

/**
 * 聊天相关的业务逻辑
 */
class ChatInfoController extends Controller {
  async chatList(userId) {
    const sql = 'select c.id,u.username,u.avatar,c.from_user,c.to_user,c.ctime,c.chat_msg from users as u, (select id,from_user,to_user,max(chat_time) as ctime,chat_msg from chatinfos where from_user = ? GROUP BY to_user) as c where c.to_user = u.id';
    const params = {replacements: [userId], type: this.db.orm.QueryTypes.SELECT};
    const list = await this.db.orm.query(sql, params);
    if(list == null) {
      throw new Error('没有获取结果');
    }
    return list;
  }

  async chatInfo(toUser,fromUser) {
    const sql = 'SELECT c.*,u.username,u.avatar FROM `chatinfos` AS c, `users` AS u where u.id = c.to_user and ((c.from_user = ? and c.to_user=?)||(c.from_user=? and c.to_user=?)) order by c.chat_time asc';
    const params = {replacements: [toUser,fromUser,fromUser,toUser], type: this.db.orm.QueryTypes.SELECT};
    const list = await this.db.orm.query(sql, params);
    if(list == null) {
      throw new Error('没有获取结果');
    }
    return list;
  }
  

}

module.exports = (options, db) => {
  return new ChatInfoController(options, db)
}
