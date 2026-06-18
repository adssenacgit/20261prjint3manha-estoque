package br.com.senac.estoqueapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Estoque API",
                version = "1.0.0",
                description = "API REST para CRUD de usuários, produtos, entradas e vendas com exclusão lógica por status.",
                contact = @Contact(name = "Senac")
        )
)
public class OpenApiConfig {
}
