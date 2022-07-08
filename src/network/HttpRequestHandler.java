package network;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import utils.Constants;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;


public class HttpRequestHandler implements HttpHandler {

    String userName;
    String userEmail;
    String userPhone;

    public void handle(HttpExchange exchange) throws IOException {

        URI uri = exchange.getRequestURI();
        String response = createResponseFromQuery(uri);
        System.out.println("Response: " + response);

        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=" +
                Constants.ENCODING);

        exchange.sendResponseHeaders(Constants.HTTP_OK_STATUS,
                response.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private String createResponseFromQuery(URI uri) {

        String query = uri.getQuery();

        if (query != null) {

            System.out.println("Request: " + query);
            String[] queryParams = query.split(Constants.AND_DELIMITER);

            if (queryParams.length > 0) {
                for (String qParam : queryParams) {
                    String[] param = qParam.split(Constants.EQUAL_DELIMITER);
                    if (param.length > 0) {
                        for (int i = 0; i < param.length; i++) {
                            if (Constants.USER_NAME.equalsIgnoreCase(param[Constants.PARAM_NAME_IDX])) {
                                userName = param[Constants.PARAM_VALUE_IDX];
                            }
                            if (Constants.USER_PHONE.equalsIgnoreCase(param[Constants.PARAM_NAME_IDX])) {
                                userEmail = param[Constants.PARAM_VALUE_IDX];
                            }
                            if (Constants.USER_EMAIL.equalsIgnoreCase(param[Constants.PARAM_NAME_IDX])) {
                                userPhone = param[Constants.PARAM_VALUE_IDX];
                            }
                        }
                    }
                }
            }
        }

        return "USER INFO: " + userName + ", email: " + userEmail + ", tel. " + userPhone;
    }
}
