alter table field_options drop constraint FK9nlyse0erepgxl3waya28nbd9
alter table field_options drop constraint FK1xtq52s08h05ucbtshddpv0by
alter table field_responses drop constraint FKktmo13kb5kfoilx378sfgdg6s
alter table field_responses drop constraint FK71rrpblhav9i962qkl7rgx5br
drop table if exists Admin cascade
drop table if exists Field cascade
drop table if exists field_options cascade
drop table if exists field_responses cascade
drop table if exists Option cascade
drop table if exists Response cascade
create table Admin (admin_id  serial not null, login varchar(50) not null, password varchar(50) not null, primary key (admin_id))
create table Field (field_id  serial not null, active boolean not null, required boolean not null, label varchar(200) not null, type varchar(255) not null, primary key (field_id))
create table field_options (field_id int4 not null, option_id int4 not null)
create table field_responses (field_id int4 not null, response_id int4 not null)
create table Option (option_id  serial not null, name varchar(200) not null, primary key (option_id))
create table Response (response_id  serial not null, value varchar(200) not null, primary key (response_id))
alter table Admin add constraint UK_d3uev2cex85xal91gucifjk81 unique (login)
alter table Field add constraint UK_2jkbeu02cugxeae27jqopy68w unique (label)
alter table field_options add constraint UK_qdxnuw61hdcrn09w0bhkwcil8 unique (option_id)
alter table field_responses add constraint UK_dp3xd6xp09dumqup0d1pv45y4 unique (response_id)
alter table field_options add constraint FK9nlyse0erepgxl3waya28nbd9 foreign key (option_id) references Option
alter table field_options add constraint FK1xtq52s08h05ucbtshddpv0by foreign key (field_id) references Field
alter table field_responses add constraint FKktmo13kb5kfoilx378sfgdg6s foreign key (response_id) references Response
alter table field_responses add constraint FK71rrpblhav9i962qkl7rgx5br foreign key (field_id) references Field

insert into Admin (login, password) values ("vova128", "c82e0994c1e3e89936669544538d9276")
insert into Field (active, required, label, type) values (true, true, "What languages did you study?", "CHECK_BOX")
insert into Option (name) values ("Java")
insert into Option (name) values ("C++")
insert into Option (name) values ("Python")
insert into Response (value) values ("Java")
insert into field_options (field_id, option_id) values (1, 1)
insert into field_options (field_id, option_id) values (1, 2)
insert into field_options (field_id, option_id) values (1, 3)
insert into field_responses (field_id, response_id) values (1, 1)