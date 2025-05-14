package dev.vsviniciuslima.application.config;

import org.apache.camel.Configuration;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CamelServletConfig {
    @Bean
    public ServletRegistrationBean<CamelHttpTransportServlet> camelServletRegistrationBean() {
        final CamelHttpTransportServlet camelServlet = new CamelHttpTransportServlet();
        camelServlet.setServletName("CamelServlet");

        final ServletRegistrationBean<CamelHttpTransportServlet> registration = new ServletRegistrationBean<>();
        registration.setServlet(camelServlet);
        registration.setUrlMappings(List.of("/api/*"));
        registration.setName("CamelServlet");
        registration.setLoadOnStartup(-1);
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return registration;
    }

}
