package br.com.alura.loja;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;



public class Servidor {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		HttpServer server = inicializaServidor();
		System.out.println("Servidor Rodando");
		System.out.println("De o 'Enter' para parar");
		System.in.read();
		server.stop();
		
	}

	static HttpServer inicializaServidor() {
		URI uri = URI.create("http://localhost:8080/");
		ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
		config.register(ResponseFilter.class);
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config  );
		return server;
	}

}
