module.exports = function(sequelize, DataTypes) {
  return sequelize.define('chatinfos', {
    id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    from_user: {
      type: DataTypes.INTEGER(11),
      allowNull: true
    },
    to_user: {
      type: DataTypes.INTEGER(11),
      allowNull: true
    },
    chat_time: {
      type: DataTypes.INTEGER(13),
      allowNull: false,
      defaultValue: '1'
    },
    chat_msg: {
      type: DataTypes.STRING(255),
      allowNull: true,
      defaultValue: '1'
    },
    info_type: {
      type: DataTypes.INTEGER(1),
      allowNull: true
    },
  }, {
    tableName: 'chatinfos',
    timestamps: false
  });
};
