-- Drop tables if exist (for clean setup)
DROP TABLE IF EXISTS gradeshistory;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS grades;
DROP TABLE IF EXISTS login_details;
DROP TABLE IF EXISTS leave_tracker;
DROP TABLE IF EXISTS leaves;

-- Create grades table
CREATE TABLE grades (
                        id INT NOT NULL,
                        name VARCHAR(25),
                        PRIMARY KEY (id)
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

-- Create LoginDetails table
CREATE TABLE login_details (
                                username VARCHAR(50) PRIMARY KEY,
                                password VARCHAR(255) NOT NULL,
                                roles VARCHAR(50) NOT NULL
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

-- Create leave_tracker table
CREATE TABLE leaves (
    employee_id INT PRIMARY KEY,
    sick_leave INT NOT NULL,
    casual_leave INT NOT NULL,
    earned_leave INT NOT NULL,
    paternity_leave INT NOT NULL
);

-- Create leaves table
CREATE TABLE leave_tracker (
    id SERIAL PRIMARY KEY,
    employee_id INT NOT NULL,
    leave_type VARCHAR(50) NOT NULL,
    from_date DATE NOT NULL,
    to_date DATE NOT NULL,
    reason TEXT
);
