-- Create Gates Table --
CREATE TABLE IF NOT EXISTS GATE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    gate_name VARCHAR(255),
    is_operational BOOLEAN
);

-- Create Parking Spot Table --
CREATE TABLE IF NOT EXISTS PARKING_SPOT (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    spot_number VARCHAR(255),
    is_occupied BOOLEAN,
    type VARCHAR(50),
    user_id BIGINT
);

-- Create User Table --
CREATE TABLE IF NOT EXISTS APP_USER (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    has_handicap_placard BOOLEAN,
    has_ev BOOLEAN,
    role VARCHAR(50)
);
