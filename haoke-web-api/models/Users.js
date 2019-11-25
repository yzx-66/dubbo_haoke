module.exports = function(sequelize, DataTypes) {
  return sequelize.define('users', {
    id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    username: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    password: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    mobile: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    type: {
      type: DataTypes.INTEGER(2),
      allowNull: false,
      defaultValue: '1'
    },
    status: {
      type: DataTypes.INTEGER(2),
      allowNull: true,
      defaultValue: '1'
    },
    avatar: {
      type: DataTypes.STRING(255),
      allowNull: true
    }
  }, {
    tableName: 'users',
    timestamps: false
  });
};
