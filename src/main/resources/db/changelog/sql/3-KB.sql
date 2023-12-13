ALTER TABLE rooms DROP COLUMN photo_url;
ALTER TABLE types_of_room ADD COLUMN
    photo_url VARCHAR(255);
