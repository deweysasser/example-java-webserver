package com.deweysasser.example.webserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;

/**
 * A trivially simple example web server in Java, using Sun's (ahem, I mean
 * Oracle's) server class.
 * 
 * @author Dewey Sasser
 * 
 */
public class Server {

	public static void main(String[] args) throws IOException {
		int port = 8666;

		// for some reason, Eclipse has some default indigestion on this in this
		// project. Change compiler settings to ignore "restricted access" and
		// all works.

		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

		// Each path is a context. Of course, for non-trivial applications you
		// might want your contexts to have subcontexts.
		server.createContext("/", new HelloPage());
		server.createContext("/error", new ErrorPage());

		// Handle 10 simaltaneous requests using an executor pattern
		ExecutorService e = Executors.newFixedThreadPool(10);

		server.setExecutor(e);

		// Because you haven't set the daemon flag on the exeuctor threads, this
		// will run forever, preventing your app from exiting. If you give the
		// exeuctorservice a thread factory that uses Thread.setDaemon(boolean)
		// you can change that behavior.

		server.start();

		System.out.println("Listening on " + port);

	}
}
