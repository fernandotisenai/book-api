package br.com.bookapi.resources;

import br.com.bookapi.models.Autor;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author user
 */
@Path("autores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AutorResources {
    
    static List<Autor> autores = new ArrayList<>();
    
    @GET
    public List<Autor> getAutores() {
        return autores;
    }
    
    @POST
    public Response addAutor(Autor autor) {
        autor.setId(UUID.randomUUID());
        autores.add(autor);        
        // return autor;
        return Response
                .status(Response.Status.CREATED)
                .entity(autor)
                .build();
    }
    
    @GET
    @Path("{id}")
    public Autor getAutor(@PathParam("id") UUID id) {
        return findAutor(id);        
    }
        
    @DELETE
    @Path("{id}")
    public void removeAutor(@PathParam("id") UUID id) {
        Autor autor = findAutor(id);                 
        autores.remove(autor);
    }
    
    @PUT
    @Path("{id}")
    public Autor updateAutor(@PathParam("id") UUID id, Autor a) {
        Autor autor = findAutor(id); 
        autor.setNome(a.getNome());
        return autor;
    }
        
    public Autor findAutor(UUID id) {
        Autor autor = null;   
        for(Autor a : autores) {
            if(a.getId().equals(id)) {
                autor = a;
            }
        }
        return autor;        
    }
} 