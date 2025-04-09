package celepar.automacao.util;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class CaptchaUtil {

    public String resolverCaptchaComOCR(String imagePath) {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Users\\samuelcjr\\AppData\\Local\\Programs\\Tesseract-OCR\\tessdata"); // Caminho para os arquivos de dados do Tesseract
        tesseract.setLanguage("eng");
        try {
            return tesseract.doOCR(new File(imagePath));
        } catch (TesseractException e) {
            e.printStackTrace();
            return null;
        }
    }

}
