package com.shopsphere.shopsphere_backend.ai;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@CrossOrigin(origins = "*")
public class AiController {

    @Autowired
    private AiService aiService;

    @PostMapping("/recommend")
    public String recommend(Authentication authentication,
                            @RequestParam Integer productId) {

        return aiService.getRecommendation(authentication.getName(), productId);
    }
}
