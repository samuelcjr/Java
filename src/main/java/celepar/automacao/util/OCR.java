package celepar.automacao.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OCR {
    private static final String INPUT = "captcha.png";
    private static final String OUTPUT = "captcha-out.png";
    private static final String TESSERACT_BIN = "C:\\Users\\samuelcjr\\AppData\\Local\\Programs\\Tesseract-OCR\\tesseract.exe";
    private static final String TESSERACT_OUTPUT = "out.txt";
    private static final int WHITE = 0x00FFFFFF, BLACK = 0x00000000;

    public static void testarOCR() throws Exception {
        BufferedImage image = ImageIO.read(new FileInputStream(INPUT));
        int average = 0;

        image = createGrayscalePic(image);

        for (int row = 0; ++row < image.getHeight();)
            for (int column = 0; ++column < image.getWidth();)
                average += image.getRGB(column, row) & 0x000000FF;
        average /= image.getWidth() * image.getHeight();

        for (int row = 0; ++row < image.getHeight();)
            for (int column = 0; ++column < image.getWidth();)
                if ((image.getRGB(column, row) & 0x000000FF) <= average * .80)
                    image.setRGB(column, row, BLACK);
                else
                    image.setRGB(column, row, WHITE);

        int consecutiveWhite = 0;

        for (int row = 0; ++row < image.getHeight();)
            for (int column = 0; ++column < image.getWidth();)
                if ((image.getRGB(column, row) & 0x000000FF) == 255)
                    consecutiveWhite++;
                else {
                    if (consecutiveWhite < 3 && column > consecutiveWhite)
                        for (int col = column - consecutiveWhite; col < column; col++)
                            image.setRGB(col, row, BLACK);
                    consecutiveWhite = 0;
                }
        consecutiveWhite = 0;

        for (int column = 0; ++column < image.getWidth();)
            for (int row = 0; ++row < image.getHeight();)
                if ((image.getRGB(column, row) & 0x000000FF) == 255)
                    consecutiveWhite++;
                else {
                    if (consecutiveWhite < 2 && row > consecutiveWhite)
                        for (int r = row - consecutiveWhite; r < row; r++)
                            image.setRGB(column, r, BLACK);
                    consecutiveWhite = 0;
                }

        for (int row = 0; ++row < image.getHeight();)
            for (int column = 0; ++column < image.getWidth();)
                if ((image.getRGB(column, row) & WHITE) == WHITE) {
                    int height = countVerticalWhite(image, column, row);
                    int width = countHorizontalWhite(image, column, row);
                    if ((width * height <= 10) || (width == 1) || (height == 1))
                        image.setRGB(column, row, BLACK);
                }

        for (int row = 0; ++row < image.getHeight();)
            for (int column = 0; ++column < image.getWidth();)
                if ((image.getRGB(column, row) & WHITE) == WHITE) {
                    int height = countVerticalWhite(image, column, row);
                    int width = countHorizontalWhite(image, column, row);
                    if ((width * height <= 10) || (width == 1) || (height == 1))
                        image.setRGB(column, row, BLACK);
                }

        for (int row = 0; ++row < image.getHeight();)
            for (int column = 0; ++column < image.getWidth();)
                if ((image.getRGB(column, row) & WHITE) != WHITE)
                    if (countBlackNeighbors(image, column, row) <= 3)
                        image.setRGB(column, row, WHITE);

        ImageIO.write(image, "png", new File(OUTPUT));
        Process tesseractProc = Runtime.getRuntime()
                .exec(TESSERACT_BIN + " " + OUTPUT + " " + TESSERACT_OUTPUT + " nobatch letters");
        tesseractProc.waitFor();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(TESSERACT_OUTPUT + ".txt")));
        System.out.println("OCR CAPTCHA: " + reader.readLine());
        reader.close();
    }

    private static int countVerticalWhite(BufferedImage image, int x, int y) {
        return (countAboveWhite(image, x, y) + countBelowWhite(image, x, y)) + 1;
    }

    private static int countAboveWhite(BufferedImage image, int x, int y) {
        int aboveWhite = 0;
        y--;
        while (y-- > 0)
            if ((image.getRGB(x, y) & WHITE) == WHITE)
                aboveWhite++;
            else
                break;
        return aboveWhite;
    }

    private static int countBelowWhite(BufferedImage image, int x, int y) {
        int belowWhite = 0;
        y++;
        while (y < image.getHeight())
            if ((image.getRGB(x, y++) & WHITE) == WHITE)
                belowWhite++;
            else
                break;
        return belowWhite;
    }

    private static int countHorizontalWhite(BufferedImage image, int x, int y) {
        return (countLeftWhite(image, x, y) + countRightWhite(image, x, y)) + 1;
    }

    private static int countLeftWhite(BufferedImage image, int x, int y) {
        int leftWhite = 0;
        x--;
        while (x-- > 0)
            if ((image.getRGB(x, y) & WHITE) == WHITE)
                leftWhite++;
            else
                break;
        return leftWhite;
    }

    private static int countRightWhite(BufferedImage image, int x, int y) {
        int rightWhite = 0;
        x++;
        while (x < image.getWidth())
            if ((image.getRGB(x++, y) & WHITE) == WHITE)
                rightWhite++;
            else
                break;
        return rightWhite;
    }

    private static int countBlackNeighbors(BufferedImage image, int x, int y) {
        int numBlacks = 0;
        if (pixelColor(image, x - 1, y) != WHITE)
            numBlacks++;
        if (pixelColor(image, x - 1, y + 1) != WHITE)
            numBlacks++;
        if (pixelColor(image, x - 1, y - 1) != WHITE)
            numBlacks++;
        if (pixelColor(image, x, y + 1) != WHITE)
            numBlacks++;
        if (pixelColor(image, x, y - 1) != WHITE)
            numBlacks++;
        if (pixelColor(image, x + 1, y) != WHITE)
            numBlacks++;
        if (pixelColor(image, x + 1, y + 1) != WHITE)
            numBlacks++;
        if (pixelColor(image, x + 1, y - 1) != WHITE)
            numBlacks++;
        return numBlacks;
    }

    private static int pixelColor(BufferedImage image, int x, int y) {
        if (x >= image.getWidth() || x < 0 || y < 0 || y >= image.getHeight())
            return WHITE;
        return image.getRGB(x, y) & WHITE;
    }

    private static BufferedImage createGrayscalePic(BufferedImage raw) {
        BufferedImage temp = new BufferedImage(raw.getWidth(), raw.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = temp.getGraphics();
        g.drawImage(raw, 0, 0, null);
        g.dispose();
        return temp;
    }

    /////////////////////////////////////////////////////////////////////////////////

    static final int DELTA = 3;

    public static boolean isEligible(BufferedImage img, int x, int y) {

        int left = x - 1;
        while (left < 0 && x - left < 2 * DELTA) {
            if (img.getRGB(left, y) == Color.WHITE.getRGB()) {
                break;
            }
            left--;
        }
        if (left < 0) {
            return false;
        }
        int right = x + 1;

        while (right < img.getWidth() && right - left < 2 * DELTA) {
            if (img.getRGB(right, y) == Color.WHITE.getRGB()) {
                break;
            }
            right++;
        }
        if (right > img.getWidth()) {
            return false;
        }
        int top = y - 1;
        while (top > 0 && y - top < 2 * DELTA) {
            if (img.getRGB(x, top) == Color.WHITE.getRGB()) {
                break;
            }
            top--;
        }
        if (top < 0) {
            return false;
        }
        int bottom = y + 1;
        while (bottom < img.getHeight() && bottom - top < 2 * DELTA) {
            if (img.getRGB(x, bottom) == Color.WHITE.getRGB()) {
                break;
            }
            bottom++;
        }
        if (bottom > img.getHeight()) {
            return false;
        }

        int width = right - left;
        int height = bottom - top;
        if (width >= DELTA && height >= DELTA) {
            return true;
        }
        return false;

    }

    public static BufferedImage cleanImage(BufferedImage source) {
        BufferedImage clone = new BufferedImage(source.getWidth(),
                source.getHeight(), source.getType());
        Graphics2D g2d = clone.createGraphics();
        g2d.drawImage(source, 0, 0, null);
        g2d.dispose();
        for (int i = 0; i < clone.getWidth(); i++) {
            for (int j = 0; j < clone.getHeight(); j++) {
                int rgb = clone.getRGB(i, j);
                if (rgb == Color.WHITE.getRGB()) {
                    continue;
                }
                if (isEligible(clone, i, j)) {
                    continue;
                } else {
                    clone.setRGB(i, j, Color.WHITE.getRGB());
                }

            }
        }

        return clone;

    }

    public static String cleanResult(String result) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length(); i++) {
            if (Character.isAlphabetic(result.charAt(i)) || Character.isDigit(result.charAt(i))) {
                sb.append(result.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void melhoriaImagem01() throws IOException, TesseractException {
        BufferedImage image = ImageIO.read(new File("captcha.png"));
        BufferedImage clean = cleanImage(image);
        ImageIO.write(clean, "png", new File("captcha-clean.png"));
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Users\\samuelcjr\\AppData\\Local\\Programs\\Tesseract-OCR\\tessdata");
        String result = tesseract.doOCR(clean);
        result = cleanResult(result);

        System.out.println("melhoriaImagem01() result : " + result);
    }

}
