INSERT INTO chance_card (id, description, money_difference, step, type)
VALUES (1, 'берет билет на самолет. Он проходит на 10 шагов вперед ', null, 10, 'CHANGE_POSITION'),
       (2, 'получает наследство 700К ', 700, null, 'CHANGE_BALANCE'),
       (3, 'празднует сегодня День рождения! Тинькофф Банк дарит ему 1000K ', 1000, null, 'CHANGE_BALANCE'),
       (4, 'получает банковские дивиденты в размере 500К ', 500, null, 'CHANGE_BALANCE'),
       (5, 'выигрывает чемпионат по настольному теннис и получает 100К ', 100, null, 'CHANGE_BALANCE'),
       (6, ',банковская ошибка в вашу пользу. Получите 300К ', 300, null, 'CHANGE_BALANCE'),
       (7, 'получает наследство 2000К ', 2000, null, 'CHANGE_BALANCE'),
       (8, 'получает наследство 500К ', 500, null, 'CHANGE_BALANCE'),
       (9, 'поучает страховые выплаты в размере 1000K ', 1000, null, 'CHANGE_BALANCE'),
       (10, 'получает штраф за превышение скорости! Вернитесь на 10 шагов назад', null, -10, 'CHANGE_POSITION'),
       (11, 'получает штраф за вождение в нетрезвом виде: пройдите на 15 шагов назад ', null, -15, 'CHANGE_POSITION'),
       (12, 'попадает по стражу: Пройдите на 5 шагов назад ', null, -5, 'CHANGE_POSITION'),
       (13, 'неожиданно возвращает старый долг 800К ', 800, null, 'CHANGE_BALANCE'),
       (14, 'выигрывает в лотерею и получает 100К ', 100, null, 'CHANGE_BALANCE'),
       (15, 'приходится долечить зубы. Пройдите на 4 шага назад ', null, -4, 'CHANGE_POSITION');

INSERT INTO common_card (id, image, card_type)
VALUES (1, '/images/rocket.svg', 'START'),
       (2, '/images/levis.svg', 'COMPANY'),
       (3, '/images/chance-1.png', 'CHANCE'),
       (4, '/images/hm.svg', 'COMPANY'),
       (5, '/images/tax_income.png', 'TAX_INCOME'),
       (6, '/images/lg.svg', 'COMPANY'),
       (7, '/images/new_balance.svg', 'COMPANY'),
       (8, '/images/chance-1.png', 'CHANCE'),
       (9, '/images/nike.svg', 'COMPANY'),
       (10, '/images/vans.svg', 'COMPANY'),
       (11, '/images/jail-visiting.png', 'NONTYPE'),
       (12, '/images/dominos.svg', 'COMPANY'),
       (13, '/images/tesla.svg', 'COMPANY'),
       (14, '/images/dunkin_donuts.svg', 'COMPANY'),
       (15, '/images/mcdonalds.svg', 'COMPANY'),
       (16, '/images/logitech.svg', 'COMPANY'),
       (17, '/images/skype.svg', 'COMPANY'),
       (18, '/images/chance-3.png', 'CHANCE'),
       (19, '/images/telegram.svg', 'COMPANY'),
       (20, '/images/youtube.svg', 'COMPANY'),
       (21, '/images/jackpot.svg', 'JACKPOT'),
       (22, '/images/gucci.svg', 'COMPANY'),
       (23, '/images/chance-4.png', 'CHANCE'),
       (24, '/images/prada.svg', 'COMPANY'),
       (25, '/images/dior.svg', 'COMPANY'),
       (26, '/images/acer.svg', 'COMPANY'),
       (27, '/images/7up.svg', 'COMPANY'),
       (28, '/images/mirinda.svg', 'COMPANY'),
       (29, '/images/bmw.svg', 'COMPANY'),
       (30, '/images/sprite.svg', 'COMPANY'),
       (31, '/images/portal_new.svg', 'TELEPORT'),
       (32, '/images/loreal.svg', 'COMPANY'),
       (33, '/images/givenchy.svg', 'COMPANY'),
       (34, '/images/chance-5.png', 'CHANCE'),
       (35, '/images/sephora.svg', 'COMPANY'),
       (36, '/images/nedozon.svg', 'COMPANY'),
       (37, '/images/tax_luxury.png', 'TAX_LUXURY'),
       (38, '/images/netflix.svg', 'COMPANY'),
       (39, '/images/chance-5.png', 'CHANCE'),
       (40, '/images/google.svg', 'COMPANY');

