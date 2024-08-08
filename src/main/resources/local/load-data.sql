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
INSERT INTO parking_lot (lot_id, name)
VALUES (UUID(), 'Main Parking Lot');

-- Insert gates associated with 'Main Parking Lot'
INSERT INTO gate (gate_id, lot_id, gate_name, is_operational, is_restricted)
SELECT UUID(), lot_id, 'South Gate', TRUE, FALSE
FROM parking_lot
WHERE name = 'Main Parking Lot'
LIMIT 1;

INSERT INTO gate (gate_id, lot_id, gate_name, is_operational, is_restricted)
SELECT UUID(), lot_id, 'North Gate', TRUE, FALSE
FROM parking_lot
WHERE name = 'Main Parking Lot'
LIMIT 1;


-- Insert floors associated with 'Main Parking Lot' and respective gates
INSERT INTO floor (floor_id, lot_id, gate_id, floor_number)
SELECT UUID(), parking_lot.lot_id, gate.gate_id, 4
FROM parking_lot
         JOIN gate ON parking_lot.lot_id = gate.lot_id
WHERE parking_lot.name = 'Main Parking Lot'
  AND gate.gate_name = 'South Gate'
LIMIT 1;

INSERT INTO floor (floor_id, lot_id, gate_id, floor_number)
SELECT UUID(), parking_lot.lot_id, gate.gate_id, 6
FROM parking_lot
         JOIN gate ON parking_lot.lot_id = gate.lot_id
WHERE parking_lot.name = 'Main Parking Lot'
  AND gate.gate_name = 'South Gate'
LIMIT 1;

INSERT INTO floor (floor_id, lot_id, gate_id, floor_number)
SELECT UUID(), parking_lot.lot_id, gate.gate_id, 8
FROM parking_lot
         JOIN gate ON parking_lot.lot_id = gate.lot_id
WHERE parking_lot.name = 'Main Parking Lot'
  AND gate.gate_name = 'South Gate'
LIMIT 1;

INSERT INTO floor (floor_id, lot_id, gate_id, floor_number)
SELECT UUID(), parking_lot.lot_id, gate.gate_id, 5
FROM parking_lot
         JOIN gate ON parking_lot.lot_id = gate.lot_id
WHERE parking_lot.name = 'Main Parking Lot'
  AND gate.gate_name = 'North Gate'
LIMIT 1;

INSERT INTO floor (floor_id, lot_id, gate_id, floor_number)
SELECT UUID(), parking_lot.lot_id, gate.gate_id, 7
FROM parking_lot
         JOIN gate ON parking_lot.lot_id = gate.lot_id
WHERE parking_lot.name = 'Main Parking Lot'
  AND gate.gate_name = 'North Gate'
LIMIT 1;

-- Insert parking spaces for each floor with specific space types (accessible, EV, regular)
INSERT INTO parking_space (space_id, floor_id, space_number, is_occupied, type)
SELECT UUID(), f.floor_id, CONCAT('4', LPAD(x, 3, '0')), FALSE,
       CASE WHEN x <= 3 THEN 'ACCESSIBLE'
            WHEN x <= 11 THEN 'EV'
            ELSE 'REGULAR' END
FROM floor f,
     SYSTEM_RANGE(1, 150) x
WHERE f.floor_number = 4;

INSERT INTO parking_space (space_id, floor_id, space_number, is_occupied, type)
SELECT UUID(), f.floor_id, CONCAT('6', LPAD(x, 3, '0')), FALSE,
       CASE WHEN x <= 3 THEN 'ACCESSIBLE'
            WHEN x <= 11 THEN 'EV'
            ELSE 'REGULAR' END
FROM floor f,
     SYSTEM_RANGE(1, 150) x
WHERE f.floor_number = 6;

INSERT INTO parking_space (space_id, floor_id, space_number, is_occupied, type)
SELECT UUID(), f.floor_id, CONCAT('8', LPAD(x, 3, '0')), FALSE,
       CASE WHEN x <= 3 THEN 'ACCESSIBLE'
            WHEN x <= 11 THEN 'EV'
            ELSE 'REGULAR' END
FROM floor f,
     SYSTEM_RANGE(1, 150) x
WHERE f.floor_number = 8;

INSERT INTO parking_space (space_id, floor_id, space_number, is_occupied, type)
SELECT UUID(), f.floor_id, CONCAT('5', LPAD(x, 3, '0')), FALSE,
       CASE WHEN x <= 3 THEN 'ACCESSIBLE'
            WHEN x <= 11 THEN 'EV'
            ELSE 'REGULAR' END
FROM floor f,
     SYSTEM_RANGE(1, 150) x
WHERE f.floor_number = 5;

INSERT INTO parking_space (space_id, floor_id, space_number, is_occupied, type)
SELECT UUID(), f.floor_id, CONCAT('7', LPAD(x, 3, '0')), FALSE,
       CASE WHEN x <= 3 THEN 'ACCESSIBLE'
            WHEN x <= 11 THEN 'EV'
            ELSE 'REGULAR' END
FROM floor f,
     SYSTEM_RANGE(1, 150) x
WHERE f.floor_number = 7;

-- Assign parking passes to users
INSERT INTO parking_pass (pass_id, user_id, issue_date, expiry_date)
SELECT UUID(), user_id, CURRENT_DATE, DATEADD('YEAR', 1, CURRENT_DATE)
FROM app_user
WHERE role = 'employee';

-- Assign parking spaces to parking passes
INSERT INTO parking_assignment (assignment_id, pass_id, space_id, assigned_date)
SELECT UUID(), p.pass_id, s.space_id, CURRENT_DATE
FROM parking_pass p
         JOIN parking_space s ON s.is_occupied = FALSE
LIMIT 150;
