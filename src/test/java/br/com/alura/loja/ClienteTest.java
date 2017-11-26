package br.com.alura.loja;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;

public class ClienteTest {
	
//	@Test
//	public void testaConexao()
//	{
//		Client cliente = ClientBuilder.newClient();
//		WebTarget target = cliente.target("http://www.mocky.io");
//		String conteudo = target.path("/v2/52aaf5bbee7ba8c60329fb7b").request().get(String.class);
//		System.out.println(conteudo);
//		Assert.assertTrue(conteudo.contains("Rua Vergueiro 3185"));
//	}
	
	HttpServer server;
	
	@Before
	public void startServidor() {
		server = Servidor.inicializaServidor();
	}
	
	@After
	public void kill()
	{
		server.stop();
	}
	
	@Test
	public void testaCarrinho()
	{

		
		Client cliente = ClientBuilder.newClient();
		WebTarget target = cliente.target("http://localhost:8080/");
		String conteudo = target.path("/carrinhos/xml/1").request().get(String.class);
		System.out.println(conteudo);
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		
		//verificar janela do JUnit para ver se ele achou o conteudo retornado na tag <rua> do xml
		Assert.assertEquals("RuaVergueiro3185,8andar", carrinho.getRua());
		

		

	}
}
