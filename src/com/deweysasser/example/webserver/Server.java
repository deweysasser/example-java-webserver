package com.deweysasser.example.webserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.sun.net.httpserver.HttpServer;

/**
 * A trivially simple example web server in Java, using Sun's (ahem, I mean
 * Oracle's) server class.
 * 
 * @author Dewey Sasser
 * 
 */
@SuppressWarnings("restriction")
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
		server.createContext("/goodbye", new GoodbyePage());

		// Handle 10 simaltaneous requests using an executor pattern. Pass a
		// ThreadFactory which sets theads as daemon, so the program will exit
		// when the server exits.

		ExecutorService e = Executors.newFixedThreadPool(10, new ThreadFactory() {
			
			@Override
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setDaemon(true);
				return t;
			}
		});

		server.setExecutor(e);

		server.start();

		System.out.println("Listening on " + port);

	}
}
