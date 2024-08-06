package ru.tatarinov.t_bank_task.model;

import java.util.List;
import java.util.Map;

public class YandexResponse {
    private List<Map<String, String>> translations;

    public List<Map<String, String>> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Map<String, String>> translations) {
        this.translations = translations;
    }
}
