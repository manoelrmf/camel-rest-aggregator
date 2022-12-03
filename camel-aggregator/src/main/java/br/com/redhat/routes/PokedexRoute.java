package br.com.redhat.routes;


import br.com.redhat.processors.PokedexProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PokedexRoute extends RouteBuilder {

    private final static String uri = "http://localhost:8082/pokemon";
    private final static String path = "/pokedex";

    @Override
    public void configure() throws Exception {

        from("direct:call-rest-pokedex")
                .log("[INTEGRACAO-POKEDEX] Inserindo na pokedex")
                .routeId("pokedex-service")
                .to("direct:call-rest-pokedex-autors")
                 .removeHeaders("CamelHttp*")
                 .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                 .setHeader("Content-Type", constant("application/json"))
                .process(new PokedexProcessor())
                .toD(uri+path);

        from("direct:call-rest-pokedex-autors")
                .log("[INTEGRACAO-AUTORES] consultado autores")
                .routeId("pokedex-service-autores")
                .removeHeaders("CamelHttp*")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader("Content-Type", constant("application/json"))
                .toD("http://localhost:8081/authors");




    }
}
