package br.com.bookapi.resources;

import br.com.bookapi.models.Autor;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
@Stateless
@Path("autores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AutorResources {

    @PersistenceContext(unitName = "BookPU")
    EntityManager entityManager;

    @GET
    public List<Autor> getAutores() {
        return entityManager
                .createQuery("SELECT a FROM Autor a", Autor.class)
                .getResultList();
    }

    @POST
    public Response addAutor(Autor autor) {
        entityManager.persist(autor);
        return Response
                .status(Response.Status.CREATED)
                .entity(autor)
                .build();
    }

    @GET
    @Path("{id}")
    public Autor getAutor(@PathParam("id") UUID id) {
        return entityManager.find(Autor.class, id);
    }

    @DELETE
    @Path("{id}")
    public void removeAutor(@PathParam("id") UUID id) {
        Autor autor = entityManager.find(Autor.class, id);
        entityManager.remove(autor);
    }

    @PUT
    @Path("{id}")
    public Autor updateAutor(@PathParam("id") UUID id, Autor a) {
        a.setId(id);
        entityManager.merge(a);
        return a;
    }
}
