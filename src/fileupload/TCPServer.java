package fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
文件上传的服务器端
    需要完成：
        读取服务器上传的文件
        保存到服务器的硬盘
            目的地：E:\net_coding\server
        给客户端回写数据

    文件复制之后程序并没有停止：
        read方法是程序进入了阻塞状态，read读取到-1结束，但是while的判断条件使得不能读取到-1.
        也不会把结束标记写给服务器。永远读取不到文件的结束标记，read读取不到就会进入死循环。8 9 10就不会执行到
        客户端等不到回写，也进入了等待的状态。
    【解决方案】 上传完文件给服务器写一个结束标志

 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        //1.创建服务器对象
        ServerSocket server=new ServerSocket(8888);
        //2.使用ServerSocket的accept方法，获取到请求的客户端socket对象
        Socket socket=server.accept();
        //3.使用Socket的getInputStream，获取网络字节输入流对象InputStream
        InputStream is=socket.getInputStream();
        //4.判断E:\net_coding\server是否存在，若不存在，创建一个
        File file=new File("E:\\net_coding\\cloud");
        if(!file.exists()){
            file.mkdirs();
        }
        //5.创建一个本地的字节输出流对象FileOutputStream，构造方法中绑定输出的目的地
        FileOutputStream fos=new FileOutputStream(file+"\\1.jpg");
        //6.使用网络字节输入流对象InputStream的read方法，读取客户端上传的文件
        int len=0;
        byte[] bytes=new byte[1024];
        while ((len=is.read(bytes))!=-1){
            //7.使用本地字节输出流对象的方法write，写文件
            fos.write(bytes,0,len);
        }

        //8.使用Socket的getOutputStream，获取网络字节输出流对象OutputStream
        //9.使用网络字节输出流对象OutputStream的方法write，给客户端回写上传成功
        socket.getOutputStream().write("上传成功".getBytes());

        //10.释放资源
        fos.close();
        socket.close();
        server.close();
    }
}
