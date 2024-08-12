INSERT INTO GATE (gate_name, is_operational)
VALUES ('South Gate', TRUE);

INSERT INTO GATE (gate_name, is_operational)
VALUES ('North Gate', TRUE);

-- Insert an admin user
INSERT INTO APP_USER (first_name, last_name, email, role, has_handicap_placard, has_ev)
VALUES ('Jason', 'Hatfield', 'jason.hatfield@lowes.com', 'admin', FALSE, FALSE);

-- Insert hard-coded employees Michael and Emily
INSERT INTO APP_USER (first_name, last_name, email, role, has_handicap_placard, has_ev)
VALUES ('Michael', 'Smith', 'michael.smith@lowes.com', 'employee', TRUE, FALSE);

INSERT INTO APP_USER (first_name, last_name, email, role, has_handicap_placard, has_ev)
VALUES ('Emily', 'Johnson', 'emily.johnson@lowes.com', 'employee', FALSE, TRUE);

-- Insert 96 additional employee users
INSERT INTO APP_USER (first_name, last_name, email, role, has_handicap_placard, has_ev)
SELECT 'EmployeeFirstName' || x, 'EmployeeLastName' || x, 'employee' || x || '@lowes.com', 'employee',
       CASE WHEN RAND() < 0.10 THEN TRUE ELSE FALSE END,  -- 10% chance of having a handicap placard
       CASE WHEN RAND() < 0.20 THEN TRUE ELSE FALSE END   -- 20% chance of having an EV
FROM SYSTEM_RANGE(1, 96);

-- Insert Parking Spots for Each Level
-- Level 1 (101 to 125)
INSERT INTO PARKING_SPOT (spot_number, is_occupied, type, user_id)
SELECT '1' || LPAD(x, 2, '0'), FALSE,
       CASE WHEN x <= 3 THEN 'handicap'
            WHEN x <= 8 THEN 'ev'
            ELSE 'regular' END, NULL
FROM SYSTEM_RANGE(1, 26);

-- Level 2 (201 to 225)
INSERT INTO PARKING_SPOT (spot_number, is_occupied, type, user_id)
SELECT '2' || LPAD(x, 2, '0'), FALSE,
       CASE WHEN x <= 3 THEN 'handicap'
            WHEN x <= 8 THEN 'ev'
            ELSE 'regular' END, NULL
FROM SYSTEM_RANGE(1, 26);

-- Level 3 (301 to 325)
INSERT INTO PARKING_SPOT (spot_number, is_occupied, type, user_id)
SELECT '3' || LPAD(x, 2, '0'), FALSE,
       CASE WHEN x <= 3 THEN 'handicap'
            WHEN x <= 8 THEN 'ev'
            ELSE 'regular' END, NULL
FROM SYSTEM_RANGE(1, 26);

-- Level 4 (401 to 425)
INSERT INTO PARKING_SPOT (spot_number, is_occupied, type, user_id)
SELECT '4' || LPAD(x, 2, '0'), FALSE,
       CASE WHEN x <= 3 THEN 'handicap'
            WHEN x <= 8 THEN 'ev'
            ELSE 'regular' END, NULL
FROM SYSTEM_RANGE(1, 26);
