module.exports = function(sequelize, DataTypes) {
  return sequelize.define('informations', {
    id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    info_title: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    info_thumb: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    info_time: {
      type: DataTypes.INTEGER(255),
      allowNull: true
    },
    info_content: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    user_id: {
      type: DataTypes.INTEGER(11),
      allowNull: true
    },
    info_status: {
      type: DataTypes.INTEGER(2),
      allowNull: true
    },
    info_type: {
      type: DataTypes.INTEGER(2),
      allowNull: true
    }
  }, {
    tableName: 'informations',
    timestamps: false
  });
};
