package localpost.googlemaps.directions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * 
 * Sample class that makes simple request to Google Maps Directions
 *
 *	Brendan Kroneberger
 *			2016-09-06
 *
 */
public class restrequest {
	
	//the URL of the API we want to connect to
	
	protected static String endpoint = "https://maps.googleapis.com/maps/api/directions/";
	
	//the character set to use the encoding URL parameters
	
	protected static String charset = "UTF-8";
	
	//API key used for making request to API	
	
	protected static String Key = "AIzaSyAI-b0OwKFzq2tHeLht0JiYzgN2kF6k_l8";
	
	public static void main(String[] args) {
		
		try{
			//The origin or starting point for directions
			String origin = "Columbia MD";
			
			//the destination or end point for directions
			String destination = "Hanover PA";
			
			//the return type of the response xml|json
			String returnType = "json";
			
			//the return type is in spanish
			String language = "es";
			
			//the trip uses a bus instead of by car
			String mode = "transit";
			
			String transitmode = "bus";
			
			//creates the url parameters as a string encoding them with the defined charset		
			String queryString = String.format("origin=%s&destination=%s&language=%s&mode=%s&transitmode=%s&key=%s",
					URLEncoder.encode(origin, charset),
					URLEncoder.encode(destination, charset),
					URLEncoder.encode(language, charset),
					URLEncoder.encode(mode,charset),
					URLEncoder.encode(transitmode, charset),
					URLEncoder.encode(Key, charset));
			
			//creates a new URL out of the endpoint, returnType and queryString
			URL googleDirections = new URL(endpoint + returnType + "?" + queryString);
			HttpURLConnection connection = (HttpsURLConnection) googleDirections.openConnection();
			connection.setRequestMethod("GET");
			
			//if we did not get a 200 (success) throw an exception
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}
			
			//read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			
			//loop of buffer line by line until it return null meaning there are no more lines
			while (br.readLine() !=null){
				//print out each line to the screen
				System.out.println(br.readLine());
			}
			
		}catch (MalformedURLException e ) {
			
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}