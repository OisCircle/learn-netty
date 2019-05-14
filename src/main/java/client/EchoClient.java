package client;


import handler.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * <p>
 *
 * </p>
 *
 * @author O
 * @version 1.0
 * @since 2018/8/12
 */
public class EchoClient {
	private final String host;
	private final int port;

	private EchoClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	private void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
					.channel(NioSocketChannel.class)
					.remoteAddress(new InetSocketAddress(host, port))
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							socketChannel.pipeline().addLast(
									new EchoClientHandler()
							);
						}
					});
			ChannelFuture future = b.connect();
			future.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					if (future.isSuccess()) {
						System.out.println("echo client connect successfully ... ");
					} else {
						System.out.println("echo client connect failed ...");
					}
				}
			});
			System.out.println("EchoClient blocking ... ");
			future.channel().closeFuture().sync();
			System.out.println("EchoClient shutdown ... ");
		} finally {
			group.shutdownGracefully().sync();
		}
	}

	public static void main(String[] args) throws Exception{
		new EchoClient("localhost", 8080).start();
	}
}
