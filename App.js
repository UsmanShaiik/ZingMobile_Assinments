const express = require('express');
const bodyParser = require('body-parser');
const mysql = require('mysql');

const app = express();
const PORT = process.env.PORT || 3005;

app.use(bodyParser.json());

const connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: '', 
  database: 'CRUD' 
});

connection.connect(err => {
  if (err) throw err;
  console.log('Connected to MySQL database');

  // Inserting the provided data into the students table
  const studentsData = [
    { name: 'Adarsh', section: 'A', subject_marks: '{"math": 85, "science": 90, "history": 75}', collegeId: 1 },
    { name: 'Andrew', section: 'B', subject_marks: '{"math": 78, "science": 92, "history": 80}', collegeId: 1 },
    { name: 'maddy', section: 'A', subject_marks: '{"math": 95, "science": 88, "history": 85}', collegeId: 2 },
    { name: 'watson', section: 'B', subject_marks: '{"math": 82, "science": 91, "history": 78}', collegeId: 4 },
    { name: 'andrew', section: 'A', subject_marks: '{"math": 90, "science": 85, "history": 88}', collegeId: 3 }
  ];

  connection.query('INSERT INTO students (name, section, subject_marks, collegeId) VALUES ?', [studentsData.map(student => [student.name, student.section, student.subject_marks, student.collegeId])], (err, result) => {
    if (err) throw err;
    console.log('Data inserted into students table.');

    // Middleware to check role-based access
    const checkPermission = (req, res, next) => {
      const { role, collegeId, section, studentId } = req.query;

      switch (role) {
        case 'superAdmin':
          next();
          break;
        case 'admin':
          if (collegeId) {
            next();
          } else {
            res.status(403).json({ error: 'Admin permission denied. Provide collegeId.' });
          }
          break;
        case 'teacher':
          if (section) {
            next();
          } else {
            res.status(403).json({ error: 'Teacher permission denied. Provide section.' });
          }
          break;
        case 'student':
          if (studentId === req.params.id) {
            next();
          } else {
            res.status(403).json({ error: 'Student permission denied. Access only your data.' });
          }
          break;
        default:
          res.status(403).json({ error: 'Invalid role.' });
      }
    };

    // READ data
    app.get('/students/:id', checkPermission, (req, res) => {
      const { role } = req.query;
      const { id } = req.params;

      let query = 'SELECT * FROM students';

      switch (role) {
        case 'admin':
          query += ` WHERE collegeId = ${req.query.collegeId}`;
          break;
        case 'teacher':
          query += ` WHERE section = '${req.query.section}'`;
          break;
        case 'student':
          query += ` WHERE id = ${id}`;
          break;
        default:
          break;
      }

      connection.query(query, (err, results) => {
        if (err) throw err;
        res.json(results);
      });
    });

    // WRITE data
    app.post('/students', checkPermission, (req, res) => {
      const { role } = req.query;

      if (role === 'superAdmin') {
        const student = req.body;
        connection.query('INSERT INTO students SET ?', student, (err, result) => {
          if (err) throw err;
          res.status(201).json({ message: 'Student created successfully.' });
        });
      } else {
        res.status(403).json({ error: 'Write permission denied. Only Super Admin can create students.' });
      }
    });

    // UPDATE data
    app.put('/students/:id', checkPermission, (req, res) => {
      const { role } = req.query;
      const { id } = req.params;

      if (role === 'student' && id !== req.query.studentId) {
        res.status(403).json({ error: 'Update permission denied. Access only your data.' });
        return;
      }

      const student = req.body;
      connection.query('UPDATE students SET ? WHERE id = ?', [student, id], (err, result) => {
        if (err) throw err;
        res.json({ message: 'Student updated successfully.' });
      });
    });

    // DELETE data
    app.delete('/students/:id', checkPermission, (req, res) => {
      const { role } = req.query;
      const { id } = req.params;

      if (role === 'superAdmin' || (role === 'student' && id === req.query.studentId)) {
        connection.query('DELETE FROM students WHERE id = ?', id, (err, result) => {
          if (err) throw err;
          res.json({ message: 'Student deleted successfully.' });
        });
      } else {
        res.status(403).json({ error: 'Delete permission denied.' });
      }
    });

    app.listen(PORT, () => {
      console.log(`Server is running on port ${PORT}`);
    });
  });
});
