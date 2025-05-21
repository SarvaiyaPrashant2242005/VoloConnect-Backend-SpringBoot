const pool = require('../config/database');

class VolunteerManagement {
    static async updateProfile(volunteerId, profileData) {
        const { bio, skills, availability, preferences } = profileData;
        const [result] = await pool.execute(
            'UPDATE volunteers SET bio = ?, skills = ?, availability = ?, preferences = ? WHERE id = ?',
            [bio, skills, availability, preferences, volunteerId]
        );
        return result.affectedRows > 0;
    }

    static async updateSkills(volunteerId, skills) {
        const [result] = await pool.execute(
            'UPDATE volunteers SET skills = ? WHERE id = ?',
            [skills, volunteerId]
        );
        return result.affectedRows > 0;
    }

    static async updateAvailability(volunteerId, availability) {
        const [result] = await pool.execute(
            'UPDATE volunteers SET availability = ? WHERE id = ?',
            [availability, volunteerId]
        );
        return result.affectedRows > 0;
    }

    static async getVolunteerStats(volunteerId) {
        const [rows] = await pool.execute(
            `SELECT 
                COUNT(DISTINCT ev.event_id) as total_events,
                SUM(CASE WHEN e.status = 'completed' THEN 1 ELSE 0 END) as completed_events,
                GROUP_CONCAT(DISTINCT ev.role) as roles
             FROM event_volunteers ev
             JOIN events e ON ev.event_id = e.id
             WHERE ev.volunteer_id = ?`,
            [volunteerId]
        );
        return rows[0];
    }

    static async searchVolunteers(criteria) {
        const { skills, availability, status } = criteria;
        let query = 'SELECT * FROM volunteers WHERE 1=1';
        const params = [];

        if (skills) {
            query += ' AND skills LIKE ?';
            params.push(`%${skills}%`);
        }
        if (availability) {
            query += ' AND availability LIKE ?';
            params.push(`%${availability}%`);
        }
        if (status) {
            query += ' AND status = ?';
            params.push(status);
        }

        const [rows] = await pool.execute(query, params);
        return rows;
    }
}

module.exports = VolunteerManagement; 