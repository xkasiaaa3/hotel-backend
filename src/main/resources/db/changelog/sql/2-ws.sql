ALTER TABLE users
    ADD password varchar(255) NOT NULL,
    DROP COLUMN phone_number;