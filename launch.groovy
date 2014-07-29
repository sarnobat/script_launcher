#!/usr/bin/env groovy
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	static String unixCommandString;

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
			System.out.println("aaa");
			try {
				System.out.println("aa");
				Process theProcess = Runtime.getRuntime().exec(ScriptLauncher.unixCommandString);//"echo 'goodbye'");
				System.out.println("a");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						theProcess.getInputStream()));
				System.out.println("b");
				try {
					int returnCode = theProcess.waitFor();
					System.out.println("c");
					StringBuffer sb = new StringBuffer();
					while(br.ready()) {
						sb.append(br.readLine());						
						System.out.println("d");
					}
					if (returnCode == 0) {
						System.out.println("e");
						JSONObject json = new JSONObject();
						json.put("status", "success");
						json.put("output", sb.toString());
						return Response.ok().header("Access-Control-Allow-Origin", "*")
								.entity(json.toString()).type("application/json").build();			
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace(System.err);
			}
			//System.out.println("1");
			//System.err.println("1");
			JSONObject json = new JSONObject();
			//System.out.println("2");
			json.put("method", "post");
			//System.out.println("3");
			return Response.ok().header("Access-Control-Allow-Origin", "*")
					.entity(json.toString()).type("application/json").build();
		}
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {

		String portNumber = "";
		options: {
			Options options = new Options();
			options.addOption(OptionBuilder.withLongOpt("port")
					.withDescription("use PORT-byte blocks").hasArg()
					.withArgName("PORT").create('p'));
			getOptions: {
				CommandLine line = new GnuParser().parse(options, args);

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
					System.out.println("command: " + ScriptLauncher.unixCommandString);
				}
				if (line.getArgs().length == 0) {
					System.out.println("No command specified");
				}
			}
		}
		HttpServer server = JdkHttpServerFactory.createHttpServer(new URI(
				"http://localhost:" + portNumber + "/"), new ResourceConfig(
				HelloWorldResource.class));
	}
}
