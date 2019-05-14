package websocket;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author lilinshen
 * @title 存储工厂的全局配置
 * @description 请填写相关描述
 * @date 2018/5/23 10:32
 */
public class NettyConfig {
    /**
     * 储存每一个客户端进来时的channel对象
     */
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

}