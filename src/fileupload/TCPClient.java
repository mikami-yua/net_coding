package fileupload;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/*
文件上传项目的客户端
    需要完成：
        读取本地数据
            数据源：E:\net_coding\local\1.jpg
        上传服务器
        读取服务器回写的数据
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        //1.读取文件创建本地的字节输入流FileInputStream，构造方法中绑定要上传的数据源
        FileInputStream fis=new FileInputStream("E:\\net_coding\\local\\1.jpg");
        //2.创建一个客户端Socket，构造方法中绑定服务器的ip和端口号
        Socket socket=new Socket("127.0.0.1",8888);
        //3.使用Socket的getOutputStream，获取网络字节输出流对象OutputStream
        OutputStream os=socket.getOutputStream();
        //4。使用本地的字节输入流FileInputStream对象的read方法读取文件
        int len=0;
        byte[] bytes=new byte[1024];
        while ((len=fis.read(bytes))!=-1){
            //5.网络字节输出流对象OutputStream对象的write把读取到的文件上传
            os.write(bytes,0,len);
        }
        /*
        解决：文件复制之后程序并没有停止，写一个结束标记
        void shutdownOutput()  禁用此套接字的输出流。
            对于TCP套接字，任何先前写入的数据将被发送，随后是TCP的正常连接终止序列。
            如果在套接字上调用shutdownOutput（）之后写入套接字输出流，则流将抛出IOException。
         */
        socket.shutdownOutput();

        //6.使用Socket的getInputStream，获取网络字节输入流对象InputStream
        InputStream is=socket.getInputStream();
        //7.使用网络字节输入流对象InputStream的read方法读取数据
        while ((len=is.read(bytes))!=-1){

            System.out.println(new String(bytes,0,len));
        }


        //8.释放资源
        fis.close();
        socket.close();
    }
}
