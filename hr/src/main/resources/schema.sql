-- Drop tables if exist (for clean setup)
DROP TABLE IF EXISTS gradeshistory;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS authenticatedusers;
DROP TABLE IF EXISTS grades;

-- Create grades table
CREATE TABLE grades (
                        id INT NOT NULL,
                        name VARCHAR(25),
                        PRIMARY KEY (id)
);

-- Create authenticatedusers table
CREATE TABLE authenticatedusers (
                                    user_name VARCHAR(30) NOT NULL,
                                    password VARCHAR(10) NOT NULL,
                                    role VARCHAR(20),
                                    PRIMARY KEY (user_name)
);

-- Create users table
CREATE TABLE users (
                       employee_id INT NOT NULL,
                       first_name VARCHAR(10),
                       last_name VARCHAR(10),
                       phone_number VARCHAR(10),
                       email_address VARCHAR(50),
                       role VARCHAR(40),
                       current_grade_id INT NOT NULL,
                       PRIMARY KEY (employee_id),
                       FOREIGN KEY (current_grade_id) REFERENCES grades(id),
                       CONSTRAINT chk_phone CHECK (
                           phone_number ~ '^[0-9]{10}$' OR phone_number = ''
)
    );

-- Create gradeshistory table
CREATE TABLE gradeshistory (
                               id SERIAL PRIMARY KEY, -- SERIAL replaces auto_increment
                               assigned_on DATE NOT NULL,
                               employee_id INT,
                               grade_id INT NOT NULL,
                               FOREIGN KEY (employee_id) REFERENCES users(employee_id),
                               FOREIGN KEY (grade_id) REFERENCES grades(id)
);
