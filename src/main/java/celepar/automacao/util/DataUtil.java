package celepar.automacao.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataUtil {

    public static String transformarData(String data) {
        if (data == null || data.length() != 8) {
            throw new IllegalArgumentException("A data deve estar no formato yyyyMMdd e não pode ser nula.");
        }

        // Extrai os componentes da data
        String ano = data.substring(0, 4);
        String mes = data.substring(4, 6);
        String dia = data.substring(6, 8);

        // Retorna a data no formato ddMMyyyy
        return dia + mes + ano;
    }

    public static String obterDataAtualFormatada() {
        // Obtém a data e hora atual
        LocalDateTime dataAtual = LocalDateTime.now();

        // Define o formato desejado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");

        // Retorna a data formatada como string
        return dataAtual.format(formatter);
    }

}
