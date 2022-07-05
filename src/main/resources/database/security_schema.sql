--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.24
-- Dumped by pg_dump version 9.6.24

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER TABLE ONLY security.verification_token DROP CONSTRAINT verification_token_user;
ALTER TABLE ONLY security.user_role DROP CONSTRAINT user_role_user_;
ALTER TABLE ONLY security.user_role DROP CONSTRAINT user_role_role;
ALTER TABLE ONLY security.user_location DROP CONSTRAINT user_location_user;
ALTER TABLE ONLY security.role_permission DROP CONSTRAINT role_permission_role;
ALTER TABLE ONLY security.role_permission DROP CONSTRAINT role_permission_permission;
ALTER TABLE ONLY security.password_reset_token DROP CONSTRAINT password_reset_token_user;
ALTER TABLE ONLY security.new_location_token DROP CONSTRAINT new_location_token_user_location;
ALTER TABLE ONLY security.user_ DROP CONSTRAINT user_user_name;
ALTER TABLE ONLY security.user_role DROP CONSTRAINT user_role_pkey;
ALTER TABLE ONLY security.user_location DROP CONSTRAINT user_location_pkey;
ALTER TABLE ONLY security.user_ DROP CONSTRAINT user___pkey;
ALTER TABLE ONLY security.role DROP CONSTRAINT role_pkey;
ALTER TABLE ONLY security.role_permission DROP CONSTRAINT role_permission_pkey;
ALTER TABLE ONLY security.password_reset_token DROP CONSTRAINT password_reset_token_pkey;
ALTER TABLE ONLY security.oauth_client_token DROP CONSTRAINT oauth_client_token_pkey;
ALTER TABLE ONLY security.oauth_client_details DROP CONSTRAINT oauth_client_details_pkey;
ALTER TABLE ONLY security.oauth_access_token DROP CONSTRAINT oauth_access_token_pkey;
ALTER TABLE ONLY security.new_location_token DROP CONSTRAINT new_location_token_pkey;
ALTER TABLE ONLY security.device_metadata DROP CONSTRAINT device_metadata_pkey;
ALTER TABLE ONLY security.clientdetails DROP CONSTRAINT clientdetails_pkey;
ALTER TABLE ONLY security.permission DROP CONSTRAINT authority_pkey;
ALTER TABLE ONLY security.permission DROP CONSTRAINT authority_name;
ALTER TABLE security.permission ALTER COLUMN id DROP DEFAULT;
DROP TABLE security.verification_token;
DROP TABLE security.user_role;
DROP SEQUENCE security.user_role_id_seq;
DROP TABLE security.user_location;
DROP TABLE security.user_;
DROP TABLE security.role_permission;
DROP SEQUENCE security.role_permission_id_seq;
DROP TABLE security.role;
DROP SEQUENCE security.role_id_seq;
DROP TABLE security.password_reset_token;
DROP TABLE security.oauth_refresh_token;
DROP TABLE security.oauth_code;
DROP TABLE security.oauth_client_token;
DROP TABLE security.oauth_client_details;
DROP TABLE security.oauth_approvals;
DROP TABLE security.oauth_access_token;
DROP TABLE security.new_location_token;
DROP SEQUENCE security.hibernate_sequence;
DROP TABLE security.device_metadata;
DROP TABLE security.clientdetails;
DROP SEQUENCE security.authority_id_seq;
DROP TABLE security.permission;
DROP SCHEMA security;
--
-- Name: security; Type: SCHEMA; Schema: -; Owner: security_owner
--

CREATE SCHEMA security;


