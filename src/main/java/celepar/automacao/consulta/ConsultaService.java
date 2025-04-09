package celepar.automacao.consulta;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import celepar.automacao.dto.AuthResponseDTO;
import celepar.automacao.dto.ConsultaPFDTO;
import celepar.automacao.http.HttpClientFactory;

@Service
public class ConsultaService {

    public ConsultaPFDTO consultarDadosPF(String cpf, AuthResponseDTO authResponseDTO) {
        try {
            // Cria o cliente HTTP
            // HttpClient client = HttpClient.newHttpClient();
            HttpClient client = HttpClientFactory.createHttpClientIgnoringSSL();

            // Cria a requisição HTTP GET
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://jbeap74hml03.sefa.parana/cup/api/v1/consulta/pf/" + cpf))
                    .header("Authorization", "Bearer " + authResponseDTO.getAccess_token())
                    .GET()
                    .build();

            // Envia a requisição e obtém a resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // Converte o JSON da resposta para o DTO usando Gson
            Gson gson = new Gson();
            ConsultaPFDTO consultaPFDTO = gson.fromJson(response.body(), ConsultaPFDTO.class);
            return consultaPFDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}