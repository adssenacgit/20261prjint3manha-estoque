package br.com.senac.estoqueapi.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record EntradaProdutoRequest(
        @NotNull(message = "O ID da entrada é obrigatório")
        Integer entradaId,

        @NotNull(message = "O ID do produto é obrigatório")
        Integer produtoId,

        @NotNull(message = "A quantidade é obrigatória")
        Integer quantidade,

        @NotNull(message = "O preço de compra é obrigatório")
        BigDecimal precoCompra,

        Integer entradaProdutoStatus
) {
}
