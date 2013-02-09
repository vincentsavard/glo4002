package ca.ulaval.glo4002.centralServer.rest.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.ulaval.glo4002.centralServer.main.CentralServer;

public class RequestTreatmentTest {

	private String RESPONSE_TO_POST_REQUEST = "POST request received at central server";
	private String CENTRAL_SERVER_URL = "http://localhost:8080/central";
	private String A_STRING = "just some text";
	private static CentralServer centralServer = new CentralServer();

	@BeforeClass
	public static void initialize() throws Exception {
		centralServer.startServer();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		centralServer.stopServer();
	}

	@Test
	public void hasReceivedAPostRequestFromClient() throws Exception {
		HttpURLConnection conn = (HttpURLConnection) new URL(CENTRAL_SERVER_URL)
				.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(
				conn.getOutputStream());
		writer.write(A_STRING);
		writer.flush();
		writer.close();

		// Get the answer
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		String answer = reader.readLine();
		writer.close();
		reader.close();
		assertTrue(answer.toString().startsWith(RESPONSE_TO_POST_REQUEST));
	}

}
