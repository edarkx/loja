package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;

@Path("carrinhos")
public class CarrinhoResource {

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//public String busca(
	public Response busca(
			@PathParam("id") long id) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		//return carrinho.toXML();
		//return carrinho.toJSON();
		
		return Response.status(200).entity(
				carrinho.toJSON()).build();
	}
	
	/**
	 * neste metodo retornar um xml para usar no ClientTest.java
	 */
	
	@Path("/xml/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String find(
		@PathParam("id") long id) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		return carrinho.toXML();
		//return carrinho.toJSON();
		
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo) {
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		new CarrinhoDAO().adiciona(carrinho);
		//devolvendo 201 no code
		URI uri = URI.create("/carrinhos/" + carrinho.getId());
		//return "<status>sucesso</status>";
		//return Response.created(uri).build();
		
		return Response.created(uri).build();
	}
}
