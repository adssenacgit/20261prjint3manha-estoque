package br.com.senac.estoqueapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ProdutoRequest(
        @NotBlank(message = "O nome do produto é obrigatório")
        String produtoNome,

        Integer produtoQuantidade,

        @NotNull(message = "O preço do produto é obrigatório")
        BigDecimal produtoPreco,

        @NotNull(message = "O código do produto é obrigatório")
        Integer produtoCodigo,

        LocalDate produtoDataValidade,

        Integer produtoStatus
) {
}
