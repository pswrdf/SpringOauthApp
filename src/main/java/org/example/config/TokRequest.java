package org.example.config;

import lombok.Data;

import java.util.List;

@Data
public class TokRequest {
    String grant_type;
    String code;
    String code_verifier;
    String redirect_uri;
    String client_id;
}
