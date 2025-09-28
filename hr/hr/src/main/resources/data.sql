insert into grades values(1,'Good');
insert into grades values(2,'bad');

insert into users values(100001,'shubham','dubey','9755033217','shubham@cognizant.com','HR',1);
insert into users values(100002,'rahul','pandey','9750033248','rahul@cognizant.com','EMPLOYEES',2);

insert into gradeshistory(assigned_on, employee_id, grade_id) values( '2024-01-02', 100001, 2);
insert into gradeshistory(assigned_on, employee_id, grade_id)  values( '2024-10-02', 100001, 1);
insert into gradeshistory(assigned_on, employee_id, grade_id)  values( '2024-11-01', 100002, 2);
insert into gradeshistory(assigned_on, employee_id, grade_id)  values( '2024-01-02', 100002, 1);