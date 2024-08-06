package ru.tatarinov.t_bank_task.service.multithreading;

import ru.tatarinov.t_bank_task.service.ExternalTranslationService.ExternalTranslationServiceInterface;

import java.util.concurrent.Callable;

public class TranslationTask implements Callable<String> {

    private final String word;
    private final String sourceLang;
    private final String targetLang;
    private final ExternalTranslationServiceInterface externalTranslationServiceInterface;

    public TranslationTask(String word, String sourceLang, String targetLang, ExternalTranslationServiceInterface externalTranslationServiceInterface) {
        this.word = word;
        this.sourceLang = sourceLang;
        this.targetLang = targetLang;
        this.externalTranslationServiceInterface = externalTranslationServiceInterface;
    }

    @Override
    public String call() {
        return externalTranslationServiceInterface.translate(word, sourceLang, targetLang);
    }
}
