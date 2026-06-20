package com.whatsapp.automacao;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WhatsappApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(WhatsappApplication.class, args);
    }

    @Override
    public void run(String... args) {

        LocalDateTime dataViagem = LocalDateTime.of(2026, 5, 1, 9, 0);

        List<String> telefones = Arrays.asList(
            "5581900000001",
            "5581900000002",
            "5581900000003",
            "5581900000004"
        );

        LocalTime horarioPrimeiroEnvio = LocalTime.of(8, 0);

        AgendadorMensagem agendador = new AgendadorMensagem();
        agendador.iniciar(dataViagem, telefones, horarioPrimeiroEnvio);

        System.out.println("MODO PRODUCAO 8H/20H ATIVO");
        System.out.println("Sistema online automatico iniciado!");
    }
}