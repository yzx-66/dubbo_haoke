module.exports = function(sequelize, DataTypes) {
  return sequelize.define('homeimgs', {
    id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    img_path: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    home_id: {
      type: DataTypes.INTEGER(11),
      allowNull: true
    },
    img_status: {
      type: DataTypes.INTEGER(2),
      allowNull: true,
      defaultValue: '1'
    }
  }, {
    tableName: 'homeimgs',
    timestamps: false
  });
};
