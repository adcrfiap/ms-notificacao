package br.com.fiap.arremate.msnotificacao.service;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SlackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlackService.class);

    @Value("${slack.webhook}")
    private String urlSlackWebook;

    public void sendMessageToSlack(String message) {
        process(message);
    }

    private void process(String message) {
        Payload payload = Payload.builder()
                .channel("#notificacao")
                .username("notificacao")
                .text(message)
                .build();

        try {
            WebhookResponse webhookResponse = Slack.getInstance().send(
                    urlSlackWebook,payload
            );
            LOGGER.info("code -> {}", webhookResponse.getCode());
            LOGGER.info("body -> {}", webhookResponse.getBody());
        } catch(IOException e) {
            LOGGER.info("Unexpected Error! WebHook -> {} - {}", urlSlackWebook, e.getMessage());
        }
    }

    private String exampleMessage() {
        return "Mensagem de teste";
    }
}
