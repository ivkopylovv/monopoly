INSERT INTO company_card (id, title, image, price, star_price, collection_number)
VALUES (2, 'Levi’s', '/images/levis.svg', 600, 500, 1),
       (4, 'H&M', '/images/hm.svg', 800, 500, 1),
       (6, 'LG', '/images/lg.svg', 2000, 0, 2),
       (7, 'New Balance', '/images/new_balance.svg', 1000, 500, 3),
       (9, 'Nike', '/images/nike.svg', 1100, 500, 3),
       (10, 'Vans', '/images/vans.svg', 1200, 500, 3),
       (12, 'Domino’s', '/images/dominos.svg', 1400, 750, 4),
       (13, 'Tesla', '/images/tesla.svg', 1500, 0, 5),
       (14, 'Dunkin donuts', '/images/dunkin_donuts.svg', 1500, 750, 4),
       (15, 'McDonald’s', '/images/mcdonalds.svg', 1600, 750, 4),
       (16, 'Logitech', '/images/logitech.svg', 2000, 0, 2),
       (17, 'Skype', '/images/skype.svg', 1800, 1000, 6),
       (19, 'Telegram', '/images/telegram.svg', 1900, 1000, 6),
       (20, 'Youtube', '/images/youtube.svg', 2000, 1000, 6),
       (22, 'Gucci', '/images/gucci.svg', 2200, 1250, 7),
       (24, 'Prada', '/images/prada.svg', 2300, 1250, 7),
       (25, 'Dior', '/images/dior.svg', 2400, 1250, 7),
       (26, 'Acer', '/images/acer.svg', 2000, 0, 2),
       (27, '7Up', '/images/7up.svg', 2600, 1500, 8),
       (28, 'Mirinda', '/images/mirinda.svg', 2700, 1500, 8),
       (29, 'BMW', '/images/bmw.svg', 1500, 0, 5),
       (30, 'Sprite', '/images/sprite.svg', 2800, 1500, 8),
       (32, 'L’Oreal', '/images/loreal.svg', 3000, 1750, 9),
       (33, 'Givenchy', '/images/givenchy.svg', 3100, 1750, 9),
       (35, 'Sephora', '/images/sephora.svg', 3200, 1750, 9),
       (36, 'Nedozon', '/images/nedozon.svg', 2000, 0, 2),
       (38, 'Netflix', '/images/netflix.svg', 3500, 2000, 10),
       (40, 'Google', '/images/google.svg', 4000, 2000, 10);

INSERT INTO chance_card (id, description, image, money_difference, step)
VALUES (3, '', '/images/chance-1.png', 0, 0),
       (8, '', '/images/chance-1.png', 0, 0),
       (18, '', '/images/chance-3.png', 0, 0),
       (23, '', '/images/chance-1.png', 0, 0),
       (34, '', '/images/chance-5.png', 0, 0),
       (39, '', '/images/chance-5.png', 0, 0);

INSERT INTO non_type_card (id, image)
VALUES (1, '/images/tinkoff.png'),
       (5, '/images/tax_income.png'),
       (11, '/images/jail-visiting.png'),
       (21, '/images/jackpot.svg'),
       (31, '/images/goToJail.png'),
       (37, '/images/tax_luxury.png');

-- to do
INSERT INTO level_fine (id, value)
VALUES (1, 20),
       (2, 100),
       (3, 400),
       (4, 600);

-- to do
INSERT INTO company_card_fines (company_card_id, fines_id)
VALUES (2, 1),
       (2, 2),
       (2, 3),
       (2, 4);
