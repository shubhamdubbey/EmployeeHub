-- Insert into grades
INSERT INTO grades (id, name) VALUES
                                  (1, 'Grade 1'),
                                  (2, 'Grade 2'),
                                  (3, 'Grade 3'),
                                  (4, 'Grade 4'),
                                  (5, 'Grade 5'),
                                  (6, 'Grade 6');

-- Insert into LoginDetails
INSERT INTO login_details (username, password, roles, new_user)
VALUES (
           'admin',
           '$2a$12$nmETnfEIHzbAf1vELZLL9.VIBQlmiSu8VZtlTY6GXyxC09wUdGQzW',
           'ADMIN',
           'Y'
       );
