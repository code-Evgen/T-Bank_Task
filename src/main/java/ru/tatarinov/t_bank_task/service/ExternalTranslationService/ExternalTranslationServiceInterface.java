package ru.tatarinov.t_bank_task.service.ExternalTranslationService;

public interface ExternalTranslationServiceInterface {
    String translate(String text, String sourceLang, String targetLang);
}
