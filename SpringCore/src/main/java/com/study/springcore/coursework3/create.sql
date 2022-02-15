-- Create emp table
CREATE TABLE IF NOT EXISTS emp
(
    eid
    int
    not
    null
    auto_increment,
    ename
    varchar
(
    50
) not null unique,
    age int,
    createtime timestamp default current_timestamp,
    PRIMARY KEY
(
    eid
)
    );

-- Add employees
INSERT INTO emp(ename, age)
VALUES ('John', 28);
INSERT INTO emp(ename, age)
VALUES ('Mary', 30);
INSERT INTO emp(ename, age)
VALUES ('Bob', 29);

SELECT *
FROM emp;

-- Create query_log table
CREATE TABLE IF NOT EXISTS query_log
(
    method_name varchar
(
    50
),
    exec_time timestamp default current_timestamp
    );

INSERT INTO query_log(method_name) VALUE ('test');

SELECT *
FROM query_log;

-- Create job table (one emp - many job, one job - one emp)
CREATE TABLE IF NOT EXISTS jobs
(
    jid
    int
    not
    null
    auto_increment,
    jname
    varchar
(
    50
) not null unique,
    eid int,
    PRIMARY KEY
(
    jid
),
    FOREIGN KEY
(
    eid
) REFERENCES emp
(
    eid
)
    );

INSERT INTO jobs(jname, eid)
VALUES ('jobA', 3);
INSERT INTO jobs(jname, eid)
VALUES ('jobB', 3);
INSERT INTO jobs(jname, eid)
VALUES ('jobC', 6);
INSERT INTO jobs(jname, eid)
VALUES ('jobD', 2);
INSERT INTO jobs(jname, eid)
VALUES ('jobE', 4);
INSERT INTO jobs(jname)
VALUES ('jobF');
INSERT INTO jobs(jname)
VALUES ('jobG');
INSERT INTO jobs(jname)
VALUES ('jobH');
