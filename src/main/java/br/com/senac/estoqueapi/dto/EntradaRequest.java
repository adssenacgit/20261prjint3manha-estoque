package br.com.senac.estoqueapi.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EntradaRequest(
        LocalDateTime entradaData,

        String entradaFornecedor,

        @NotNull(message = "O valor total da entrada é obrigatório")
        BigDecimal entradaValorTotal,

        Integer entradaStatus,

        @NotNull(message = "O ID do usuário é obrigatório")
        Integer usuarioId
) {
}
