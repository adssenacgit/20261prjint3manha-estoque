package br.com.senac.estoqueapi.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VendaRequest(
        LocalDateTime vendaData,

        @NotNull(message = "O valor total da venda é obrigatório")
        BigDecimal vendaValorTotal,

        Integer vendaStatus,

        @NotNull(message = "O ID do usuário é obrigatório")
        Integer usuarioId
) {
}
