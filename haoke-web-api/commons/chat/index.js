// 定义WebSocket 服务器的模块类
const WSServer = require("./wsserver.js")
const DataPacketType = require("./datapackettype.js")
const DataPacket = require("./datapacket.js")
const EventType = require('./eventtype.js')

const KEY_wxServer = Symbol("_wxServer")
const KEY_userReg = Symbol("_userReg")
const KEY_db = Symbol("_db")

class IMServer {

  constructor(wxPort,db) {
    // this[KEY_browserServer] = new WSServer(browserPort)
    this[KEY_wxServer] = new WSServer(wxPort)
    this[KEY_userReg] = new Map()
    this[KEY_db] = db
  }

  /**
   * 启动服务器
   */
  startService(db) {
    // 监听微信小程序端连接成功触发的事件
    this[KEY_wxServer].addEventListener(EventType.CONNECTED, client => {
      // 1. 创建数据包
      let dp = new DataPacket()
        // 2. 设置数据包属性
      dp.type = DataPacketType.WELCOME
      dp.content = "weclome123"
        // 3. 发送数据包
      this[KEY_wxServer].sendDataPacket(client, dp)
    })

    // 监听点对点通信
    this[KEY_wxServer].addEventListener(DataPacketType.MSG_TEXT_SEND, (client, dataPacket) => {
      let c = JSON.parse(dataPacket.content);
      if(this[KEY_userReg].has(c.to_user)) {
        // 添加聊天记录
        const {ChatInfo} = this[KEY_db].models
      console.log(c)

        ChatInfo.create({
          from_user: c.from_user,
          to_user: c.to_user,
          chat_msg: c.chat_msg,
          chat_time: new Date().getTime()
        }).then(ret=>{
          console.log(this[KEY_userReg].size)
          dataPacket.type = DataPacketType.MSG_TEXT_REC;
          ret.avatar = c.avatar;
          let str = JSON.stringify(ret);
          str = JSON.parse(str);
          str.avatar = c.avatar;
          console.log(str)
          dataPacket.content = JSON.stringify(str);
          this[KEY_wxServer].sendDataPacket(this[KEY_userReg].get(c.to_user), dataPacket)
          })
      } else {
        let dp = new DataPacket()
        dp.type = DataPacketType.MSG_TEXT_REC;
        dp.content = "对方不在线";
        this[KEY_wxServer].sendDataPacket(client,dp);
      }
    })

    // 监听用户身份
    this[KEY_wxServer].addEventListener(DataPacketType.USER_REG, (client, dataPacket) => {
      // 记录用户身份
      if(!this[KEY_userReg].has(dataPacket.content)){
        this[KEY_userReg].set(Number(dataPacket.content), client)
      }
    })
    this[KEY_wxServer].startService()
  }
}

module.exports = IMServer
