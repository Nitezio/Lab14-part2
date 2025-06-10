INSERT INTO users(email, name, password) VALUES
    ('admin@gmail.com','admin','$2a$12$JGriZzgFwZNEeuIzFcocjug9wb0/G0EJ1nco27FZoCvVLmfpfiiWe');
INSERT INTO users(email, name, password) VALUES
    ('test@gmail.com','John','$2a$12$F2QRPx07EsQTswGmyxB4sOw7RLCnIDqux/LWhO5vfegs42OS2LE.C');
INSERT INTO users(email, name, password) VALUES
    ('abc@gmail.com','Alex','$2a$12$aRRjmnRA5MkVg3M.xSc1G.jQkkvfx73WJhIvI.77IMkJyZpTT5dvC');
INSERT INTO users(email, name, password) VALUES
    ('def@gmail.com','Boss','$2a$12$MBeLkc4HGARn/pOnBl1HSugtEBvL5EwRvQ4EzqAykvt4hUogKl/Zy');

INSERT INTO roles(name) VALUES ('ROLE_ADMIN');
INSERT INTO roles(name) VALUES ('ROLE_USER');

INSERT INTO users_roles(role_id, user_id) VALUES (1,1);
INSERT INTO users_roles(role_id, user_id) VALUES (2,2);
INSERT INTO users_roles(role_id, user_id) VALUES (2,3);
INSERT INTO users_roles(role_id, user_id) VALUES (2,4);
INSERT INTO users_roles(role_id, user_id) VALUES (1,4);
