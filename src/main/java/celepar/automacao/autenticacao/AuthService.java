package celepar.automacao.autenticacao;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import celepar.automacao.dto.AuthResponseDTO;

@Service
public class AuthService {

    public AuthResponseDTO getToken() {
        try {
            // Monta o corpo da requisição
            String requestBody = "grant_type=client_credentials"
                    + "&client_secret=6dc29d538a2c715e6abbb39a0dced6a7bf113167dd9dbe288cd00126ac5c25fbe513655c22b8f1e6e24324196422a77b7fcefd979df7b1df6f1f768d82e56977"
                    + "&client_id=38b95e1463c7a4b24af9f0db878a8641"
                    + "&scope=h:cup:cpf:completo";

            // Cria o cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Cria a requisição HTTP POST
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://authz-hml.identidadedigital.pr.gov.br/cidadao_authz/api/v2/token"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // Envia a requisição e obtém a resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Converte o JSON da resposta para o DTO usando Gson
            Gson gson = new Gson();
            AuthResponseDTO authResponse = gson.fromJson(response.body(), AuthResponseDTO.class);
            return authResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