INSERT INTO company_card (id, title, sphere, price, star_price, sale_price, collection_number, common_card_id)
VALUES (2, 'Levis', 'Одежда', 600, 500, 300, 1, 2),
       (4, 'H&M', 'Одежда', 800, 500, 400, 1, 4),
       (6, 'LG', 'Электроника', 2000, 1200, 1000, 2, 6),
       (7, 'New Balance', 'Спортивная одежда', 1000, 500, 500, 3, 7),
       (9, 'Nike', 'Спортивная одежда', 1100, 500, 550, 3, 9),
       (10, 'Vans', 'Спортивная одежда', 1200, 500, 600, 3, 10),
       (12, 'Domino’s', 'Фастфуд', 1400, 750, 700, 4, 12),
       (13, 'Tesla', 'Автомобили', 1500, 800, 750, 5, 13),
       (14, 'Dunkin donuts', 'Фастфуд', 1500, 750, 750, 4, 14),
       (15, 'McDonald’s', 'Фастфуд', 1600, 750, 800, 4, 15),
       (16, 'Logitech', 'Электроника', 2000, 1200, 1000, 2, 16),
       (17, 'Skype', 'Приложение', 1800, 1000, 900, 6, 17),
       (19, 'Telegram', 'Приложение', 1900, 1000, 950, 6, 19),
       (20, 'Youtube', 'Приложение', 2000, 1000, 1000, 6, 20),
       (22, 'Gucci', 'Премиум', 2200, 1250, 1100, 7, 22),
       (24, 'Prada', 'Премиум', 2300, 1250, 1150, 7, 24),
       (25, 'Dior', 'Премиум', 2400, 1250, 1200, 7, 25),
       (26, 'Acer', 'Электроника', 2000, 1200, 1000, 2, 26),
       (27, '7Up', 'Напитки', 2600, 1500, 1300, 8, 27),
       (28, 'Mirinda', 'Напитки', 2700, 1500, 1350, 8, 28),
       (29, 'BMW', 'Автомобили', 1500, 800, 900, 5, 29),
       (30, 'Sprite', 'Напитки', 2800, 1500, 1400, 8, 30),
       (32, 'L’Oreal', 'Парфюмерия', 3000, 1750, 1500, 9, 32),
       (33, 'Givenchy', 'Парфюмерия', 3100, 1750, 1550, 9, 33),
       (35, 'Sephora', 'Парфюмерия', 3200, 1750, 1600, 9, 35),
       (36, 'Nedozon', 'Электроника', 2000, 1200, 1000, 2, 36),
       (38, 'Netflix', 'IT', 3500, 2000, 1750, 10, 38),
       (40, 'Google', 'IT', 4000, 2000, 2000, 10, 40);

INSERT INTO level_fine (value)
VALUES (20),
       (100),
       (300),
       (900),
       (1600),
       (2500);
INSERT INTO level_fine (value)
VALUES (40),
       (200),
       (600),
       (1800),
       (3200),
       (4500);
INSERT INTO level_fine (value)
VALUES (300),
       (900),
       (2000),
       (4500),
       (9200),
       (12000);
INSERT INTO level_fine (value)
VALUES (60),
       (300),
       (900),
       (2700),
       (4000),
       (5500);
INSERT INTO level_fine (value)
VALUES (60),
       (300),
       (900),
       (2700),
       (4000),
       (5500);
INSERT INTO level_fine (value)
VALUES (80),
       (400),
       (1000),
       (3000),
       (4500),
       (6000);
INSERT INTO level_fine (value)
VALUES (100),
       (500),
       (1500),
       (4500),
       (6250),
       (7500);
INSERT INTO level_fine (value)
VALUES (150),
       (400),
       (900),
       (1600),
       (3700),
       (6200);
INSERT INTO level_fine (value)
VALUES (100),
       (500),
       (1500),
       (4500),
       (6250),
       (7500);
INSERT INTO level_fine (value)
VALUES (120),
       (600),
       (1800),
       (5000),
       (7000),
       (9000);
INSERT INTO level_fine (value)
VALUES (300),
       (900),
       (2000),
       (4500),
       (9200),
       (12000);
INSERT INTO level_fine (value)
VALUES (140),
       (700),
       (2000),
       (5500),
       (7500),
       (9500);
INSERT INTO level_fine (value)
VALUES (140),
       (700),
       (2000),
       (5500),
       (7500),
       (9500);
INSERT INTO level_fine (value)
VALUES (160),
       (800),
       (2200),
       (6000),
       (8000),
       (10000);
INSERT INTO level_fine (value)
VALUES (180),
       (900),
       (2500),
       (7000),
       (8750),
       (10500);
INSERT INTO level_fine (value)
VALUES (180),
       (900),
       (2500),
       (7000),
       (8750),
       (10500);
INSERT INTO level_fine (value)
VALUES (200),
       (1000),
       (3000),
       (7500),
       (9250),
       (11000);
