-- Diagrama AIS

INSERT INTO diagrama_ais(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo)
    VALUES (1, 10, 'TODOS', 'TODOS');

-- Diagrama Mage

-- Superficie -> Marítimo

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (2, 8, 'A', 'SUPERFICIE', 'MARITIMO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (3, 15, 'B', 'SUPERFICIE', 'MARITIMO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (4, 15, 'C', 'SUPERFICIE', 'MARITIMO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (5, 18, 'D', 'SUPERFICIE', 'MARITIMO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (6, 40, 'E', 'SUPERFICIE', 'MARITIMO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (7, 40, 'F', 'SUPERFICIE', 'MARITIMO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (8, 37, 'G', 'SUPERFICIE', 'MARITIMO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (9, 33, 'H', 'SUPERFICIE', 'MARITIMO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (10, 30, 'I', 'SUPERFICIE', 'MARITIMO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (11, 30, 'J', 'SUPERFICIE', 'MARITIMO');

-- Submarino -> Maritimo

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (22, 6, 'A', 'SUBMARINO', 'MARITIMO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (23, 10, 'B', 'SUBMARINO', 'MARITIMO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (24, 10, 'C', 'SUBMARINO', 'MARITIMO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (25, 15, 'D', 'SUBMARINO', 'MARITIMO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (26, 25, 'E', 'SUBMARINO', 'MARITIMO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (27, 25, 'F', 'SUBMARINO', 'MARITIMO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (28, 20, 'G', 'SUBMARINO', 'MARITIMO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (29, 18, 'H', 'SUBMARINO', 'MARITIMO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (30, 15, 'I', 'SUBMARINO', 'MARITIMO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (31, 15, 'J', 'SUBMARINO', 'MARITIMO');

-- Mage Superficie -> Aéreo

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (32, 200, 'A', 'SUPERFICIE', 'AEREO');


INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (33, 200, 'B', 'SUPERFICIE', 'AEREO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (34, 200, 'C', 'SUPERFICIE', 'AEREO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (35, 200, 'D', 'SUPERFICIE', 'AEREO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (36, 110, 'E', 'SUPERFICIE', 'AEREO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (37, 110, 'F', 'SUPERFICIE', 'AEREO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (38, 90, 'G', 'SUPERFICIE', 'AEREO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (39, 145, 'H', 'SUPERFICIE', 'AEREO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (40, 130, 'I', 'SUPERFICIE', 'AEREO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (41, 40, 'J', 'SUPERFICIE', 'AEREO');

-- Mage Aereo (distancia = 180 * cos 20 graus)

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (42, 169.144, 'I', 'AEREO', 'TODOS');

-- Mage Submarino

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (43, 180, 'I', 'SUBMARINO', 'AEREO');

INSERT INTO diagrama_mage(
            id_diagrama, distancia_maxima, banda_radar, veiculo_operador, veiculo_alvo)
    VALUES (44, 180, 'I', 'SUBMARINO', 'SUPERFICIE');

-- Radar

-- Superficie -> Superficie (Para a detecção de submarino é considerado o tipo pequeno e 
-- aplicada a fórmula (distancia_maxima * 0,1 * numero_mastros_içados)

-- A

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (45, 5, 'SUPERFICIE', 'SUPERFICIE', 
            'A', 'GRANDE');

-- B

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (46, 5, 'SUPERFICIE', 'SUPERFICIE', 
            'B', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (47, 10, 'SUPERFICIE', 'SUPERFICIE', 
            'B', 'GRANDE');

-- C

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (48, 5, 'SUPERFICIE', 'SUPERFICIE', 
            'C', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (49, 10, 'SUPERFICIE', 'SUPERFICIE', 
            'C', 'GRANDE');

-- D

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (50, 2, 'SUPERFICIE', 'SUPERFICIE', 
            'D', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (51, 6, 'SUPERFICIE', 'SUPERFICIE', 
            'D', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (52, 12, 'SUPERFICIE', 'SUPERFICIE', 
            'D', 'GRANDE');

-- E

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (53, 10, 'SUPERFICIE', 'SUPERFICIE', 
            'E', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (54, 24, 'SUPERFICIE', 'SUPERFICIE', 
            'E', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (55, 30, 'SUPERFICIE', 'SUPERFICIE', 
            'E', 'GRANDE');

-- F

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (56, 10, 'SUPERFICIE', 'SUPERFICIE', 
            'F', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (57, 24, 'SUPERFICIE', 'SUPERFICIE', 
            'F', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (58, 30, 'SUPERFICIE', 'SUPERFICIE', 
            'F', 'GRANDE');

-- G

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (59, 10, 'SUPERFICIE', 'SUPERFICIE', 
            'G', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (60, 22, 'SUPERFICIE', 'SUPERFICIE', 
            'G', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (61, 28, 'SUPERFICIE', 'SUPERFICIE', 
            'G', 'GRANDE');

-- H

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (62, 12, 'SUPERFICIE', 'SUPERFICIE', 
            'H', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (63, 16, 'SUPERFICIE', 'SUPERFICIE', 
            'H', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (64, 22, 'SUPERFICIE', 'SUPERFICIE', 
            'H', 'GRANDE');

-- I

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (65, 12, 'SUPERFICIE', 'SUPERFICIE', 
            'I', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (66, 15, 'SUPERFICIE', 'SUPERFICIE', 
            'I', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (67, 20, 'SUPERFICIE', 'SUPERFICIE', 
            'I', 'GRANDE');

-- J

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (68, 12, 'SUPERFICIE', 'SUPERFICIE', 
            'J', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (69, 15, 'SUPERFICIE', 'SUPERFICIE', 
            'J', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (70, 20, 'SUPERFICIE', 'SUPERFICIE', 
            'J', 'GRANDE');

-- X

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (108, 2, 'SUPERFICIE', 'SUPERFICIE', 
            'X', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (109, 6, 'SUPERFICIE', 'SUPERFICIE', 
            'X', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (110, 12, 'SUPERFICIE', 'SUPERFICIE', 
            'X', 'GRANDE');

-- S

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (111, 10, 'SUPERFICIE', 'SUPERFICIE', 
            'S', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (112, 24, 'SUPERFICIE', 'SUPERFICIE', 
            'S', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (113, 30, 'SUPERFICIE', 'SUPERFICIE', 
            'S', 'GRANDE');

-- Radar Superficie -> Aereo

-- A e B

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (71, 83, 'SUPERFICIE', 'AEREO', 
            'A', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (72, 130, 'SUPERFICIE', 'AEREO', 
            'A', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (73, 168, 'SUPERFICIE', 'AEREO', 
            'A', 'GRANDE');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (74, 83, 'SUPERFICIE', 'AEREO', 
            'B', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (75, 130, 'SUPERFICIE', 'AEREO', 
            'B', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (76, 168, 'SUPERFICIE', 'AEREO', 
            'B', 'GRANDE');

-- C e D

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (77, 70, 'SUPERFICIE', 'AEREO', 
            'C', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (78, 110, 'SUPERFICIE', 'AEREO', 
            'C', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (79, 130, 'SUPERFICIE', 'AEREO', 
            'C', 'GRANDE');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (80, 70, 'SUPERFICIE', 'AEREO', 
            'D', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (81, 110, 'SUPERFICIE', 'AEREO', 
            'D', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (82, 130, 'SUPERFICIE', 'AEREO', 
            'D', 'GRANDE');

-- E e F

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (83, 50, 'SUPERFICIE', 'AEREO', 
            'E', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (84, 60, 'SUPERFICIE', 'AEREO', 
            'E', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (85, 70, 'SUPERFICIE', 'AEREO', 
            'E', 'GRANDE');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (86, 50, 'SUPERFICIE', 'AEREO', 
            'F', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (87, 60, 'SUPERFICIE', 'AEREO', 
            'F', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (88, 70, 'SUPERFICIE', 'AEREO', 
            'F', 'GRANDE');

-- G

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (89, 18, 'SUPERFICIE', 'AEREO', 
            'G', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (90, 20, 'SUPERFICIE', 'AEREO', 
            'G', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (91, 50, 'SUPERFICIE', 'AEREO', 
            'G', 'GRANDE');

-- H

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (92, 20, 'SUPERFICIE', 'AEREO', 
            'H', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (93, 30, 'SUPERFICIE', 'AEREO', 
            'H', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (94, 40, 'SUPERFICIE', 'AEREO', 
            'H', 'GRANDE');

-- I

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (95, 15, 'SUPERFICIE', 'AEREO', 
            'I', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (96, 20, 'SUPERFICIE', 'AEREO', 
            'I', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (97, 25, 'SUPERFICIE', 'AEREO', 
            'I', 'GRANDE');

-- J

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (98, 10, 'SUPERFICIE', 'AEREO', 
            'J', 'GRANDE');

-- Radar Aereo -> TODOS (calculado por distancia * cos 20 graus)
-- 30 milhas para veículos pequenos
-- 80 milhas para veículos médios
-- 120 milhas para veículos grandes

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (99, 28.19, 'AEREO', 'TODOS', 
            'I', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (100, 75.175, 'AEREO', 'TODOS', 
            'I', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (101, 112.763, 'AEREO', 'TODOS', 
            'I', 'GRANDE');

-- Radar Submarino -> Superficie

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (102, 4, 'SUBMARINO', 'SUPERFICIE', 
            'I', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (103, 8, 'SUBMARINO', 'SUPERFICIE', 
            'I', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (104, 11, 'SUBMARINO', 'SUPERFICIE', 
            'I', 'GRANDE');

-- Radar Submarino -> Aereo

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (105, 180, 'SUBMARINO', 'AEREO', 
            'I', 'PEQUENO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (106, 180, 'SUBMARINO', 'AEREO', 
            'I', 'MEDIO');

INSERT INTO diagrama_radar(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, 
            banda_radar, tamanho_alvo)
    VALUES (107, 180, 'SUBMARINO', 'AEREO', 
            'I', 'GRANDE');

-- Diagrama IFF

INSERT INTO diagrama_iff(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, alcance_maximo)
    VALUES (108, 80, 'SUPERFICIE', 'SUPERFICIE',30000);
INSERT INTO diagrama_iff(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, alcance_maximo)
    VALUES (109, 80, 'SUPERFICIE', 'SUBMARINO',30000);
INSERT INTO diagrama_iff(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, alcance_maximo)
    VALUES (110, 80, 'SUPERFICIE', 'AEREO',30000);

INSERT INTO diagrama_iff(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, alcance_maximo)
    VALUES (111, 80, 'SUBMARINO', 'SUPERFICIE',30000);
INSERT INTO diagrama_iff(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, alcance_maximo)
    VALUES (112, 80, 'SUBMARINO', 'SUBMARINO',30000);
INSERT INTO diagrama_iff(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, alcance_maximo)
    VALUES (113, 80, 'SUBMARINO', 'AEREO',30000);

INSERT INTO diagrama_iff(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, alcance_maximo)
    VALUES (114, 80, 'AEREO', 'SUPERFICIE',30000);
INSERT INTO diagrama_iff(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, alcance_maximo)
    VALUES (115, 80, 'AEREO', 'SUBMARINO',30000);
INSERT INTO diagrama_iff(
            id_diagrama, distancia_maxima, veiculo_operador, veiculo_alvo, alcance_maximo)
    VALUES (116, 80, 'AEREO', 'AEREO',30000);


-- Graficos

-- Mage Superficie -> Aereo

-- A e B

INSERT INTO grafico(
            id_grafico)
    VALUES (1);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (32, 1);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (33, 1);

-- C e D

INSERT INTO grafico(
            id_grafico)
    VALUES (2);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (34, 2);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (35, 2);

-- E e F

INSERT INTO grafico(
            id_grafico)
    VALUES (3);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (36, 3);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (37, 3);

-- G

INSERT INTO grafico(
            id_grafico)
    VALUES (4);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (38, 4);

-- H

INSERT INTO grafico(
            id_grafico)
    VALUES (5);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (39, 5);

-- I

INSERT INTO grafico(
            id_grafico)
    VALUES (6);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (40, 6);

-- J

INSERT INTO grafico(
            id_grafico)
    VALUES (7);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (41, 7);

-- Mage Submarino -> Aereo / Superficie

INSERT INTO grafico(
            id_grafico)
    VALUES (8);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (43, 8);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (44, 8);

-- Radar

-- Superficie --> Ar

-- A e B

-- PEQUENO
INSERT INTO grafico(
            id_grafico)
    VALUES (9);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (71, 9);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (74, 9);

-- MEDIO

INSERT INTO grafico(
            id_grafico)
    VALUES (10);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (72, 10);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (75, 10);

-- GRANDE

INSERT INTO grafico(
            id_grafico)
    VALUES (11);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (73, 10);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (76, 10);

-- C e D

-- PEQUENO
INSERT INTO grafico(
            id_grafico)
    VALUES (12);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (77, 12);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (80, 12);

-- MEDIO

INSERT INTO grafico(
            id_grafico)
    VALUES (13);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (78, 13);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (81, 13);

-- GRANDE

INSERT INTO grafico(
            id_grafico)
    VALUES (14);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (79, 14);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (82, 14);

-- E e F

-- PEQUENO
INSERT INTO grafico(
            id_grafico)
    VALUES (15);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (83, 15);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (86, 15);

-- MEDIO

INSERT INTO grafico(
            id_grafico)
    VALUES (16);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (84, 16);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (87, 16);

-- GRANDE

INSERT INTO grafico(
            id_grafico)
    VALUES (17);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (85, 17);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (88, 17);

-- G

-- PEQUENO
INSERT INTO grafico(
            id_grafico)
    VALUES (18);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (89, 18);

-- MEDIO

INSERT INTO grafico(
            id_grafico)
    VALUES (19);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (90, 19);

-- GRANDE

INSERT INTO grafico(
            id_grafico)
    VALUES (20);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (91, 20);

-- H

-- PEQUENO
INSERT INTO grafico(
            id_grafico)
    VALUES (21);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (92, 21);

-- MEDIO

INSERT INTO grafico(
            id_grafico)
    VALUES (22);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (93, 22);

-- GRANDE

INSERT INTO grafico(
            id_grafico)
    VALUES (23);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (94, 23);

-- I

-- PEQUENO
INSERT INTO grafico(
            id_grafico)
    VALUES (24);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (95, 24);

-- MEDIO

INSERT INTO grafico(
            id_grafico)
    VALUES (25);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (96, 25);

-- GRANDE

INSERT INTO grafico(
            id_grafico)
    VALUES (26);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (97, 26);

-- J

-- GRANDE

INSERT INTO grafico(
            id_grafico)
    VALUES (27);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (100, 27);

-- Submarino --> Aéreo

INSERT INTO grafico(
            id_grafico)
    VALUES (28);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (105, 28);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (106, 28);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (107, 28);

-- Superficie - iff

INSERT INTO grafico(
            id_grafico)
    VALUES (29);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (108, 29);
INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (109, 29);
INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (110, 29);

INSERT INTO grafico(
            id_grafico)
    VALUES (30);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (111, 30);
INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (112, 30);
INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (113, 30);

INSERT INTO grafico(
            id_grafico)
    VALUES (31);

INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (114, 31);
INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (115, 31);
INSERT INTO diagrama_grafico(
            id_diagrama, id_grafico)
    VALUES (116, 31);

-- Coordenadas Graficos

-- Mage Superficie -> Aereo

-- A e B

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 1);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 8, 50, 1);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 200, 50, 1);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 120, 10, 1);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 120, 18, 1);

-- C e D

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 2);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 8, 50, 2);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 200, 50, 2);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 100, 5, 2);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 100, 10, 2);

-- E e F

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 3);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 7, 40, 3);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 110, 40, 3);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 110, 18, 3);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 90, 10, 3);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 90, 40, 3);

-- G

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 4);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 10, 4);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 6, 40, 4);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 90, 40, 4);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 90, 22, 4);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 60, 1, 4);

-- H

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 5);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 6, 40, 5);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 145, 40, 5);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 50, 7, 5);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 50, 2, 5);

-- I

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 6);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 5, 6);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 5, 35, 6);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 130, 32, 6);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 30, 5, 6);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 30, 0, 6);

-- J

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 7);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 5, 7);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 3, 20, 7);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 40, 20, 7);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 40, 2, 7);

-- Submarino -> Aereo / Superficie

-- I

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 8);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 180, 45, 8);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 180, 28, 8);

-- Radar

-- Superficie -> Superficie

-- A e B

-- PEQUENO

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 9);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 9, 20, 9);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 83, 20, 9);

-- MEDIO

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 10);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 10, 30, 10);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 130, 30, 10);

-- GRANDE

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 11);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 11, 40, 11);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 168, 40, 11);

-- C e D

-- PEQUENO

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 12);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 9, 20, 12);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 70, 20, 12);

-- MEDIO

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 13);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 12, 27, 13);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 110, 27, 13);

-- GRANDE

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 14);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 10, 30, 14);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 130, 30, 14);

