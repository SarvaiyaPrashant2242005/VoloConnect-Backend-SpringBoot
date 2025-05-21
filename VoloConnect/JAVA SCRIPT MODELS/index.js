const Event = require('./Event');
const Volunteer = require('./Volunteer');
const Query = require('./Query');
const EventVolunteer = require('./EventVolunteer');

// Define associations
Event.belongsToMany(Volunteer, {
    through: EventVolunteer,
    foreignKey: 'event_id',
    otherKey: 'volunteer_id'
});

Volunteer.belongsToMany(Event, {
    through: EventVolunteer,
    foreignKey: 'volunteer_id',
    otherKey: 'event_id'
});

// Export models
module.exports = {
    Event,
    Volunteer,
    Query,
    EventVolunteer
}; 