package ru.tatarinov.t_bank_task.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tatarinov.t_bank_task.service.ExternalTranslationService.YandexTranslateService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class YandexTranslateServiceTest {

    @Autowired
    private YandexTranslateService yandexTranslateService;

    @Value("${yandex.api.key}")
    private String apiKey;

    @Value("${yandex.api.url}")
    private String apiUrl;

    @Test
    public void testTranslate() {
        String text = "dog";
        String sourceLang = "en";
        String targetLang = "ru";
        String translatedText = "собака";

        String result = yandexTranslateService.translate(text, sourceLang, targetLang);
        assertEquals(translatedText, result);
    }
}