--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: historify
--

INSERT INTO users (id, email, last_login, password, role, token, user_name) VALUES (1, 'historify@sveri.de', '2016-06-19 05:41:00.845054+00', '$2a$10$1rb2WfWjvLpkxn7/JsnfF.uyA9yBF5XNUn.lzflSR5jjhBHwOdXmy', 'ROLE_ADMIN', NULL, 'admin');
INSERT INTO users (id, email, last_login, password, role, token, user_name) VALUES (10, 'sveri@sveri.de', '2016-07-12 15:14:35.57902+02', '$2a$10$n8aY6Xv7xnxB4fBjIMi3weG5jJz/eSafQHFor8KgFgjwhbBHhGRFq', 'ROLE_USER', 'dde879cb634d2d1d165c00511a466e1a', 'sveri');


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: historify
--

SELECT pg_catalog.setval('users_id_seq', 10, true);


--
-- PostgreSQL database dump complete
--

