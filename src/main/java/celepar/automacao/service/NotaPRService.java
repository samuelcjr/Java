package celepar.automacao.service;

import java.io.File;

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
	public static WebDriver driver;

	@Autowired
	public NotaPRService(NotaPRRepository notaPRRepository, AuthService authService, ConsultaService consultaService) {
		this.notaPRRepository = notaPRRepository;
		this.authService = authService;
		this.consultaService = consultaService;
	}

	public String usuarioCadastro(String cpf) {

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

		// Captura do CAPTCHA
		try {
			// Captura a imagem do CAPTCHA
			File captchaImage = driver.findElement(By.id("imagemCaptcha")).getScreenshotAs(OutputType.FILE);
			File destino = new File("captcha.png");
			FileUtils.copyFile(captchaImage, destino);

			// Resolve o CAPTCHA usando Tesseract
			CaptchaUtil captchaUtil = new CaptchaUtil();
			String captchaTexto = captchaUtil.resolverCaptchaComTesseract("captcha.png");
			// OCR.testarOCR();
			// OCR.melhoriaImagem01();

			// Preenche o campo do CAPTCHA com o texto retornado
			System.out.println("Texto do CAPTCHA: " + captchaTexto);
			driver.findElement(By.id("imagemSeguranca")).sendKeys(captchaTexto);

		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao processar o CAPTCHA.";
		}

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
		notaPRRepository.atualizarEmail("samuelcjr71@gmail.com", "samuelcjr71_" + DataUtil.obterDataAtualFormatada() + "@gmail.com");

		return "Passo 02 realizado com sucesso!";

	}

}
