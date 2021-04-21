import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
public class MainChannelClient {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SocketChannel socketChannel = null;
        boolean flag = true;
        Scanner in = new Scanner(System.in);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            socketChannel = SocketChannel.open();

            // 设置连接为非阻塞模式，可能未连接完成就返回
            //socketChannel.configureBlocking(false);
            socketChannel.socket().connect(new InetSocketAddress("localhost", 10077));

            // 判断是否连接成功，等待连接成功
            while (!socketChannel.finishConnect()) {
            }

            while (flag) {

                String temp = in.nextLine();

                buffer.clear();

                buffer.put(temp.getBytes());

                // limit指针 移动到 position位置
                buffer.flip();

                // 当buffer中有足够空间，则写到buffer中
                while (buffer.hasRemaining())
                    socketChannel.write(buffer);

                if ("exit".equals(temp))
                    flag = false;
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("与服务断开连接");
        }

        if (socketChannel != null) {
            try {
                socketChannel.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