INSERT INTO level_fine (value)
VALUES (300),
       (900),
       (2000),
       (4500),
       (9200),
       (12000);
INSERT INTO level_fine (value)
VALUES (220),
       (1100),
       (3300),
       (8000),
       (9750),
       (11500);
INSERT INTO level_fine (value)
VALUES (220),
       (1100),
       (3300),
       (8000),
       (9750),
       (11500);
INSERT INTO level_fine (value)
VALUES (100),
       (400),
       (1000),
       (1900),
       (3100),
       (5000);
INSERT INTO level_fine (value)
VALUES (240),
       (1200),
       (3600),
       (8500),
       (10250),
       (12000);
INSERT INTO level_fine (value)
VALUES (260),
       (1300),
       (3900),
       (9000),
       (11000),
       (12750);
INSERT INTO level_fine (value)
VALUES (260),
       (1300),
       (3900),
       (9000),
       (11000),
       (12750);
INSERT INTO level_fine (value)
VALUES (280),
       (1500),
       (4500),
       (10000),
       (12000),
       (14000);
INSERT INTO level_fine (value)
VALUES (300),
       (900),
       (2000),
       (4500),
       (9200),
       (12000);
INSERT INTO level_fine (value)
VALUES (350),
       (1750),
       (5000),
       (11000),
       (13000),
       (15000);
INSERT INTO level_fine (value)
VALUES (500),
       (2000),
       (6000),
       (14000),
       (17000),
       (20000);

INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (2, 1),
       (2, 2),
       (2, 3),
       (2, 4),
       (2, 5),
       (2, 6);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (4, 7),
       (4, 8),
       (4, 9),
       (4, 10),
       (4, 11),
       (4, 12);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (6, 13),
       (6, 14),
       (6, 15),
       (6, 16),
       (6, 17),
       (6, 18);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (7, 19),
       (7, 20),
       (7, 21),
       (7, 22),
       (7, 23),
       (7, 24);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (9, 25),
       (9, 26),
       (9, 27),
       (9, 28),
       (9, 29),
       (9, 30);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (10, 31),
       (10, 32),
       (10, 33),
       (10, 34),
       (10, 35),
       (10, 36);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (12, 37),
       (12, 38),
       (12, 39),
       (12, 40),
       (12, 41),
       (12, 42);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (13, 43),
       (13, 44),
       (13, 45),
       (13, 46),
       (13, 47),
       (13, 48);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (14, 49),
       (14, 50),
       (14, 51),
       (14, 52),
       (14, 53),
       (14, 54);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (15, 55),
       (15, 56),
       (15, 57),
       (15, 58),
       (15, 59),
       (15, 60);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (16, 61),
       (16, 62),
       (16, 63),
       (16, 64),
       (16, 65),
       (16, 66);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (17, 67),
       (17, 68),
       (17, 69),
       (17, 70),
       (17, 71),
       (17, 72);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (19, 73),
       (19, 74),
       (19, 75),
       (19, 76),
       (19, 77),
       (19, 78);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (20, 79),
       (20, 80),
       (20, 81),
       (20, 82),
       (20, 83),
       (20, 84);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (22, 85),
       (22, 86),
       (22, 87),
       (22, 88),
       (22, 89),
       (22, 90);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (24, 91),
       (24, 92),
       (24, 93),
       (24, 94),
       (24, 95),
       (24, 96);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (25, 97),
       (25, 98),
       (25, 99),
       (25, 100),
       (25, 101),
       (25, 102);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (26, 103),
       (26, 104),
       (26, 105),
       (26, 106),
       (26, 107),
       (26, 108);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (27, 109),
       (27, 110),
       (27, 111),
       (27, 112),
       (27, 113),
       (27, 114);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (28, 115),
       (28, 116),
       (28, 117),
       (28, 118),
       (28, 119),
       (28, 120);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (29, 121),
       (29, 122),
       (29, 123),
       (29, 124),
       (29, 125),
       (29, 126);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (30, 127),
       (30, 128),
       (30, 129),
       (30, 130),
       (30, 131),
       (30, 132);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (32, 133),
       (32, 134),
       (32, 135),
       (32, 136),
       (32, 137),
       (32, 138);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (33, 139),
       (33, 140),
       (33, 141),
       (33, 142),
       (33, 143),
       (33, 144);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (35, 145),
       (35, 146),
       (35, 147),
       (35, 148),
       (35, 149),
       (35, 150);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (36, 151),
       (36, 152),
       (36, 153),
       (36, 154),
       (36, 155),
       (36, 156);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (38, 157),
       (38, 158),
       (38, 159),
       (38, 160),
       (38, 161),
       (38, 162);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (40, 163),
       (40, 164),
       (40, 165),
       (40, 166),
       (40, 167),
       (40, 168);