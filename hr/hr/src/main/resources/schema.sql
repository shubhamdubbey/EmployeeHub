create table grades(

	id int not null,
	name varchar(25),
	Primary Key(id)
);
create table users(
	employee_id int NOT NULL,
    first_name varchar(10),
    last_name varchar(10),
    phone_number varchar(10) ,
    email_address varchar(50),
    role varchar(40),
    current_grade_id int not null,
    Primary Key(employee_id),
    foreign key(current_grade_id) references grades(id),
    constraint chk_phone check (REGEXP_LIKE(phone_number, '^$|[0-9]{10}'))
);

create table gradeshistory(
	id int auto_increment,
        assigned_on date not null,
	employee_id int,
        grade_id int not null,
	Primary Key(id),
	foreign key (employee_id) references users(employee_id),
	foreign key (grade_id) references grades(id)
);

