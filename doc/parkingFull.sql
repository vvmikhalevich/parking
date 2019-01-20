--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.15
-- Dumped by pg_dump version 9.5.5

-- Started on 2019-01-21 01:02:03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2226 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 182 (class 1259 OID 17483)
-- Name: brand; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE brand (
    id integer NOT NULL,
    name character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE brand OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 17481)
-- Name: brand_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE brand_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE brand_id_seq OWNER TO postgres;

--
-- TOC entry 2227 (class 0 OID 0)
-- Dependencies: 181
-- Name: brand_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE brand_id_seq OWNED BY brand.id;


--
-- TOC entry 186 (class 1259 OID 17505)
-- Name: car; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE car (
    id integer NOT NULL,
    number character varying NOT NULL,
    model_id integer NOT NULL,
    user_account_id integer NOT NULL,
    tariff_id integer NOT NULL,
    foto_id integer NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE car OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 17503)
-- Name: car_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE car_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE car_id_seq OWNER TO postgres;

--
-- TOC entry 2228 (class 0 OID 0)
-- Dependencies: 185
-- Name: car_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE car_id_seq OWNED BY car.id;


--
-- TOC entry 198 (class 1259 OID 17571)
-- Name: event; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE event (
    id integer NOT NULL,
    place_id integer NOT NULL,
    car_id integer NOT NULL,
    time_start timestamp without time zone NOT NULL,
    time_end timestamp without time zone,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE event OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 17569)
-- Name: event_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE event_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE event_id_seq OWNER TO postgres;

--
-- TOC entry 2229 (class 0 OID 0)
-- Dependencies: 197
-- Name: event_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE event_id_seq OWNED BY event.id;


--
-- TOC entry 188 (class 1259 OID 17516)
-- Name: foto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE foto (
    id integer NOT NULL,
    link character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE foto OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 17514)
-- Name: foto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE foto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE foto_id_seq OWNER TO postgres;

--
-- TOC entry 2230 (class 0 OID 0)
-- Dependencies: 187
-- Name: foto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE foto_id_seq OWNED BY foto.id;


--
-- TOC entry 184 (class 1259 OID 17494)
-- Name: model; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE model (
    id integer NOT NULL,
    name character varying NOT NULL,
    brand_id integer NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE model OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 17492)
-- Name: model_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE model_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE model_id_seq OWNER TO postgres;

--
-- TOC entry 2231 (class 0 OID 0)
-- Dependencies: 183
-- Name: model_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE model_id_seq OWNED BY model.id;


--
-- TOC entry 194 (class 1259 OID 17549)
-- Name: parking; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE parking (
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


ALTER TABLE parking OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 17547)
-- Name: parking_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE parking_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE parking_id_seq OWNER TO postgres;

--
-- TOC entry 2232 (class 0 OID 0)
-- Dependencies: 193
-- Name: parking_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE parking_id_seq OWNED BY parking.id;


--
-- TOC entry 196 (class 1259 OID 17560)
-- Name: place; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE place (
    id integer NOT NULL,
    name character varying NOT NULL,
    parking_id integer NOT NULL,
    car_id integer,
    user_account_id bigint,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE place OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 17558)
-- Name: place_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE place_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE place_id_seq OWNER TO postgres;

--
-- TOC entry 2233 (class 0 OID 0)
-- Dependencies: 195
-- Name: place_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE place_id_seq OWNED BY place.id;


--
-- TOC entry 200 (class 1259 OID 17579)
-- Name: tariff; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tariff (
    id integer NOT NULL,
    name character varying NOT NULL,
    cost_per_day numeric NOT NULL,
    status character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE tariff OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 17577)
-- Name: tariff_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tariff_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tariff_id_seq OWNER TO postgres;

--
-- TOC entry 2234 (class 0 OID 0)
-- Dependencies: 199
-- Name: tariff_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tariff_id_seq OWNED BY tariff.id;


--
-- TOC entry 192 (class 1259 OID 17538)
-- Name: transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE transaction (
    id integer NOT NULL,
    user_account_id integer NOT NULL,
    value numeric NOT NULL,
    description character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE transaction OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 17536)
