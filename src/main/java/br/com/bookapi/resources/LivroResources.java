package br.com.bookapi.resources;

import br.com.bookapi.models.Livro;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author User
 */
@Stateless
@Path("livros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LivroResources {

    @PersistenceContext(unitName = "BookPU")
    EntityManager entityManager;

    @GET
    public List<Livro> getLivros(@QueryParam("titulo") String titulo) {
        if (titulo == null || titulo.isBlank()) {
            return entityManager
                    .createNamedQuery("Livro.findAll", Livro.class)
                    .getResultList();
        } else {
            return entityManager
                    .createNamedQuery("Livro.findByTitulo")
                    .setParameter("titulo", titulo)
                    .getResultList();
        }
    }

    @POST
    public Livro addLivro(Livro livro) {
        entityManager.persist(livro);
        return livro;
    }

    @GET
    @Path("{id}")
    public Livro getLivro(@PathParam("id") UUID id) {
        return entityManager.find(Livro.class, id);
    }

    @DELETE
    @Path("{id}")
    public void removeLivro(@PathParam("id") UUID id) {
        Livro livroEncontrado = entityManager.find(Livro.class, id);
        entityManager.remove(livroEncontrado);
    }

    @PUT
    @Path("{id}")
    public Livro updateLivro(@PathParam("id") UUID id, Livro livro) {
        livro.setId(id);
        entityManager.merge(livro);
        return livro;
    }

//    @GET
//    @Path("busca")
//    public List<Livro> searchLivros() {
//        return entityManager
//                .createNamedQuery("Livro.findByTitulo")
//                .setParameter("titulo", titulo)
//                .getResultList();
//    }
}