-- Truncate tables to start fresh
TRUNCATE TABLE "User" CASCADE;
TRUNCATE TABLE profile;

-- Insert into User
DO $$
DECLARE
    new_user_id int;
BEGIN
    INSERT INTO "User" (username, password, email)
    VALUES ('johndoe', 'passw0rd', 'johndoe@gmail.com')
    RETURNING id INTO new_user_id;

    -- Insert into Profile
    INSERT INTO profile (id, name, surname, date_of_birth, phone, gender)
    VALUES (new_user_id, 'john', 'doe', '0001-01-01', '+78805553535', 'male');
END $$;
