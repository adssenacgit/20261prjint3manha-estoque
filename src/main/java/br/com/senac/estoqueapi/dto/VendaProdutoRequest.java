package br.com.senac.estoqueapi.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record VendaProdutoRequest(
        @NotNull(message = "O ID do produto é obrigatório")
        Integer produtoId,

        @NotNull(message = "A quantidade da venda é obrigatória")
        Integer vendaQuantidade,

        @NotNull(message = "O preço unitário da venda é obrigatório")
        BigDecimal vendaPrecoUnidade,

        Integer vendaProdutoStatus,

        @NotNull(message = "O ID da venda é obrigatório")
        Integer vendaId
) {
}
