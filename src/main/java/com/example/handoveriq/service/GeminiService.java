package com.example.handoveriq.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Slf4j
@Service
public class GeminiService {
    private final WebClient  webClient;


    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.uri}")
    private String apiUri;

    public GeminiService(WebClient webClient) {
        this.webClient = webClient;
    }

    // FOR PROMPT METHOD
  public String getGeminiResponse(String prompt) {
      Map<String, Object> requestBody = Map.of(
              "contents", new Object[] {
                      Map.of(
                              "parts", new Object[] {
                                      Map.of(
                                              "text", prompt
                                      )
                              }
                      )
              }
      );


      try {
          return webClient
                  .post()
                  .uri(apiUri + "?key=" + apiKey)
                  .bodyValue(requestBody)
                  .retrieve()
                  .bodyToMono(String.class)
                  .block();
      }catch(Exception e) {
          return "Error from Gemini API: " + e.getMessage();
      }
  }


}
