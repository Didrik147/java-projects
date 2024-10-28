import static java.lang.System.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainApp {
    public static void main(String[] args){
        try {
            int numPeople = 10;

            // Create a URL object
            URL url = new URL("https://randomuser.me/api/?nat=no&results=" + numPeople);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method (GET)
            connection.setRequestMethod("GET");

            connection.connect();

            //Getting the response code
            int responsecode = connection.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();

                JSONObject mainObject = new JSONObject(inline);
                JSONArray results = mainObject.getJSONArray("results");

                for(int i=0; i<results.length(); i++) {
                    JSONObject user = results.getJSONObject(i);

                    JSONObject name = user.getJSONObject("name");
                    String firstName = name.getString("first");
                    String lastName = name.getString("last");
                    JSONObject dob = user.getJSONObject("dob");
                    String birthDate = dob.getString("date");
                    birthDate = birthDate.split("T")[0];

                    out.println("Name: " + firstName + " " + lastName);
                    out.println("Born: " + birthDate);
                    out.println("");
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
