package demo1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
TCP通信的服务器端
    表示服务器端的类：java.net.ServerSocket
        这个类实现了服务器套接字。 服务器套接字等待通过网络进入的请求。 它根据该请求执行一些操作，然后可能将结果返回给请求者。

    构造方法：
        ServerSocket(int port)  创建绑定到指定端口的服务器套接字。

    服务器端必须明确一件事：
        那个客户端请求的服务器，使用accept方法获取到请求的客户端对象Socket

    成员方法
        Socket accept()  侦听要连接到此套接字并接受它。

    服务器的实现步骤：
        1.创建服务器ServerSocket对象和系统要指定的端口号
        2.使用accept()方法获取请求的客户端对象Socket
        3.使用Socket对象中的方法getInputStream()获取网络字节输入流对象
        4.使用网络字节输入流InputStream中的方法read，读取客户端发送的数据
        5.使用Socket对象中的方法getOutputStream()获取网络字节输出流对象
        6.使用网络字节输出流对象OutputStream中的方法write，给客户端回写数据
        7.释放资源（Socket，ServerSocket）
 */
public class TcpServer {
    public static void main(String[] args) throws IOException {
        //1.创建服务器ServerSocket对象和系统要指定的端口号
        ServerSocket server=new ServerSocket(8888);
        //2.使用accept()方法获取请求的客户端对象Socket
        Socket socket=server.accept();
        //3.使用Socket对象中的方法getInputStream()获取网络字节输入流对象
        InputStream is=socket.getInputStream();
        //4.使用网络字节输入流InputStream中的方法read，读取客户端发送的数据
        byte[] bytes=new byte[1024];
        int len=is.read(bytes);
        System.out.println(new String(bytes,0,len));
        //5.使用Socket对象中的方法getOutputStream()获取网络字节输出流对象
        OutputStream os=socket.getOutputStream();
        //6.使用网络字节输出流对象OutputStream中的方法write，给客户端回写数据
        os.write("收到谢谢".getBytes());
        //7.释放资源（Socket，ServerSocket）
        socket.close();
        server.close();
    }
}
