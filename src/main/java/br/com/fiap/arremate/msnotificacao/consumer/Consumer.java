package br.com.fiap.arremate.msnotificacao.consumer;


import br.com.fiap.arremate.msnotificacao.dto.IntensaoCompra;
import br.com.fiap.arremate.msnotificacao.email.EmailConfig;
import br.com.fiap.arremate.msnotificacao.service.SlackService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class Consumer {

    private EmailConfig emailConfig;

    private SlackService slackService;

    @RabbitListener(queues = "arremate.queue")
    private void enviarNotificacao(String notificacao){
        System.out.println("Objeto da fila - > " + new Gson().fromJson(notificacao, IntensaoCompra.class));
        emailConfig.send(criarCorpoEmail(new Gson().fromJson(notificacao, IntensaoCompra.class)));
        slackService.sendMessageToSlack(criarCorpoEmail(new Gson().fromJson(notificacao, IntensaoCompra.class)));
    }

    private String criarCorpoEmail(IntensaoCompra intensao) {
        StringBuilder intensaoBuilder = new StringBuilder();

        intensaoBuilder.append("Intensão: " + intensao.getDescricao())
                .append(System.lineSeparator())
                .append("Produto: " + intensao.getProduto().getNome())
                .append(System.lineSeparator())
                .append("Categoria: " + intensao.getProduto().getCategoria().getNome())
                .append(System.lineSeparator())
                .append("Marca: " + intensao.getProduto().getMarca().getNome())
                .append(System.lineSeparator())
                .append("Modelo: " + intensao.getProduto().getModelo().getNome())
                .append(System.lineSeparator())
                .append("Valor estimado: " + intensao.getValorEstimado())
                .append(System.lineSeparator())
                .append("Comprador: " + intensao.getComprador().getNome());

        return intensaoBuilder.toString();
    }
}
