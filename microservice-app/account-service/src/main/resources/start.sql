CREATE KEYSPACE springcloud
    WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};

use springcloud;

CREATE TABLE emp(
                    emp_id int PRIMARY KEY,
                    emp_name text,
                    emp_city text,
                    emp_sal varint,
                    emp_phone varint
);


CREATE TABLE accounts(
                    id varchar PRIMARY KEY,
                    name varchar,
                    uname varchar,
                    surname varchar,
                    email varchar,
                    pwd varchar,
                    birth_date timestamp,
                    created_at timestamp,
                    is_active boolean
);
