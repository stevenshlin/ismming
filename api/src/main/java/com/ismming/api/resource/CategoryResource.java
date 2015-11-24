package com.ismming.api.resource;

import com.ismming.api.util.ResponseBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Scope("prototype")
@Path("/")
public class CategoryResource {

    @GET
    @Path("category")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories() throws Exception {
        return ResponseBuilder.ok("level_1", "men");
    }
}
