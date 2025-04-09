package celepar.automacao.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseDTO {

    String remote_addr;
    String auth_type;
    String client;
    String jwt;
    String ida;
    String iss;
    String source;
    String token_type;
    String access_token;
    String grant_type;
    String client_credentials;
    String expires_in;

}
