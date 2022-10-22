package antisolid;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// пример класс AntiO клиент и сервер Server, нарушается OCP так как клиент зависит от сервера, возможное решение - использование интерфейса
// при внесении нового функционала появляется необходимость менять код на стороне клиента\сервера
public class AntiO {
    Socket socket  = new Socket("127.0.0.1", 5000);

    public AntiO() throws IOException {
        String message1 = "Accept";
        OutputStream ostream = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(ostream);
        dos.writeBytes(message1);
        dos.close();
        ostream.close();
        socket.close();
    }
    public void oAuth(){
        System.out.println("here must be authentication depended on server");
    }
}
class Server{
    ServerSocket sersock = new ServerSocket(5000);
    Server() throws IOException {
        System.out.println("Server is ready");  //  message to know the server is running
        Socket sock = sersock.accept();

        InputStream istream = sock.getInputStream();
        DataInputStream dstream = new DataInputStream(istream);
        String message2 = dstream.readLine();
        System.out.println(message2);
        dstream.close(); istream.close(); sock.close(); sersock.close();
    }
    public void oAuth(){
        System.out.println("here must be auth depended on server");
    }
}
