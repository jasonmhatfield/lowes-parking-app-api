INSERT INTO GATE (gate_name, is_operational) VALUES ('Main Gate', true);
INSERT INTO GATE (gate_name, is_operational) VALUES ('South Gate', true);

INSERT INTO PARKING_SPOT (spot_number, is_occupied, type, user_id) VALUES ('A1', false, 'regular', null);
INSERT INTO PARKING_SPOT (spot_number, is_occupied, type, user_id) VALUES ('A2', false, 'regular', null);
INSERT INTO PARKING_SPOT (spot_number, is_occupied, type, user_id) VALUES ('B1', false, 'handicap', null);
INSERT INTO PARKING_SPOT (spot_number, is_occupied, type, user_id) VALUES ('B2', false, 'handicap', null);
INSERT INTO PARKING_SPOT (spot_number, is_occupied, type, user_id) VALUES ('C1', false, 'ev', null);
INSERT INTO PARKING_SPOT (spot_number, is_occupied, type, user_id) VALUES ('C2', false, 'ev', null);

INSERT INTO APP_USER (first_name, last_name, email, has_handicap_placard, has_ev, role)
VALUES ('Jason', 'Hatfield', 'jason.hatfield@example.com', false, false, 'admin');

INSERT INTO APP_USER (first_name, last_name, email, has_handicap_placard, has_ev, role)
VALUES ('John', 'Doe', 'john.doe@example.com', false, true, 'employee');

INSERT INTO APP_USER (first_name, last_name, email, has_handicap_placard, has_ev, role)
VALUES ('Jane', 'Doe', 'jane.doe@example.com', true, false, 'employee');

