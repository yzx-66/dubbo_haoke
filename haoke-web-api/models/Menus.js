module.exports = function(sequelize, DataTypes) {
  return sequelize.define('menus', {
    id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    menu_name: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    menu_logo: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    menu_path: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    menu_status: {
      type: DataTypes.INTEGER(2),
      allowNull: false,
      defaultValue: '1'
    },
    menu_style: {
      type: DataTypes.STRING(255),
      allowNull: true,
      defaultValue: '1'
    }
  }, {
    tableName: 'menus',
    timestamps: false
  });
};
