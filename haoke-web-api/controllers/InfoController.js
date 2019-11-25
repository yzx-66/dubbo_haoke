const Controller = require('./Controller.js')

/**
 * 资讯相关的业务逻辑
 */
class InfoController extends Controller {
  // 获取问题列表
  async questionList(pagenum, pagesize) {
    const sql = 'SELECT u.username,q.question_name,q.question_tag,a.answer_content,a.atime,a.question_id,a.qnum from questions as q left join(SELECT answer_content,max(answer_time) as atime,COUNT(question_id) as qnum,question_id from answers GROUP BY question_id) as a on a.question_id = q.id left JOIN users as u on q.user_id = u.id LIMIT ?, ?';
    const params = {replacements: [pagenum, pagesize], type: this.db.orm.QueryTypes.SELECT};
    const list = await this.db.orm.query(sql, params);
    const ret = await this.db.orm.query('select count(*) as count from questions', {type: this.db.orm.QueryTypes.SELECT});
    if(list == null) {
      throw new Error('没有获取结果');
    }
    return {
      total: ret[0].count,
      data: list
    };
  }
  // 添加问题
  async addQuestion(question, user_id) {
    const {Question} = this.db.models
    const ret = await Question.create({
      question_name: question,
      user_id: user_id,
      question_tag: '学区,昌平',
      question_time: new Date().getTime()
    })
    return ret;
  }
  // 获取资讯列表
  async infoList(pagenum, pagesize, type) {
    const Op = this.db.Sequelize.Op
    const {Infomation} = this.db.models
    const condition = {
      order: [['id', 'desc']],
      offset: parseInt(pagenum), 
      limit: parseInt(pagesize)
    }
    condition.where = {};
    if(type) {
      condition.where.info_type = {
        [Op.eq]: type
      }
    }
    const infos = await Infomation.findAndCountAll(condition)
    if(infos == null) {
      throw new Error('查询出错了')
    }
    return {
      total: infos.count,
      data: infos.rows
    };
  }
}

module.exports = (options, db) => {
  return new InfoController(options, db)
}
