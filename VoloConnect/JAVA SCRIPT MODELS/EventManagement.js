const pool = require('../config/database');

class EventManagement {
    static async assignVolunteer(eventId, volunteerId, role) {
        const [result] = await pool.execute(
            'INSERT INTO event_volunteers (event_id, volunteer_id, role) VALUES (?, ?, ?)',
            [eventId, volunteerId, role]
        );
        return result.insertId;
    }

    static async removeVolunteer(eventId, volunteerId) {
        const [result] = await pool.execute(
            'DELETE FROM event_volunteers WHERE event_id = ? AND volunteer_id = ?',
            [eventId, volunteerId]
        );
        return result.affectedRows > 0;
    }

    static async getEventVolunteers(eventId) {
        const [rows] = await pool.execute(
            `SELECT v.*, ev.role 
             FROM volunteers v 
             JOIN event_volunteers ev ON v.id = ev.volunteer_id 
             WHERE ev.event_id = ?`,
            [eventId]
        );
        return rows;
    }

    static async getVolunteerEvents(volunteerId) {
        const [rows] = await pool.execute(
            `SELECT e.*, ev.role 
             FROM events e 
             JOIN event_volunteers ev ON e.id = ev.event_id 
             WHERE ev.volunteer_id = ?`,
            [volunteerId]
        );
        return rows;
    }

    static async updateVolunteerRole(eventId, volunteerId, newRole) {
        const [result] = await pool.execute(
            'UPDATE event_volunteers SET role = ? WHERE event_id = ? AND volunteer_id = ?',
            [newRole, eventId, volunteerId]
        );
        return result.affectedRows > 0;
    }
}

module.exports = EventManagement; 