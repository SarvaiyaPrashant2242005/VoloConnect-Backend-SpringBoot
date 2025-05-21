const { DataTypes } = require('sequelize');
const sequelize = require('../config/sequelize');

const EventVolunteer = sequelize.define('EventVolunteer', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    event_id: {
        type: DataTypes.INTEGER,
        allowNull: false,
        references: {
            model: 'events',
            key: 'id'
        }
    },
    volunteer_id: {
        type: DataTypes.INTEGER,
        allowNull: false,
        references: {
            model: 'volunteers',
            key: 'id'
        }
    },
    role: {
        type: DataTypes.STRING,
        allowNull: false
    }
}, {
    timestamps: true,
    tableName: 'event_volunteers'
});

module.exports = EventVolunteer; 