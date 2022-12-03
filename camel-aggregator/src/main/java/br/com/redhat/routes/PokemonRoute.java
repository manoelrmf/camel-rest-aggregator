package br.com.redhat.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PokemonRoute extends RouteBuilder {

    private static final String uri = "https://pokeapi.co/api/v2";
    private static final String path = "/pokemon/";

    @Override
    public void configure() throws Exception {
co
        from("direct:call-rest-pokemon")
                .routeId("pokemon-service")
                .removeHeaders("CamelHttp*")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .toD(uri+path+"${header.nome}");


    }
}
