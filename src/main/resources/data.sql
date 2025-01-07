--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

-- Started on 2024-05-27 01:06:12

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

ALTER TABLE IF EXISTS ONLY public.team DROP CONSTRAINT IF EXISTS fkum5ejph85lolunj2rmm19kqc;
ALTER TABLE IF EXISTS ONLY public.web_user_roles DROP CONSTRAINT IF EXISTS fkt2rw5y5435mvydug75kcv4h7e;
ALTER TABLE IF EXISTS ONLY public.criteria_mark DROP CONSTRAINT IF EXISTS fksf3wkbshhmbo1rhim3ch6s084;
ALTER TABLE IF EXISTS ONLY public.criteria DROP CONSTRAINT IF EXISTS fkmcosq0mx5b4eaq5429hmh75hy;
ALTER TABLE IF EXISTS ONLY public.notification DROP CONSTRAINT IF EXISTS fkjo54fjtqjy0ieakuuvigiliwe;
ALTER TABLE IF EXISTS ONLY public.web_user_roles DROP CONSTRAINT IF EXISTS fkh4au0i7bw8aaumlbn8sas91vj;
ALTER TABLE IF EXISTS ONLY public.user_in_team_record DROP CONSTRAINT IF EXISTS fk802nvhkk3sx2hm44cgay60951;
ALTER TABLE IF EXISTS ONLY public.criteria_mark DROP CONSTRAINT IF EXISTS fk788tkkcv7cy7mgjpki08jp46n;
ALTER TABLE IF EXISTS ONLY public.user_in_team DROP CONSTRAINT IF EXISTS fk3y4r8uudjd07svjr7sgg939dt;
ALTER TABLE IF EXISTS ONLY public.user_in_team DROP CONSTRAINT IF EXISTS fk2yeyxuinoai2kw796s8gxnq2h;
ALTER TABLE IF EXISTS ONLY public.team_record DROP CONSTRAINT IF EXISTS fk2l2c0dg5mcnm83ckkrpsdcpmc;
ALTER TABLE IF EXISTS ONLY public.web_user DROP CONSTRAINT IF EXISTS web_user_pkey;
ALTER TABLE IF EXISTS ONLY public.user_in_team_record DROP CONSTRAINT IF EXISTS user_in_team_record_pkey;
ALTER TABLE IF EXISTS ONLY public.user_in_team DROP CONSTRAINT IF EXISTS user_in_team_pkey;
ALTER TABLE IF EXISTS ONLY public.team_record DROP CONSTRAINT IF EXISTS team_record_pkey;
ALTER TABLE IF EXISTS ONLY public.team DROP CONSTRAINT IF EXISTS team_pkey;
ALTER TABLE IF EXISTS ONLY public.role DROP CONSTRAINT IF EXISTS role_pkey;
ALTER TABLE IF EXISTS ONLY public.notification DROP CONSTRAINT IF EXISTS notification_pkey;
ALTER TABLE IF EXISTS ONLY public.criteria DROP CONSTRAINT IF EXISTS criteria_pkey;
ALTER TABLE IF EXISTS ONLY public.criteria_mark DROP CONSTRAINT IF EXISTS criteria_mark_pkey;
ALTER TABLE IF EXISTS ONLY public.conference DROP CONSTRAINT IF EXISTS conference_pkey;
DROP SEQUENCE IF EXISTS public.web_user_seq;
DROP TABLE IF EXISTS public.web_user_roles;
DROP TABLE IF EXISTS public.web_user;
DROP SEQUENCE IF EXISTS public.user_in_team_record_seq;
DROP TABLE IF EXISTS public.user_in_team_record;
DROP TABLE IF EXISTS public.user_in_team;
DROP SEQUENCE IF EXISTS public.team_seq;
DROP SEQUENCE IF EXISTS public.team_record_seq;
DROP TABLE IF EXISTS public.team_record;
DROP TABLE IF EXISTS public.team;
DROP SEQUENCE IF EXISTS public.role_seq;
DROP TABLE IF EXISTS public.role;
DROP SEQUENCE IF EXISTS public.notification_seq;
DROP TABLE IF EXISTS public.notification;
DROP SEQUENCE IF EXISTS public.criteria_seq;
DROP SEQUENCE IF EXISTS public.criteria_mark_seq;
DROP TABLE IF EXISTS public.criteria_mark;
DROP TABLE IF EXISTS public.criteria;
DROP SEQUENCE IF EXISTS public.conference_seq;
DROP TABLE IF EXISTS public.conference;
SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 214 (class 1259 OID 47321)
-- Name: conference; Type: TABLE; Schema: public; Owner: ISU
--

