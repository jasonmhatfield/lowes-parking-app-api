-- Insert Gates --
INSERT INTO GATE (gate_name, is_operational)
VALUES
    ('South Gate', TRUE),
    ('North Gate', TRUE);

-- Insert an admin user and hard-coded employees --
INSERT INTO APP_USER (first_name, last_name, email, role, has_handicap_placard, has_ev)
VALUES
    ('Admin', 'User', 'lowes.admin@lowes.com', 'admin', FALSE, FALSE),
    ('Mark', 'Jess', 'mark.jess@lowes.com', 'employee', TRUE, FALSE),
    ('Emily', 'Johnson', 'emily.johnson@lowes.com', 'employee', FALSE, TRUE);

-- Insert 98 additional employee users --
INSERT INTO APP_USER (first_name, last_name, email, role, has_handicap_placard, has_ev)
VALUES
    ('David', 'Miller', 'david.miller@lowes.com', 'employee', FALSE, TRUE),
    ('Sarah', 'Brown', 'sarah.brown@lowes.com', 'employee', FALSE, FALSE),
    ('James', 'Williams', 'james.williams@lowes.com', 'employee', TRUE, TRUE),
    ('Jennifer', 'Jones', 'jennifer.jones@lowes.com', 'employee', FALSE, TRUE),
    ('Robert', 'Garcia', 'robert.garcia@lowes.com', 'employee', TRUE, FALSE),
    ('Jessica', 'Martinez', 'jessica.martinez@lowes.com', 'employee', FALSE, TRUE),
    ('John', 'Davis', 'john.davis@lowes.com', 'employee', TRUE, FALSE),
    ('Elizabeth', 'Lopez', 'elizabeth.lopez@lowes.com', 'employee', FALSE, TRUE),
    ('Christopher', 'Gonzalez', 'christopher.gonzalez@lowes.com', 'employee', TRUE, FALSE),
    ('Ashley', 'Wilson', 'ashley.wilson@lowes.com', 'employee', FALSE, TRUE),
    ('Matthew', 'Anderson', 'matthew.anderson@lowes.com', 'employee', FALSE, FALSE),
    ('Amanda', 'Thomas', 'amanda.thomas@lowes.com', 'employee', TRUE, TRUE),
    ('Joshua', 'Taylor', 'joshua.taylor@lowes.com', 'employee', FALSE, TRUE),
    ('Megan', 'Lee', 'megan.lee@lowes.com', 'employee', TRUE, FALSE),
    ('Andrew', 'Perez', 'andrew.perez@lowes.com', 'employee', FALSE, FALSE),
    ('Daniel', 'Moore', 'daniel.moore@lowes.com', 'employee', FALSE, TRUE),
    ('Hannah', 'Clark', 'hannah.clark@lowes.com', 'employee', TRUE, FALSE),
    ('Joseph', 'Lewis', 'joseph.lewis@lowes.com', 'employee', FALSE, TRUE),
    ('Victoria', 'Robinson', 'victoria.robinson@lowes.com', 'employee', TRUE, FALSE),
    ('Benjamin', 'Walker', 'benjamin.walker@lowes.com', 'employee', FALSE, TRUE),
    ('Rachel', 'Young', 'rachel.young@lowes.com', 'employee', TRUE, TRUE),
    ('Samuel', 'King', 'samuel.king@lowes.com', 'employee', FALSE, FALSE),
    ('Sophia', 'Wright', 'sophia.wright@lowes.com', 'employee', TRUE, FALSE),
    ('Alexander', 'Scott', 'alexander.scott@lowes.com', 'employee', FALSE, TRUE),
    ('Brandon', 'Hill', 'brandon.hill@lowes.com', 'employee', FALSE, FALSE),
    ('Laura', 'Green', 'laura.green@lowes.com', 'employee', TRUE, FALSE),
    ('Aaron', 'Adams', 'aaron.adams@lowes.com', 'employee', FALSE, TRUE),
    ('Olivia', 'Baker', 'olivia.baker@lowes.com', 'employee', TRUE, FALSE),
    ('Nathan', 'Gonzalez', 'nathan.gonzalez@lowes.com', 'employee', FALSE, TRUE),
    ('Sophia', 'Carter', 'sophia.carter@lowes.com', 'employee', TRUE, FALSE),
    ('Zachary', 'Mitchell', 'zachary.mitchell@lowes.com', 'employee', FALSE, TRUE),
    ('Lily', 'Parker', 'lily.parker@lowes.com', 'employee', TRUE, FALSE),
    ('Ethan', 'Evans', 'ethan.evans@lowes.com', 'employee', FALSE, TRUE),
    ('Samantha', 'Edwards', 'samantha.edwards@lowes.com', 'employee', TRUE, TRUE),
    ('Dylan', 'Collins', 'dylan.collins@lowes.com', 'employee', FALSE, FALSE),
    ('Charlotte', 'Stewart', 'charlotte.stewart@lowes.com', 'employee', TRUE, FALSE),
    ('Gabriel', 'Morris', 'gabriel.morris@lowes.com', 'employee', FALSE, TRUE),
    ('Victoria', 'Rogers', 'victoria.rogers@lowes.com', 'employee', TRUE, FALSE),
    ('Jack', 'Reed', 'jack.reed@lowes.com', 'employee', FALSE, TRUE),
    ('Abigail', 'Cook', 'abigail.cook@lowes.com', 'employee', TRUE, FALSE),
    ('Lucas', 'Morgan', 'lucas.morgan@lowes.com', 'employee', FALSE, TRUE),
    ('Grace', 'Bell', 'grace.bell@lowes.com', 'employee', TRUE, FALSE),
    ('Owen', 'Murphy', 'owen.murphy@lowes.com', 'employee', FALSE, TRUE),
    ('Chloe', 'Bailey', 'chloe.bailey@lowes.com', 'employee', TRUE, FALSE),
    ('Landon', 'Rivera', 'landon.rivera@lowes.com', 'employee', FALSE, TRUE),
    ('Avery', 'Cooper', 'avery.cooper@lowes.com', 'employee', TRUE, TRUE),
    ('Isaac', 'Richardson', 'isaac.richardson@lowes.com', 'employee', FALSE, FALSE),
    ('Ellie', 'Cox', 'ellie.cox@lowes.com', 'employee', TRUE, FALSE),
    ('Jackson', 'Ward', 'jackson.ward@lowes.com', 'employee', FALSE, TRUE),
    ('Ella', 'Perez', 'ella.perez@lowes.com', 'employee', TRUE, FALSE),
    ('Henry', 'Gray', 'henry.gray@lowes.com', 'employee', FALSE, TRUE),
    ('Isabella', 'James', 'isabella.james@lowes.com', 'employee', TRUE, FALSE),
    ('Lucas', 'Watson', 'lucas.watson@lowes.com', 'employee', FALSE, TRUE),
    ('Amelia', 'Brooks', 'amelia.brooks@lowes.com', 'employee', TRUE, FALSE),
    ('Aiden', 'Bennett', 'aiden.bennett@lowes.com', 'employee', FALSE, TRUE),
    ('Mia', 'Wood', 'mia.wood@lowes.com', 'employee', TRUE, FALSE),
    ('Caleb', 'Hughes', 'caleb.hughes@lowes.com', 'employee', FALSE, TRUE),
    ('Ariana', 'Flores', 'ariana.flores@lowes.com', 'employee', TRUE, FALSE),
    ('Liam', 'Powell', 'liam.powell@lowes.com', 'employee', FALSE, TRUE),
    ('Natalie', 'Barnes', 'natalie.barnes@lowes.com', 'employee', TRUE, FALSE),
    ('Noah', 'Long', 'noah.long@lowes.com', 'employee', FALSE, TRUE),
    ('Lydia', 'Sanders', 'lydia.sanders@lowes.com', 'employee', TRUE, FALSE),
    ('Wyatt', 'Price', 'wyatt.price@lowes.com', 'employee', FALSE, TRUE),
    ('Sofia', 'Jenkins', 'sofia.jenkins@lowes.com', 'employee', TRUE, FALSE),
    ('Carter', 'Reyes', 'carter.reyes@lowes.com', 'employee', FALSE, TRUE),
    ('Riley', 'Ramirez', 'riley.ramirez@lowes.com', 'employee', TRUE, FALSE),
    ('Asher', 'Myers', 'asher.myers@lowes.com', 'employee', FALSE, TRUE),
    ('Ella', 'Ross', 'ella.ross@lowes.com', 'employee', TRUE, TRUE),
    ('Landon', 'Morales', 'landon.morales@lowes.com', 'employee', FALSE, FALSE),
    ('Aubrey', 'Foster', 'aubrey.foster@lowes.com', 'employee', TRUE, FALSE),
    ('Benjamin', 'Griffin', 'benjamin.griffin@lowes.com', 'employee', FALSE, TRUE),
    ('Chloe', 'Perry', 'chloe.perry@lowes.com', 'employee', TRUE, FALSE),
    ('Daniel', 'Henderson', 'daniel.henderson@lowes.com', 'employee', FALSE, TRUE),
    ('Lily', 'Coleman', 'lily.coleman@lowes.com', 'employee', TRUE, TRUE),
    ('James', 'Howard', 'james.howard@lowes.com', 'employee', FALSE, FALSE),
    ('Grace', 'Warren', 'grace.warren@lowes.com', 'employee', TRUE, FALSE),
    ('Eli', 'Patterson', 'eli.patterson@lowes.com', 'employee', FALSE, TRUE),
    ('Zoey', 'Reed', 'zoey.reed@lowes.com', 'employee', TRUE, FALSE),
    ('Henry', 'Cooper', 'henry.cooper@lowes.com', 'employee', FALSE, TRUE),
    ('Scarlett', 'Bailey', 'scarlett.bailey@lowes.com', 'employee', TRUE, FALSE),
    ('Nathan', 'Turner', 'nathan.turner@lowes.com', 'employee', FALSE, TRUE),
    ('Ava', 'Hayes', 'ava.hayes@lowes.com', 'employee', TRUE, FALSE),
    ('Owen', 'Cox', 'owen.cox@lowes.com', 'employee', FALSE, TRUE),
    ('Mia', 'Diaz', 'mia.diaz@lowes.com', 'employee', TRUE, TRUE),
    ('Logan', 'Hughes', 'logan.hughes@lowes.com', 'employee', FALSE, FALSE),
    ('Lila', 'Butler', 'lila.butler@lowes.com', 'employee', TRUE, FALSE),
    ('Ryan', 'Foster', 'ryan.foster@lowes.com', 'employee', FALSE, TRUE),
    ('Ella', 'Sullivan', 'ella.sullivan@lowes.com', 'employee', TRUE, FALSE),
    ('Jackson', 'Murphy', 'jackson.murphy@lowes.com', 'employee', FALSE, TRUE),
    ('Sophie', 'Rivera', 'sophie.rivera@lowes.com', 'employee', TRUE, TRUE),
    ('Ethan', 'Collins', 'ethan.collins@lowes.com', 'employee', FALSE, FALSE),
    ('Aubrey', 'Bell', 'aubrey.bell@lowes.com', 'employee', TRUE, FALSE),
    ('Luke', 'Green', 'luke.green@lowes.com', 'employee', FALSE, TRUE),
    ('Emma', 'Kelly', 'emma.kelly@lowes.com', 'employee', TRUE, FALSE),
    ('Grayson', 'Ward', 'grayson.ward@lowes.com', 'employee', FALSE, TRUE),
    ('Madeline', 'Ross', 'madeline.ross@lowes.com', 'employee', TRUE, FALSE),
    ('Caleb', 'Powell', 'caleb.powell@lowes.com', 'employee', FALSE, TRUE),
    ('Liam', 'Hughes', 'liam.hughes@lowes.com', 'employee', FALSE, TRUE);

