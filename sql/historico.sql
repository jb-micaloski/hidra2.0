ALTER DATABASE hidra_historico SET bytea_output TO 'escape';

-- Cria SCHEMA hidra_historico
CREATE SCHEMA hidra_historico
  AUTHORIZATION postgres;

-- Cria TABLE data_historico
CREATE TABLE hidra_historico.data_historico
(
  id_data_historico integer NOT NULL,
  data_exercicio character varying(255),
  latitude double precision,
  longitude double precision,
  nome_exercicio character varying(255),
  CONSTRAINT data_historico_pkey PRIMARY KEY (id_data_historico)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hidra_historico.data_historico
  OWNER TO postgres;

-- Cria SEQUENCE da tabela data_historico
CREATE SEQUENCE hidra_historico.data_historico_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE hidra_historico.data_historico_seq
  OWNER TO postgres;

-- Cria TABLE historico
CREATE TABLE hidra_historico.historico
(
  id_historico integer NOT NULL,
  dataexercicio bigint,
  datareal bigint,
  historico bytea,
  id_data_historico integer,
  CONSTRAINT historico_pkey PRIMARY KEY (id_historico),
  CONSTRAINT data_fk FOREIGN KEY (id_data_historico)
      REFERENCES hidra_historico.data_historico (id_data_historico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hidra_historico.historico
  OWNER TO postgres;

-- Cria TABLE marcador_historico
CREATE TABLE hidra_historico.marcador_historico
(
  data_real bigint NOT NULL,
  id_data_historico integer,
  nome_marcador character varying(255),
  CONSTRAINT marcador_historico_pkey PRIMARY KEY (data_real),
  CONSTRAINT id_data_historico_fk FOREIGN KEY (id_data_historico)
      REFERENCES hidra_historico.data_historico (id_data_historico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hidra_historico.marcador_historico
  OWNER TO postgres;

-- Cria SEQUENCE da tabela historico
CREATE SEQUENCE hidra_historico.historico_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE hidra_historico.historico_seq
  OWNER TO postgres;
