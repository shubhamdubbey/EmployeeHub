-- Insert into grades
INSERT INTO grades (id, name) VALUES
                                  (1, 'Grade 1'),
                                  (2, 'Grade 2'),
                                  (3, 'Grade 3'),
                                  (4, 'Grade 4'),
                                  (5, 'Grade 5'),
                                  (6, 'Grade 6');

-- Insert into users
INSERT INTO users (employee_id, first_name, last_name, phone_number, email_address, role, current_grade_id)
VALUES
    (100001, 'Shubham', 'Dubey', '9755033217', 'shubham@cognizant.com', 'HR', 5),
    (100002, 'Rahul', 'Pandey', '9755033217', 'rahul@cognizant.com', 'EMPLOYEES', 3),
    (100003, 'Ayush', 'Verma', '9755433217', 'ayush@cognizant.com', 'EMPLOYEES', 6),
    (100004, 'Aarya', 'Shelar', '9753033217', 'aarya@cognizant.com', 'EMPLOYEES', 4),
    (100005, 'Parmeet', 'LNU', '9755233217', 'parmeet@cognizant.com', 'EMPLOYEES', 4),
    (100006, 'Tushar', 'Sahu', '9755133217', 'tushar@cognizant.com', 'TRAVELDESKEXC', 5);

-- Insert into gradeshistory
INSERT INTO gradeshistory (assigned_on, employee_id, grade_id) VALUES
                                                                   ('2021-01-02', 100001, 6),
                                                                   ('2022-03-02', 100001, 5),
                                                                   ('2018-10-02', 100002, 6),
                                                                   ('2020-11-05', 100002, 5),
                                                                   ('2022-04-02', 100002, 4),
                                                                   ('2024-03-02', 100002, 3),
                                                                   ('2024-03-02', 100003, 6),
                                                                   ('2018-10-02', 100004, 6),
                                                                   ('2020-11-05', 100004, 5),
                                                                   ('2022-04-02', 100004, 4),
                                                                   ('2018-10-02', 100005, 6),
                                                                   ('2020-11-05', 100005, 5),
                                                                   ('2022-04-02', 100005, 4),
                                                                   ('2018-10-02', 100002, 6),
                                                                   ('2020-11-05', 100002, 5);

-- Insert into authenticatedusers
INSERT INTO authenticatedusers (email, password, role) VALUES
    ('admin@cognizant.com', 'admin', 'HR');
