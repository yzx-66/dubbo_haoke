module.exports = function(sequelize, DataTypes) {
  return sequelize.define('answers', {
    id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    answer_content: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    question_id: {
      type: DataTypes.INTEGER(11),
      allowNull: true
    },
    user_id: {
      type: DataTypes.INTEGER(11),
      allowNull: true
    },
    answer_status: {
      type: DataTypes.INTEGER(2),
      allowNull: true
    },
    answer_time: {
      type: DataTypes.INTEGER(11),
      allowNull: true
    }
  }, {
    tableName: 'answers',
    timestamps: false
  });
};
