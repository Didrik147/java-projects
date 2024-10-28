import static java.lang.System.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainApp {
    public static void main(String[] args){
        try {
            int numPeople = 10;
            // Create HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Create HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://randomuser.me/api/?nat=no&results=" + numPeople))
                .header("Accept", "application/json")
                .GET()
                .build();

            // Send request and get response
            HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

            // Parse JSON response
            JSONObject mainObject = new JSONObject(response.body());
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
