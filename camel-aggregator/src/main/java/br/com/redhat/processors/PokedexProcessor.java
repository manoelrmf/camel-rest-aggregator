package br.com.redhat.processors;

import br.com.redhat.domain.Pokemon;
import br.com.redhat.utils.JacksonUtils;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.util.json.JsonObject;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

public class PokedexProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
        String nomePokemon = exchange.getIn().getHeader("nome", String.class);

        if(!StringUtils.isEmpty(nomePokemon)){
            exchange
                    .getIn()
                    .setBody(JacksonUtils.objectToJson(new Pokemon(nomePokemon)));
        }

	}
    
}