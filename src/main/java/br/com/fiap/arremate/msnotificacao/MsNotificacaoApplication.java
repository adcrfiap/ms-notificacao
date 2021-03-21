package br.com.fiap.arremate.msnotificacao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class MsNotificacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNotificacaoApplication.class, args);
	}
}
