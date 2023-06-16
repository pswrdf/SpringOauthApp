package org.example.config;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@RestController
@RequiredArgsConstructor
public class WelcomeEndpoint {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome, hello";
    }

    @PostMapping(value = "/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    public void token(TokRequest paramMap, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        System.out.println(paramMap);
    }

//    grant_type = "authorization_code"
//    code = "e0b41b1c-2748-4432-9042-1c86798b89e9.af4aae87-9e27-435a-bf47-10c81d814891.59461c94-fb93-4311-a4de-94b232e72c25"
//    redirect_uri = "http://localhost:8080/login/oauth2/code/avanpost"
//    client_id = null
}
