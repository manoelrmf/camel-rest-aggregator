package br.com.redhat.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestDefinition;
import org.springframework.stereotype.Component;

@Component
public class RestRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		restConfiguration().host("0.0.0.0").port(8080).bindingMode(RestBindingMode.auto);

		RestDefinition rest = rest("/integration")
			.get("/authors")
				.route().routeId("rest-all-authors")
				.to("direct:call-rest-all")
			.endRest()
			.get("/authors/{name}")
				.route().routeId("rest-author-by-name")
				.to("direct:call-rest-author")
			.endRest();
		getPokemon(rest);
		getPokedex(rest);
	}

	private void getPokemon(RestDefinition rest){
		rest.get("/pokemon/{nome}").route().routeId("rest-pokemon-by-name")
				.to("direct:call-rest-pokemon").endRest();

	}

	private void getPokedex(RestDefinition rest){
		rest.get("/pokedex/{nome}").route().routeId("rest-pokedex")
				.to("direct:call-rest-pokedex").endRest();

	}
}