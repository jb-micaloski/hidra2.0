-- 05-07-2016 [Camila Siqueira] - Refatoração Veículos

INSERT INTO objeto_tatico(id_objeto_tatico)
        SELECT id_objeto_tatico FROM veiculo_superficie;
INSERT INTO veiculo(id_objeto_tatico, id_informacao_veiculo, primario)
 	SELECT id_objeto_tatico, id_informacao_veiculo, primario FROM veiculo_superficie
        WHERE (SELECT COUNT(id_objeto_tatico) FROM veiculo) = 0;

INSERT INTO objeto_tatico(id_objeto_tatico)
        SELECT id_objeto_tatico FROM veiculo_submarino; 
INSERT INTO veiculo(id_objeto_tatico, id_informacao_veiculo, primario)
	SELECT id_objeto_tatico, id_informacao_veiculo, primario FROM veiculo_submarino;

INSERT INTO objeto_tatico(id_objeto_tatico)
        SELECT id_objeto_tatico FROM veiculo_helicoptero;  
INSERT INTO veiculo(id_objeto_tatico, id_informacao_veiculo, primario)
	SELECT id_objeto_tatico, id_informacao_veiculo, primario FROM veiculo_helicoptero;

INSERT INTO objeto_tatico(id_objeto_tatico)
        SELECT id_objeto_tatico FROM veiculo_aviao;   
INSERT INTO veiculo(id_objeto_tatico, id_informacao_veiculo, primario)
 	SELECT id_objeto_tatico, id_informacao_veiculo, primario FROM veiculo_aviao;

ALTER TABLE veiculo_superficie DROP COLUMN IF EXISTS id_informacao_veiculo;
ALTER TABLE veiculo_superficie DROP COLUMN IF EXISTS primario;
ALTER TABLE veiculo_superficie ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES veiculo (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE veiculo_submarino DROP COLUMN IF EXISTS id_informacao_veiculo;
ALTER TABLE veiculo_submarino DROP COLUMN IF EXISTS primario;
ALTER TABLE veiculo_submarino ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES veiculo (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE veiculo_helicoptero DROP COLUMN IF EXISTS id_informacao_veiculo;
ALTER TABLE veiculo_helicoptero DROP COLUMN IF EXISTS primario;
ALTER TABLE veiculo_helicoptero ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES veiculo (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE veiculo_aviao DROP COLUMN IF EXISTS id_informacao_veiculo;
ALTER TABLE veiculo_aviao DROP COLUMN IF EXISTS primario;
ALTER TABLE veiculo_aviao ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES veiculo (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

