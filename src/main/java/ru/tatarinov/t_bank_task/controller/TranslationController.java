package ru.tatarinov.t_bank_task.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tatarinov.t_bank_task.service.ExternalTranslationService.YandexTranslateService;
import ru.tatarinov.t_bank_task.service.LoggingService;
import ru.tatarinov.t_bank_task.service.TranslationService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/translate")
public class TranslationController {
    private final TranslationService translationService;
    private final LoggingService loggingService;
    private final YandexTranslateService yandexTranslateService;

    public TranslationController(TranslationService translationService, LoggingService loggingService, YandexTranslateService yandexTranslateService) {
        this.translationService = translationService;
        this.loggingService = loggingService;
        this.yandexTranslateService = yandexTranslateService;
    }

    @PostMapping
    public ResponseEntity<String> translate(@RequestParam String text, @RequestParam("source") String sourceLang, @RequestParam("target") String targetLang, HttpServletRequest request) throws InterruptedException, ExecutionException {
        String[] words = text.split(" ");

        List<String> translatedWords = translationService.translateWords(Arrays.asList(words), sourceLang, targetLang, yandexTranslateService);
        String translatedText = String.join(" ", translatedWords);
        loggingService.logRequest(request.getRemoteAddr(), text, translatedText);
        return ResponseEntity.ok(translatedText);
    }
}
