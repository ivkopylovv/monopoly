INSERT INTO company_card
    (id, string_title, image, price, star_price, fines, collection_number)
VALUES (1, 'Wildberries', '/images/wildberries.jpg', 100000, 10000, 20000, 1),
        (2, 'Ozon', '/images/ozon.jpg', 200000, 10000, 10000, 2),
        (3, 'Apple', '/images/apple.jpg', 250000, 10000, 15000, 1),
        (4, 'Samsung', '/images/samsung.jpg', 300000, 15000, 30000, 3),
        (5, 'Huawei', '/images/huewei.jpg', 150000, 5000, 20000, 1);

INSERT INTO chance_card
    ("id", description, money, step)
VALUES (1, 'Поздравляем с Днем рождения! Получите в подарок 50k', 50000, 1),
       (2, 'Вы выиграли в лотерею! Получите 20k', 20000, 1),
       (3, 'О нет, вас только что оштрафовали! Заплатите 10k', 10000, 1),
       (4, 'Вы сегодня щедрый, отдайте всем игрокам по 20k', 20000, 1),
       (5, 'Сосед слева требует от вас 10k. Так отдайте же их ему!', 10000, 1);

INSERT INTO nontype_card
    ("id", image)
VALUES (1, '/images/card1.jpg'),
       (2, '/images/card2.jpg'),
       (3, '/images/card3.jpg',),
       (4, '/images/card4.jpg',),
       (5, '/images/card5.jpg',);

INSERT INTO level_fine
    ("id", "value")
VALUES (1, 100),
       (2, 200),
       (3, 400),
       (4, 800),
       (5, 1200);

INSERT INTO player
    ("id", "name", "position", balance)
VALUES (1, 'Ivan', 1, 150000),
       (2, 'Masha', 1, 150000),
       (3, 'Nikita', 1, 150000),
       (4, 'Danila', 1, 150000),
       (5, 'Bogdan', 1, 150000);

INSERT INTO "session"
    ("id", "player_id", "state", "cards_id")
VALUES (1, 1, "inprogress", 1),
       (1, 2, "inprogress", 1),
       (1, 3, "inprogress", 1),
       (4, 4, "inprogress", 2),
       (5, 5, "inprogress", 3);

INSERT INTO "session_card_states"
("session_id", "cards_state_id")
VALUES (1, 1),
       (2, 3),
       (3, 2);

INSERT INTO "card_level_fines"
("level_fine_id", "card_id")
VALUES (1, 1),
       (2, 2),
       (3, 3,);

INSERT INTO "card_state"
("id", "current_fine", "level", "card", "owner_name")
VALUES (1, 20000, 1, "company", "null"),
       (2, 15000, 2, "company", "not_null"),
       (3, 10000, 1, "company", "null");