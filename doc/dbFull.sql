--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.14
-- Dumped by pg_dump version 9.5.14

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: brand; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.brand (
    id integer NOT NULL,
    name character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.brand OWNER TO postgres;

--
-- Name: brand_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.brand_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.brand_id_seq OWNER TO postgres;

--
-- Name: brand_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.brand_id_seq OWNED BY public.brand.id;


--
-- Name: car; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car (
    id integer NOT NULL,
    number character varying NOT NULL,
    model_id integer NOT NULL,
    user_account_id integer NOT NULL,
    tariff_id integer NOT NULL,
    foto_id integer NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.car OWNER TO postgres;

--
-- Name: car_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.car_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_id_seq OWNER TO postgres;

--
-- Name: car_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.car_id_seq OWNED BY public.car.id;


--
-- Name: event; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.event (
    id integer NOT NULL,
    place_id integer NOT NULL,
    car_id integer NOT NULL,
    time_start timestamp without time zone NOT NULL,
    time_end timestamp without time zone,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.event OWNER TO postgres;

--
-- Name: event_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.event_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.event_id_seq OWNER TO postgres;

--
-- Name: event_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.event_id_seq OWNED BY public.event.id;


--
-- Name: foto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.foto (
    id integer NOT NULL,
    link character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.foto OWNER TO postgres;

--
-- Name: foto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.foto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.foto_id_seq OWNER TO postgres;

--
-- Name: foto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.foto_id_seq OWNED BY public.foto.id;


--
-- Name: model; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.model (
    id integer NOT NULL,
    name character varying NOT NULL,
    brand_id integer NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.model OWNER TO postgres;

--
-- Name: model_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.model_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.model_id_seq OWNER TO postgres;

--
-- Name: model_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.model_id_seq OWNED BY public.model.id;


--
-- Name: parking; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.parking (
    id integer NOT NULL,
    name character varying NOT NULL,
    adress character varying NOT NULL,
    width integer NOT NULL,
    length integer NOT NULL,
    cost_per_day numeric NOT NULL,
    status character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.parking OWNER TO postgres;

--
-- Name: parking_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.parking_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.parking_id_seq OWNER TO postgres;

--
-- Name: parking_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.parking_id_seq OWNED BY public.parking.id;


--
-- Name: place; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.place (
    id integer NOT NULL,
    name character varying NOT NULL,
    parking_id integer NOT NULL,
    car_id integer,
    user_account_id bigint,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.place OWNER TO postgres;

--
-- Name: place_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.place_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.place_id_seq OWNER TO postgres;

--
-- Name: place_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.place_id_seq OWNED BY public.place.id;


--
-- Name: tariff; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tariff (
    id integer NOT NULL,
    name character varying NOT NULL,
    cost_per_day numeric NOT NULL,
    status character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.tariff OWNER TO postgres;

--
-- Name: tariff_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tariff_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tariff_id_seq OWNER TO postgres;

--
-- Name: tariff_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tariff_id_seq OWNED BY public.tariff.id;


--
-- Name: transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transaction (
    id integer NOT NULL,
    user_account_id integer NOT NULL,
    value numeric NOT NULL,
    description character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.transaction OWNER TO postgres;

--
-- Name: transaction_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transaction_id_seq OWNER TO postgres;

--
-- Name: transaction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transaction_id_seq OWNED BY public.transaction.id;


--
-- Name: user_account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_account (
    id integer NOT NULL,
    first_name character varying NOT NULL,
    last_name character varying NOT NULL,
    role character varying NOT NULL,
    email character varying NOT NULL,
    password character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.user_account OWNER TO postgres;

--
-- Name: user_account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_account_id_seq OWNER TO postgres;

--
-- Name: user_account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_account_id_seq OWNED BY public.user_account.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.brand ALTER COLUMN id SET DEFAULT nextval('public.brand_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car ALTER COLUMN id SET DEFAULT nextval('public.car_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event ALTER COLUMN id SET DEFAULT nextval('public.event_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.foto ALTER COLUMN id SET DEFAULT nextval('public.foto_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model ALTER COLUMN id SET DEFAULT nextval('public.model_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.parking ALTER COLUMN id SET DEFAULT nextval('public.parking_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.place ALTER COLUMN id SET DEFAULT nextval('public.place_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tariff ALTER COLUMN id SET DEFAULT nextval('public.tariff_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction ALTER COLUMN id SET DEFAULT nextval('public.transaction_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_account ALTER COLUMN id SET DEFAULT nextval('public.user_account_id_seq'::regclass);


--
-- Data for Name: brand; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.brand (id, name, created, updated) VALUES (1, 'Audi', '2019-01-21 17:16:17.591', '2019-01-21 17:16:17.591');
INSERT INTO public.brand (id, name, created, updated) VALUES (2, 'Kia', '2019-01-21 17:16:23.939', '2019-01-21 17:16:23.939');
INSERT INTO public.brand (id, name, created, updated) VALUES (3, 'Opel', '2019-01-21 17:16:29.661', '2019-01-21 17:16:29.661');
INSERT INTO public.brand (id, name, created, updated) VALUES (4, 'Smart', '2019-01-21 17:16:34.517', '2019-01-21 17:16:34.517');
INSERT INTO public.brand (id, name, created, updated) VALUES (5, 'Jaguar', '2019-01-21 17:16:43.729', '2019-01-21 17:16:43.729');


--
-- Name: brand_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.brand_id_seq', 5, true);


--
-- Data for Name: car; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.car (id, number, model_id, user_account_id, tariff_id, foto_id, created, updated) VALUES (2, '8859EC-4', 4, 2, 1, 2, '2019-01-23 20:27:03.635', '2019-01-23 20:42:50.786');
INSERT INTO public.car (id, number, model_id, user_account_id, tariff_id, foto_id, created, updated) VALUES (1, '2019BI-4', 1, 1, 3, 1, '2019-01-21 17:19:18.769', '2019-01-25 20:37:25.987');


--
-- Name: car_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.car_id_seq', 2, true);


--
-- Data for Name: event; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: event_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.event_id_seq', 1, false);


--
-- Data for Name: foto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.foto (id, link, created, updated) VALUES (2, 'ce498f87-fac2-422a-84e5-783ae07034dc', '2019-01-23 20:27:03.547', '2019-01-23 20:42:50.779');
INSERT INTO public.foto (id, link, created, updated) VALUES (1, 'd2af2fd1-89c9-422e-a32d-08a89b29d005', '2019-01-21 17:19:18.751', '2019-01-25 20:37:25.974');


--
-- Name: foto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.foto_id_seq', 2, true);


--
-- Data for Name: model; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.model (id, name, brand_id, created, updated) VALUES (1, 'Omega B', 3, '2019-01-21 17:17:34.662', '2019-01-21 17:17:34.662');
INSERT INTO public.model (id, name, brand_id, created, updated) VALUES (2, 'A6', 1, '2019-01-21 17:17:39.876', '2019-01-21 17:17:39.876');
INSERT INTO public.model (id, name, brand_id, created, updated) VALUES (3, 'Forfour', 4, '2019-01-21 17:17:52.158', '2019-01-21 17:17:52.158');
INSERT INTO public.model (id, name, brand_id, created, updated) VALUES (4, 'Fortwo', 4, '2019-01-21 17:18:01.567', '2019-01-21 17:18:01.567');
INSERT INTO public.model (id, name, brand_id, created, updated) VALUES (5, 'Sorento', 2, '2019-01-21 17:18:09.802', '2019-01-21 17:18:09.802');


--
-- Name: model_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.model_id_seq', 5, true);


--
-- Data for Name: parking; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.parking (id, name, adress, width, length, cost_per_day, status, created, updated) VALUES (1, 'Central', 'Grodno, BLK 32', 5, 9, 1, 'enable', '2019-01-21 17:19:45.693', '2019-01-23 22:13:58.909');


--
-- Name: parking_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.parking_id_seq', 1, true);


--
-- Data for Name: place; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (2, '1_2', 1, NULL, NULL, '2019-01-21 17:19:59.147', '2019-01-21 17:19:59.147');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (3, '3_2', 1, NULL, NULL, '2019-01-21 17:19:59.159', '2019-01-21 17:19:59.159');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (4, '4_2', 1, NULL, NULL, '2019-01-21 17:19:59.172', '2019-01-21 17:19:59.172');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (5, '6_2', 1, NULL, NULL, '2019-01-21 17:19:59.182', '2019-01-21 17:19:59.182');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (6, '7_2', 1, NULL, NULL, '2019-01-21 17:19:59.191', '2019-01-21 17:19:59.191');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (7, '9_2', 1, NULL, NULL, '2019-01-21 17:19:59.204', '2019-01-21 17:19:59.204');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (8, '1_3', 1, NULL, NULL, '2019-01-21 17:19:59.212', '2019-01-21 17:19:59.212');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (9, '3_3', 1, NULL, NULL, '2019-01-21 17:19:59.22', '2019-01-21 17:19:59.22');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (10, '4_3', 1, NULL, NULL, '2019-01-21 17:19:59.23', '2019-01-21 17:19:59.23');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (11, '6_3', 1, NULL, NULL, '2019-01-21 17:19:59.238', '2019-01-21 17:19:59.238');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (12, '7_3', 1, NULL, NULL, '2019-01-21 17:19:59.245', '2019-01-21 17:19:59.245');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (13, '9_3', 1, NULL, NULL, '2019-01-21 17:19:59.254', '2019-01-21 17:19:59.254');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (14, '1_4', 1, NULL, NULL, '2019-01-21 17:19:59.266', '2019-01-21 17:19:59.266');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (15, '3_4', 1, NULL, NULL, '2019-01-21 17:19:59.272', '2019-01-21 17:19:59.272');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (16, '4_4', 1, NULL, NULL, '2019-01-21 17:19:59.281', '2019-01-21 17:19:59.281');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (17, '6_4', 1, NULL, NULL, '2019-01-21 17:19:59.288', '2019-01-21 17:19:59.288');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (18, '7_4', 1, NULL, NULL, '2019-01-21 17:19:59.296', '2019-01-21 17:19:59.296');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (19, '9_4', 1, NULL, NULL, '2019-01-21 17:19:59.311', '2019-01-21 17:19:59.311');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (21, '1_1', 1, NULL, NULL, '2019-01-23 20:09:17.295', '2019-01-23 20:09:17.295');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (22, '3_1', 1, NULL, NULL, '2019-01-23 20:09:17.434', '2019-01-23 20:09:17.434');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (23, '8_1', 1, NULL, NULL, '2019-01-23 20:09:17.447', '2019-01-23 20:09:17.447');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (24, '1_2', 1, NULL, NULL, '2019-01-23 20:09:17.458', '2019-01-23 20:09:17.458');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (25, '3_2', 1, NULL, NULL, '2019-01-23 20:09:17.466', '2019-01-23 20:09:17.466');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (26, '4_2', 1, NULL, NULL, '2019-01-23 20:09:17.477', '2019-01-23 20:09:17.477');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (27, '6_2', 1, NULL, NULL, '2019-01-23 20:09:17.486', '2019-01-23 20:09:17.486');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (28, '1_3', 1, NULL, NULL, '2019-01-23 20:09:17.493', '2019-01-23 20:09:17.493');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (29, '3_3', 1, NULL, NULL, '2019-01-23 20:09:17.502', '2019-01-23 20:09:17.502');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (30, '5_3', 1, NULL, NULL, '2019-01-23 20:09:17.512', '2019-01-23 20:09:17.512');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (31, '6_3', 1, NULL, NULL, '2019-01-23 20:09:17.52', '2019-01-23 20:09:17.52');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (32, '7_3', 1, NULL, NULL, '2019-01-23 20:09:17.528', '2019-01-23 20:09:17.528');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (33, '9_3', 1, NULL, NULL, '2019-01-23 20:09:17.538', '2019-01-23 20:09:17.538');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (34, '1_4', 1, NULL, NULL, '2019-01-23 20:09:17.545', '2019-01-23 20:09:17.545');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (35, '3_4', 1, NULL, NULL, '2019-01-23 20:09:17.553', '2019-01-23 20:09:17.553');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (36, '4_4', 1, NULL, NULL, '2019-01-23 20:09:17.56', '2019-01-23 20:09:17.56');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (37, '5_4', 1, NULL, NULL, '2019-01-23 20:09:17.569', '2019-01-23 20:09:17.569');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (38, '8_4', 1, NULL, NULL, '2019-01-23 20:09:17.576', '2019-01-23 20:09:17.576');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (39, '1_5', 1, NULL, NULL, '2019-01-23 20:09:17.587', '2019-01-23 20:09:17.587');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (40, '3_5', 1, NULL, NULL, '2019-01-23 20:09:17.594', '2019-01-23 20:09:17.594');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (41, '4_5', 1, NULL, NULL, '2019-01-23 20:09:17.603', '2019-01-23 20:09:17.603');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (42, '8_5', 1, NULL, NULL, '2019-01-23 20:09:17.61', '2019-01-23 20:09:17.61');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (20, '1_5', 1, NULL, 2, '2019-01-21 17:19:59.337', '2019-01-23 20:27:28.801');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (1, '1_1', 1, NULL, 1, '2019-01-21 17:19:59.047', '2019-01-23 20:30:07.325');
INSERT INTO public.place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (43, '9_5', 1, 1, 2, '2019-01-23 20:09:17.621', '2019-01-25 20:36:38.11');


--
-- Name: place_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.place_id_seq', 43, true);


--
-- Data for Name: tariff; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tariff (id, name, cost_per_day, status, created, updated) VALUES (1, 'Day', 3, 'enable', '2019-01-21 17:18:35.82', '2019-01-21 17:18:35.82');
INSERT INTO public.tariff (id, name, cost_per_day, status, created, updated) VALUES (2, 'Week', 2, 'enable', '2019-01-21 17:18:45.113', '2019-01-21 17:18:45.113');
INSERT INTO public.tariff (id, name, cost_per_day, status, created, updated) VALUES (3, 'Month', 1, 'enable', '2019-01-21 17:18:56.318', '2019-01-21 17:18:56.318');


--
-- Name: tariff_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tariff_id_seq', 3, true);


--
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.transaction (id, user_account_id, value, description, created, updated) VALUES (1, 1, -1, 'списание ', '2019-01-25 20:57:33.318', '2019-01-25 20:57:33.318');
INSERT INTO public.transaction (id, user_account_id, value, description, created, updated) VALUES (2, 1, 10, 'пополнение счета', '2019-01-25 20:58:20.753', '2019-01-25 20:58:20.753');


--
-- Name: transaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transaction_id_seq', 2, true);


--
-- Data for Name: user_account; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_account (id, first_name, last_name, role, email, password, created, updated) VALUES (1, 'VIKTAR', 'MIKHALEVICH', 'admin', 'admin', '2dd0f8c9233b5d3da66e439054e1dcec', '2019-01-21 17:17:01.345', '2019-01-23 21:14:36.925');
INSERT INTO public.user_account (id, first_name, last_name, role, email, password, created, updated) VALUES (2, 'LIUDMILA', 'Kukel', 'user', 'kukel', 'f991b7346a1e5c08262642312b4f1482', '2019-01-21 17:17:16.005', '2019-01-23 21:14:48.997');
INSERT INTO public.user_account (id, first_name, last_name, role, email, password, created, updated) VALUES (4, 'VIKTAR', 'MIKHALEVICH', 'admin', 'v.v.mikhalevich@gmail.com', '2dd0f8c9233b5d3da66e439054e1dcec', '2019-01-22 14:44:31.353', '2019-01-23 21:15:07.036');


--
-- Name: user_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_account_id_seq', 4, true);


--
-- Name: brand_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.brand
    ADD CONSTRAINT brand_pk PRIMARY KEY (id);


--
-- Name: car_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_pk PRIMARY KEY (id);


--
-- Name: event_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_pk PRIMARY KEY (id);


--
-- Name: foto_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.foto
    ADD CONSTRAINT foto_pk PRIMARY KEY (id);


--
-- Name: model_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model
    ADD CONSTRAINT model_pk PRIMARY KEY (id);


--
-- Name: parking_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.parking
    ADD CONSTRAINT parking_pk PRIMARY KEY (id);


--
-- Name: place_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.place
    ADD CONSTRAINT place_pk PRIMARY KEY (id);


--
-- Name: tariff_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tariff
    ADD CONSTRAINT tariff_pk PRIMARY KEY (id);


--
-- Name: transaction_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_pk PRIMARY KEY (id);


--
-- Name: user_account_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_account
    ADD CONSTRAINT user_account_pk PRIMARY KEY (id);


--
-- Name: car_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_fk0 FOREIGN KEY (model_id) REFERENCES public.model(id);


--
-- Name: car_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_fk1 FOREIGN KEY (user_account_id) REFERENCES public.user_account(id);


--
-- Name: car_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_fk2 FOREIGN KEY (tariff_id) REFERENCES public.tariff(id);


--
-- Name: car_fk3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_fk3 FOREIGN KEY (foto_id) REFERENCES public.foto(id);


--
-- Name: event_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_fk0 FOREIGN KEY (place_id) REFERENCES public.place(id);


--
-- Name: event_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_fk1 FOREIGN KEY (car_id) REFERENCES public.car(id);


--
-- Name: model_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model
    ADD CONSTRAINT model_fk0 FOREIGN KEY (brand_id) REFERENCES public.brand(id);


--
-- Name: place_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.place
    ADD CONSTRAINT place_fk0 FOREIGN KEY (parking_id) REFERENCES public.parking(id);


--
-- Name: place_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.place
    ADD CONSTRAINT place_fk1 FOREIGN KEY (car_id) REFERENCES public.car(id);


--
-- Name: place_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.place
    ADD CONSTRAINT place_fk2 FOREIGN KEY (user_account_id) REFERENCES public.user_account(id);


--
-- Name: transaction_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_fk0 FOREIGN KEY (user_account_id) REFERENCES public.user_account(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

