-- Create the app_user table to store user information
CREATE TABLE IF NOT EXISTS app_user (
                                        user_id UUID PRIMARY KEY,
                                        first_name VARCHAR(255),
                                        last_name VARCHAR(255),
                                        email VARCHAR(255),
                                        role VARCHAR(50),
                                        has_handicap_placard BOOLEAN,
                                        has_ev BOOLEAN
);

-- Create the parking_lot table to store parking lot information
CREATE TABLE IF NOT EXISTS parking_lot
(
                                           lot_id UUID PRIMARY KEY,
                                           name VARCHAR(255)
);

-- Create the gate table to store gate information associated with parking lots
CREATE TABLE IF NOT EXISTS gate
(
                                    gate_id UUID PRIMARY KEY,
                                    lot_id UUID REFERENCES parking_lot (lot_id),
                                    gate_name VARCHAR(255),
                                    is_operational BOOLEAN,
                                    is_restricted BOOLEAN
);

-- Create the floor table to store floor information associated with parking lots and gates
CREATE TABLE IF NOT EXISTS floor
(
                                     floor_id UUID PRIMARY KEY,
                                     lot_id  UUID REFERENCES parking_lot (lot_id),
                                     gate_id UUID REFERENCES gate (gate_id),
                                     floor_number INT
);

-- Create the parking_pass table to store parking pass information for users
CREATE TABLE IF NOT EXISTS parking_pass
(
                                            pass_id UUID PRIMARY KEY,
                                            user_id UUID REFERENCES app_user(user_id),
                                            issue_date DATE,
                                            expiry_date DATE
);

-- Create the parking_space table to store parking space information associated with floors
CREATE TABLE IF NOT EXISTS parking_space
(
                                             space_id UUID PRIMARY KEY,
                                             floor_id UUID REFERENCES floor (floor_id),
                                             space_number VARCHAR(50),
                                             is_occupied BOOLEAN,
                                             type VARCHAR(50)
);

-- Create the parking_assignment table to map parking passes to parking spaces
CREATE TABLE IF NOT EXISTS parking_assignment
(
                                                  assignment_id UUID PRIMARY KEY,
                                                  pass_id  UUID REFERENCES parking_pass (pass_id),
                                                  space_id UUID REFERENCES parking_space (space_id),
                                                  assigned_date DATE
);
