package ru.tatarinov.t_bank_task.service.ExternalTranslationService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.tatarinov.t_bank_task.model.YandexResponse;
import ru.tatarinov.t_bank_task.util.ResponseExceptionBody;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YandexTranslateService implements ExternalTranslationServiceInterface {
    @Value("${yandex.api.key}")
    private String apiKey;

    @Value("${yandex.api.url}")
    private String apiUrl;

    @Value("${yandex.folderId}")
    private String folderId;

    private final RestTemplate restTemplate;

    public YandexTranslateService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String translate(String text, String sourceLang, String targetLang) {

        URI uri = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Api-Key " + apiKey);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("sourceLanguageCode", sourceLang);
        requestBody.put("targetLanguageCode", targetLang);
        List<String> textList = new ArrayList<>();
        textList.add(text);
        requestBody.put("texts", textList);
        requestBody.put("folderId", folderId);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<YandexResponse> response = restTemplate.exchange(uri, HttpMethod.POST, request, YandexResponse.class);

            if (response.getBody() != null && response.getStatusCode().is2xxSuccessful()) {
                return response.getBody().getTranslations().get(0).get("text");
            } else {
                throw new IllegalStateException("Ошибка первода : " + response.getStatusCode());
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            ResponseExceptionBody responseExceptionBody = e.getResponseBodyAs(ResponseExceptionBody.class);
            if (responseExceptionBody != null) {
                String msg = responseExceptionBody.getMessage();
                throw new IllegalArgumentException(msg);
            } else
                throw new IllegalStateException("Ошибка доступа к ресурсу перевода");
        }
    }
}
