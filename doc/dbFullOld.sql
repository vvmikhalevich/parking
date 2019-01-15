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
    model_id integer NOT NULL,
    number character varying NOT NULL,
    user_account_id integer NOT NULL,
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
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    id integer NOT NULL,
    user_account_id integer NOT NULL,
    car_id integer NOT NULL,
    tariff_id integer NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.client OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.client_id_seq OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.client_id_seq OWNED BY public.client.id;


--
-- Name: event; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.event (
    id integer NOT NULL,
    car_id integer NOT NULL,
    place_id integer NOT NULL,
    time_start timestamp without time zone NOT NULL,
    time_end timestamp without time zone NOT NULL,
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
    status character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL,
    width integer DEFAULT 5 NOT NULL,
    length integer DEFAULT 5 NOT NULL
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
    car_id integer NOT NULL,
    status character varying NOT NULL,
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
-- Name: place_owner; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.place_owner (
    id integer NOT NULL,
    user_account_id integer NOT NULL,
    place_id integer NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.place_owner OWNER TO postgres;

--
-- Name: place_owner_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.place_owner_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.place_owner_id_seq OWNER TO postgres;

--
-- Name: place_owner_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.place_owner_id_seq OWNED BY public.place_owner.id;


--
-- Name: tariff; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tariff (
    id integer NOT NULL,
    name character varying NOT NULL,
    price numeric NOT NULL,
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
    client_id integer NOT NULL,
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

ALTER TABLE ONLY public.client ALTER COLUMN id SET DEFAULT nextval('public.client_id_seq'::regclass);


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

ALTER TABLE ONLY public.place_owner ALTER COLUMN id SET DEFAULT nextval('public.place_owner_id_seq'::regclass);


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

INSERT INTO public.brand (id, name, created, updated) VALUES (2, 'Opel', '2018-12-28 21:05:33.467', '2018-12-28 21:05:33.467');
INSERT INTO public.brand (id, name, created, updated) VALUES (1, 'Audi', '2018-12-28 21:05:18.352', '2018-12-28 21:21:44.636');
INSERT INTO public.brand (id, name, created, updated) VALUES (5, 'BMW', '2018-12-31 01:35:24.585', '2018-12-31 01:43:41.824');
INSERT INTO public.brand (id, name, created, updated) VALUES (3, 'Smart', '2018-12-28 21:20:09.291', '2018-12-31 01:56:29.764');
INSERT INTO public.brand (id, name, created, updated) VALUES (6, 'Nissan', '2018-12-31 14:39:51.774', '2018-12-31 14:39:51.774');
INSERT INTO public.brand (id, name, created, updated) VALUES (7, 'Toyota', '2019-01-04 17:51:34.259', '2019-01-04 17:51:34.259');


--
-- Name: brand_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.brand_id_seq', 8, true);


--
-- Data for Name: car; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.car (id, model_id, number, user_account_id, foto_id, created, updated) VALUES (6, 11, '2019BI-4', 1, 1, '2019-01-04 16:27:44.263', '2019-01-04 16:27:44.263');
INSERT INTO public.car (id, model_id, number, user_account_id, foto_id, created, updated) VALUES (4, 11, '2019BI-4', 1, 2, '2019-01-04 15:03:25.467', '2019-01-04 16:59:36.13');
INSERT INTO public.car (id, model_id, number, user_account_id, foto_id, created, updated) VALUES (5, 8, '2019BI-4', 1, 1, '2019-01-04 16:27:33.419', '2019-01-04 17:04:21.31');
INSERT INTO public.car (id, model_id, number, user_account_id, foto_id, created, updated) VALUES (9, 14, '1122', 2, 2, '2019-01-04 17:52:21.491', '2019-01-04 17:52:21.491');
INSERT INTO public.car (id, model_id, number, user_account_id, foto_id, created, updated) VALUES (10, 8, '8859EC-4', 3, 3, '2019-01-06 02:13:36.969', '2019-01-06 02:13:36.969');
INSERT INTO public.car (id, model_id, number, user_account_id, foto_id, created, updated) VALUES (11, 12, '', 3, 4, '2019-01-09 21:43:28.684', '2019-01-09 21:43:28.684');


--
-- Name: car_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.car_id_seq', 11, true);


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.client (id, user_account_id, car_id, tariff_id, created, updated) VALUES (1, 1, 4, 2, '2019-01-06 02:02:33.376', '2019-01-06 02:02:33.376');
INSERT INTO public.client (id, user_account_id, car_id, tariff_id, created, updated) VALUES (2, 2, 9, 3, '2019-01-06 02:12:06.069', '2019-01-06 02:12:06.069');
INSERT INTO public.client (id, user_account_id, car_id, tariff_id, created, updated) VALUES (3, 3, 10, 3, '2019-01-06 02:13:50.375', '2019-01-06 02:13:50.375');


--
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.client_id_seq', 3, true);


--
-- Data for Name: event; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.event (id, car_id, place_id, time_start, time_end, created, updated) VALUES (1, 4, 1, '2019-01-06 18:58:05.615', '2019-01-06 18:58:56.987', '2019-01-06 18:58:05.615', '2019-01-06 18:58:56.987');
INSERT INTO public.event (id, car_id, place_id, time_start, time_end, created, updated) VALUES (2, 10, 5, '2019-01-06 19:07:18.985', '2019-01-06 19:07:18.985', '2019-01-06 19:07:18.985', '2019-01-06 19:07:18.985');
INSERT INTO public.event (id, car_id, place_id, time_start, time_end, created, updated) VALUES (3, 4, 5, '2019-01-09 20:17:53.565', '2019-01-09 20:17:53.565', '2019-01-09 20:17:53.565', '2019-01-09 20:17:53.565');
INSERT INTO public.event (id, car_id, place_id, time_start, time_end, created, updated) VALUES (4, 4, 1, '2019-01-09 22:19:21.546', '2019-01-09 22:19:21.546', '2019-01-09 22:19:21.546', '2019-01-09 22:19:21.546');
INSERT INTO public.event (id, car_id, place_id, time_start, time_end, created, updated) VALUES (5, 4, 1, '2019-01-09 22:19:53.116', '2019-01-09 22:19:53.116', '2019-01-09 22:19:53.116', '2019-01-09 22:19:53.116');
INSERT INTO public.event (id, car_id, place_id, time_start, time_end, created, updated) VALUES (6, 4, 1, '2019-01-09 00:00:00', '2019-01-09 00:00:00', '2019-01-09 22:21:12.611', '2019-01-09 22:21:12.611');


--
-- Name: event_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.event_id_seq', 6, true);


--
-- Data for Name: foto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.foto (id, link, created, updated) VALUES (1, '123', '2019-01-04 14:58:32.747', '2019-01-04 14:58:32.747');
INSERT INTO public.foto (id, link, created, updated) VALUES (2, '321', '2019-01-04 14:58:37.429', '2019-01-04 14:58:37.429');
INSERT INTO public.foto (id, link, created, updated) VALUES (3, 'http:\\foto.by\1233', '2019-01-04 17:53:06.818', '2019-01-04 17:53:06.818');
INSERT INTO public.foto (id, link, created, updated) VALUES (4, 'http:\\foto.by\1233213333333', '2019-01-09 20:43:19.029', '2019-01-09 20:43:19.029');


--
-- Name: foto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.foto_id_seq', 4, true);


--
-- Data for Name: model; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.model (id, name, brand_id, created, updated) VALUES (8, 'fortwo', 3, '2018-12-31 02:03:08.005', '2018-12-31 02:03:22.12');
INSERT INTO public.model (id, name, brand_id, created, updated) VALUES (9, 'a8', 1, '2018-12-31 02:03:45.855', '2018-12-31 02:03:45.855');
INSERT INTO public.model (id, name, brand_id, created, updated) VALUES (11, 'Omega B', 2, '2018-12-31 14:27:31.51', '2018-12-31 14:27:31.51');
INSERT INTO public.model (id, name, brand_id, created, updated) VALUES (12, 'Almera', 6, '2018-12-31 14:40:08.281', '2018-12-31 14:40:08.281');
INSERT INTO public.model (id, name, brand_id, created, updated) VALUES (10, '5', 5, '2018-12-31 02:07:04.731', '2018-12-31 14:40:17.824');
INSERT INTO public.model (id, name, brand_id, created, updated) VALUES (13, 'Forfour', 3, '2018-12-31 16:19:39.816', '2018-12-31 16:19:39.816');
INSERT INTO public.model (id, name, brand_id, created, updated) VALUES (14, 'Yaris', 7, '2019-01-04 17:51:59.495', '2019-01-04 17:51:59.495');
INSERT INTO public.model (id, name, brand_id, created, updated) VALUES (16, '5', 5, '2019-01-09 21:43:11.547', '2019-01-09 21:43:11.547');
INSERT INTO public.model (id, name, brand_id, created, updated) VALUES (17, 'a8', 1, '2019-01-09 21:43:17.387', '2019-01-09 21:43:17.387');


--
-- Name: model_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.model_id_seq', 17, true);


--
-- Data for Name: parking; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.parking (id, name, adress, status, created, updated, width, length) VALUES (1, 'Central', 'Grodno, BLK 32', 'enable', '2019-01-04 17:54:07.384', '2019-01-04 17:54:07.384', 5, 5);
INSERT INTO public.parking (id, name, adress, status, created, updated, width, length) VALUES (2, 'South', 'Grodno, Yugnaya 18', 'enable', '2019-01-04 17:55:24.22', '2019-01-04 17:55:24.22', 5, 5);


--
-- Name: parking_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.parking_id_seq', 3, true);


--
-- Data for Name: place; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.place (id, name, parking_id, car_id, status, created, updated) VALUES (4, 'b1', 1, 10, 'enable', '2019-01-06 17:58:13.297', '2019-01-06 17:58:13.297');
INSERT INTO public.place (id, name, parking_id, car_id, status, created, updated) VALUES (1, 'a2', 1, 4, 'enable', '2019-01-06 17:52:20.267', '2019-01-06 17:58:19.896');
INSERT INTO public.place (id, name, parking_id, car_id, status, created, updated) VALUES (5, 'a1', 2, 9, 'enable', '2019-01-06 18:37:37.153', '2019-01-06 18:37:37.153');


--
-- Name: place_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.place_id_seq', 5, true);


--
-- Data for Name: place_owner; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.place_owner (id, user_account_id, place_id, created, updated) VALUES (1, 3, 5, '2019-01-06 22:48:04.098', '2019-01-06 22:48:18.396');
INSERT INTO public.place_owner (id, user_account_id, place_id, created, updated) VALUES (3, 1, 1, '2019-01-06 22:48:22.934', '2019-01-06 22:48:22.934');


--
-- Name: place_owner_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.place_owner_id_seq', 3, true);


--
-- Data for Name: tariff; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tariff (id, name, price, status, created, updated) VALUES (1, 'Light', 10, 'enable', '2019-01-02 15:09:09.233', '2019-01-02 15:09:09.233');
INSERT INTO public.tariff (id, name, price, status, created, updated) VALUES (2, 'Medium', 15, 'enable', '2019-01-02 15:10:28.907', '2019-01-02 15:10:28.907');
INSERT INTO public.tariff (id, name, price, status, created, updated) VALUES (3, 'Premuim', 25, 'enable', '2019-01-04 17:52:40.096', '2019-01-04 17:52:40.096');


--
-- Name: tariff_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tariff_id_seq', 3, true);


--
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.transaction (id, client_id, value, description, created, updated) VALUES (1, 1, 2, 'пополнение счета', '2019-01-06 15:10:21.874', '2019-01-06 15:14:57.466');
INSERT INTO public.transaction (id, client_id, value, description, created, updated) VALUES (3, 2, -2, 'списание', '2019-01-06 15:15:18.544', '2019-01-06 15:15:18.544');
INSERT INTO public.transaction (id, client_id, value, description, created, updated) VALUES (4, 1, -1, 'списание', '2019-01-09 20:43:04.427', '2019-01-09 20:43:04.427');


--
-- Name: transaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transaction_id_seq', 4, true);


--
-- Data for Name: user_account; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_account (id, first_name, last_name, role, email, password, created, updated) VALUES (1, 'VIKTAR', 'MIKHALEVICH', '0', 'admin', 'nimda', '2019-01-02 21:19:55.268', '2019-01-02 21:19:55.268');
INSERT INTO public.user_account (id, first_name, last_name, role, email, password, created, updated) VALUES (2, 'Miroslav', 'MIKHALEVICH', '1', 'mim@mail.ru', 'nimda', '2019-01-02 21:20:32.603', '2019-01-02 21:20:49.525');
INSERT INTO public.user_account (id, first_name, last_name, role, email, password, created, updated) VALUES (3, 'Ludmila', 'Kukel', '1', 'kukel', 'lekuk', '2019-01-06 02:12:57.31', '2019-01-06 02:12:57.31');


--
-- Name: user_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_account_id_seq', 3, true);


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
-- Name: client_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pk PRIMARY KEY (id);


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
-- Name: place_owner_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.place_owner
    ADD CONSTRAINT place_owner_pk PRIMARY KEY (id);


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
    ADD CONSTRAINT car_fk2 FOREIGN KEY (foto_id) REFERENCES public.foto(id);


--
-- Name: client_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_fk0 FOREIGN KEY (user_account_id) REFERENCES public.user_account(id);


--
-- Name: client_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_fk1 FOREIGN KEY (car_id) REFERENCES public.car(id);


--
-- Name: client_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_fk2 FOREIGN KEY (tariff_id) REFERENCES public.tariff(id);


--
-- Name: event_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_fk0 FOREIGN KEY (car_id) REFERENCES public.car(id);


--
-- Name: event_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_fk1 FOREIGN KEY (place_id) REFERENCES public.place(id);


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
-- Name: place_owner_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.place_owner
    ADD CONSTRAINT place_owner_fk0 FOREIGN KEY (user_account_id) REFERENCES public.user_account(id);


--
-- Name: place_owner_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.place_owner
    ADD CONSTRAINT place_owner_fk1 FOREIGN KEY (place_id) REFERENCES public.place(id);


--
-- Name: transaction_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_fk0 FOREIGN KEY (client_id) REFERENCES public.client(id);


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

