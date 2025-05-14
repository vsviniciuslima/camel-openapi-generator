package dev.vsviniciuslima.routePolicy;

import dev.vsviniciuslima.errorHandlerFactory.CustomErrorHandlerFactory;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.NamedNode;
import org.apache.camel.spi.RoutePolicy;
import org.apache.camel.spi.RoutePolicyFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Factory para criar e aplicar NewconErrorPolicy a todas as rotas Camel.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class NewconErrorPolicyFactory implements RoutePolicyFactory {
    private final CustomErrorHandlerFactory customErrorHandlerFactory;
    private final CamelContext camelContext;

    @Value("${newcon.error-handler.target-hosts:newcon-api}")
    private String[] targetHosts;

    @Value("${newcon.error-handler.enabled:true}")
    private boolean enabled;

    @PostConstruct
    public void init() {
        if (!enabled) {
            log.info("NewconErrorPolicy desabilitado via configuração");
            return;
        }

        // Registrar esta factory no CamelContext para que seja aplicada a todas as rotas
        camelContext.addRoutePolicyFactory(this);

        log.info("NewconErrorPolicyFactory registrado no CamelContext");
        log.info("Monitorando chamadas HTTP para hosts: {}", Arrays.toString(targetHosts));
    }

    @Override
    public RoutePolicy createRoutePolicy(CamelContext camelContext, String routeId, NamedNode route) {
        // Não aplicar às nossas próprias rotas de processamento de erro

        if (routeId != null && (
                routeId.equals("handleNewconError") ||
                        routeId.equals("consultarErroNewcon") ||
                        routeId.equals("logErroNewcon") ||
                        routeId.equals("capturarErroNewcon"))) {
            return null;
        }

        log.info("Criando NewconErrorPolicy para rota: {}", routeId);
        return new NewconErrorPolicy(routeId, customErrorHandlerFactory);
    }
}
