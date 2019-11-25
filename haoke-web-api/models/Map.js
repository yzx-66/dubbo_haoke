module.exports = function(sequelize, DataTypes) {
  return sequelize.define('map', {
    id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    x: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    y: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    type: {
      type: DataTypes.INTEGER(2),
      allowNull: true,
      defaultValue: '1'
    }
  }, {
    tableName: 'map',
    timestamps: false
  });
};
