package websocket.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <p>
 *
 * </p>
 *
 * @author O
 * @version 1.0
 * @since 2018/11/28
 */
public class ChatBootstrap {
	public static void main(String[] args) {
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup workers = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(boss, workers);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.childHandler(new ChatInitializer());
			ChannelFuture channelFuture = bootstrap.bind(8000);
			System.out.println("服务器成功启动netty...");
			channelFuture.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			boss.shutdownGracefully();
			workers.shutdownGracefully();
		}
	}
}
