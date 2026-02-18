package dev.vsviniciuslima.routePolicy;

import dev.vsviniciuslima.client.ApiClient;
import dev.vsviniciuslima.client.api.PetsApi;
import dev.vsviniciuslima.client.model.CreatePet;
import dev.vsviniciuslima.client.model.ErrorsIdGet200Response;
import dev.vsviniciuslima.client.model.PetsPost200Response;
import dev.vsviniciuslima.client.model.PetsPost400Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewconErrorRoutePolicyProcessor implements Processor {
    private final PetsApi petsApi;

    @Value("${server.port}")
    private final Integer serverPort = 8080;

    private final String baseUrl = "http://localhost:" + serverPort;

    @Override
    public void process(Exchange exchange) {
        final PetsPost400Response petsPost400Response = exchange.getMessage().getBody(PetsPost400Response.class);

        log.info("Conectando ao Newcon para buscar detalhes do erro {}", petsPost400Response.getErrorId());

        updateBasePath(baseUrl);

        getErrorDetails(petsPost400Response)
                .or(() -> {
                    log.info("Não foi possível recuperar detalhes do erro, tentando recuperar de outro servidor.");
                    updateBasePath(baseUrl + "/v2");
                    return getErrorDetails(petsPost400Response);
                })
                .ifPresentOrElse(
                    errorDetails -> log.info("Detalhes do erro recuperados com sucesso: {}", errorDetails),
                    () -> log.error("Não foi possível recuperar detalhes do erro.")
                );
    }

    private void updateBasePath(String basePath) {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(basePath);
        petsApi.setApiClient(apiClient);
    }

    private Optional<ErrorsIdGet200Response> getErrorDetails(PetsPost400Response petsPost400Response) {
        try {

            PetsPost200Response petsPost200Response = petsApi.petsPost(false, new CreatePet());

            ErrorsIdGet200Response foo = petsApi.errorsIdGet("foo");

            return Optional.of(petsApi.errorsIdGet(petsPost400Response.getErrorId()));
        } catch (RestClientResponseException e) {
            return Optional.empty();
        }
    }


}
