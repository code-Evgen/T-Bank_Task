package ru.tatarinov.t_bank_task.model;

public class RequestEntry {
    private int id;
    private String ip;
    private String inputText;
    private String translatedText;

    public int getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getInputText() {
        return inputText;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }
}