-- Name: transaction_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE transaction_id_seq OWNER TO postgres;

--
-- TOC entry 2235 (class 0 OID 0)
-- Dependencies: 191
-- Name: transaction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE transaction_id_seq OWNED BY transaction.id;


--
-- TOC entry 190 (class 1259 OID 17527)
-- Name: user_account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_account (
    id integer NOT NULL,
    first_name character varying NOT NULL,
    last_name character varying NOT NULL,
    role character varying NOT NULL,
    email character varying NOT NULL,
    password character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE user_account OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 17525)
-- Name: user_account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_account_id_seq OWNER TO postgres;

--
-- TOC entry 2236 (class 0 OID 0)
-- Dependencies: 189
-- Name: user_account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_account_id_seq OWNED BY user_account.id;


--
-- TOC entry 2044 (class 2604 OID 17486)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY brand ALTER COLUMN id SET DEFAULT nextval('brand_id_seq'::regclass);


--
-- TOC entry 2046 (class 2604 OID 17508)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY car ALTER COLUMN id SET DEFAULT nextval('car_id_seq'::regclass);


--
-- TOC entry 2052 (class 2604 OID 17574)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY event ALTER COLUMN id SET DEFAULT nextval('event_id_seq'::regclass);


--
-- TOC entry 2047 (class 2604 OID 17519)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY foto ALTER COLUMN id SET DEFAULT nextval('foto_id_seq'::regclass);


--
-- TOC entry 2045 (class 2604 OID 17497)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY model ALTER COLUMN id SET DEFAULT nextval('model_id_seq'::regclass);


--
-- TOC entry 2050 (class 2604 OID 17552)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY parking ALTER COLUMN id SET DEFAULT nextval('parking_id_seq'::regclass);


--
-- TOC entry 2051 (class 2604 OID 17563)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY place ALTER COLUMN id SET DEFAULT nextval('place_id_seq'::regclass);


--
-- TOC entry 2053 (class 2604 OID 17582)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tariff ALTER COLUMN id SET DEFAULT nextval('tariff_id_seq'::regclass);


--
-- TOC entry 2049 (class 2604 OID 17541)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY transaction ALTER COLUMN id SET DEFAULT nextval('transaction_id_seq'::regclass);


--
-- TOC entry 2048 (class 2604 OID 17530)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_account ALTER COLUMN id SET DEFAULT nextval('user_account_id_seq'::regclass);


--
-- TOC entry 2200 (class 0 OID 17483)
-- Dependencies: 182
-- Data for Name: brand; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO brand (id, name, created, updated) VALUES (1, 'Audi', '2019-01-20 22:39:08.308', '2019-01-20 22:39:08.308');
INSERT INTO brand (id, name, created, updated) VALUES (2, 'Opel', '2019-01-20 22:39:21.168', '2019-01-20 22:39:21.168');
INSERT INTO brand (id, name, created, updated) VALUES (3, 'Kia', '2019-01-20 22:39:26.255', '2019-01-20 22:39:26.255');
INSERT INTO brand (id, name, created, updated) VALUES (4, 'Jaguar', '2019-01-20 22:39:30.855', '2019-01-20 22:39:30.855');
INSERT INTO brand (id, name, created, updated) VALUES (5, 'BMW', '2019-01-20 22:39:41.287', '2019-01-20 22:39:41.287');
INSERT INTO brand (id, name, created, updated) VALUES (6, 'Smart', '2019-01-20 22:40:13.046', '2019-01-20 22:40:13.046');


--
-- TOC entry 2237 (class 0 OID 0)
-- Dependencies: 181
-- Name: brand_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('brand_id_seq', 6, true);


