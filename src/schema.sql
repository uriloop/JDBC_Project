--
-- PostgreSQL database dump
--

-- Dumped from database version 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: caracteristicas; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.caracteristicas (
                                        id smallint NOT NULL,
                                        altura_max smallint,
                                        altura_min smallint,
                                        ancho_max smallint,
                                        ancho_min smallint,
                                        scoville_max integer,
                                        scoville_min integer,
                                        dies_cult_max smallint,
                                        dies_cult_min smallint,
                                        color character varying(20),
                                        rendimiento character varying(20)
);


ALTER TABLE public.caracteristicas OWNER TO usuario;

--
-- Name: cultivo; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.cultivo (
                                id smallint NOT NULL,
                                prof_semilla numeric,
                                dist_semillas numeric,
                                dist_plantas smallint,
                                temp_cre_max smallint,
                                temp_cre_min smallint,
                                temp_germ_max smallint,
                                temp_germ_min smallint,
                                luz character varying(10)
);


ALTER TABLE public.cultivo OWNER TO usuario;

--
-- Name: pimientos; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.pimientos (
                                  id smallint NOT NULL,
                                  nombre character varying(20),
                                  descripcion text,
                                  familia character varying(10),
                                  origen character varying(20),
                                  img character varying(50)
);


ALTER TABLE public.pimientos OWNER TO usuario;

--
-- Data for Name: caracteristicas; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.caracteristicas (id, altura_max, altura_min, ancho_max, ancho_min, scoville_max, scoville_min, dies_cult_max, dies_cult_min, color, rendimiento) FROM stdin;
\.


--
-- Data for Name: cultivo; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.cultivo (id, prof_semilla, dist_semillas, dist_plantas, temp_cre_max, temp_cre_min, temp_germ_max, temp_germ_min, luz) FROM stdin;
\.


--
-- Data for Name: pimientos; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.pimientos (id, nombre, descripcion, familia, origen, img) FROM stdin;
\.


--
-- Name: caracteristicas caracteristicas_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.caracteristicas
    ADD CONSTRAINT caracteristicas_pkey PRIMARY KEY (id);


--
-- Name: cultivo cultivo_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.cultivo
    ADD CONSTRAINT cultivo_pkey PRIMARY KEY (id);


--
-- Name: pimientos pimientos_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.pimientos
    ADD CONSTRAINT pimientos_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

