package com.game.monopoly.helper;

import com.game.monopoly.entity.CompanyCard;
import com.game.monopoly.entity.compositekey.CompanyCardId;

import java.util.ArrayList;
import java.util.List;

import static com.game.monopoly.enums.CardType.COMPANY;

public class SessionHelper {

    public static List<CompanyCard> initializeCompanyCards(String sessionId) {
        List<CompanyCard> cards = new ArrayList<>();
        cards.add(new CompanyCard(new CompanyCardId(1L, sessionId), "Wildberries", "/images/wildberries.jpg", 600, 0, 500, 0, COMPANY, null, 1, ));
        cards.add(new CompanyCard(new CompanyCardId(2L, sessionId), "Ozon", "/images/ozon.jpg", 800, 0, 500, 0, COMPANY, null, 1, ));
        cards.add(new CompanyCard(new CompanyCardId(3L, sessionId), "Apple", "/images/apple.jpg", 2000, 0, 700, 0, COMPANY, null, 2, ));
        cards.add(new CompanyCard(new CompanyCardId(4L, sessionId), "Samsung", "/images/samsung.jpg", 2000, 0, 700, 0, COMPANY, null, 2, ));
        cards.add(new CompanyCard(new CompanyCardId(5L, sessionId), "Huawei", "/images/huawei.jpg", 2000, 0, 700, 0, COMPANY, null, 2, ));
        cards.add(new CompanyCard(new CompanyCardId(6L, sessionId), "Vivo", "/images/vivo.jpg", 2000, 0, 700, 0, COMPANY, null, 2, ));
        cards.add(new CompanyCard(new CompanyCardId(7L, sessionId), "Adidas", "/images/adidas.jpg", 1000, 0, 500, 0, COMPANY, null, 3, ));
        cards.add(new CompanyCard(new CompanyCardId(8L, sessionId), "Nike", "/images/nike.jpg", 1100, 0, 500, 0, COMPANY, null, 3, ));
        cards.add(new CompanyCard(new CompanyCardId(9L, sessionId), "Puma", "/images/puma.jpg", 1200, 0, 500, 0, COMPANY, null, 3, ));
        cards.add(new CompanyCard(new CompanyCardId(10L, sessionId), "Kfc", "/images/kfc.jpg", 1400, 0, 750, 0, COMPANY, null, 4, ));
        cards.add(new CompanyCard(new CompanyCardId(11L, sessionId), "Burger king", "/images/burgerking.jpg", 1500, 0, 750, 0, COMPANY, null, 4, ));
        cards.add(new CompanyCard(new CompanyCardId(12L, sessionId), "Вкусно и точка", "/images/vkysno.jpg", 1600, 0, 750, 0, COMPANY,null, 4, ));
        cards.add(new CompanyCard(new CompanyCardId(13L, sessionId), "Vk", "/images/vk.jpg", 1800, 0, 1000, 0, COMPANY, null, 5, ));
        cards.add(new CompanyCard(new CompanyCardId(14L, sessionId), "Telegram", "/images/telegram.jpg", 1900, 0, 1000, 0, COMPANY, null, 5, ));
        cards.add(new CompanyCard(new CompanyCardId(15L, sessionId), "Одноклассники", "/images/klass.jpg", 2000, 0, 1000, 0, COMPANY, null, 5, ));
        cards.add(new CompanyCard(new CompanyCardId(16L, sessionId), "Tiffany", "/images/tiffani.jpg", 2200, 0, 1250, 0, COMPANY, null, 6, ));
        cards.add(new CompanyCard(new CompanyCardId(17L, sessionId), "Sokolov", "/images/sokolov.jpg", 2300, 0, 1250, 0, COMPANY, null, 6, ));
        cards.add(new CompanyCard(new CompanyCardId(18L, sessionId), "Cartier", "/images/cartier.jpg", 2400, 0, 1250, 0, COMPANY, null, 6, ));
        cards.add(new CompanyCard(new CompanyCardId(19L, sessionId), "Fanta", "/images/fanta.jpg", 2600, 0, 1500, 0, COMPANY, null, 7, ));
        cards.add(new CompanyCard(new CompanyCardId(20L, sessionId), "Lipton", "/images/lipton.jpg", 2700, 0, 1500, 0, COMPANY, null, 7, ));
        cards.add(new CompanyCard(new CompanyCardId(21L, sessionId), "Coca-cola", "/images/coca-cola.jpg", 2800, 0, 1500, 0, COMPANY, null, 7, ));
        cards.add(new CompanyCard(new CompanyCardId(22L, sessionId), "Sber", "/images/sber.jpg", 3000, 0, 1750, 0, COMPANY, null, 8, ));
        cards.add(new CompanyCard(new CompanyCardId(23L, sessionId), "Почта банк", "/images/pochta.jpg", 3100, 0, 1750, 0, COMPANY, null, 8, ));
        cards.add(new CompanyCard(new CompanyCardId(24L, sessionId), "Tinkoff", "/images/tinkoff.jpg", 3200, 0, 1750, 0, COMPANY, null, 8, ));
        cards.add(new CompanyCard(new CompanyCardId(25L, sessionId), "Tenerife", "/images/tenerife.jpg", 3500, 0, 2000, 0, COMPANY, null, 9, ));
        cards.add(new CompanyCard(new CompanyCardId(26L, sessionId), "Maldives", "/images/maldives.jpg", 4000, 0, 2000, 0, COMPANY, null, 9, ));
        cards.add(new CompanyCard(new CompanyCardId(27L, sessionId), "Mercedes", "/images/mercedes.jpg", 1500, 0, 2150, 0, COMPANY, null, 10, ));
        cards.add(new CompanyCard(new CompanyCardId(28L, sessionId), "Porsche", "/images/porsche.jpg", 1500, 0, 2150, 0, COMPANY, null, 10, ));
        return cards;


    }
}
