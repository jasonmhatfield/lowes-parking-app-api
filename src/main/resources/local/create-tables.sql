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

-- Create the Parking_Lot table to store parking lot information
CREATE TABLE IF NOT EXISTS Parking_Lot (
                                           lot_id UUID PRIMARY KEY,
                                           name VARCHAR(255)
);

-- Create the Gate table to store gate information associated with parking lots
CREATE TABLE IF NOT EXISTS Gate (
                                    gate_id UUID PRIMARY KEY,
                                    lot_id UUID REFERENCES Parking_Lot(lot_id),
                                    gate_name VARCHAR(255),
                                    is_operational BOOLEAN,
                                    is_restricted BOOLEAN
);

-- Create the Floor table to store floor information associated with parking lots and gates
CREATE TABLE IF NOT EXISTS Floor (
                                     floor_id UUID PRIMARY KEY,
                                     lot_id UUID REFERENCES Parking_Lot(lot_id),
                                     gate_id UUID REFERENCES Gate(gate_id),
                                     floor_number INT
);

-- Create the Parking_Pass table to store parking pass information for users
CREATE TABLE IF NOT EXISTS Parking_Pass (
                                            pass_id UUID PRIMARY KEY,
                                            user_id UUID REFERENCES app_user(user_id),
                                            issue_date DATE,
                                            expiry_date DATE
);

-- Create the Parking_Space table to store parking space information associated with floors
CREATE TABLE IF NOT EXISTS Parking_Space (
                                             space_id UUID PRIMARY KEY,
                                             floor_id UUID REFERENCES Floor(floor_id),
                                             space_number VARCHAR(50),
                                             is_occupied BOOLEAN,
                                             type VARCHAR(50)
);

-- Create the Parking_Assignment table to map parking passes to parking spaces
CREATE TABLE IF NOT EXISTS Parking_Assignment (
                                                  assignment_id UUID PRIMARY KEY,
                                                  pass_id UUID REFERENCES Parking_Pass(pass_id),
                                                  space_id UUID REFERENCES Parking_Space(space_id),
                                                  assigned_date DATE
);
