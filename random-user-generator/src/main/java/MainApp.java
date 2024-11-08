import static java.lang.System.*;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainApp {
    public static void main(String[] args) {
        try {
            int numPeople = 96;

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

            // Out file
            //PrintWriter writer = new PrintWriter("random_employees.csv");

            OutputStream os = new FileOutputStream("tilfeldige_ansatte.csv");
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));


            String[] titles = {"Rådgiver", "Spesialrådgiver", "Ingeniør", "Sivilingeniør", "Data Platfrom Specialist", "Data Engineer", "Utvikler", "Data Scientist", "Assistant Account Executive", "Renholdskonsulent", "Idéastronaut", "Førstekonsulent", "Lærling", "Konsulent", "Blikkenslager", "Byggingeniør"};

            writer.println("ansattnr;fornavn;etternavn;fødselsdato;stilling;lønn;kontorkode");

            for (int i = 0; i < results.length(); i++) {
                // Getting data about one person
                JSONObject user = results.getJSONObject(i);

                JSONObject name = user.getJSONObject("name");
                String firstName = name.getString("first");
                String lastName = name.getString("last");
                JSONObject dob = user.getJSONObject("dob");
                String birthDate = dob.getString("date");
                birthDate = birthDate.split("T")[0];

                // Ansattnr.
                writer.print((i+1+4) + ";");

                // Navn
                writer.print(firstName + ";" + lastName + ";");

                // Fødselsdato
                writer.print(birthDate + ";");

                // Stilling
                writer.print(titles[randInt(0, titles.length-1)] + ";");

                // Lønn
                writer.print(randInt(300, 999)*1000.0 + ";");

                // Kontorkode
                writer.println(randInt(1,10));
            }

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Creating a random integer between low and high (inclusive)
    private static int randInt(int low, int high) {
        return (int) (Math.random() * (high - low + 1)) + low;
    }
}
