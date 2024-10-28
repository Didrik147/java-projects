import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class Currency {
    public static void main(String[] args){
        try {
            // Create a URL object
            String fromCurrency = "USD";
            //fromCurrency = JOptionPane.showInputDialog("Convert from:");

            URL url = new URL("https://open.er-api.com/v6/latest/" + fromCurrency);

            double fromValue = 1.0;
            //fromValue = Double.parseDouble(JOptionPane.showInputDialog("Amount:"));


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

                JSONObject jsonResponse = new JSONObject(inline);
                JSONObject rates = jsonResponse.getJSONObject("rates");

                String time_last_update = jsonResponse.getString("time_last_update_utc");

                String[] stringArr = time_last_update.split(" ");

                String outMsg = "";

                for(int i=0; i<4; i++){
                    outMsg += stringArr[i];
                    if (i < 4)
                        outMsg += " ";
                }

                outMsg += "\n";

                System.out.println();

                String toCurrency = "NOK";
                //toCurrency = JOptionPane.showInputDialog("Convert to:");

                double toValue = rates.getDouble(toCurrency)*fromValue;

                outMsg += String.format("%.2f", fromValue) + " " + fromCurrency + " = " + String.format("%.2f", toValue) + " " + toCurrency;

                JOptionPane.showMessageDialog(null, outMsg);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}