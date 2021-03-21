package br.com.fiap.arremate.msnotificacao.service;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@Service
public class SlackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlackService.class);

    @Value("classpath*:/slack/config.txt")
    private Resource resourceFile;

    public void sendMessageToSlack(String message) {
        process(message);
    }

    private void process(String message) {
        String urlSlackWebhook = getURLSlackWebhook() + "B01RZ0003L2/ukU2y6XyPTo7xQFnnZVp325J";

        Payload payload = Payload.builder()
                .channel("#notificacao")
                .username("ms-notificacao")
                .text(message)
                .build();

        try {
            WebhookResponse webhookResponse = Slack.getInstance().send(
                    urlSlackWebhook,payload
            );
            LOGGER.info("code -> {}", webhookResponse.getCode());
            LOGGER.info("body -> {}", webhookResponse.getBody());
        } catch(IOException e) {
            LOGGER.info("Unexpected Error! WebHook -> {} - {}", urlSlackWebhook, e.getMessage());
        }
    }

    private String getURLSlackWebhook() {
        try {
            File file = resourceFile.getFile();
            Scanner scanner = new Scanner(file);
            return scanner.nextLine();
        } catch (IOException e) {
            LOGGER.error("Failed to get URL slack webhook - {}", e.getMessage());
        }
        return null;
    }
}