--
-- TOC entry 2204 (class 0 OID 17505)
-- Dependencies: 186
-- Data for Name: car; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO car (id, number, model_id, user_account_id, tariff_id, foto_id, created, updated) VALUES (3, '4454 EP-4', 3, 2, 2, 3, '2019-01-20 23:39:07.53', '2019-01-20 23:39:07.53');
INSERT INTO car (id, number, model_id, user_account_id, tariff_id, foto_id, created, updated) VALUES (4, '2019 BI-4', 2, 1, 3, 4, '2019-01-21 00:47:22.991', '2019-01-21 00:47:22.991');


--
-- TOC entry 2238 (class 0 OID 0)
-- Dependencies: 185
-- Name: car_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('car_id_seq', 4, true);


--
-- TOC entry 2216 (class 0 OID 17571)
-- Dependencies: 198
-- Data for Name: event; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2239 (class 0 OID 0)
-- Dependencies: 197
-- Name: event_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('event_id_seq', 1, true);


--
-- TOC entry 2206 (class 0 OID 17516)
-- Dependencies: 188
-- Data for Name: foto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO foto (id, link, created, updated) VALUES (3, 'e3859c5c-6ca3-4f0c-95fc-a0bc1c46d032', '2019-01-20 23:39:07.528', '2019-01-20 23:39:07.528');
INSERT INTO foto (id, link, created, updated) VALUES (4, '4ed46159-9a12-4dc5-89f0-904a2297420b', '2019-01-21 00:47:22.988', '2019-01-21 00:47:22.988');


--
-- TOC entry 2240 (class 0 OID 0)
-- Dependencies: 187
-- Name: foto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('foto_id_seq', 4, true);


--
-- TOC entry 2202 (class 0 OID 17494)
-- Dependencies: 184
-- Data for Name: model; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO model (id, name, brand_id, created, updated) VALUES (1, 'A6', 1, '2019-01-20 22:41:18.98', '2019-01-20 22:41:18.98');
INSERT INTO model (id, name, brand_id, created, updated) VALUES (2, 'Omega B', 2, '2019-01-20 22:41:26.364', '2019-01-20 22:41:26.364');
INSERT INTO model (id, name, brand_id, created, updated) VALUES (3, 'Fortwo', 6, '2019-01-20 22:41:45.987', '2019-01-20 22:41:45.987');
INSERT INTO model (id, name, brand_id, created, updated) VALUES (4, 'Forfour', 1, '2019-01-20 22:41:53.433', '2019-01-20 22:41:53.433');
INSERT INTO model (id, name, brand_id, created, updated) VALUES (5, 'Sorento', 3, '2019-01-20 22:42:03.302', '2019-01-20 22:42:03.302');
INSERT INTO model (id, name, brand_id, created, updated) VALUES (6, 'Sportage', 3, '2019-01-20 22:42:11.707', '2019-01-20 22:42:11.707');


--
-- TOC entry 2241 (class 0 OID 0)
-- Dependencies: 183
-- Name: model_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('model_id_seq', 6, true);


--
-- TOC entry 2212 (class 0 OID 17549)
-- Dependencies: 194
-- Data for Name: parking; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO parking (id, name, adress, width, length, cost_per_day, status, created, updated) VALUES (1, 'Central', 'Grodno, BLK 3', 5, 8, 3, 'enable', '2019-01-20 23:40:17.069', '2019-01-21 00:45:25.42');


--
-- TOC entry 2242 (class 0 OID 0)
-- Dependencies: 193
-- Name: parking_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('parking_id_seq', 1, true);


