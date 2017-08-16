create table "users"
(
	id varchar not null
		constraint user_pkey
			primary key,
	first_name varchar not null,
	surname varchar not null,
	age integer not null
);