-- Insert Parking Spots for Levels 1 to 4 (101 to 426)
INSERT INTO PARKING_SPOT (spot_number, is_occupied, type, user_id)
SELECT
    CONCAT(floor, LPAD(spot, 2, '0')),
    FALSE,
    CASE
        WHEN spot <= 3 THEN 'handicap'
        WHEN spot <= 8 THEN 'ev'
        ELSE 'regular'
        END,
    NULL
FROM (VALUES (1), (2), (3), (4)) AS floors(floor)
         JOIN SYSTEM_RANGE(1, 26) AS spots(spot);

-- Reset all parking spots
UPDATE PARKING_SPOT SET is_occupied = FALSE, user_id = NULL;

-- Create a temporary table to store available users
CREATE TABLE AVAILABLE_USERS AS
SELECT id, has_handicap_placard, has_ev
FROM APP_USER
WHERE role = 'employee'
  AND email NOT IN ('lowes.admin@lowes.com', 'mark.jess@lowes.com', 'emily.johnson@lowes.com');

-- Fill Handicap spots (60% of 12 spots = 7 spots)
MERGE INTO PARKING_SPOT PS
    USING (
        SELECT PS.id AS spot_id, AU.id AS user_id
        FROM (SELECT id FROM PARKING_SPOT WHERE type = 'handicap' ORDER BY RAND() LIMIT 7) PS
                 CROSS JOIN (SELECT id FROM AVAILABLE_USERS WHERE has_handicap_placard = TRUE ORDER BY RAND() LIMIT 1) AU
    ) AS FS
