package com.deweysasser.example.webserver;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/** Serve up a basic Hello World page
 * 
 * @author Dewey Sasser
 *
 */
@SuppressWarnings("restriction")
public class HelloPage implements HttpHandler {

	@Override
	public void handle(HttpExchange req) throws IOException {
		String response = "<html><h1>Hello World</h1> " 
				+ "<p>Do you want an error?  Try <a href=\"error\">this</a></p>"
				+ "<p>Do you want to exit?  Try <a href=\"goodbye\">this</a></p>"
				+ "</html>";
		
		req.sendResponseHeaders(200, response.length());
		OutputStream os = req.getResponseBody();
		
		os.write(response.getBytes());
		os.close();
		
	}

}
