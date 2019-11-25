// 数据包类型
const KEY_type = Symbol("_type")
// 数据包内容
const KEY_content = Symbol("_content")

class DataPacket {
  constructor(rawData) {
    let data = {
        type: "",
        content: ""
      }
      // 原始数据解析
    if (typeof(rawData) === "string") {
      data = JSON.parse(rawData)
    } else if (typeof(rawData) === "object") {
      data = rawData
    }
    // 对属性赋值
    Object.assign(this, data)
  }

  set type(type) {
    this[KEY_type] = type
  }
  get type() {
    return this[KEY_type]
  }

  set content(content) {
    this[KEY_content] = content
  }

  get content() {
    return this[KEY_content]
  }

  /**
   * 返回底层原始通讯格式
   */
  rawData() {
    return JSON.stringify({
      type: this.type,
      content: this.content
    })
  }

}

module.exports = DataPacket
