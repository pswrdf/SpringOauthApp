package org.example.config;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WelcomeEndpoint {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome, hello";
    }
}
