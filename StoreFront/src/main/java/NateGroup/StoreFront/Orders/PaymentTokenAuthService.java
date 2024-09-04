package NateGroup.StoreFront.Orders;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentTokenAuthService {
    private final Dotenv dotenv;

    private final String PAYPAL_API_BASE_URL;
    private final String PAYPAL_CLIENT_ID;
    private final String PAYPAL_CLIENT_SECRET;
    private final String PAYPAL_MERCHANT_ID;
    private final String DOMAINS;

    private final RestClient restClient;

    enum TokenType {
        CLIENT_TOKEN,
        ACCESS_TOKEN
    }



    public PaymentTokenAuthService() {
        this.dotenv = Dotenv.load();

        //session servlet made off env file
        this.PAYPAL_API_BASE_URL = this.dotenv.get("PAYPAL_API_BASE_URL", "https://api-m.sandbox.paypal.com");
        this.PAYPAL_CLIENT_ID = this.dotenv.get("PAYPAL_CLIENT_ID");
        this.PAYPAL_CLIENT_SECRET = this.dotenv.get("PAYPAL_CLIENT_SECRET");
        this.PAYPAL_MERCHANT_ID = this.dotenv.get("PAYPAL_MERCHANT_ID");
        this.DOMAINS = this.dotenv.get("DOMAINS");

        String auth = this.PAYPAL_CLIENT_ID + ":" + this.PAYPAL_CLIENT_SECRET;
        String apiKey = new String(Base64.getEncoder().encode(auth.getBytes()));
        this.restClient = RestClient.builder().baseUrl(PAYPAL_API_BASE_URL).defaultHeader("Authorization", "Basic " + apiKey).build();
    }



    private final String getToken(UriComponentsBuilder bodyUriBuilder, OAuth2AccessToken.TokenType tokenType) {
        Boolean hasMerchantId = this.PAYPAL_MERCHANT_ID != null && !this.PAYPAL_MERCHANT_ID.isEmpty();

        String bodyUri = bodyUriBuilder.buildAndExpand().toUriString().substring(1);

        RestClient.RequestBodyUriSpec client = this.restClient.post();

        //make post request with necessary auth header and merch id
        if (hasMerchantId) {
            client.header("PayPal-Auth-Assertion", getAuthAssertionToken(PAYPAL_CLIENT_ID, PAYPAL_MERCHANT_ID));
        }

        if (tokenType == OAuth2AccessToken.TokenType.BEARER && hasMerchantId) {
            client.header("PayPal-Partner-Attribution-ID", this.PAYPAL_MERCHANT_ID);
        }

        ResponseEntity<Map> result = client
                .uri("/v1/oauth2/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(bodyUri)
                .retrieve()
                .toEntity(Map.class);

        String accessToken = (String) result.getBody().get("access_token");

        return accessToken;
    }


    public String getClientToken() {
        UriComponentsBuilder bodyUriBuilder = UriComponentsBuilder.newInstance()
                .queryParam("grant_type", "client_credentials")
                .queryParam("response_type", "client_token")
                .queryParam("intent", "sdk_init")
                .queryParam("domains[]", this.DOMAINS);

        String clientToken = this.getToken(bodyUriBuilder, OAuth2AccessToken.TokenType.BEARER);

        return clientToken;
    }


    public String getAccessToken() {
        UriComponentsBuilder bodyUriBuilder = UriComponentsBuilder.newInstance().queryParam("grant_type", "client_credentials");

        String accessToken = this.getToken(bodyUriBuilder, OAuth2AccessToken.TokenType.BEARER);

        return accessToken;
    }


    private String getAuthAssertionToken(String clientId, String merchantId) {
        try {
            HashMap<String, String> header = new HashMap<>();
            header.put("alg", "none");

            HashMap<String, String> body = new HashMap<>();
            body.put("iss", clientId);
            body.put("payer_id", merchantId);

            String signature = "";

            ObjectMapper mapper = new ObjectMapper();
            String headerJson;
            String bodyJson;
            headerJson = mapper.writeValueAsString(header);
            bodyJson = mapper.writeValueAsString(body);

            String headerEncoded = Base64.getEncoder().encodeToString(headerJson.getBytes(StandardCharsets.UTF_8));
            String bodyEncoded = Base64.getEncoder().encodeToString(bodyJson.getBytes(StandardCharsets.UTF_8));
            String signatureEncoded = Base64.getEncoder().encodeToString(signature.getBytes(StandardCharsets.UTF_8));

            String result = headerEncoded + "." + bodyEncoded + ".";

            if (!signature.isEmpty()) {
                result += signatureEncoded;
            }

            return result;
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}




