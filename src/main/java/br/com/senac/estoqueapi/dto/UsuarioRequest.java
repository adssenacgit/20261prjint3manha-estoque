package br.com.senac.estoqueapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequest(
        @NotBlank(message = "O nome do usuário é obrigatório")
        String usuarioNome,

        String empresaNome,

        @NotBlank(message = "O e-mail do usuário é obrigatório")
        @Email(message = "Informe um e-mail válido")
        String usuarioEmail,

        @NotBlank(message = "O CPF do usuário é obrigatório")
        String usuarioCpf,

        @NotBlank(message = "A senha do usuário é obrigatória")
        String usuarioSenha,

        Integer usuarioStatus
) {
}
