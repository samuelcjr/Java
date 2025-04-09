package celepar.automacao.util;

public class StringUtil {

    public static String formataCPF(String input) {
        if (input == null) {
            throw new IllegalArgumentException("A entrada não pode ser nula.");
        }
        // Substitui todos os caracteres que não são números por uma string vazia
        return input.replaceAll("[^0-9]", "");
    }

}
