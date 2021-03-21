package br.com.fiap.arremate.msnotificacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IntensaoCompra {

    private Produto produto;

    private Comprador comprador;

    private String descricao;

    private BigDecimal valorEstimado;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate data;

}
