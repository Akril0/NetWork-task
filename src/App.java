//  Для получения ответа сервера:
//  1) Запустить этот класс;
//  2) В браузере ввести
//  localhost:8000/server-app?userName=Tom&userPhone=555 123-9874&userEmail=tom@mail.com
//  или
//  localhost:8000/server-app?userName=Tom&userPhone=555%20123-9874&userEmail=tom@mail.com
//  и нажать Enter.

import network.HttpRequestHandler;
import network.HttpServerSimple;
import utils.Constants;
//localhost:8000/server-app?userName=Tom&userEmail=alexbatin2003@gmail.com&userPhone=+380967473670
public class App {

    public static void main(String[] args) {

        HttpServerSimple simpleHttpServer = new HttpServerSimple(
                Constants.PORT, Constants.CONTEXT,
                new HttpRequestHandler()
        );

        simpleHttpServer.start();

        System.out.println("The server is running and listening on a port " +
                Constants.PORT);
    }
}
