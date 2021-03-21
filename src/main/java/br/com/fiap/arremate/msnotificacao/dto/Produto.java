package br.com.fiap.arremate.msnotificacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Produto {

    private String nome;

    private CategoriaDTO categoria;

    private MarcaDTO marca;

    private ModeloDTO modelo;

}
