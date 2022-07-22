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
VALUES (1, '/images/rocket.png', 'START'),
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
       (23, '/images/chance-1.png', 'CHANCE'),
       (24, '/images/prada.svg', 'COMPANY'),
       (25, '/images/dior.svg', 'COMPANY'),
       (26, '/images/acer.svg', 'COMPANY'),
       (27, '/images/7up.svg', 'COMPANY'),
       (28, '/images/mirinda.svg', 'COMPANY'),
       (29, '/images/bmw.svg', 'COMPANY'),
       (30, '/images/sprite.svg', 'COMPANY'),
       (31, '/images/portal.png', 'TELEPORT'),
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
VALUES (2, 'Levi’s', 'Одежда', 600, 500, 300, 1, 2),
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
       (6, 16);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (7, 17),
       (7, 18),
       (7, 19),
       (7, 20),
       (7, 21),
       (7, 22);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (9, 23),
       (9, 24),
       (9, 25),
       (9, 26),
       (9, 27),
       (9, 28);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (10, 29),
       (10, 30),
       (10, 31),
       (10, 32),
       (10, 33),
       (10, 34);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (12, 35),
       (12, 36),
       (12, 37),
       (12, 38),
       (12, 39),
       (12, 40);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (13, 41),
       (13, 42);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (14, 43),
       (14, 44),
       (14, 45),
       (14, 46),
       (14, 47),
       (14, 48);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (15, 49),
       (15, 50),
       (15, 51),
       (15, 52),
       (15, 53),
       (15, 54);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (16, 55),
       (16, 56),
       (16, 57),
       (16, 58);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (17, 59),
       (17, 60),
       (17, 61),
       (17, 62),
       (17, 63),
       (17, 64);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (19, 65),
       (19, 66),
       (19, 67),
       (19, 68),
       (19, 69),
       (19, 70);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (20, 71),
       (20, 72),
       (20, 73),
       (20, 74),
       (20, 75),
       (20, 76);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (22, 77),
       (22, 78),
       (22, 79),
       (22, 80),
       (22, 81),
       (22, 82);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (24, 83),
       (24, 84),
       (24, 85),
       (24, 86),
       (24, 87),
       (24, 88);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (25, 89),
       (25, 90),
       (25, 91),
       (25, 92),
       (25, 93),
       (25, 94);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (26, 95),
       (26, 96),
       (26, 97),
       (26, 98);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (27, 99),
       (27, 100),
       (27, 101),
       (27, 102),
       (27, 103),
       (27, 104);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (28, 105),
       (28, 106),
       (28, 107),
       (28, 108),
       (28, 109),
       (28, 110);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (29, 111),
       (29, 112);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (30, 113),
       (30, 114),
       (30, 115),
       (30, 116),
       (30, 117),
       (30, 118);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (32, 119),
       (32, 120),
       (32, 121),
       (32, 122),
       (32, 123),
       (32, 124);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (33, 125),
       (33, 126),
       (33, 127),
       (33, 128),
       (33, 129),
       (33, 130);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (35, 131),
       (35, 132),
       (35, 133),
       (35, 134),
       (35, 135),
       (35, 136);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (36, 137),
       (36, 138),
       (36, 139),
       (36, 140);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (38, 141),
       (38, 142),
       (38, 143),
       (38, 144),
       (38, 145),
       (38, 146);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (40, 147),
       (40, 148),
       (40, 149),
       (40, 150),
       (40, 151),
       (40, 152);