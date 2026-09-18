package com.shopsphere.shopsphere_backend.ai;

import com.shopsphere.shopsphere_backend.products.model.Product;
import com.shopsphere.shopsphere_backend.products.repository.ProductRepository;
import com.shopsphere.shopsphere_backend.user.model.User;
import com.shopsphere.shopsphere_backend.user.Repository.userRepo;
import com.shopsphere.shopsphere_backend.userpreferences.UserPreference;
import com.shopsphere.shopsphere_backend.userpreferences.UserPreferenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AiService {

    @Autowired
    private userRepo userRepo;

    @Autowired
    private UserPreferenceRepo preferenceRepo;

    @Autowired
    private ProductRepository productRepo;

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getRecommendation(String username, Integer productId) {

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserPreference preference = preferenceRepo.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Preference not found"));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        String prompt = """
                You are ShopSphere AI.

                User Profile:

                Gender: %s
                Skin Type: %s
                Skin Concern: %s
                Undertone: %s
                Hair Type: %s
                Body Type: %s
                Preferred Style: %s
                Budget: %s

                Product:

                Name: %s
                Description: %s
                Category: %s
                Price: %.2f

                Explain in less than 80 words whether this product suits the user.
                """.formatted(
                preference.getGender(),
                preference.getSkinType(),
                preference.getSkinConcern(),
                preference.getUndertone(),
                preference.getHairType(),
                preference.getBodyType(),
                preference.getPreferredStyle(),
                preference.getBudget(),
                product.getProductName(),
                product.getProductDescription(),
                product.getProductCategory().getName(),
                product.getProductPrice()
        );

        return callGemini(prompt);
    }

    private String callGemini(String prompt) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-goog-api-key", apiKey);

        Map<String, Object> part = new HashMap<>();
        part.put("text", prompt);

        Map<String, Object> content = new HashMap<>();
        content.put("parts", List.of(part));

        Map<String, Object> body = new HashMap<>();
        body.put("contents", List.of(content));

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(body, headers);

        try {

            ResponseEntity<String> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    request,
                    String.class
            );

            return response.getBody();

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Gemini API call failed: " + e.getMessage(), e
            );
        }
    }
}