CREATE TABLE public.conference (
    id bigint NOT NULL,
    registration_closing timestamp(6) without time zone,
    description text,
    name character varying(255)
);


ALTER TABLE public.conference OWNER TO "ISU";

--
-- TOC entry 215 (class 1259 OID 47326)
-- Name: conference_seq; Type: SEQUENCE; Schema: public; Owner: ISU
--

CREATE SEQUENCE public.conference_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.conference_seq OWNER TO "ISU";

--
-- TOC entry 216 (class 1259 OID 47327)
-- Name: criteria; Type: TABLE; Schema: public; Owner: ISU
--

CREATE TABLE public.criteria (
    conference_id bigint,
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.criteria OWNER TO "ISU";

--
-- TOC entry 217 (class 1259 OID 47330)
-- Name: criteria_mark; Type: TABLE; Schema: public; Owner: ISU
--

CREATE TABLE public.criteria_mark (
    mark smallint NOT NULL,
    criteria_id bigint,
    id bigint NOT NULL,
    team_record_id bigint
);


ALTER TABLE public.criteria_mark OWNER TO "ISU";

--
-- TOC entry 218 (class 1259 OID 47333)
-- Name: criteria_mark_seq; Type: SEQUENCE; Schema: public; Owner: ISU
--

CREATE SEQUENCE public.criteria_mark_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.criteria_mark_seq OWNER TO "ISU";

--
-- TOC entry 219 (class 1259 OID 47334)
-- Name: criteria_seq; Type: SEQUENCE; Schema: public; Owner: ISU
--

CREATE SEQUENCE public.criteria_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.criteria_seq OWNER TO "ISU";

--
-- TOC entry 220 (class 1259 OID 47335)
-- Name: notification; Type: TABLE; Schema: public; Owner: ISU
--

CREATE TABLE public.notification (
    id bigint NOT NULL,
    user_id bigint,
    message character varying(255),
    url character varying(255)
);


ALTER TABLE public.notification OWNER TO "ISU";

--
-- TOC entry 221 (class 1259 OID 47340)
-- Name: notification_seq; Type: SEQUENCE; Schema: public; Owner: ISU
--

CREATE SEQUENCE public.notification_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.notification_seq OWNER TO "ISU";

--
-- TOC entry 222 (class 1259 OID 47341)
-- Name: role; Type: TABLE; Schema: public; Owner: ISU
--

CREATE TABLE public.role (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.role OWNER TO "ISU";

--
-- TOC entry 223 (class 1259 OID 47344)
-- Name: role_seq; Type: SEQUENCE; Schema: public; Owner: ISU
--

CREATE SEQUENCE public.role_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_seq OWNER TO "ISU";

--
-- TOC entry 224 (class 1259 OID 47345)
-- Name: team; Type: TABLE; Schema: public; Owner: ISU
--

CREATE TABLE public.team (
    id bigint NOT NULL,
    leader_id bigint,
    name character varying(255)
);


ALTER TABLE public.team OWNER TO "ISU";

--
-- TOC entry 225 (class 1259 OID 47348)
-- Name: team_record; Type: TABLE; Schema: public; Owner: ISU
--

CREATE TABLE public.team_record (
    conference_id bigint,
    id bigint NOT NULL,
    leader_id bigint,
    name character varying(255)
);


ALTER TABLE public.team_record OWNER TO "ISU";

--
-- TOC entry 226 (class 1259 OID 47351)
-- Name: team_record_seq; Type: SEQUENCE; Schema: public; Owner: ISU
--

CREATE SEQUENCE public.team_record_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.team_record_seq OWNER TO "ISU";

--
-- TOC entry 227 (class 1259 OID 47352)
-- Name: team_seq; Type: SEQUENCE; Schema: public; Owner: ISU
--

CREATE SEQUENCE public.team_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.team_seq OWNER TO "ISU";

--
-- TOC entry 228 (class 1259 OID 47353)
-- Name: user_in_team; Type: TABLE; Schema: public; Owner: ISU
--

CREATE TABLE public.user_in_team (
    is_active boolean,
    team_id bigint NOT NULL,
    user_id bigint NOT NULL,
    activation_code character varying(255)
);


ALTER TABLE public.user_in_team OWNER TO "ISU";

--
-- TOC entry 229 (class 1259 OID 47356)
-- Name: user_in_team_record; Type: TABLE; Schema: public; Owner: ISU
--

CREATE TABLE public.user_in_team_record (
    id bigint NOT NULL,
    team_record_id bigint,
    user_id bigint,
    username character varying(255)
);


ALTER TABLE public.user_in_team_record OWNER TO "ISU";

--
-- TOC entry 230 (class 1259 OID 47359)
-- Name: user_in_team_record_seq; Type: SEQUENCE; Schema: public; Owner: ISU
--

CREATE SEQUENCE public.user_in_team_record_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_in_team_record_seq OWNER TO "ISU";

--
-- TOC entry 231 (class 1259 OID 47360)
-- Name: web_user; Type: TABLE; Schema: public; Owner: ISU
--

CREATE TABLE public.web_user (
    id bigint NOT NULL,
    email character varying(255),
    first_name character varying(255),
    password character varying(255),
    profile_img character varying(255),
    second_name character varying(255),
    username character varying(255)
);


ALTER TABLE public.web_user OWNER TO "ISU";

--
-- TOC entry 232 (class 1259 OID 47365)
-- Name: web_user_roles; Type: TABLE; Schema: public; Owner: ISU
--

CREATE TABLE public.web_user_roles (
    roles_id bigint NOT NULL,
    users_id bigint NOT NULL
);


ALTER TABLE public.web_user_roles OWNER TO "ISU";

--
-- TOC entry 233 (class 1259 OID 47368)
-- Name: web_user_seq; Type: SEQUENCE; Schema: public; Owner: ISU
--

CREATE SEQUENCE public.web_user_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.web_user_seq OWNER TO "ISU";

--
-- TOC entry 3394 (class 0 OID 47321)
-- Dependencies: 214
-- Data for Name: conference; Type: TABLE DATA; Schema: public; Owner: ISU
--

INSERT INTO public.conference (id, registration_closing, description, name) VALUES (2552, '2024-05-21 06:40:00', 'Дорогие студенты!

Приглашаем вас принять участие в командном соревновании по теории чисел, которое пройдет в аудитории 318.

На соревновании вас ждут увлекательные задачи и интересные математические головоломки, которые позволят вам проявить свои знания и навыки в этой области.

Не упустите возможность испытать свои силы, побороться за звание лучшей математической команды и, конечно же, получить ценные призы!

Ждем вас на соревновании и желаем удачи!', 'Командное соревнование по теории чисел') ON CONFLICT DO NOTHING;
INSERT INTO public.conference (id, registration_closing, description, name) VALUES (2502, '2024-05-31 20:30:00', 'Уважаемые студенты!

Приглашаем вас принять участие в олимпиаде по дифференциальным уравнениям.

Олимпиада представляет собой уникальную возможность продемонстрировать свои знания и навыки в области дифференциальных уравнений, а также побороться за ценные призы и сертификаты.

Участвовать могут студенты всех курсов и специальностей, интересующиеся математикой и физикой.

Не упустите шанс проявить себя и показать свой профессионализм. Ждем вас на олимпиаде!', 'Олимпиада по дифференциальным уравнениям') ON CONFLICT DO NOTHING;
INSERT INTO public.conference (id, registration_closing, description, name) VALUES (2504, '2024-06-02 18:30:00', 'Уважаемые студенты!

Мы рады пригласить вас принять участие в олимпиаде по теории вероятностей. Это мероприятие предоставляет уникальную возможность продемонстрировать и углубить ваши знания в области, которая играет ключевую роль в современной математике и приложениях.', 'Олимпиада по теории вероятностей') ON CONFLICT DO NOTHING;
INSERT INTO public.conference (id, registration_closing, description, name) VALUES (2505, '2024-05-20 22:50:00', 'Уважаемые студенты!

Мы рады пригласить вас принять участие в олимпиаде по теории графов. Это мероприятие предоставляет уникальную возможность продемонстрировать и углубить ваши знания в области, которая играет важную роль в различных областях математики, информатики, теории сетей и других приложениях.', 'Олимпиада по теории графов') ON CONFLICT DO NOTHING;
INSERT INTO public.conference (id, registration_closing, description, name) VALUES (2506, '2024-05-20 22:50:00', 'Уважаемые студенты!

Мы рады пригласить вас принять участие в олимпиаде по анализу данных. Это мероприятие призвано продемонстрировать ваше умение работать с данными, применять различные методы анализа и извлечения информации из них, а также решать практические задачи на их основе.', 'Олимпиада по анализу данных') ON CONFLICT DO NOTHING;
INSERT INTO public.conference (id, registration_closing, description, name) VALUES (2503, '2024-05-11 15:30:00', 'Уважаемые студенты!

Приглашаем вас принять участие в интересной и познавательной конференции по булевым функциям. Это событие соберет ведущих специалистов и исследователей в области логики и теории вычислений.

На конференции вы сможете услышать доклады о самых актуальных исследованиях в области булевых функций, обсудить с коллегами интересные темы и поделиться своими идеями и открытиями.

Мы уверены, что участие в конференции поможет вам расширить свои знания и навыки, а также найти новых друзей и единомышленников.

Ждем вас на конференции и надеемся на продуктивное и интересное время проведенное вместе!', 'Конференция "Планета булевых функций"') ON CONFLICT DO NOTHING;
INSERT INTO public.conference (id, registration_closing, description, name) VALUES (2553, '2024-05-21 06:40:00', 'Уважаемые студенты,

Приглашаем вас принять участие во Всероссийской олимпиаде по спортивному программированию! Это уникальное соревнование, которое позволит вам проявить свои навыки и способности в области программирования, а также поучаствовать в увлекательных задачах и испытаниях.

Олимпиада пройдет в формате онлайн, что позволит участвовать в ней из любой точки мира. Вы сможете померяться силами с другими студентами, проявить свою выдумку и профессионализм в решении сложных задач.', 'Олимпиада по спортивному программированию') ON CONFLICT DO NOTHING;


--
-- TOC entry 3396 (class 0 OID 47327)
-- Dependencies: 216
-- Data for Name: criteria; Type: TABLE DATA; Schema: public; Owner: ISU
--

INSERT INTO public.criteria (conference_id, id, name) VALUES (2502, 902, 'Общая оценка') ON CONFLICT DO NOTHING;
INSERT INTO public.criteria (conference_id, id, name) VALUES (2503, 903, 'Общая оценка') ON CONFLICT DO NOTHING;
INSERT INTO public.criteria (conference_id, id, name) VALUES (2504, 904, 'Анализ и интерпретация результатов') ON CONFLICT DO NOTHING;
INSERT INTO public.criteria (conference_id, id, name) VALUES (2504, 905, 'Решение задач') ON CONFLICT DO NOTHING;
INSERT INTO public.criteria (conference_id, id, name) VALUES (2504, 906, 'Понимание основных концепций') ON CONFLICT DO NOTHING;
INSERT INTO public.criteria (conference_id, id, name) VALUES (2505, 907, 'Общая оценка') ON CONFLICT DO NOTHING;
INSERT INTO public.criteria (conference_id, id, name) VALUES (2506, 908, 'Общая оценка') ON CONFLICT DO NOTHING;
INSERT INTO public.criteria (conference_id, id, name) VALUES (2552, 952, 'Общая оценка') ON CONFLICT DO NOTHING;
INSERT INTO public.criteria (conference_id, id, name) VALUES (2553, 953, 'Общая оценка') ON CONFLICT DO NOTHING;


--
-- TOC entry 3397 (class 0 OID 47330)
-- Dependencies: 217
-- Data for Name: criteria_mark; Type: TABLE DATA; Schema: public; Owner: ISU
--

INSERT INTO public.criteria_mark (mark, criteria_id, id, team_record_id) VALUES (100, 904, 708, 1154) ON CONFLICT DO NOTHING;
INSERT INTO public.criteria_mark (mark, criteria_id, id, team_record_id) VALUES (60, 905, 709, 1154) ON CONFLICT DO NOTHING;
INSERT INTO public.criteria_mark (mark, criteria_id, id, team_record_id) VALUES (70, 906, 710, 1154) ON CONFLICT DO NOTHING;
INSERT INTO public.criteria_mark (mark, criteria_id, id, team_record_id) VALUES (100, 904, 702, 1152) ON CONFLICT DO NOTHING;
INSERT INTO public.criteria_mark (mark, criteria_id, id, team_record_id) VALUES (100, 905, 704, 1152) ON CONFLICT DO NOTHING;
INSERT INTO public.criteria_mark (mark, criteria_id, id, team_record_id) VALUES (100, 906, 703, 1152) ON CONFLICT DO NOTHING;


--
-- TOC entry 3400 (class 0 OID 47335)
-- Dependencies: 220
-- Data for Name: notification; Type: TABLE DATA; Schema: public; Owner: ISU
--



--
-- TOC entry 3402 (class 0 OID 47341)
-- Dependencies: 222
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: ISU
--

INSERT INTO public.role (id, name) VALUES (1, 'ROLE_USER') ON CONFLICT DO NOTHING;
INSERT INTO public.role (id, name) VALUES (2, 'ROLE_ADMIN') ON CONFLICT DO NOTHING;


--
-- TOC entry 3404 (class 0 OID 47345)
-- Dependencies: 224
-- Data for Name: team; Type: TABLE DATA; Schema: public; Owner: ISU
--

INSERT INTO public.team (id, leader_id, name) VALUES (5102, 1, 'Not the best team really') ON CONFLICT DO NOTHING;
INSERT INTO public.team (id, leader_id, name) VALUES (5153, 605, 'Losers') ON CONFLICT DO NOTHING;
INSERT INTO public.team (id, leader_id, name) VALUES (5154, 607, 'Teammates') ON CONFLICT DO NOTHING;


--
-- TOC entry 3405 (class 0 OID 47348)
-- Dependencies: 225
-- Data for Name: team_record; Type: TABLE DATA; Schema: public; Owner: ISU
--

INSERT INTO public.team_record (conference_id, id, leader_id, name) VALUES (2504, 1152, 605, 'Losers') ON CONFLICT DO NOTHING;
INSERT INTO public.team_record (conference_id, id, leader_id, name) VALUES (2504, 1154, 1, 'Not the best team really') ON CONFLICT DO NOTHING;


--
-- TOC entry 3408 (class 0 OID 47353)
-- Dependencies: 228
-- Data for Name: user_in_team; Type: TABLE DATA; Schema: public; Owner: ISU
--

INSERT INTO public.user_in_team (is_active, team_id, user_id, activation_code) VALUES (true, 5102, 1, NULL) ON CONFLICT DO NOTHING;
INSERT INTO public.user_in_team (is_active, team_id, user_id, activation_code) VALUES (true, 5102, 604, NULL) ON CONFLICT DO NOTHING;
INSERT INTO public.user_in_team (is_active, team_id, user_id, activation_code) VALUES (true, 5153, 605, NULL) ON CONFLICT DO NOTHING;
INSERT INTO public.user_in_team (is_active, team_id, user_id, activation_code) VALUES (true, 5154, 607, NULL) ON CONFLICT DO NOTHING;


--
-- TOC entry 3409 (class 0 OID 47356)
-- Dependencies: 229
-- Data for Name: user_in_team_record; Type: TABLE DATA; Schema: public; Owner: ISU
--

INSERT INTO public.user_in_team_record (id, team_record_id, user_id, username) VALUES (952, 1152, 605, 'Zinelyan') ON CONFLICT DO NOTHING;
INSERT INTO public.user_in_team_record (id, team_record_id, user_id, username) VALUES (954, 1154, 603, 'Qusho') ON CONFLICT DO NOTHING;
INSERT INTO public.user_in_team_record (id, team_record_id, user_id, username) VALUES (955, 1154, 1, 'Sick') ON CONFLICT DO NOTHING;
INSERT INTO public.user_in_team_record (id, team_record_id, user_id, username) VALUES (956, 1154, 604, 'Zarin') ON CONFLICT DO NOTHING;


--
-- TOC entry 3411 (class 0 OID 47360)
-- Dependencies: 231
-- Data for Name: web_user; Type: TABLE DATA; Schema: public; Owner: ISU
--

INSERT INTO public.web_user (id, email, first_name, password, profile_img, second_name, username) VALUES (602, 'Leeyna@mail.ru', 'Victoria', '$2a$10$wXUQOY1NE3mOWrbJtYDpnuOj5XRhHh1PKWX8d3jv6U.eF6n8tS/fi', NULL, 'Rice', 'Leeyna') ON CONFLICT DO NOTHING;
INSERT INTO public.web_user (id, email, first_name, password, profile_img, second_name, username) VALUES (605, 'Zinelyan@mail.ru', 'Eugene', '$2a$10$CWBxyRdqaLezrpI2teJCguBoP7.QuqLk5cKJb6l6oLoFPENF1bTxu', NULL, 'Johnson', 'Zinelyan') ON CONFLICT DO NOTHING;
INSERT INTO public.web_user (id, email, first_name, password, profile_img, second_name, username) VALUES (606, 'Zalimelar@mail.ru', 'Donald', '$2a$10$Lrq.bVKegsebLe4JF1CoyuRddtmDIi68hwRJIkl3DOysWci4oboSi', NULL, 'Mathis', 'Zalimelar') ON CONFLICT DO NOTHING;
INSERT INTO public.web_user (id, email, first_name, password, profile_img, second_name, username) VALUES (607, 'Zateonol@mail.ru', 'Robert', '$2a$10$1GXdk8QBNvXnGqhs56lLDu5YLmSqaFFjk8Xzvksz6xFZnt9HmUjKC', NULL, 'Carter', 'Zateonol') ON CONFLICT DO NOTHING;
INSERT INTO public.web_user (id, email, first_name, password, profile_img, second_name, username) VALUES (111, 'Admin@mail.ru', 'Admin', '$2a$10$C28pQUd/Zxds8R4sNH9Ubu88gEH7d.5Y4mckVMOf/LH4kj1rK4w9q', NULL, NULL, 'Admin') ON CONFLICT DO NOTHING;
INSERT INTO public.web_user (id, email, first_name, password, profile_img, second_name, username) VALUES (603, 'Qusho@mail.ru', 'Raymond', '$2a$10$zOcRH/rLwh6zh90wzJY6Su60z5RtS23PCbhyIX..TzjPCYRmq0MaG', NULL, 'Morrison', 'Qusho') ON CONFLICT DO NOTHING;
INSERT INTO public.web_user (id, email, first_name, password, profile_img, second_name, username) VALUES (1, 'Sick@mail.ru', 'Jason', '$2a$10$C28pQUd/Zxds8R4sNH9Ubu88gEH7d.5Y4mckVMOf/LH4kj1rK4w9q', NULL, 'Miller', 'Sick') ON CONFLICT DO NOTHING;
INSERT INTO public.web_user (id, email, first_name, password, profile_img, second_name, username) VALUES (604, 'Zarin@mail.ru', 'Thomas', '$2a$10$wileH8bmY7j.EV4agkYvs.MpGPC5PoyaoMsJfO/wEU75wxLJVgVNS', NULL, 'Smith', 'Zarin') ON CONFLICT DO NOTHING;


--
-- TOC entry 3412 (class 0 OID 47365)
-- Dependencies: 232
-- Data for Name: web_user_roles; Type: TABLE DATA; Schema: public; Owner: ISU
--

INSERT INTO public.web_user_roles (roles_id, users_id) VALUES (1, 1) ON CONFLICT DO NOTHING;
INSERT INTO public.web_user_roles (roles_id, users_id) VALUES (2, 111) ON CONFLICT DO NOTHING;
INSERT INTO public.web_user_roles (roles_id, users_id) VALUES (1, 602) ON CONFLICT DO NOTHING;
INSERT INTO public.web_user_roles (roles_id, users_id) VALUES (1, 603) ON CONFLICT DO NOTHING;
INSERT INTO public.web_user_roles (roles_id, users_id) VALUES (1, 604) ON CONFLICT DO NOTHING;
INSERT INTO public.web_user_roles (roles_id, users_id) VALUES (1, 605) ON CONFLICT DO NOTHING;
INSERT INTO public.web_user_roles (roles_id, users_id) VALUES (1, 606) ON CONFLICT DO NOTHING;
INSERT INTO public.web_user_roles (roles_id, users_id) VALUES (1, 607) ON CONFLICT DO NOTHING;


--
-- TOC entry 3419 (class 0 OID 0)
-- Dependencies: 215
-- Name: conference_seq; Type: SEQUENCE SET; Schema: public; Owner: ISU
--

SELECT pg_catalog.setval('public.conference_seq', 1, false);


--
-- TOC entry 3420 (class 0 OID 0)
-- Dependencies: 218
-- Name: criteria_mark_seq; Type: SEQUENCE SET; Schema: public; Owner: ISU
--

SELECT pg_catalog.setval('public.criteria_mark_seq', 1, false);


--
-- TOC entry 3421 (class 0 OID 0)
-- Dependencies: 219
-- Name: criteria_seq; Type: SEQUENCE SET; Schema: public; Owner: ISU
--

SELECT pg_catalog.setval('public.criteria_seq', 1, false);


--
-- TOC entry 3422 (class 0 OID 0)
-- Dependencies: 221
-- Name: notification_seq; Type: SEQUENCE SET; Schema: public; Owner: ISU
--

SELECT pg_catalog.setval('public.notification_seq', 1, false);


--
-- TOC entry 3423 (class 0 OID 0)
-- Dependencies: 223
-- Name: role_seq; Type: SEQUENCE SET; Schema: public; Owner: ISU
--

SELECT pg_catalog.setval('public.role_seq', 1, false);


--
-- TOC entry 3424 (class 0 OID 0)
-- Dependencies: 226
-- Name: team_record_seq; Type: SEQUENCE SET; Schema: public; Owner: ISU
--

SELECT pg_catalog.setval('public.team_record_seq', 1, false);


--
-- TOC entry 3425 (class 0 OID 0)
-- Dependencies: 227
-- Name: team_seq; Type: SEQUENCE SET; Schema: public; Owner: ISU
--

SELECT pg_catalog.setval('public.team_seq', 1, false);


--
-- TOC entry 3426 (class 0 OID 0)
-- Dependencies: 230
-- Name: user_in_team_record_seq; Type: SEQUENCE SET; Schema: public; Owner: ISU
--

SELECT pg_catalog.setval('public.user_in_team_record_seq', 1, false);


--
-- TOC entry 3427 (class 0 OID 0)
-- Dependencies: 233
-- Name: web_user_seq; Type: SEQUENCE SET; Schema: public; Owner: ISU
--

SELECT pg_catalog.setval('public.web_user_seq', 1, false);


--
-- TOC entry 3222 (class 2606 OID 47370)
-- Name: conference conference_pkey; Type: CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.conference
    ADD CONSTRAINT conference_pkey PRIMARY KEY (id);


--
-- TOC entry 3226 (class 2606 OID 47372)
-- Name: criteria_mark criteria_mark_pkey; Type: CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.criteria_mark
    ADD CONSTRAINT criteria_mark_pkey PRIMARY KEY (id);


--
-- TOC entry 3224 (class 2606 OID 47374)
-- Name: criteria criteria_pkey; Type: CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.criteria
    ADD CONSTRAINT criteria_pkey PRIMARY KEY (id);


--
-- TOC entry 3228 (class 2606 OID 47376)
-- Name: notification notification_pkey; Type: CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.notification
    ADD CONSTRAINT notification_pkey PRIMARY KEY (id);


--
-- TOC entry 3230 (class 2606 OID 47378)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- TOC entry 3232 (class 2606 OID 47380)
-- Name: team team_pkey; Type: CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.team
    ADD CONSTRAINT team_pkey PRIMARY KEY (id);


--
-- TOC entry 3234 (class 2606 OID 47382)
-- Name: team_record team_record_pkey; Type: CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.team_record
    ADD CONSTRAINT team_record_pkey PRIMARY KEY (id);


--
-- TOC entry 3236 (class 2606 OID 47384)
-- Name: user_in_team user_in_team_pkey; Type: CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.user_in_team
    ADD CONSTRAINT user_in_team_pkey PRIMARY KEY (team_id, user_id);


--
-- TOC entry 3238 (class 2606 OID 47386)
-- Name: user_in_team_record user_in_team_record_pkey; Type: CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.user_in_team_record
    ADD CONSTRAINT user_in_team_record_pkey PRIMARY KEY (id);


--
-- TOC entry 3240 (class 2606 OID 47388)
-- Name: web_user web_user_pkey; Type: CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.web_user
    ADD CONSTRAINT web_user_pkey PRIMARY KEY (id);


--
-- TOC entry 3246 (class 2606 OID 47389)
-- Name: team_record fk2l2c0dg5mcnm83ckkrpsdcpmc; Type: FK CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.team_record
    ADD CONSTRAINT fk2l2c0dg5mcnm83ckkrpsdcpmc FOREIGN KEY (conference_id) REFERENCES public.conference(id);


--
-- TOC entry 3247 (class 2606 OID 47394)
-- Name: user_in_team fk2yeyxuinoai2kw796s8gxnq2h; Type: FK CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.user_in_team
    ADD CONSTRAINT fk2yeyxuinoai2kw796s8gxnq2h FOREIGN KEY (user_id) REFERENCES public.web_user(id);


--
-- TOC entry 3248 (class 2606 OID 47399)
-- Name: user_in_team fk3y4r8uudjd07svjr7sgg939dt; Type: FK CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.user_in_team
    ADD CONSTRAINT fk3y4r8uudjd07svjr7sgg939dt FOREIGN KEY (team_id) REFERENCES public.team(id);


--
-- TOC entry 3242 (class 2606 OID 47404)
-- Name: criteria_mark fk788tkkcv7cy7mgjpki08jp46n; Type: FK CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.criteria_mark
    ADD CONSTRAINT fk788tkkcv7cy7mgjpki08jp46n FOREIGN KEY (team_record_id) REFERENCES public.team_record(id);


--
-- TOC entry 3249 (class 2606 OID 47409)
-- Name: user_in_team_record fk802nvhkk3sx2hm44cgay60951; Type: FK CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.user_in_team_record
    ADD CONSTRAINT fk802nvhkk3sx2hm44cgay60951 FOREIGN KEY (team_record_id) REFERENCES public.team_record(id);


--
-- TOC entry 3250 (class 2606 OID 47414)
-- Name: web_user_roles fkh4au0i7bw8aaumlbn8sas91vj; Type: FK CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.web_user_roles
    ADD CONSTRAINT fkh4au0i7bw8aaumlbn8sas91vj FOREIGN KEY (users_id) REFERENCES public.web_user(id);


--
-- TOC entry 3244 (class 2606 OID 47419)
-- Name: notification fkjo54fjtqjy0ieakuuvigiliwe; Type: FK CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.notification
    ADD CONSTRAINT fkjo54fjtqjy0ieakuuvigiliwe FOREIGN KEY (user_id) REFERENCES public.web_user(id);


--
-- TOC entry 3241 (class 2606 OID 47424)
-- Name: criteria fkmcosq0mx5b4eaq5429hmh75hy; Type: FK CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.criteria
    ADD CONSTRAINT fkmcosq0mx5b4eaq5429hmh75hy FOREIGN KEY (conference_id) REFERENCES public.conference(id);


--
-- TOC entry 3243 (class 2606 OID 47429)
-- Name: criteria_mark fksf3wkbshhmbo1rhim3ch6s084; Type: FK CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.criteria_mark
    ADD CONSTRAINT fksf3wkbshhmbo1rhim3ch6s084 FOREIGN KEY (criteria_id) REFERENCES public.criteria(id);


--
-- TOC entry 3251 (class 2606 OID 47434)
-- Name: web_user_roles fkt2rw5y5435mvydug75kcv4h7e; Type: FK CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.web_user_roles
    ADD CONSTRAINT fkt2rw5y5435mvydug75kcv4h7e FOREIGN KEY (roles_id) REFERENCES public.role(id);


--
-- TOC entry 3245 (class 2606 OID 47439)
-- Name: team fkum5ejph85lolunj2rmm19kqc; Type: FK CONSTRAINT; Schema: public; Owner: ISU
--

ALTER TABLE ONLY public.team
    ADD CONSTRAINT fkum5ejph85lolunj2rmm19kqc FOREIGN KEY (leader_id) REFERENCES public.web_user(id);


-- Completed on 2024-05-27 01:06:12

--
-- PostgreSQL database dump complete
--

