package utilities.api;


import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class ApiUtility {

	public String getCookiesForUrl(String url, String hostname) {

		String cookies = null;
		try
		{
			Response res = RestAssured
					.given()
						.headers(ApiUtility.getReqHeaders(hostname))
					.when()
						.get(url);
	
			System.out.println(res.getCookies().toString());
			cookies = res.getCookies().toString().replace("{", "").replace("}", "");
	
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return cookies;
	}
	
	
	public String getResponseFromGetApi(String url, Map<String,Object> headers) {

		String response = null;
		try
		{
			Response res = RestAssured
					.given()
						.headers(headers)
					.when()
						.get(url);
	
			response = res.asString();
	
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return response;
	}
	
	
	public static Map<String,Object> getReqHeaders(String hostname) {

		Map<String,Object> headers = new HashMap<String,Object>();
		switch (hostname) {
			case "shop.warriors.com":

				headers.put("upgrade-insecure-requests", 1);
				headers.put("sec-ch-ua-platform", "Windows");
				headers.put("sec-ch-ua-mobile", "?0");
				headers.put("sec-ch-ua", "\"Google Chrome\";v=\"129\", \"Not=A?Brand\";v=\"8\", \"Chromium\";v=\"129\"");
				headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36");
			}
		
		return headers;
	}
}
