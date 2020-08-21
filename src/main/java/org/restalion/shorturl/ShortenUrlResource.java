package org.restalion.shorturl;

import java.net.URI;

import javax.inject.Inject;
import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.restalion.shorturl.dto.ShortUrlRequestDto;
import org.restalion.shorturl.dto.ShortenUrlDto;
import org.restalion.shorturl.service.ShortUrlService;

@Path("/")
public class ShortenUrlResource {

    @Inject
    ShortUrlService service;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response shortUrlRequest(ShortUrlRequestDto inputMessage) {

        try {
            String shortenUrl = service.createShortenUrl(inputMessage.getActualUrl());

            ShortenUrlDto output = new ShortenUrlDto();
            output.setActualUrl(inputMessage.getActualUrl());
            output.setShortenUrl(shortenUrl);

            return Response.ok(output).build();

            
        } catch (ValidationException ve) {
            ve.printStackTrace();
            return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{shortenUrl}")
    public Response returnActualUrl(@PathParam String shortenUrl) {
        String actualUrl = service.getActualUrl(shortenUrl);
        try {
            return Response.temporaryRedirect(new URI(actualUrl)).build();
        } catch (Exception e) {
            return Response.noContent().build();
        }
        
    }
}