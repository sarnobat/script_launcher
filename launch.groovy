#!/usr/bin/env groovy
import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.Parser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Parser;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.net.httpserver.HttpServer;

public class ScriptLauncher {
	private static String unixCommandString;

	@Path("/")
	public static class HelloWorldResource { // Must be public

		@GET
		@Path("/{token}")
		@Consumes("text/plain")
		@Produces("application/json")
		public Response get(@PathParam("token") String token
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
			json.put("method", "post");
			System.out.println("3");
			return Response.ok().header("Access-Control-Allow-Origin", "*")
					.entity(json.toString()).type("application/json").build();
		}
	}

	public static void main(String[] args) throws Exception {

		String portNumber = "";
		// create the command line parser
		Parser parser = new GnuParser();

		// create the Options
		Options options = new Options();
		options.addOption(
				OptionBuilder.withLongOpt("port")
				.withDescription("use PORT-byte blocks").hasArg()
				.withArgName("PORT").create('p')
				);
//		options.addOption("p", "port", false,
//				"do not hide entries starting with .");
		// parse the command line arguments
		CommandLine line = parser.parse(options, args);

		System.out.println("leftovers: " + line.getArgs());


		if (line.hasOption("port")) {
			portNumber = line.getOptionValue("port");
			System.out.println(portNumber);
		}

		if (line.getArgs().length > 1) {
			portNumber = line.getArgs()[1];
		}
		if (line.getArgs().length > 0) {
			ScriptLauncher.unixCommandString = line.getArgs()[0];
		}
		if (line.getArgs().length == 0) {
			System.out.println("No command specified");
		}
		HttpServer server = JdkHttpServerFactory.createHttpServer(new URI(
				"http://localhost:" + portNumber + "/"), new ResourceConfig(
				HelloWorldResource.class));
	}
}
