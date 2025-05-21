const { DataTypes } = require('sequelize');
const sequelize = require('../config/sequelize');

const Query = sequelize.define('Query', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    name: {
        type: DataTypes.STRING,
        allowNull: false
    },
    email: {
        type: DataTypes.STRING,
        allowNull: false,
        validate: {
            isEmail: true
        }
    },
    subject: {
        type: DataTypes.STRING,
        allowNull: false
    },
    message: {
        type: DataTypes.TEXT,
        allowNull: false
    },
    response: {
        type: DataTypes.TEXT,
        allowNull: true
    },
    status: {
        type: DataTypes.ENUM('pending', 'responded', 'closed'),
        defaultValue: 'pending'
    }
}, {
    timestamps: true,
    tableName: 'queries'
});

module.exports = Query; 