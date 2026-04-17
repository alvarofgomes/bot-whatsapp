package com.whatsapp.automacao;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AgendadorMensagem {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final WhatsAppService whatsappService = new WhatsAppService();

    public void iniciar(LocalDateTime dataViagem, List<String> telefonesDestino, LocalTime horarioPrimeiroEnvio) {

        long atrasoInicial = calcularAtrasoInicial(horarioPrimeiroEnvio);

        System.out.println("Atraso inicial em segundos: " + atrasoInicial);

        scheduler.scheduleAtFixedRate(() -> {
            try {
                LocalDateTime agora = LocalDateTime.now();
                System.out.println("Executando envio em: " + agora);

                if (!agora.isBefore(dataViagem)) {
                    String mensagemFinal = "Hoje é o grande dia! Boa viagem!";

                    for (String telefone : telefonesDestino) {
                        whatsappService.enviarMensagem(telefone, mensagemFinal);
                    }

                    System.out.println("Encerrando envios.");
                    scheduler.shutdown();
                    return;
                }

                String mensagem = ContadorViagem.montarMensagem(dataViagem);

                for (String telefone : telefonesDestino) {
                    whatsappService.enviarMensagem(telefone, mensagem);
                }

            } catch (Exception e) {
                System.err.println("Erro ao enviar mensagens: " + e.getMessage());
                e.printStackTrace();
            }

        }, atrasoInicial, 60, TimeUnit.SECONDS); // teste: repete a cada 60 segundos
    }

    private long calcularAtrasoInicial(LocalTime horarioPrimeiroEnvio) {
        LocalDateTime agora = LocalDateTime.now();

        LocalDateTime proximoEnvio = agora.withHour(horarioPrimeiroEnvio.getHour())
                .withMinute(horarioPrimeiroEnvio.getMinute())
                .withSecond(horarioPrimeiroEnvio.getSecond())
                .withNano(0);

        if (!proximoEnvio.isAfter(agora)) {
            proximoEnvio = proximoEnvio.plusDays(1);
        }

        return Duration.between(agora, proximoEnvio).getSeconds();
    }
}