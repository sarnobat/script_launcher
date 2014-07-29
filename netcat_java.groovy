#!/usr/bin/env groovy
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

public class PortListener {
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
		System.out.println("begin");

		String portNumberStr = "4458";

		Options options = new Options();
		options.addOption(OptionBuilder.withLongOpt("port")
				.withDescription("use PORT-byte blocks").hasArg()
				.withArgName("PORT").create('p'));

		CommandLine line = new GnuParser().parse(options, args);

		System.out.println("leftovers: " + line.getArgs());

		if (line.hasOption("port")) {
			portNumberStr = line.getOptionValue("port");
			System.out.println(portNumberStr);
		}
		int portNumber = Integer.parseInt(portNumberStr);

		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(portNumber);

		while (true) {
			Socket clientSocket = serverSocket.accept();
			System.out.println("Got new connection");
			BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			System.out.println(br.readLine());
			clientSocket.close();
		}
	}

}
