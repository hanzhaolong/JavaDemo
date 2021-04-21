import java.io.IOException;
import java.nio.channels.Channel;
import java.util.LinkedList;

public class ChannelManager {
    private static LinkedList<Channel> list = new LinkedList<>();

    private static Thread thread; //用于开启心跳测试通道连接,实现省略

    public static void addChannel(Channel channel) {
        list.add(channel);
    }

    //关闭所有的通道连接
    public static void closeChannles() {
        for (Channel channel : list) {
            try {
                channel.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("关闭通道失败");
            }
            list.remove();
        }
    }
}