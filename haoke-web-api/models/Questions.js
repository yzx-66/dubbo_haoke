module.exports = function(sequelize, DataTypes) {
  return sequelize.define('questions', {
    id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    question_name: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    user_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      defaultValue: '1'
    },
    question_time: {
      type: DataTypes.INTEGER(11),
      allowNull: true,
      defaultValue: '1'
    }
  }, {
    tableName: 'questions',
    timestamps: false
  });
};
