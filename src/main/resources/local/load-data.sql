-- Insert an admin user
INSERT INTO app_user (user_id, first_name, last_name, email, role, has_handicap_placard, has_ev)
VALUES (UUID(), 'Jason', 'Hatfield', 'jason.hatfield@lowes.com', 'admin', FALSE, FALSE);

-- Insert three dedicated employee users with specific roles
INSERT INTO app_user (user_id, first_name, last_name, email, role, has_handicap_placard, has_ev)
VALUES (UUID(), 'John', 'Doe', 'john.doe@lowes.com', 'employee', FALSE, FALSE);

INSERT INTO app_user (user_id, first_name, last_name, email, role, has_handicap_placard, has_ev)
VALUES (UUID(), 'Jane', 'Smith', 'jane.smith@lowes.com', 'employee', FALSE, TRUE);

INSERT INTO app_user (user_id, first_name, last_name, email, role, has_handicap_placard, has_ev)
VALUES (UUID(), 'Alice', 'Johnson', 'alice.johnson@lowes.com', 'employee', TRUE, FALSE);

-- Insert 996 additional employee users with randomized attributes
INSERT INTO app_user (user_id, first_name, last_name, email, role, has_handicap_placard, has_ev)
SELECT UUID(), 'FirstName' || x, 'LastName' || x, 'user' || x || '@lowes.com', 'employee',
       CASE WHEN RAND() < 0.01 THEN TRUE ELSE FALSE END,
       CASE WHEN RAND() < 0.05 THEN TRUE ELSE FALSE END
FROM SYSTEM_RANGE(5, 1000);

-- Insert a parking lot named 'Main Parking Lot'
INSERT INTO Parking_Lot (lot_id, name) VALUES (UUID(), 'Main Parking Lot');

-- Insert gates associated with 'Main Parking Lot'
INSERT INTO Gate (gate_id, lot_id, gate_name)
SELECT UUID(), lot_id, 'South Gate'
FROM Parking_Lot WHERE name = 'Main Parking Lot' LIMIT 1;

INSERT INTO Gate (gate_id, lot_id, gate_name)
SELECT UUID(), lot_id, 'North Gate'
FROM Parking_Lot WHERE name = 'Main Parking Lot' LIMIT 1;

-- Insert floors associated with 'Main Parking Lot' and respective gates
INSERT INTO Floor (floor_id, lot_id, gate_id, floor_number)
SELECT UUID(), Parking_Lot.lot_id, Gate.gate_id, 4
FROM Parking_Lot
         JOIN Gate ON Parking_Lot.lot_id = Gate.lot_id
WHERE Parking_Lot.name = 'Main Parking Lot' AND Gate.gate_name = 'South Gate' LIMIT 1;

INSERT INTO Floor (floor_id, lot_id, gate_id, floor_number)
SELECT UUID(), Parking_Lot.lot_id, Gate.gate_id, 6
FROM Parking_Lot
         JOIN Gate ON Parking_Lot.lot_id = Gate.lot_id
WHERE Parking_Lot.name = 'Main Parking Lot' AND Gate.gate_name = 'South Gate' LIMIT 1;

INSERT INTO Floor (floor_id, lot_id, gate_id, floor_number)
SELECT UUID(), Parking_Lot.lot_id, Gate.gate_id, 8
FROM Parking_Lot
         JOIN Gate ON Parking_Lot.lot_id = Gate.lot_id
WHERE Parking_Lot.name = 'Main Parking Lot' AND Gate.gate_name = 'South Gate' LIMIT 1;

INSERT INTO Floor (floor_id, lot_id, gate_id, floor_number)
SELECT UUID(), Parking_Lot.lot_id, Gate.gate_id, 5
FROM Parking_Lot
         JOIN Gate ON Parking_Lot.lot_id = Gate.lot_id
WHERE Parking_Lot.name = 'Main Parking Lot' AND Gate.gate_name = 'North Gate' LIMIT 1;

INSERT INTO Floor (floor_id, lot_id, gate_id, floor_number)
SELECT UUID(), Parking_Lot.lot_id, Gate.gate_id, 7
FROM Parking_Lot
         JOIN Gate ON Parking_Lot.lot_id = Gate.lot_id
WHERE Parking_Lot.name = 'Main Parking Lot' AND Gate.gate_name = 'North Gate' LIMIT 1;

-- Insert parking spaces for each floor with specific space types (accessible, EV, regular)
INSERT INTO Parking_Space (space_id, floor_id, space_number, is_occupied, type)
SELECT UUID(), f.floor_id, CONCAT('4', LPAD(x, 3, '0')), FALSE,
       CASE WHEN x <= 3 THEN 'ACCESSIBLE'
            WHEN x <= 11 THEN 'EV'
            ELSE 'REGULAR' END
FROM Floor f, SYSTEM_RANGE(1, 150) x WHERE f.floor_number = 4;

INSERT INTO Parking_Space (space_id, floor_id, space_number, is_occupied, type)
SELECT UUID(), f.floor_id, CONCAT('6', LPAD(x, 3, '0')), FALSE,
       CASE WHEN x <= 3 THEN 'ACCESSIBLE'
            WHEN x <= 11 THEN 'EV'
            ELSE 'REGULAR' END
FROM Floor f, SYSTEM_RANGE(1, 150) x WHERE f.floor_number = 6;

INSERT INTO Parking_Space (space_id, floor_id, space_number, is_occupied, type)
SELECT UUID(), f.floor_id, CONCAT('8', LPAD(x, 3, '0')), FALSE,
       CASE WHEN x <= 3 THEN 'ACCESSIBLE'
            WHEN x <= 11 THEN 'EV'
            ELSE 'REGULAR' END
FROM Floor f, SYSTEM_RANGE(1, 150) x WHERE f.floor_number = 8;

INSERT INTO Parking_Space (space_id, floor_id, space_number, is_occupied, type)
SELECT UUID(), f.floor_id, CONCAT('5', LPAD(x, 3, '0')), FALSE,
       CASE WHEN x <= 3 THEN 'ACCESSIBLE'
            WHEN x <= 11 THEN 'EV'
            ELSE 'REGULAR' END
FROM Floor f, SYSTEM_RANGE(1, 150) x WHERE f.floor_number = 5;

INSERT INTO Parking_Space (space_id, floor_id, space_number, is_occupied, type)
SELECT UUID(), f.floor_id, CONCAT('7', LPAD(x, 3, '0')), FALSE,
       CASE WHEN x <= 3 THEN 'ACCESSIBLE'
            WHEN x <= 11 THEN 'EV'
            ELSE 'REGULAR' END
FROM Floor f, SYSTEM_RANGE(1, 150) x WHERE f.floor_number = 7;
