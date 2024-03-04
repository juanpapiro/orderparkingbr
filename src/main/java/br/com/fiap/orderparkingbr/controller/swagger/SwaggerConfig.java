package br.com.fiap.orderparkingbr.controller.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI usersApi() {

        Info info = new Info()
                .title("Api para reserva de estacionamento em parquímetros")
                .version("0.0.1")
                .description("Esta API expõe endpoints para gerenciar reservas de estacionamento em parquímetros");

        List<Tag> tags = Arrays.asList(
                new Tag().name("Reservas de estacionamento").description("Gerenciamento de reservas de estacionamento")
        );

        Server localServer = new Server();
        localServer.setUrl("http://localhost:8002");
        localServer.setDescription("Server URL local");

        return new OpenAPI().info(info).tags(tags).servers(Arrays.asList(localServer));

    }

}
