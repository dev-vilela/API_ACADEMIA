INSERT INTO modalidades(nome) VALUES
('Musculação'),
('Personal'),
('Jiu-Jitsu'),
('Karatê'),
('Pilates');

INSERT INTO planos(modalidade_id, nome, valor_mensal)
SELECT id, 'Mensal', 120.00 FROM modalidades WHERE nome = 'Musculação';

INSERT INTO planos(modalidade_id, nome, valor_mensal)
SELECT id, 'Trimestal', 330.00 FROM modalidades WHERE nome = 'Musculação';

INSERT INTO planos(modalidade_id, nome, valor_mensal)
SELECT id, 'Mensal', 150.00 FROM modalidades WHERE nome = 'Personal';

INSERT INTO planos(modalidade_id, nome, valor_mensal)
SELECT id, 'Mensal', 180.00 FROM modalidades WHERE nome = 'Jiu-Jitsu';

INSERT INTO graduacoes(modalidade_id, nome)
SELECT id, 'Faixa Branca' FROM modalidades WHERE nome = 'Jiu-Jitsu';

INSERT INTO graduacoes(modalidade_id, nome)
SELECT id, 'Faixa Azul' FROM modalidades WHERE nome = 'Jiu-Jitsu';

INSERT INTO graduacoes(modalidade_id, nome)
SELECT id, 'Faixa Roxa' FROM modalidades WHERE nome = 'Jiu-Jitsu';