package ru.tatarinov.t_bank_task.service;

import org.springframework.stereotype.Service;
import ru.tatarinov.t_bank_task.model.RequestEntry;
import ru.tatarinov.t_bank_task.repository.RequestRepository;

@Service
public class LoggingService {
    private final RequestRepository requestLogRepository;

    public LoggingService(RequestRepository requestLogRepository) {
        this.requestLogRepository = requestLogRepository;
    }

    public void logRequest(String ip, String inputText, String translatedText) {
        RequestEntry log = new RequestEntry();
        log.setIp(ip);
        log.setInputText(inputText);
        log.setTranslatedText(translatedText);
        requestLogRepository.save(log);
    }
}
