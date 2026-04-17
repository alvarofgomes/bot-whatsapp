package com.whatsapp.automacao;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class WhatsAppService {

    private static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    private static final String FROM_WHATSAPP = "whatsapp:+14155238886";

    private boolean inicializado = false;

    private void inicializar() {
        if (!inicializado) {
            if (ACCOUNT_SID == null || AUTH_TOKEN == null) {
                throw new IllegalStateException("Variaveis TWILIO_ACCOUNT_SID e TWILIO_AUTH_TOKEN nao configuradas.");
            }
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            inicializado = true;
        }
    }

    public void enviarMensagem(String telefoneDestino, String mensagem) {
        try {
            inicializar();

            Message message = Message.creator(
                    new PhoneNumber("whatsapp:+" + telefoneDestino),
                    new PhoneNumber(FROM_WHATSAPP),
                    mensagem
            ).create();

            System.out.println("Enviado para " + telefoneDestino);
            System.out.println("SID: " + message.getSid());
            System.out.println("-----------------------------------");

        } catch (Exception e) {
            System.err.println("Erro ao enviar para " + telefoneDestino + ": " + e.getMessage());
        }
    }
}