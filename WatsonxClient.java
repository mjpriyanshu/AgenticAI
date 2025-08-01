import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class WatsonxClient {
    public static void main(String[] args) throws IOException {
        // Replace with your actual IBM Cloud API Key
        String API_KEY = "YOUR_API_KEY";

        // Endpoint for your deployed Watsonx AI agent
        String DEPLOYMENT_URL = "DEPOYMENT_URL_HERE"; 

        // Step 1: Get IAM token
        URL tokenUrl = new URL("https://iam.cloud.ibm.com/identity/token");
        String body = "grant_type=urn:ibm:params:oauth:grant-type:apikey&apikey=" + API_KEY;

        HttpURLConnection tokenConn = (HttpURLConnection) tokenUrl.openConnection();
        tokenConn.setRequestMethod("POST");
        tokenConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        tokenConn.setRequestProperty("Accept", "application/json");
        tokenConn.setDoOutput(true);
        try (OutputStream os = tokenConn.getOutputStream()) {
            os.write(body.getBytes(StandardCharsets.UTF_8));
        }

        StringBuilder responseBuilder = new StringBuilder();
        String line;
        int tokenResponseCode = tokenConn.getResponseCode();
        BufferedReader in;
        if (tokenResponseCode == 200) {
            in = new BufferedReader(new InputStreamReader(tokenConn.getInputStream()));
        } else {
            in = new BufferedReader(new InputStreamReader(tokenConn.getErrorStream()));
            System.err.println("Error response from IAM token request (HTTP " + tokenResponseCode + "):");
        }
        while ((line = in.readLine()) != null) {
            responseBuilder.append(line);
        }
        in.close();
        String tokenResponse = responseBuilder.toString();
        if (tokenResponseCode != 200) {
            System.err.println(tokenResponse);
            throw new IOException("Failed to get IAM token. HTTP code: " + tokenResponseCode);
        }
        // Use a JSON library to parse tokenResponse and extract "access_token"

        String accessToken = tokenResponse.split("\"access_token\":\"")[1].split("\"")[0];

        // Step 2: Call the deployed Watsonx agent
        URL scoringUrl = new URL(DEPLOYMENT_URL);
        HttpURLConnection scoreConn = (HttpURLConnection) scoringUrl.openConnection();
        scoreConn.setRequestMethod("POST");
        scoreConn.setRequestProperty("Authorization", "Bearer " + accessToken);
        scoreConn.setRequestProperty("Content-Type", "application/json");
        scoreConn.setDoOutput(true);

        // Replace content and role as per your input format
        String payload = "{\"prompt\":\"Suggest ways to reduce my electricity bill.\",\"parameters\":{\"max_new_tokens\":100,\"temperature\":0.7}}";
        System.out.println(payload);
        try (OutputStream os = scoreConn.getOutputStream()) {
            os.write(payload.getBytes(StandardCharsets.UTF_8));
        }

        int scoreResponseCode = scoreConn.getResponseCode();
        BufferedReader scoreIn;
        if (scoreResponseCode == 200) {
            scoreIn = new BufferedReader(new InputStreamReader(scoreConn.getInputStream()));
        } else {
            scoreIn = new BufferedReader(new InputStreamReader(scoreConn.getErrorStream()));
            System.err.println("Error response from Watsonx agent request (HTTP " + scoreResponseCode + "):");
        }
        String response;
        while ((response = scoreIn.readLine()) != null) {
            System.out.println(response);
        }
        scoreIn.close();
        if (scoreResponseCode != 200) {
            throw new IOException("Failed to get response from Watsonx agent. HTTP code: " + scoreResponseCode);
        }
    }
}

