const jwt = require('jsonwebtoken')
const sha1 = require('node-sha1')
const Controller = require('./Controller.js')

/**
 * 主页相关的业务逻辑
 */
class HomeController extends Controller {
  // 获取地图数据
  async mapList(type) {
    const {Map} = this.db.models
    const list = await Map.findAll({
      where:{type: type}
    })
    if(list == null) {
      throw new Error('没有地图数据')
    }
    return list;
  }
  // 获取菜单数据
  async menuList(userId) {
    const {Menu} = this.db.models
    const list = await Menu.findAll();
    if(list == null) {
      throw new Error('没有菜单数据')
    }
    return list;
  }
  // 获取轮播图数据
  async swipeList(userId) {
    const {Swipe} = this.db.models
    const list = await Swipe.findAll();
    if(list == null) {
      throw new Error('没有Swipe数据')
    }
    return list;
  }
  // 获取资讯数据
  async infoList() {
    const {Infomation} = this.db.models
    const condition = {
      order: [['info_time', 'desc']],
      offset: 0, 
      limit: 2
    }
    const list = await Infomation.findAll(condition);
    if(list == null) {
      throw new Error('没有资讯数据')
    }
    return list;
  }
  // 获取问答数据
  async faqList() {
    const sql = 'SELECT q.question_name,q.question_tag,a.answer_content,a.atime,a.question_id,a.qnum from questions as q, (SELECT answer_content,max(answer_time) as atime,COUNT(question_id) as qnum,question_id from answers GROUP BY question_id) as a WHERE a.question_id = q.id';
    const params = {type: this.db.orm.QueryTypes.SELECT};
    const list = await this.db.orm.query(sql, params);
    if(list == null) {
      throw new Error('没有获取结果');
    }
    return list;
  }
  // 获取房屋信息
  async houseList() {
    // 每种分类查询前两条数据
    const sql = 'select a.* from(select t1.*,(select count(*)+1 from homes where home_type=t1.home_type and home_time<t1.home_time) as group_id from homes t1) a where a.group_id<=2 order by home_type';
    const params = {type: this.db.orm.QueryTypes.SELECT};
    const list = await this.db.orm.query(sql, params);
    if(list == null) {
      throw new Error('没有获取结果');
    }
    return list;
  }
  // 根据类型获取房屋列表
  async listByType(type) {
    console.log(1)
    const {Home} = this.db.models
    const list = await Home.findAll({
      where:{home_type: type}
    })
    if(list == null) {
        throw new Error('获取房屋信息出错')
    }
    return list;
  }
}

module.exports = (options, db) => {
  return new HomeController(options, db)
}