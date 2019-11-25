const WebSocket = require("ws")
const WebSocketServer = WebSocket.Server
const DataPacket = require("./datapacket.js")
const EventType = require("./eventtype.js")
const events = require("events")

// 定义成员变量KEY存储端口号
const KEY_port = Symbol("_port")

// 定义成员变量key 存储服务器端变量
const KEY_server = Symbol("_server")

// 定义事件触发器变量名
const key_eventEmitter = Symbol("_eventEmitter")

// 定义启动心跳函数的变量名
const KEY_startHeartBeat = Symbol("_startHeartBeat")

// 定义心跳定时器变量名
const KEY_heartBeatTimer = Symbol("_heartBeatTimer")
class WSServer {
  /**
   * @param {Number} port 服务监听端口
   */
  constructor(port) {
    this[KEY_port] = port
    this[key_eventEmitter] = new events.EventEmitter()
  }
  /**
   * 启动 WebScoket 服务器
   */
  startService() {
    // 在这里创建真正的WebSocketServer
    this[KEY_server] = new WebSocketServer({
      port: this[KEY_port]
    })
    // 监听服务器连接事件
    this[KEY_server].on("connection", client => {
      // client 表示连接客户端
      console.log("连接成功")
      // 4.1  实现事件触发
      this[key_eventEmitter].emit(EventType.CONNECTED, client)
      // 对连接客户端进行监听事件，关闭，出错，接收消息等
      client.on("close", () => {
        console.log("连接关闭")
        // 4.2 实现事件触发
        this[key_eventEmitter].emit(EventType.CLOSED, client)
      })

      client.on("error", err => {
        console.log(err)
      })

      // 接收数据事件监听
      client.on("message", data => {
        // 接收消息
        // 1. 让消息内容构建成数据包格式
        let dp = new DataPacket(data)
        // 2. 通过数据包的类型触发事件，事件类型就是数据包的类型
        this[key_eventEmitter].emit(dp.type, client, dp)
      })
    })

    // 监听服务器出错事件
    this[KEY_server].on("error", err => {
      console.log("server error:", err);
    })
  }

  /**
   * 
   * 专门负责发送数据包封装对象
   * @param {ws} client 客户端连接对象
   * @param {DataPacket} dataPacket 数据包对象
   */
  sendDataPacket(client, dataPacket) {
    // 为了发送是否有效所以需要对客户端进行判定
    if (!client || !dataPacket) {
      return
    }
    // 判定客户端当前的连接状态
    // readyState 这个状态来描述客户端当前的连接状态
    // static CONNECTING: number;   // 连接中
    // static OPEN: number;         // 已连接
    // static CLOSING: number;      // 关闭中
    // static CLOSED: number;       // 已关闭
    if (client.readyState == WebSocket.OPEN) {
      // 向客户端发送数据包
      client.send(dataPacket.rawData())
    }
  }


  /**
   * 
   * 2.1 添加事件处理监听器
   * @param {EventType} eventType 事件类型
   * @param {Function} handler 事件处理函数
   */
  addEventListener(eventType, handler) {
    this[key_eventEmitter].on(eventType, handler)
  }

  /**
   * 
   * 2.2 移除事件处理监听器
   * @param {EventType} eventType 事件类型
   * @param {Function} handler 事件处理函数
   */
  removeEventListener(eventType, handler) {
    this[key_eventEmitter].removeListener(eventType, handler)
  }
}

module.exports = WSServer
