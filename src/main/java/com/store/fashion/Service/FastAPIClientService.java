package com.store.fashion.Service;

import com.store.fashion.dto.FastAPIRecommendationResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
@Service
public class FastAPIClientService {

    private final WebClient webClient;
    private final String FASTAPI_URL = "http://localhost:8000";

    public FastAPIClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(FASTAPI_URL).build();
    }


    public List<String> getRecommendations(byte[] imageBytes, String filename) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", new ByteArrayResource(imageBytes))
                .filename(filename)
                .contentType(MediaType.IMAGE_JPEG);

        // Mapper la réponse à notre classe interne FastAPIRecommendationResponse
        FastAPIRecommendationResponse response = webClient.post()
                .uri("/recommend")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(FastAPIRecommendationResponse.class) // Utilisez le DTO pour la réponse FastAPI
                .block();

        return response != null ? response.getResults() : null;
    }
}