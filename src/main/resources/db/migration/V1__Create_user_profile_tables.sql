-- Drop tables if they exist
DROP TABLE IF EXISTS profile CASCADE;
DROP TABLE IF EXISTS "User" CASCADE;

-- Create User table
CREATE TABLE "User" (
    id SERIAL PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(20) NOT NULL
);

-- Create Profile table
CREATE TABLE profile (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20),
    surname VARCHAR(20),
    date_of_birth DATE,
    phone VARCHAR(20),
    gender VARCHAR(20),
    CONSTRAINT profile_fk_user FOREIGN KEY (id) REFERENCES "User" (id)
);
