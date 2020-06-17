
package br.com.bookapi.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author user
 */
@Path("hello")
public class HelloWorldResources {
    @GET
    public String helloWorld(){
        return "Hello World Java Web Service!";
    }
}
