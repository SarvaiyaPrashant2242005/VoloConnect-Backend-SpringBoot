const { DataTypes } = require('sequelize');
const sequelize = require('../config/sequelize');

const Volunteer = sequelize.define('Volunteer', {
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
        unique: true,
        validate: {
            isEmail: true
        }
    },
    phone: {
        type: DataTypes.STRING,
        allowNull: false
    },
    skills: {
        type: DataTypes.TEXT,
        allowNull: true
    },
    availability: {
        type: DataTypes.TEXT,
        allowNull: true
    },
    bio: {
        type: DataTypes.TEXT,
        allowNull: true
    },
    preferences: {
        type: DataTypes.TEXT,
        allowNull: true
    },
    status: {
        type: DataTypes.ENUM('active', 'inactive', 'pending'),
        defaultValue: 'pending'
    }
}, {
    timestamps: true,
    tableName: 'volunteers'
});

module.exports = Volunteer; 