TRUNCATE TABLE "User" CASCADE;
TRUNCATE TABLE profile;

DO $$
DECLARE
    new_user_id int;
BEGIN
    INSERT INTO "User"(username)
    VALUES ('johndoe')
    RETURNING id INTO new_user_id;

    INSERT INTO profile (id, name, surname, date_of_birth, phone, email, gender, password)
    VALUES (new_user_id, 'john', 'doe', '01-01-0001', '+78805553535', 'johndoe@gmail.com', 'submarine', 'passw0rd');
END $$;
