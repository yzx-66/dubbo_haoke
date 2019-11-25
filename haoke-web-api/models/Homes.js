module.exports = function(sequelize, DataTypes) {
  return sequelize.define('homes', {
    id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    home_name: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    home_price: {
      type: DataTypes.DECIMAL(10),
      allowNull: true
    },
    home_desc: {
      type: DataTypes.STRING(512),
      allowNull: true
    },
    home_infos: {
      type: DataTypes.STRING(512),
      allowNull: true
    },
    home_type: {
      type: DataTypes.INTEGER(2),
      allowNull: false,
      defaultValue: '1'
    },
    home_tags: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    home_address: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    user_id: {
      type: DataTypes.INTEGER(11),
      allowNull: true,
      defaultValue: '1'
    },
    home_status: {
      type: DataTypes.INTEGER(2),
      allowNull: false,
      defaultValue: '1'
    },
    home_time: {
      type: DataTypes.INTEGER(13),
      allowNull: true
    }
  }, {
    tableName: 'homes',
    timestamps: false
  });
};