ALTER SCHEMA security OWNER TO security_owner;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: permission; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.permission (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE security.permission OWNER TO security_owner;

--
-- Name: authority_id_seq; Type: SEQUENCE; Schema: security; Owner: security_owner
--

CREATE SEQUENCE security.authority_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.authority_id_seq OWNER TO security_owner;

--
-- Name: authority_id_seq; Type: SEQUENCE OWNED BY; Schema: security; Owner: security_owner
--

ALTER SEQUENCE security.authority_id_seq OWNED BY security.permission.id;


--
-- Name: clientdetails; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.clientdetails (
    appid character varying(256) NOT NULL,
    resourceids character varying(256),
    appsecret character varying(256),
    scope character varying(256),
    granttypes character varying(256),
    redirecturl character varying(256),
    authorities character varying(256),
    access_token_validity integer,
    refresh_token_validity integer,
    additionalinformation character varying(4096),
    autoapprovescopes character varying(256)
);


ALTER TABLE security.clientdetails OWNER TO security_owner;

--
-- Name: device_metadata; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.device_metadata (
    id bigint NOT NULL,
    device_details character varying(255),
    last_logged_in timestamp without time zone,
    location character varying(255),
    user_id bigint
);


ALTER TABLE security.device_metadata OWNER TO security_owner;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: security; Owner: security_owner
--

CREATE SEQUENCE security.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.hibernate_sequence OWNER TO security_owner;

--
-- Name: new_location_token; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.new_location_token (
    id bigint NOT NULL,
    token character varying(255),
    user_location_id bigint NOT NULL
);


ALTER TABLE security.new_location_token OWNER TO security_owner;

--
-- Name: oauth_access_token; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.oauth_access_token (
    token_id character varying(256),
    token bytea,
    authentication_id character varying(256) NOT NULL,
    user_name character varying(256),
    client_id character varying(256),
    authentication bytea,
    refresh_token character varying(256)
);


ALTER TABLE security.oauth_access_token OWNER TO security_owner;

--
-- Name: oauth_approvals; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.oauth_approvals (
    userid character varying(256),
    clientid character varying(256),
    scope character varying(256),
    status character varying(10),
    expiresat timestamp without time zone,
    lastmodifiedat timestamp without time zone
);


ALTER TABLE security.oauth_approvals OWNER TO security_owner;

--
-- Name: oauth_client_details; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.oauth_client_details (
    client_id character varying(256) NOT NULL,
    resource_ids character varying(256),
    client_secret character varying(256),
    scope character varying(256),
    authorized_grant_types character varying(256),
    web_server_redirect_uri character varying(256),
    authorities character varying(256),
    access_token_validity integer,
    refresh_token_validity integer,
    additional_information character varying(4096),
    autoapprove character varying(256)
);


ALTER TABLE security.oauth_client_details OWNER TO security_owner;

--
-- Name: oauth_client_token; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.oauth_client_token (
    token_id character varying(256),
    token bytea,
    authentication_id character varying(256) NOT NULL,
    user_name character varying(256),
    client_id character varying(256)
);


ALTER TABLE security.oauth_client_token OWNER TO security_owner;

--
-- Name: oauth_code; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.oauth_code (
    code character varying(256),
    authentication bytea
);


ALTER TABLE security.oauth_code OWNER TO security_owner;

--
-- Name: oauth_refresh_token; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.oauth_refresh_token (
    token_id character varying(256),
    token bytea,
    authentication bytea
);


ALTER TABLE security.oauth_refresh_token OWNER TO security_owner;

--
-- Name: password_reset_token; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.password_reset_token (
    id bigint NOT NULL,
    expiry_date timestamp without time zone,
    token character varying(255),
    pty_id bigint NOT NULL
);


ALTER TABLE security.password_reset_token OWNER TO security_owner;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: security; Owner: security_owner
--

CREATE SEQUENCE security.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.role_id_seq OWNER TO security_owner;

--
-- Name: role; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.role (
    id bigint DEFAULT nextval('security.role_id_seq'::regclass) NOT NULL,
    name character varying(40)
);


ALTER TABLE security.role OWNER TO security_owner;

--
-- Name: role_permission_id_seq; Type: SEQUENCE; Schema: security; Owner: security_owner
--

CREATE SEQUENCE security.role_permission_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.role_permission_id_seq OWNER TO security_owner;

--
-- Name: role_permission; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.role_permission (
    id bigint DEFAULT nextval('security.role_permission_id_seq'::regclass) NOT NULL,
    role_id bigint NOT NULL,
    permission_id bigint NOT NULL
);


ALTER TABLE security.role_permission OWNER TO security_owner;

--
-- Name: user_; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.user_ (
    pty_id bigint NOT NULL,
    account_expired boolean,
    account_locked boolean,
    credentials_expired boolean,
    enabled boolean,
    password character varying(255),
    user_name character varying(255),
    is_using2fa boolean,
    secret character varying(255)
);


ALTER TABLE security.user_ OWNER TO security_owner;

--
-- Name: user_location; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.user_location (
    id bigint NOT NULL,
    country character varying(255),
    enabled boolean NOT NULL,
    pty_id bigint NOT NULL
);


ALTER TABLE security.user_location OWNER TO security_owner;

--
-- Name: user_role_id_seq; Type: SEQUENCE; Schema: security; Owner: security_owner
--

CREATE SEQUENCE security.user_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.user_role_id_seq OWNER TO security_owner;

--
-- Name: user_role; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.user_role (
    id bigint DEFAULT nextval('security.user_role_id_seq'::regclass) NOT NULL,
    pty_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE security.user_role OWNER TO security_owner;

--
-- Name: verification_token; Type: TABLE; Schema: security; Owner: security_owner
--

CREATE TABLE security.verification_token (
    id bigint NOT NULL,
    expiry_date timestamp without time zone,
    token character varying(255),
    pty_id bigint NOT NULL
);


ALTER TABLE security.verification_token OWNER TO security_owner;

--
-- Name: permission id; Type: DEFAULT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.permission ALTER COLUMN id SET DEFAULT nextval('security.authority_id_seq'::regclass);


--
-- Name: permission authority_name; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.permission
    ADD CONSTRAINT authority_name UNIQUE (name);


--
-- Name: permission authority_pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.permission
    ADD CONSTRAINT authority_pkey PRIMARY KEY (id);


--
-- Name: clientdetails clientdetails_pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.clientdetails
    ADD CONSTRAINT clientdetails_pkey PRIMARY KEY (appid);


--
-- Name: device_metadata device_metadata_pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.device_metadata
    ADD CONSTRAINT device_metadata_pkey PRIMARY KEY (id);


--
-- Name: new_location_token new_location_token_pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.new_location_token
    ADD CONSTRAINT new_location_token_pkey PRIMARY KEY (id);


--
-- Name: oauth_access_token oauth_access_token_pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.oauth_access_token
    ADD CONSTRAINT oauth_access_token_pkey PRIMARY KEY (authentication_id);


--
-- Name: oauth_client_details oauth_client_details_pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.oauth_client_details
    ADD CONSTRAINT oauth_client_details_pkey PRIMARY KEY (client_id);


--
-- Name: oauth_client_token oauth_client_token_pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.oauth_client_token
    ADD CONSTRAINT oauth_client_token_pkey PRIMARY KEY (authentication_id);


--
-- Name: password_reset_token password_reset_token_pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.password_reset_token
    ADD CONSTRAINT password_reset_token_pkey PRIMARY KEY (id);


--
-- Name: role_permission role_permission_pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.role_permission
    ADD CONSTRAINT role_permission_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: user_ user___pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.user_
    ADD CONSTRAINT user___pkey PRIMARY KEY (pty_id);


--
-- Name: user_location user_location_pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.user_location
    ADD CONSTRAINT user_location_pkey PRIMARY KEY (id);


--
-- Name: user_role user_role_pkey; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (id);


--
-- Name: user_ user_user_name; Type: CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.user_
    ADD CONSTRAINT user_user_name UNIQUE (user_name);


--
-- Name: new_location_token new_location_token_user_location; Type: FK CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.new_location_token
    ADD CONSTRAINT new_location_token_user_location FOREIGN KEY (user_location_id) REFERENCES security.user_location(id);


--
-- Name: password_reset_token password_reset_token_user; Type: FK CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.password_reset_token
    ADD CONSTRAINT password_reset_token_user FOREIGN KEY (pty_id) REFERENCES security.user_(pty_id);


--
-- Name: role_permission role_permission_permission; Type: FK CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.role_permission
    ADD CONSTRAINT role_permission_permission FOREIGN KEY (permission_id) REFERENCES security.permission(id);


--
-- Name: role_permission role_permission_role; Type: FK CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.role_permission
    ADD CONSTRAINT role_permission_role FOREIGN KEY (role_id) REFERENCES security.role(id);


--
-- Name: user_location user_location_user; Type: FK CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.user_location
    ADD CONSTRAINT user_location_user FOREIGN KEY (pty_id) REFERENCES security.user_(pty_id);


--
-- Name: user_role user_role_role; Type: FK CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.user_role
    ADD CONSTRAINT user_role_role FOREIGN KEY (role_id) REFERENCES security.role(id);


--
-- Name: user_role user_role_user_; Type: FK CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.user_role
    ADD CONSTRAINT user_role_user_ FOREIGN KEY (pty_id) REFERENCES security.user_(pty_id);


--
-- Name: verification_token verification_token_user; Type: FK CONSTRAINT; Schema: security; Owner: security_owner
--

ALTER TABLE ONLY security.verification_token
    ADD CONSTRAINT verification_token_user FOREIGN KEY (pty_id) REFERENCES security.user_(pty_id);


--
-- Name: SCHEMA security; Type: ACL; Schema: -; Owner: security_owner
--

GRANT USAGE ON SCHEMA security TO security_app;
GRANT USAGE ON SCHEMA security TO mochi_app;


--
-- Name: TABLE permission; Type: ACL; Schema: security; Owner: security_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.permission TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.permission TO mochi_app;


--
-- Name: SEQUENCE authority_id_seq; Type: ACL; Schema: security; Owner: security_owner
--

GRANT ALL ON SEQUENCE security.authority_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE security.authority_id_seq TO security_app;


--
-- Name: TABLE clientdetails; Type: ACL; Schema: security; Owner: security_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.clientdetails TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.clientdetails TO mochi_app;


--
-- Name: TABLE device_metadata; Type: ACL; Schema: security; Owner: security_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.device_metadata TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.device_metadata TO mochi_app;


--
-- Name: SEQUENCE hibernate_sequence; Type: ACL; Schema: security; Owner: security_owner
--

GRANT ALL ON SEQUENCE security.hibernate_sequence TO mochi_app;
GRANT ALL ON SEQUENCE security.hibernate_sequence TO security_app;


--
-- Name: TABLE new_location_token; Type: ACL; Schema: security; Owner: security_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.new_location_token TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.new_location_token TO mochi_app;


--
-- Name: TABLE oauth_access_token; Type: ACL; Schema: security; Owner: security_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.oauth_access_token TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.oauth_access_token TO mochi_app;


--
-- Name: TABLE oauth_approvals; Type: ACL; Schema: security; Owner: security_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.oauth_approvals TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.oauth_approvals TO mochi_app;


--
-- Name: TABLE oauth_client_details; Type: ACL; Schema: security; Owner: security_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.oauth_client_details TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.oauth_client_details TO mochi_app;


--
-- Name: TABLE oauth_client_token; Type: ACL; Schema: security; Owner: security_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.oauth_client_token TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.oauth_client_token TO mochi_app;


--
-- Name: TABLE oauth_code; Type: ACL; Schema: security; Owner: security_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.oauth_code TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.oauth_code TO mochi_app;


--
-- Name: TABLE oauth_refresh_token; Type: ACL; Schema: security; Owner: security_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.oauth_refresh_token TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.oauth_refresh_token TO mochi_app;


--
-- Name: TABLE password_reset_token; Type: ACL; Schema: security; Owner: security_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.password_reset_token TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.password_reset_token TO mochi_app;


--
-- Name: SEQUENCE role_id_seq; Type: ACL; Schema: security; Owner: security_owner
--

GRANT ALL ON SEQUENCE security.role_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE security.role_id_seq TO security_app;


--
-- Name: TABLE role; Type: ACL; Schema: security; Owner: security_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.role TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.role TO mochi_app;


--
-- Name: SEQUENCE role_permission_id_seq; Type: ACL; Schema: security; Owner: security_owner
--

GRANT ALL ON SEQUENCE security.role_permission_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE security.role_permission_id_seq TO security_app;


--
-- Name: TABLE role_permission; Type: ACL; Schema: security; Owner: security_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.role_permission TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.role_permission TO mochi_app;


--
-- Name: TABLE user_; Type: ACL; Schema: security; Owner: security_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.user_ TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.user_ TO mochi_app;


--
-- Name: TABLE user_location; Type: ACL; Schema: security; Owner: security_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.user_location TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.user_location TO mochi_app;


--
-- Name: SEQUENCE user_role_id_seq; Type: ACL; Schema: security; Owner: security_owner
--

GRANT ALL ON SEQUENCE security.user_role_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE security.user_role_id_seq TO security_app;


--
-- Name: TABLE user_role; Type: ACL; Schema: security; Owner: security_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.user_role TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.user_role TO mochi_app;


--
-- Name: TABLE verification_token; Type: ACL; Schema: security; Owner: security_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.verification_token TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE security.verification_token TO mochi_app;


--
-- PostgreSQL database dump complete
--

