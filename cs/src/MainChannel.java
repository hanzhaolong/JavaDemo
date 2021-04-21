import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class MainChannel {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 声明服务器监听通道 和select选择器
        ServerSocketChannel serverChannel = null;
        Selector selector = null;

        try {
            // 实例化selector 此种实例化模式说明 selector 是单例的
            selector = Selector.open();
            // 实例化服务器监听端口
            serverChannel = ServerSocketChannel.open();
            // 绑定监听地址。
            serverChannel.socket().bind(new InetSocketAddress(10077));
            // 设置channel为非阻塞模式,一定要非阻塞模式才能注册到selector中
            serverChannel.configureBlocking(false);

            // 把监听通道注册到选择器中， 监听此通道的连接事件。SelectionKey.OP_ACCEPT 指定为连接事件。
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            //所有通道，交给通道管理器，统一管理
            ChannelManager.addChannel(serverChannel);
            // 开启一个线程负责管理selector,并轮询是否有注册监听的事件就绪。
            Thread thread = new Thread(new ServerNIO(selector));
            thread.start();

            // 然后主线程 就可以干其他的事情了。不管客户端连接 还是I/O
            // 都不会阻塞此线程，只会阻塞selector管理线程，且只在等待事件发生时阻塞。
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("服务器监听发生异常");
        }
    }
}
