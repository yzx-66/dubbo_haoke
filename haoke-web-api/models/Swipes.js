module.exports = function(sequelize, DataTypes) {
  return sequelize.define('swipes', {
    id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    original_path: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    thumbnail_path: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    status: {
      type: DataTypes.INTEGER(2),
      allowNull: true,
      defaultValue: '1'
    }
  }, {
    tableName: 'swipes',
    timestamps: false
  });
};
