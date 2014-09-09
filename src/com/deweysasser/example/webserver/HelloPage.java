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
public class HelloPage implements HttpHandler {

	@Override
	public void handle(HttpExchange req) throws IOException {
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(req.getResponseBody()));
		
		String response = "<html><h1>Hello World</h1>Do you want an error?  Try <a href=\"error\">this</a></html>";
		
		req.sendResponseHeaders(200, response.length());
		OutputStream os = req.getResponseBody();
		
		os.write(response.getBytes());
		os.close();
		
	}

}
