#!/usr/bin/env groovy
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
		) throws JSONException, IOException {
			JSONObject json = runScript();
			// System.out.println(token);
			// System.err.println("1");
			// JSONObject json = new JSONObject();
			// System.out.println("2");
			// json.put("composable", "scripts");
			// System.out.println("3");
			return Response.ok().header("Access-Control-Allow-Origin", "*")
					.entity(json.toString()).type("application/json").build();
		}

		@POST
		@Path("/")
		@Consumes("text/plain")
		@Produces("application/json")
		public Response post(String body
		// TODO: @QueryParam("rootId") Integer iRootId
		) throws JSONException, IOException {
			System.out.println(body);
			System.out.println("1");
			JSONObject json = runScript();
			// System.err.println("1");
			// JSONObject json = new JSONObject();
			System.out.println("2");
			// json.put("method", "post");
			// System.out.println("3");
			return Response.ok().header("Access-Control-Allow-Origin", "*")
					.entity(json.toString()).type("application/json").build();
		}

		private static JSONObject runScript() throws IOException {
			JSONObject json = new JSONObject();
			_new: {
				try {
					BufferedReader br = new BufferedReader(
							new InputStreamReader(System.in));
					BufferedWriter bw = new BufferedWriter(
							new OutputStreamWriter(System.out));
					while (br.ready()) {
						String line = br.readLine();
						System.out.println(line);
						System.out.println("d");
						bw.write(line);

					}

					// br.close();
					// bw.flush();
					// bw.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			delete_this: {
				if (false) {
					System.out.println("aaa");
					try {
						System.out.println("aa");
						Process theProcess = Runtime.getRuntime().exec(
								ScriptLauncher.unixCommandString);// "echo 'goodbye'");
						System.out.println("a");
						BufferedReader br = new BufferedReader(
								new InputStreamReader(
										theProcess.getInputStream()));
						System.out.println("b");
						try {
							int returnCode = theProcess.waitFor();
							System.out.println("c");
							StringBuffer sb = new StringBuffer();
							while (br.ready()) {
								sb.append(br.readLine());
								System.out.println("d");
							}
							if (returnCode == 0) {
								System.out.println("e");
								json.put("status", "success");
								json.put("output", sb.toString());
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} catch (Exception e) {
						System.out.println(e);
						e.printStackTrace(System.err);
					}
				}
			}
			return json;
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

				// For now just use named args
				if (false) {
					_2_args: {
						if (line.getArgs().length > 1) {
							portNumber = line.getArgs()[1];
						}
						if (line.getArgs().length > 0) {
							ScriptLauncher.unixCommandString = line.getArgs()[0];
							System.out.println("command: "
									+ ScriptLauncher.unixCommandString);
						}

						if (line.getArgs().length == 0) {
							System.out.println("No command specified");
						}
					}
				}
			}
		}
		final String portNumberFinal = portNumber;
		// new Thread() {
		// @Override public void run() {
		try {
			HttpServer server = JdkHttpServerFactory.createHttpServer(new URI(
					"http://localhost:" + portNumberFinal + "/"),
					new ResourceConfig(HelloWorldResource.class));
		} catch (Exception e) {
			System.out.println(e);
			System.exit(-1);
		}
		// }
		// }.run();

	}
}
