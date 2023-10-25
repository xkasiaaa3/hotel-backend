CREATE TABLE users(
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    surname VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(14),
    role VARCHAR(255)
);

CREATE TABLE types_of_room(
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    number_of_people INT,
    number_of_beds INT,
    bathroom BOOLEAN,
    balcony BOOLEAN,
    price_per_day DOUBLE PRECISION
);

CREATE TABLE rooms(
    id UUID PRIMARY KEY,
    number INT,
    type_id UUID,
    floor INT,
    photo_url VARCHAR(255)
);

CREATE TABLE reservations(
    id UUID PRIMARY KEY,
    start_date DATE,
    end_date DATE,
    user_id UUID,
    room_id UUID,
    all_inclusive BOOLEAN,
    price DOUBLE PRECISION
)
