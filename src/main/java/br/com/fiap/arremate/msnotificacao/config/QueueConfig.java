package br.com.fiap.arremate.msnotificacao.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Bean
    public Queue queue() {
        return QueueBuilder
                .durable("arremate.queue")
                .build();
    }
}
