package dummy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JustCheck {

	public static void main(String[] args) {
		
		ArrayList<Integer> day = new ArrayList<Integer>();
		ArrayList<Integer> year = new ArrayList<Integer>();
		
		List<String> duration = Arrays.asList("6h","2d","2y","6d","5d","1y","5d","1h","10h");
		
		for(String item:duration)
		{
			if(item.contains("d"))
				day.add(Integer.parseInt(item.replace("d", "")));
			else if(item.contains("y"))
				year.add(Integer.parseInt(item.replace("y", "")));
		}
		Collections.sort(day);
		Collections.sort(year);
		System.out.println(day);
		System.out.println(year);
		
		day.removeIf(n -> n < 3); 
		System.out.println(day);
		System.out.println(day.size());
		

		String url = "https://shop.warriors.com/golden-state-warriors-men-jackets/t-25690618+ga-01+d-3438843071+z-9-4167543?_ref=p-GALP:m-SIDE_NAV";
		System.out.println(url.split("\\?")[0]+"?pageNumber=2");
		
		 String jsonData = "{\r\n"
		 		+ "  \"browse-data\":{\"locations\":[{\"lat\":\"23.053\",\"long\":\"72.629\",\"location\":\"ABC\",\"address\":\"DEF\",\"city\":\"Ahmedabad\",\"state\":\"Gujrat\",\"phonenumber\":\"1234567\"},{\"lat\":\"21.013\",\"long\":\"52.619\",\"location\":\"XYZ\",\"address\":\"MNP\",\"city\":\"Ghaziabad\",\"state\":\"UP\",\"phonenumber\":\"212321\"}]}}";    
		 System.out.println(jsonData);
		 JSONObject json = new JSONObject(jsonData);
		 JSONObject json1 = (JSONObject) json.get("browse-data");
        JSONArray productsArray = json1.getJSONArray("locations");  
        
        for (int j = 0; j < productsArray.length(); j++) {  
              
            JSONObject product = productsArray.getJSONObject(j);  
            System.out.println("phonenumber:"+product.get("phonenumber"));  
        }      
	
	
	
	}

}
