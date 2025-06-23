-- 11-07-2017 [Fernanda] - Refatoração tipoPlataforma

-- tipos que vieram do enum(TipoNavio)


INSERT INTO  tipo_navio (id_tipo_navio,nome) VALUES (23, 'Fragata');
INSERT INTO  tipo_navio (id_tipo_navio,nome) VALUES (24, 'Contratorpedeiro');
INSERT INTO  tipo_navio (id_tipo_navio,nome) VALUES (25, 'Varredor');
INSERT INTO  tipo_navio (id_tipo_navio,nome) VALUES (26, 'Navio Patrulha');
INSERT INTO  tipo_navio (id_tipo_navio,nome) VALUES (27, 'Navio de Des. Carros de Combate');
INSERT INTO  tipo_navio (id_tipo_navio,nome) VALUES (28, 'Mercante');
INSERT INTO  tipo_navio (id_tipo_navio,nome) VALUES (29, 'Navio Genérico');
INSERT INTO  tipo_navio (id_tipo_navio,nome) VALUES (30, 'Navio Tanque');
INSERT INTO  tipo_navio (id_tipo_navio,nome) VALUES (31, 'Corveta');
INSERT INTO  tipo_navio (id_tipo_navio,nome) VALUES (32, 'Navio de Des. de Doca');

-- criacao da sequence que o hibernate nao gera automaticamente
DROP SEQUENCE IF EXISTS tipo_plataforma_seq;

CREATE SEQUENCE tipo_plataforma_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE tipo_plataforma_seq
  OWNER TO postgres;
