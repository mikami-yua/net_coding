package demo1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/*
TCP通信的客户端：向服务器发送连接请求，给服务器发送数据读取服务器回写的数据
表示客户端的类
    java.net.Socket 该类实现客户端套接字（也称为“套接字”）。 套接字是两台机器之间通讯的端点。
    套接字：包含了IP地址和端口号的网络单位

    构造方法：
        Socket(String host, int port)创建流套接字并将其连接到指定主机上的指定端口号
        String host:服务器主机的名称，也可以传ip地址
        int port：服务器端口号
    成员方法
        OutputStream getOutputStream()  返回此套接字的输出流。
        InputStream getInputStream()  返回此套接字的输入流。
        void close()  关闭此套接字。

实现步骤
    1.创建一个客户端对象Socket，构造方法中绑定服务器的ip地址和端口号
    2.使用Socket对象中的方法getOutputStream()获取网络字节输出流对象，OutputStream对象（不是自己创建的，是通过方法获取的）
    3.使用网络字节输出流对象OutputStream中的方法write，给服务器发送数据
    4.使用Socket对象中的方法getInputStream()获取网络字节输入流对象，InputStream
    5.使用网络字节输入流InputStream中的方法read，读取服务器回写的数据
    6.关闭Socket
    【注意】CS进行交互时，必须使用Socket中提供的网络流，不能使用自己创建的流对象
    【注意】当创建Socket对象时，就会去请求服务器经过3次握手建立连接通路，如果服务器已启动，就可以交互了。若服务器未启动，将抛出异常

 */
public class TcpClient {
    public static void main(String[] args) throws IOException {
        //1.创建一个客户端对象Socket，构造方法中绑定服务器的ip地址和端口号
        Socket socket=new Socket("127.0.0.1",8888);
        /*
        java.net.ConnectException: Connection refused: connect服务器没有启动
         */

        //2.使用Socket对象中的方法getOutputStream()获取网络字节输出流对象
        OutputStream os=socket.getOutputStream();
        //使用网络字节输出流对象OutputStream中的方法write，给服务器发送数据
        os.write("你好服务器".getBytes());

        //4.使用Socket对象中的方法getInputStream()获取网络字节输入流对象
        InputStream is=socket.getInputStream();

        //5.使用网络字节输入流InputStream中的方法read，读取服务器回写的数据
        byte[] bytes=new byte[1024];
        int len=is.read(bytes);
        System.out.println(new String(bytes,0,len));
        //6.关闭Socket
        socket.close();
    }

}
