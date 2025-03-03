-- Umetanje nastavnika
INSERT INTO teacher (email, password, first_name, last_name) VALUES
('teacher1@example.com', 'password123', 'John', 'Doe'),
('teacher2@example.com', 'password123', 'Jane', 'Smith'),
('teacher3@example.com', 'password123', 'Mark', 'Johnson');

-- Umetanje razreda
INSERT INTO classroom (name, teacher_id) VALUES
('I-1', 1),
('II-1', 2),
('III-1', 3);

-- Umetanje predmeta
INSERT INTO subject (name) VALUES
('Matematika'),
('Bosanski jezik'),
('Engleski jezik'),
('Priroda i društvo'),
('Muzička kultura');

-- Umetanje povezanosti predmeta i razreda
INSERT INTO classroom_subject (classroom_id, subject_id) VALUES
(1, 1), (1, 2), (1, 3),
(2, 1), (2, 2), (2, 3), (2, 4),
(3, 1), (3, 2), (3, 3), (3, 4), (3, 5);

-- Umetanje učenika
INSERT INTO student (first_name, last_name, classroom_id) VALUES
('Amar', 'Mehic', 1),
('Emina', 'Smajic', 1),
('Haris', 'Prelic', 2),
('Lamija', 'Hasanovic', 2),
('Adnan', 'Sehic', 3);

-- Umetanje ocjena
INSERT INTO grade (grade_value, student_id, subject_id) VALUES
(5, 1, 1), (4, 1, 2), (3, 1, 3),
(5, 2, 1), (5, 2, 2), (4, 2, 3),
(4, 3, 1), (3, 3, 2), (5, 3, 4),
(5, 4, 1), (4, 4, 2), (4, 4, 4),
(3, 5, 1), (5, 5, 2), (4, 5, 3), (5, 5, 5);
