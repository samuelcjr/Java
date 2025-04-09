package celepar.automacao.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import celepar.automacao.service.NotaPRService;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Config {

    public void abrirBrowser(boolean abrir, WebDriver driver) {

        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();

        if (abrir == true) {
            // Abre o navegador para executar o teste
            options.addArguments("--headless=false");
        } else {
            // Executa o teste sem abrir o navegador
            options.addArguments("--headless");
        }
        // Inicializa o driver
        NotaPRService.driver = new FirefoxDriver(options);
    }

    public void sleep() {
        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
