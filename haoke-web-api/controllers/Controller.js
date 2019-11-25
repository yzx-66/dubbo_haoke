const KEY_options = Symbol("_options")
const KEY_db = Symbol("_db")
class Controller {
  constructor(options, db) {
    this[KEY_options] = options
    this[KEY_db] = db
  }
  get options() {
    return this[KEY_options]
  }
  get db() {
    return this[KEY_db]
  }
}
module.exports = Controller
