import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerNIO implements Runnable {
    // 客户端通道，用于给某个客户端收发数据
    private SocketChannel socketChannel;

    // 缓冲区，用户收发数据，面向通道。
    private ByteBuffer buf = ByteBuffer.allocate(1024);

    // 选择器，从主线程注入
    private Selector selector;

    // 指定线程是否轮询
    private boolean flag = true;

    // 构造器中注入selector
    public ServerNIO(Selector selector) {
        // TODO Auto-generated constructor stub
        this.selector = selector;
    }

    // 开启线程等待事件的发生，轮询通道。
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            while (flag) {

                /**
                 * 获取等待的事件就绪的通道，如果没有通道有事件就绪有三种情况
                 * 1. 直接返回:selectNow();
                 * 2. 超时返回:select(int timeout);
                 * 3. 阻 塞: select();
                 */
                int nums = selector.select(); // 阻塞模式
                if (nums > 0) { // 阻塞模式下 此次判定多余
                    // 当事件发生了，获取发生事件通道的key集合;
                    Set<SelectionKey> selectKeys = selector.selectedKeys();

                    // 迭代遍历这个keys集合
                    Iterator<SelectionKey> iter = selectKeys.iterator();

                    while (iter.hasNext()) {
                        // 获取单个通道的key
                        SelectionKey key = iter.next();
                        // 如果是读取事件就绪。说明有客户端向服务器发送数据了。
                        if (key.isReadable()) {
                            // 先获取到客户端channel
                            socketChannel = (SocketChannel) key.channel();
                            buf.clear();
                            // 利用buffer读取数据。
                            int len = socketChannel.read(buf);
                            if (len > 0) {
                                byte[] str = new byte[len];
                                buf.rewind();
                                buf.get(str, 0, len);
                                buf.clear();
                                System.out.println("获取客户端数据：" + new String(str));

                                // 给客户端回复数据
                                String temp = "服务器回复: 已经收到您发送的数据，祝您一路平安!";
                                buf.clear();
                                buf.put(temp.getBytes());
                                buf.flip();
                                socketChannel.write(buf);
                                System.out.println("已经向客户端回复");
                                //此处可以利用ChannelManager向其他所有通道广播数据。只要在ChannelManager中写一个广播方法即可
                            }
                        } else if (key.isAcceptable()) { // 如果是接受客户端连接就绪
                            // 从key中获取对应的通道
                            ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                            // 接受这个连接。
                            SocketChannel socketChannel = serverChannel.accept();
                            // 如果连接不为空，则控制台打印 连接的客户端地址。
                            if (socketChannel != null) {
                                // 由于关闭selector的时候，并不会关闭通道，最好使用一个容器，将通道都保存起来
                                //然后开启心跳连接，如果通道出现异常则关闭此通道，当应用程序关闭的时候，关闭所有的通道。
                                ChannelManager.addChannel(socketChannel);

                                System.out.println(String.format("接收到客户端的连接:IP[%s]-端口[%s]",
                                        socketChannel.socket().getPort(), socketChannel.socket().getInetAddress()));
                                // 把这个通道设置为非阻塞模式，然后又注册到selector中，等待事件发生
                                socketChannel.configureBlocking(false);
                                socketChannel.register(selector, SelectionKey.OP_READ);
                            }
                        } //写事件在缓冲区直接达到阈值时候出发，一般不注册写事件。
                        // 通道处理完毕后 将此通道删除。如果下次又有此时间，会有一个新的key，所以记得删除处理过的key。
                        iter.remove();
                    }

                }
            }
        } catch (IOException e) {
            System.out.println("服务器断开连接");
        }

        try {
            // 注意此处只会关闭，selector使注册到琪上面的selectionKeys无效，通道本身不会关闭。
            selector.close();
            ChannelManager.closeChannles();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}

