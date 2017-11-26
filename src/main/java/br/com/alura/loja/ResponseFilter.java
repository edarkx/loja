package br.com.alura.loja;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

/**
 * Classe criada para liberar o acesso, quando chamaado o rest por outro endereço
 * @author edmint
 * Referencia:
 * https://cursos.alura.com.br/forum/topico-access-control-allow-origin-ao-consumir-a-api-em-aplicacoes-externas-33003
 */
public class ResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
    }

}
