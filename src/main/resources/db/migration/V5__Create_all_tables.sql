CREATE TABLE IF NOT EXISTS Post (
    id SERIAL PRIMARY KEY,
    title VARCHAR(50),
    image_url VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Advertisement (
    id SERIAL PRIMARY KEY,
    text VARCHAR(200),
    link_to_advertisement VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Album (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    date_released DATE
);

CREATE TABLE IF NOT EXISTS Artist (
    id SERIAL PRIMARY KEY,
    nickname VARCHAR(20),
    name VARCHAR(20),
    surname VARCHAR(20),
    date_of_birth DATE,
    date_of_death DATE
);

CREATE TABLE IF NOT EXISTS Band (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20),
    genre VARCHAR(20),
    description VARCHAR(200),
    country VARCHAR(20),
    date_created DATE,
    date_disbanded DATE
);

CREATE TABLE IF NOT EXISTS Comment (
    id SERIAL PRIMARY KEY,
    title VARCHAR(20),
    text VARCHAR(50),
    rating INTEGER,
    date_created DATE,
    user_id INTEGER,
    CONSTRAINT comment_fk FOREIGN KEY (user_id) REFERENCES "User"(id)
);

CREATE TABLE IF NOT EXISTS Event (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20),
    date_organized DATE,
    place VARCHAR(30),
    description VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS Playlist (
    id SERIAL PRIMARY KEY,
    title VARCHAR(20),
    description VARCHAR(200),
    isPublic BOOLEAN
);
