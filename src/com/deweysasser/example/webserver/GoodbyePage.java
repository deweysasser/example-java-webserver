package com.deweysasser.example.webserver;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/** Serve up a basic Hello World page
 * 
 * @author Dewey Sasser
 *
 */
public class GoodbyePage implements HttpHandler {

	@Override
	public void handle(HttpExchange req) throws IOException {
		String response = "<html><h1>Goodbye Cruel World</h1>What a world<br/>What a world...</html>";
		
		req.sendResponseHeaders(200, response.length());
		OutputStream os = req.getResponseBody();
		
		os.write(response.getBytes());
		os.close();
		
		System.exit(0);
	}

}
