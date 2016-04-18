package com.deweysasser.example.webserver;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/** Serve up a page with a 500 response code
 * 
 * @author Dewey Sasser
 *
 */
@SuppressWarnings("restriction")
public class ErrorPage implements HttpHandler {

	@Override
	public void handle(HttpExchange req) throws IOException {
		String response = "<html><h1>Go away, lout!</h1></html>";
		
		req.sendResponseHeaders(500, response.length());
		OutputStream os = req.getResponseBody();
		
		os.write(response.getBytes());
		os.close();
		
	}

}
