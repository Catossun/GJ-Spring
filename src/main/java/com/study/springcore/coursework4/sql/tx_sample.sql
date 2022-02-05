-- Tables: books, stocks, wallets
-- Create books table
CREATE TABLE IF NOT EXISTS books
(
    bid
    integer
    not
    null
    auto_increment,
    bname
    varchar
(
    20
) not null,
    price integer default 0,
    create_time timestamp default CURRENT_TIMESTAMP,
    PRIMARY KEY
(
    bid
)
    );
-- Sample data
INSERT INTO finweb.books (bid, bname, price, create_time)
VALUES (1, 'Java', 150, '2022-01-23 07:01:25');
INSERT INTO finweb.books (bid, bname, price, create_time)
VALUES (2, 'Python', 100, '2022-01-23 07:01:25');

-- Create stocks table
CREATE TABLE IF NOT EXISTS stocks
(
    sid
    integer
    not
    null
    auto_increment,
    bid
    integer
    not
    null,
    amount
    integer
    default
    0,
    PRIMARY
    KEY
(
    sid
),
    FOREIGN KEY
(
    bid
) REFERENCES books
(
    bid
)
    );
-- Sample data
INSERT INTO finweb.stocks (sid, bid, amount)
VALUES (1, 1, 10);
INSERT INTO finweb.stocks (sid, bid, amount)
VALUES (2, 2, 10);

-- Create wallets table
CREATE TABLE IF NOT EXISTS wallets
(
    wid
    integer
    not
    null
    auto_increment,
    wname
    varchar
(
    20
) not null,
    money integer default 0,
    PRIMARY KEY
(
    wid
)
    );
-- Sample data
INSERT INTO wallets (wid, wname, money)
VALUES (1, 'Bob', 3000);

-- Create transaction log (order_log) table
-- Bob spent 300$ buying two Java books at 2020/1/23 PM 2:07:51
-- P.S., when book price changed, order_log should not be affected
CREATE TABLE IF NOT EXISTS order_log
(
    oid
    integer
    not
    null
    auto_increment,
    wid
    integer
    not
    null,
    bid
    integer
    not
    null,
    book_price
    integer
    not
    null,
    amount
    integer
    not
    null,
    order_time
    timestamp
    not
    null
    default
    CURRENT_TIMESTAMP,
    PRIMARY
    KEY
(
    oid
),
    FOREIGN KEY
(
    bid
) REFERENCES books
(
    bid
),
    FOREIGN KEY
(
    wid
) REFERENCES wallets
(
    wid
)
    )