--
-- TOC entry 2214 (class 0 OID 17560)
-- Dependencies: 196
-- Data for Name: place; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (4, '1_2', 1, NULL, NULL, '2019-01-21 00:45:25.362', '2019-01-21 00:45:25.362');
INSERT INTO place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (5, '3_2', 1, NULL, NULL, '2019-01-21 00:45:25.367', '2019-01-21 00:45:25.367');
INSERT INTO place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (6, '4_2', 1, NULL, NULL, '2019-01-21 00:45:25.378', '2019-01-21 00:45:25.378');
INSERT INTO place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (7, '6_2', 1, NULL, NULL, '2019-01-21 00:45:25.381', '2019-01-21 00:45:25.381');
INSERT INTO place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (8, '7_2', 1, NULL, NULL, '2019-01-21 00:45:25.383', '2019-01-21 00:45:25.383');
INSERT INTO place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (9, '1_3', 1, NULL, NULL, '2019-01-21 00:45:25.386', '2019-01-21 00:45:25.386');
INSERT INTO place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (10, '3_3', 1, NULL, NULL, '2019-01-21 00:45:25.39', '2019-01-21 00:45:25.39');
INSERT INTO place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (11, '4_3', 1, NULL, NULL, '2019-01-21 00:45:25.393', '2019-01-21 00:45:25.393');
INSERT INTO place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (12, '6_3', 1, NULL, NULL, '2019-01-21 00:45:25.396', '2019-01-21 00:45:25.396');
INSERT INTO place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (14, '1_4', 1, NULL, NULL, '2019-01-21 00:45:25.403', '2019-01-21 00:45:25.403');
INSERT INTO place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (15, '3_4', 1, NULL, NULL, '2019-01-21 00:45:25.406', '2019-01-21 00:45:25.406');
INSERT INTO place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (16, '4_4', 1, NULL, NULL, '2019-01-21 00:45:25.409', '2019-01-21 00:45:25.409');
INSERT INTO place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (17, '6_4', 1, NULL, NULL, '2019-01-21 00:45:25.412', '2019-01-21 00:45:25.412');
INSERT INTO place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (18, '7_4', 1, NULL, NULL, '2019-01-21 00:45:25.415', '2019-01-21 00:45:25.415');
INSERT INTO place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (13, '7_3', 1, 4, 1, '2019-01-21 00:45:25.399', '2019-01-21 00:49:56.708');
INSERT INTO place (id, name, parking_id, car_id, user_account_id, created, updated) VALUES (19, '1_5', 1, 3, 2, '2019-01-21 00:45:25.417', '2019-01-21 00:50:30.482');


--
-- TOC entry 2243 (class 0 OID 0)
-- Dependencies: 195
-- Name: place_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('place_id_seq', 19, true);


--
-- TOC entry 2218 (class 0 OID 17579)
-- Dependencies: 200
-- Data for Name: tariff; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tariff (id, name, cost_per_day, status, created, updated) VALUES (1, 'Day', 3, 'enable', '2019-01-20 22:45:24.426', '2019-01-20 22:45:24.426');
INSERT INTO tariff (id, name, cost_per_day, status, created, updated) VALUES (2, 'Week', 2, 'enable', '2019-01-20 22:45:33.391', '2019-01-20 22:45:33.391');
INSERT INTO tariff (id, name, cost_per_day, status, created, updated) VALUES (3, 'Month', 1, 'enable', '2019-01-20 22:45:44.122', '2019-01-20 22:45:51.494');


--
-- TOC entry 2244 (class 0 OID 0)
-- Dependencies: 199
-- Name: tariff_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tariff_id_seq', 3, true);


--
-- TOC entry 2210 (class 0 OID 17538)
-- Dependencies: 192
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2245 (class 0 OID 0)
-- Dependencies: 191
-- Name: transaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('transaction_id_seq', 1, true);


--
-- TOC entry 2208 (class 0 OID 17527)
-- Dependencies: 190
-- Data for Name: user_account; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO user_account (id, first_name, last_name, role, email, password, created, updated) VALUES (1, 'VIKTAR', 'MIKHALEVICH', 'admin', 'admin', 'nimda', '2019-01-20 22:40:40.796', '2019-01-20 22:40:40.796');
INSERT INTO user_account (id, first_name, last_name, role, email, password, created, updated) VALUES (2, 'LIUDMILA', 'KUKEL', 'user', 'kukel', 'kukel', '2019-01-20 22:41:06.813', '2019-01-20 22:41:06.813');


--
-- TOC entry 2246 (class 0 OID 0)
-- Dependencies: 189
-- Name: user_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_account_id_seq', 2, true);


--
-- TOC entry 2055 (class 2606 OID 17491)
-- Name: brand_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY brand
    ADD CONSTRAINT brand_pk PRIMARY KEY (id);


