# Bot WhatsApp — Contagem Regressiva de Viagem

A namorada de um amigo meu ficava mandando mensagem todo dia no grupo contando quantos dias faltavam pra viagem que íamos fazer juntos. Aí resolvi automatizar isso pra ela — metade brincadeira, metade oportunidade de colocar em prática uma integração real com Spring Boot e a API do Twilio. 😂

## Como funcionava

A cada 12 horas (8h e 20h), o bot enviava automaticamente uma mensagem para todos os participantes informando quantos dias, horas e minutos faltavam para a viagem. No dia da viagem, enviava uma mensagem especial e encerrava.

```
🌴 Fala galera!

⏳ Faltam 12 dias 4 horas 30 minutos
pra nossa viagem! 😎
```

## Prints

![Print do bot funcionando](print_whatsapp.jpg)

## Tech Stack

- **Java 21**
- **Spring Boot 4.0.5**
- **Twilio API** — envio de mensagens via WhatsApp

## Arquitetura

```
WhatsappApplication       → ponto de entrada, configura a data e os telefones
AgendadorMensagem         → agenda os envios com ScheduledExecutorService (a cada 12h)
ContadorViagem            → monta a mensagem com a contagem regressiva
WhatsAppService           → integração com a API do Twilio
```

## Variáveis de ambiente necessárias

| Variável              | Descrição                  |
|-----------------------|----------------------------|
| `TWILIO_ACCOUNT_SID`  | Account SID da conta Twilio |
| `TWILIO_AUTH_TOKEN`   | Auth Token da conta Twilio  |

## Como rodar

```bash
# Configure as variáveis de ambiente
export TWILIO_ACCOUNT_SID=seu_sid
export TWILIO_AUTH_TOKEN=seu_token

# Suba o projeto
./mvnw spring-boot:run
```

> Requer uma conta Twilio com o Sandbox de WhatsApp ativado e os números de destino cadastrados no sandbox.
