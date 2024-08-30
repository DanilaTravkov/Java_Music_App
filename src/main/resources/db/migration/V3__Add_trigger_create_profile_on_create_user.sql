CREATE OR REPLACE FUNCTION create_profile_on_user_insert()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO Profile(id)
    VALUES (NEW.id);
    RETURN NEW;
END;

$$ LANGUAGE plpgsql;

CREATE TRIGGER user_insert_trigger
AFTER INSERT ON "User"
FOR EACH ROW
EXECUTE FUNCTION create_profile_on_user_insert();