--
-- TOC entry 2059 (class 2606 OID 17513)
-- Name: car_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY car
    ADD CONSTRAINT car_pk PRIMARY KEY (id);


--
-- TOC entry 2071 (class 2606 OID 17576)
-- Name: event_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY event
    ADD CONSTRAINT event_pk PRIMARY KEY (id);


--
-- TOC entry 2061 (class 2606 OID 17524)
-- Name: foto_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY foto
    ADD CONSTRAINT foto_pk PRIMARY KEY (id);


--
-- TOC entry 2057 (class 2606 OID 17502)
-- Name: model_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY model
    ADD CONSTRAINT model_pk PRIMARY KEY (id);


--
-- TOC entry 2067 (class 2606 OID 17557)
-- Name: parking_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY parking
    ADD CONSTRAINT parking_pk PRIMARY KEY (id);


--
-- TOC entry 2069 (class 2606 OID 17568)
-- Name: place_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY place
    ADD CONSTRAINT place_pk PRIMARY KEY (id);


--
-- TOC entry 2073 (class 2606 OID 17587)
-- Name: tariff_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tariff
    ADD CONSTRAINT tariff_pk PRIMARY KEY (id);


--
-- TOC entry 2065 (class 2606 OID 17546)
-- Name: transaction_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY transaction
    ADD CONSTRAINT transaction_pk PRIMARY KEY (id);


--
-- TOC entry 2063 (class 2606 OID 17535)
-- Name: user_account_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_account
    ADD CONSTRAINT user_account_pk PRIMARY KEY (id);


--
-- TOC entry 2075 (class 2606 OID 17593)
-- Name: car_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY car
    ADD CONSTRAINT car_fk0 FOREIGN KEY (model_id) REFERENCES model(id);


--
-- TOC entry 2076 (class 2606 OID 17598)
-- Name: car_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY car
    ADD CONSTRAINT car_fk1 FOREIGN KEY (user_account_id) REFERENCES user_account(id);


--
-- TOC entry 2077 (class 2606 OID 17603)
-- Name: car_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY car
    ADD CONSTRAINT car_fk2 FOREIGN KEY (tariff_id) REFERENCES tariff(id);


--
-- TOC entry 2078 (class 2606 OID 17608)
-- Name: car_fk3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY car
    ADD CONSTRAINT car_fk3 FOREIGN KEY (foto_id) REFERENCES foto(id);


--
-- TOC entry 2083 (class 2606 OID 17633)
-- Name: event_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY event
    ADD CONSTRAINT event_fk0 FOREIGN KEY (place_id) REFERENCES place(id);


--
-- TOC entry 2084 (class 2606 OID 17638)
-- Name: event_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY event
    ADD CONSTRAINT event_fk1 FOREIGN KEY (car_id) REFERENCES car(id);


--
-- TOC entry 2074 (class 2606 OID 17588)
-- Name: model_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY model
    ADD CONSTRAINT model_fk0 FOREIGN KEY (brand_id) REFERENCES brand(id);


--
-- TOC entry 2080 (class 2606 OID 17618)
-- Name: place_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY place
    ADD CONSTRAINT place_fk0 FOREIGN KEY (parking_id) REFERENCES parking(id);


--
-- TOC entry 2081 (class 2606 OID 17623)
-- Name: place_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY place
    ADD CONSTRAINT place_fk1 FOREIGN KEY (car_id) REFERENCES car(id);


--
-- TOC entry 2082 (class 2606 OID 17628)
-- Name: place_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY place
    ADD CONSTRAINT place_fk2 FOREIGN KEY (user_account_id) REFERENCES user_account(id);


--
-- TOC entry 2079 (class 2606 OID 17613)
-- Name: transaction_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY transaction
    ADD CONSTRAINT transaction_fk0 FOREIGN KEY (user_account_id) REFERENCES user_account(id);


--
-- TOC entry 2225 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2019-01-21 01:02:04

--
-- PostgreSQL database dump complete
--

