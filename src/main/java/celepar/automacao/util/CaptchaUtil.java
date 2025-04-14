package celepar.automacao.util;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class CaptchaUtil {

	
	/**
	 * Resolve captcha de uma imagem
	 * @param caminho da imagem
	 * @return texto resolvido
	 */
    public String resolverCaptchaComTesseract(String imagePath) {
        Tesseract tesseract = new Tesseract();

        // Configura o caminho para o diretório tessdata
        // tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");
        tesseract.setDatapath("C:\\Users\\samuelcjr\\AppData\\Local\\Programs\\Tesseract-OCR\\tessdata");
        tesseract.setLanguage("eng");

        try {
            // Realiza o OCR na imagem
            return tesseract.doOCR(new File(imagePath));
        } catch (TesseractException e) {
            e.printStackTrace();
            return null;
        }
    }
}

