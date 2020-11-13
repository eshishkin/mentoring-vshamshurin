CREATE TABLE users (
	id BIGSERIAL NOT NULL PRIMARY KEY,
    name character varying(50) NOT NULL,
    email character varying(150) NOT NULL,
    phone character varying(50) NOT NULL,
	car_id BIGINT REFERENCES cars(id)
);

CREATE TABLE cars (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	brand character varying(50) NOT NULL,
	wheels_id BIGINT REFERENCES wheels(id) NOT NULL,
	transmission_id BIGINT REFERENCES transmissions(id) NOT NULL, 
	engine_id BIGINT REFERENCES engines(id) NOT NULL

);

CREATE TABLE transmissions (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	description character varying(250),
	price NUMERIC(9, 2) NOT NULL
);

CREATE TABLE engines (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	description character varying(250),
	price NUMERIC(9, 2) NOT NULL,
	power INTEGER NOT NULL
);


CREATE TABLE wheels (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	description character varying(250),
	price NUMERIC(9, 2) NOT NULL
);
