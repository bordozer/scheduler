package schemway.scheduler.services.remote;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Service
public class WebClientServiceImpl implements WebClientService {

    private static final Logger LOGGER = Logger.getLogger(WebClientServiceImpl.class);

    private final static String USER_AGENT = "Mozilla/5.0";

    @Override
    public void send(final HttpParameters parameters) throws Exception {
        String requestUrl = parameters.getRequestUrl();
        schemway.core.models.RequestMethod requestMethod = parameters.getRequestMethod();

        switch (requestMethod) {
            case GET:
                sendGet(requestUrl);
                break;
            case POST:
                sendPost(requestUrl, parameters.getPostJson());
                break;
            default:
                LOGGER.error(String.format("Unsupported Request Method: '%s'", requestMethod));
        }

    }

    private void sendGet(final String url) throws IOException {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        LOGGER.debug(String.format("Remote client response: %s (%d)", response.toString(), responseCode));
    }

    // TODO: postJSON
    private void sendPost(final String url, final String json) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = ""; // sn=C02G8416DRJM&cn=&locale=&caller=&num=12345

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        LOGGER.debug(String.format("Remote client response: %s (%d)", response.toString(), responseCode));
    }
}