-- E e F

-- PEQUENO

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 15);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 5, 20, 15);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 50, 20, 15);

-- MEDIO

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 16);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 4, 30, 16);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 60, 30, 16);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 60, 8, 16);

-- GRANDE

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 17);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 6, 35, 17);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 70, 35, 17);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 70, 8, 17);

-- G

-- PEQUENO

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 3, 0, 18);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 8, 17, 18);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 18, 17, 18);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 18, 13, 18);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 10, 8, 18);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 10, 0, 18);

-- MEDIO

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 2, 0, 19);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 3, 10, 19);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 4, 20, 19);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 20, 10, 19);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 20, 20, 19);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 10, 7, 19);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 10, 0, 19);

-- GRANDE

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 20);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 1, 10, 20);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 4, 30, 20);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 50, 30, 20);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 50, 16, 20);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 10, 4, 20);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 10, 20);

-- H

-- PEQUENO

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 21);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 10, 9, 21);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 20, 9, 21);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 20, 4, 21);

-- MEDIO

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 22);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 16, 15, 22);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 30, 15, 22);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 30, 5, 22);

-- GRANDE

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 23);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 5, 20, 23);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 40, 20, 23);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 40, 20, 23);

-- I

-- PEQUENO

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 2, 0, 24);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 4, 10, 24);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 15, 10, 24);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 15, 3, 24);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 10, 2, 24);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 10, 0, 24);

-- MEDIO

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 2, 0, 25);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 4, 10, 25);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 20, 10, 25);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 20, 4, 25);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 10, 2, 25);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 10, 0, 25);

-- GRANDE

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 26);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 5, 26);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 3, 20, 26);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 25, 21, 26);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 25, 5, 26);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 10, 2, 26);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 10, 0, 26);

-- J

-- GRANDE

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 27);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 5, 27);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 1, 10, 27);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 10, 10, 27);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 10, 27);

-- Submarino --> Aéreo

-- I

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 28);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 10, 28);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 115, 10, 28);

--- Coordenadas superficie IFF
INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 29);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 20, 0, 29);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 80, 6320, 29);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 80, 30000, 29);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 4.14, 30000, 29);

--- Coordenadas submarino IFF
INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 0, 0, 30);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 20, 0, 30);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 80, 6320, 30);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 80, 30000, 30);

INSERT INTO coordenada_diagrama(
            id_coord_diagrama, alcance, altitude, id_diagrama_grafico)
    VALUES (nextval('coord_diagrama_seq'), 4.14, 30000, 30);