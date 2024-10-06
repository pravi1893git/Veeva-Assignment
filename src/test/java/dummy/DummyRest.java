package dummy;

import drivermanager.DriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.api.ApiUtility;

public class DummyRest {

	public static void main(String[] args) {
		
		//RestAssured.baseURI = "https://shop.warriors.com/golden-state-warriors-men-jackets/t-25690618+ga-01+d-3438843071+z-9-4167543?_ref=p-GALP:m-SIDE_NAV";
		
//		Response response = RestAssured.given().header("Content-Type", "text/html").when().get("https://shop.warriors.com/golden-state-warriors-men-jackets/t-25690618+ga-01+d-3438843071+z-9-4167543?_ref=p-GALP:m-SIDE_NAV").getCookies();
//		System.out.println();
		
		//RequestSpecification reqspec = RestAssured.given().header("Content-Type", "text/html").when().get().getCookies()
				
//		Map<String, String> allCookies = RestAssured.when().get("https://www.nba.com/sixers/").getCookies();
//		System.out.println(allCookies);

		//List<String> myCookieValues = allCookies.getValues("myCookieName");

		Response res = RestAssured.given().header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36")
		        .header("upgrade-insecure-requests", 1)
		        .header("sec-ch-ua-platform", "Windows")
		        .header("sec-ch-ua-mobile", "?0")
		        .header("sec-ch-ua", "\"Brave\";v=\"129\", \"Not=A?Brand\";v=\"8\", \"Chromium\";v=\"129\"")
		        
		        .when().get("https://shop.warriors.com/");
		System.out.println(res.getCookies().toString());
		
//		Response res = RestAssured.given().headers(ApiUtility.getReqHeaders("shop.warriors.com"))
//		        
//		        .when().get("https://shop.warriors.com/");
//		System.out.println(res.getCookies().toString());
		
		//System.out.println(ApiUtility.getReqHeaders("shop.warriors.com"));
		
		
		
	}

}
