package celepar.automacao.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import celepar.automacao.autenticacao.AuthService;
import celepar.automacao.config.Config;
import celepar.automacao.consulta.ConsultaService;
import celepar.automacao.dto.AuthResponseDTO;
import celepar.automacao.dto.ConsultaPFDTO;
import celepar.automacao.repository.NotaPRRepository;
import celepar.automacao.util.CaptchaUtil;
import celepar.automacao.util.DataUtil;
import celepar.automacao.util.StringUtil;

@Service
public class NotaPRService {

    Config config = new Config();
    CaptchaUtil captchaUtil = new CaptchaUtil();

    private NotaPRRepository notaPRRepository;
    private AuthService authService;
    private ConsultaService consultaService;

    @Autowired
    public NotaPRService(NotaPRRepository notaPRRepository, AuthService authService, ConsultaService consultaService) {
        this.notaPRRepository = notaPRRepository;
        this.authService = authService;
        this.consultaService = consultaService;
    }

    public static WebDriver driver;

    public String usuarioCadastro(String cpf) {

        //teste Ruy

        cpf = StringUtil.formataCPF(cpf);

        // Inicia o cadastro do usuário
        config.abrirBrowser(true, driver);

        // Acessa a URL do sistema
        driver.get("http://wfly8hml01.sefa.parana/nfprweb/publico/CadastroConsumidor");

        // Preenche incicialmente o CPF
        driver.findElement(By.id("CPF")).sendKeys(cpf);

        // Serviço para buscar Data de Nascimento, Nome Completo, Nome da Mãe e CEP
        // 1 - Gerar o token
        AuthResponseDTO authResponseDTO = authService.getToken();

        // 2 - Com o token obtido, realizar a chamada para o serviço de consulta
        ConsultaPFDTO consultaPFDTO = consultaService.consultarDadosPF(cpf, authResponseDTO);

        driver.findElement(By.id("dtNascimento")).sendKeys(DataUtil.transformarData(consultaPFDTO.getDtNasc()));
        driver.findElement(By.id("nmUsuario")).sendKeys(consultaPFDTO.getNomeContribuinte());
        driver.findElement(By.id("nmMae")).sendKeys(consultaPFDTO.getNomeMae());
        driver.findElement(By.id("CEPLogradouro")).sendKeys(consultaPFDTO.getCep());
        driver.findElement(By.id("email")).sendKeys("samuelcjr71@gmail.com");
        driver.findElement(By.id("confirmeEmail")).sendKeys("samuelcjr71@gmail.com");

        // Captura do Captcha
        try {
            File captchaImage = driver.findElement(By.id("imagemCaptcha")).getScreenshotAs(OutputType.FILE);
            File destino = new File("captcha.png");
            FileUtils.copyFile(captchaImage, destino);

            // Aumenta a resolução da imagem
             BufferedImage originalImage = ImageIO.read(new File("captcha.png"));
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();
            BufferedImage resizedImage = new BufferedImage(width * 2, height * 2, originalImage.getType());
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, width * 2, height * 2, null);
            g.dispose();
            ImageIO.write(resizedImage, "png", new File("captcha_resized.png")); 

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Resolve o captcha com OCR

        String captchaTexto = captchaUtil.resolverCaptchaComOCR("captcha_resized.png");
        System.out.println("Texto do captcha: " + captchaTexto);
        driver.findElement(By.id("imagemSeguranca")).sendKeys(captchaTexto);

        driver.manage().window().maximize();

        return "CPF: " + cpf + " do passo 01 realizado com sucesso!";

    }

    public String usuarioCadastro02(String urlEmail) {

        // Continuação do cadastro do usuário
        config.abrirBrowser(false, driver);

        driver.get(urlEmail);
        driver.findElement(By.id("senha")).sendKeys("teste1234");
        driver.findElement(By.id("senhaC")).sendKeys("teste1234");
        driver.findElement(By.cssSelector(".button")).click();

        // Atualiza o email na base de dados
        notaPRRepository.atualizarEmail("samuelcjr71@gmail.com",
                "samuelcjr71_" + DataUtil.obterDataAtualFormatada() + "@gmail.com");

        return "Passo 02 realizado com sucesso!";

    }

}
