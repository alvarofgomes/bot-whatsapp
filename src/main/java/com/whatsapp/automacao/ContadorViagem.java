package com.whatsapp.automacao;

import java.time.Duration;
import java.time.LocalDateTime;

public class ContadorViagem {

    public static String montarMensagem(LocalDateTime dataViagem) {
        LocalDateTime agora = LocalDateTime.now();
        
        //apenas lançada no dia da viagem
        if (!agora.isBefore(dataViagem)) {
            return "A viagem de vocês chegou!";
        }

        Duration duracao = Duration.between(agora, dataViagem);
        //Regra de calculo
        long totalSegundos = duracao.getSeconds();
        long dias = totalSegundos / 86400;
        long horas = (totalSegundos % 86400) / 3600;
        long minutos = (totalSegundos % 3600) / 60;
        long segundos = totalSegundos % 60;

        return String.format(
        	    "🌴 Fala galera!\n\n" +
        	    "⏳ Faltam %d dias %d horas %d minutos\n" +
        	    "pra nossa viagem! 😎",
        	    dias, horas, minutos
        	);
    }
}