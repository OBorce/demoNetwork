-- Create views for testing

INSERT INTO demo.user_details (id, email, password)
 VALUES (1, 'user', '{bcrypt}$2a$10$cyf5NfobcruKQ8XGjUJkEegr9ZWFqaea6vjpXWEaSqTa2xL9wjgQC'),
 (2, 'user2', '{bcrypt}$2a$10$cyf5NfobcruKQ8XGjUJkEegr9ZWFqaea6vjpXWEaSqTa2xL9wjgQC'),
 (3, 'user3', '{bcrypt}$2a$10$cyf5NfobcruKQ8XGjUJkEegr9ZWFqaea6vjpXWEaSqTa2xL9wjgQC');

INSERT INTO demo.oauth_client_details (client_id, client_secret, scope, authorized_grant_types, authorities, access_token_validity)
  VALUES ('clientId', '{bcrypt}$2a$10$vCXMWCn7fDZWOcLnIEhmK.74dvK1Eh8ae2WrWlhr2ETPLoxQctN4.', 'read,write', 'password,refresh_token,client_credentials', 'ROLE_CLIENT', 300);

INSERT INTO demo.location (id, longitude, latitude, city, country)
VALUES (1, 1.2, 1.3, 'Veles', 'Macedonia'),
 (2, 2.2, 3.3, 'Skopje', 'Macedonia');

INSERT INtO demo.network_user (id, nickname, first_name, last_name, date_created, location)
VALUES (1, 'Nick', 'Name', 'LName', CURRENT_TIMESTAMP(), 1),
 (2, 'Nick2', 'Name2', 'LName2', CURRENT_TIMESTAMP(), 2),
 (3, 'Nick3', 'Name3', 'LName3', CURRENT_TIMESTAMP(), 2);

INSERT INTO demo.post (id, title, description, date_created, created_by, location)
VALUES (1, 'Title', 'Description', CURRENT_TIMESTAMP(), 1, 1),
 (2, 'Title2', 'Description2', CURRENT_TIMESTAMP(), 2, 2),
 (3, 'Title3', 'Description3', CURRENT_TIMESTAMP(), 2, 2);
