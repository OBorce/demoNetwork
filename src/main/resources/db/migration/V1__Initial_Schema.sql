CREATE SCHEMA demo;

CREATE TABLE demo.user_details (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(120) NOT NULL UNIQUE,
    password VARCHAR(120) NOT NULL
);

CREATE TABLE demo.location (
    id BIGSERIAL PRIMARY KEY,

    longitude REAL NOT NULL,
    latitude REAL NOT NULL,
    city VARCHAR(120) NOT NULL,
    country VARCHAR(120) NOT NULL,

    CONSTRAINT unique_location UNIQUE (longitude, latitude)
);

CREATE TABLE demo.network_user (
    id BIGINT PRIMARY KEY REFERENCES demo.user_details(id),

    nickname VARCHAR(20) NOT NULL UNIQUE,
    first_name VARCHAR(40),
    last_name VARCHAR(40),

    date_created TIMESTAMP NOT NULL,

    location BIGINT REFERENCES demo.location(id)
);

CREATE TABLE demo.post (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(120) NOT NULL,
    description VARCHAR(1000) NOT NULL,

    date_created TIMESTAMP NOT NULL,
    created_by BIGINT NOT NULL REFERENCES demo.network_user(id),

    location BIGINT REFERENCES demo.location(id)
);

CREATE INDEX posts_by_title ON demo.post USING hash (title);
CREATE INDEX locations_by_city ON demo.location USING hash (city);

CREATE TABLE demo.oauth_client_details (
    client_id VARCHAR(255) PRIMARY KEY,
    resource_ids VARCHAR(255),
    client_secret VARCHAR(255),
    scope VARCHAR(255),
    authorized_grant_types VARCHAR(255),
    web_server_redirect_uri VARCHAR(255),
    authorities VARCHAR(255),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information VARCHAR(4096),
    autoapprove VARCHAR(255)
);
