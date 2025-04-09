package celepar.automacao.http;

import java.net.http.HttpClient;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpClientFactory {

    public static HttpClient createHttpClientIgnoringSSL() {
        try {
            // Configura um TrustManager que aceita todos os certificados
            TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    public X509Certificate[] getAcceptedIssuers() { return null; }
                }
            };

            // Configura o SSLContext para usar o TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Cria o HttpClient ignorando SSL
            return HttpClient.newBuilder()
                    .sslContext(sslContext)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao configurar HttpClient ignorando SSL", e);
        }
    }

}