ON PS.id = FS.spot_id
WHEN MATCHED THEN
    UPDATE SET is_occupied = TRUE, user_id = FS.user_id;

-- Remove assigned users from available users
DELETE FROM AVAILABLE_USERS WHERE id IN (SELECT user_id FROM PARKING_SPOT WHERE user_id IS NOT NULL);

-- Fill EV spots (60% of 20 spots = 12 spots)
MERGE INTO PARKING_SPOT PS
    USING (
        SELECT PS.id AS spot_id, AU.id AS user_id
        FROM (SELECT id FROM PARKING_SPOT WHERE type = 'ev' AND is_occupied = FALSE ORDER BY RAND() LIMIT 12) PS
                 CROSS JOIN (SELECT id FROM AVAILABLE_USERS WHERE has_ev = TRUE ORDER BY RAND() LIMIT 1) AU
    ) AS FS
ON PS.id = FS.spot_id
WHEN MATCHED THEN
    UPDATE SET is_occupied = TRUE, user_id = FS.user_id;

-- Remove assigned users from available users
DELETE FROM AVAILABLE_USERS WHERE id IN (SELECT user_id FROM PARKING_SPOT WHERE user_id IS NOT NULL);

-- Fill Regular spots (60% of 72 spots = 43 spots)
MERGE INTO PARKING_SPOT PS
    USING (
        SELECT PS.id AS spot_id, AU.id AS user_id
        FROM (SELECT id FROM PARKING_SPOT WHERE type = 'regular' AND is_occupied = FALSE ORDER BY RAND() LIMIT 43) PS
                 CROSS JOIN (SELECT id FROM AVAILABLE_USERS ORDER BY RAND() LIMIT 1) AU
    ) AS FS
ON PS.id = FS.spot_id
WHEN MATCHED THEN
    UPDATE SET is_occupied = TRUE, user_id = FS.user_id;

-- Clean up
DROP TABLE AVAILABLE_USERS;
