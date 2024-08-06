package ru.tatarinov.t_bank_task.service;

import org.springframework.stereotype.Service;
import ru.tatarinov.t_bank_task.service.ExternalTranslationService.ExternalTranslationServiceInterface;
import ru.tatarinov.t_bank_task.service.multithreading.TranslationTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class TranslationService {
    private final ExecutorService executorService;

    public TranslationService() {
        this.executorService = Executors.newFixedThreadPool(10);
    }

    public List<String> translateWords(List<String> words, String sourceLang, String targetLang, ExternalTranslationServiceInterface externalTranslationService) {

        List<TranslationTask> taskList = new ArrayList<>();
        for (String word: words){
            TranslationTask task = new TranslationTask(word, sourceLang, targetLang, externalTranslationService);
            taskList.add(task);
        }

        List<Future<String>> futures = null;
        List<String> translatedWordsList = new ArrayList<>();
        try {
            futures = executorService.invokeAll(taskList);
            for (Future<String> future : futures) {
                translatedWordsList.add(future.get());
            }
        }
        catch(InterruptedException | ExecutionException e){
            throw new RuntimeException(e);
        }

        return translatedWordsList;
    }
}
