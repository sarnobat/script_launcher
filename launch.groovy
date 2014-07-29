#!/usr/bin/env groovy
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.net.httpserver.HttpServer;

public class Server {
	private static String unixCommandString;
        @Path("/")
        public static class HelloWorldResource { // Must be public

                @GET
                @Path("/{token}")
                @Consumes("text/plain")
                @Produces("application/json")
                public Response get(
                	  @PathParam("token") String token
                	// TODO: @QueryParam("rootId") Integer iRootId
                	) throws JSONException {
                		System.out.println(token);
                		System.err.println("1");
                        JSONObject json = new JSONObject();
                		System.out.println("2");
                        json.put("composable", "scripts");
                		System.out.println("3");
					    return Response.ok().header("Access-Control-Allow-Origin", "*")
						 			.entity(json.toString()).type("application/json").build();
                }
                
                @POST
                @Path("/")
                @Consumes("text/plain")
                @Produces("application/json")
                public Response post(
                	// TODO: @QueryParam("rootId") Integer iRootId
                	) throws JSONException {
                		System.out.println("1");
                		System.err.println("1");
                        JSONObject json = new JSONObject();
                		System.out.println("2");
                        json.put("foo", "bar");
                		System.out.println("3");
					    return Response.ok().header("Access-Control-Allow-Origin", "*")
						 			.entity(json.toString()).type("application/json").build();
                }
        }

        public static void main(String[] args) throws URISyntaxException {
		String portNumber = args[1];
		this.unixCommandString = args[0];
                HttpServer server = JdkHttpServerFactory.createHttpServer(
                                new URI("http://localhost:" + portNumber + "/"), new ResourceConfig(HelloWorldResource.class));
        }
}
