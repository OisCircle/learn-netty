package server;

import initializer.FileInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * <p>
 *
 * </p>
 *
 * @author O
 * @version 1.0
 * @since 2018/9/1
 */
public class FileServer {
	private static final int PORT = 8081;

	public static void main(String[] args) throws Exception {
		NioEventLoopGroup parent = new NioEventLoopGroup();
		NioEventLoopGroup child = new NioEventLoopGroup();

		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(parent,child)
					.channel(NioServerSocketChannel.class)
					.localAddress(new InetSocketAddress(PORT))
					.childHandler(new FileInitializer())
					.childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
			;
			ChannelFuture future = b.bind();
			future.addListener(f -> {
				if (f.isSuccess()) {
					System.out.println("file server bind success ... port : "+future.channel().localAddress().toString());
				} else {
					throw new Exception();
				}
			});

			future.channel().closeFuture().sync();
			System.out.println("file server close successfully ...");
		} finally {
			child.shutdownGracefully().sync();
			parent.shutdownGracefully().sync();
			System.out.println("group shut down ...");
		}
	}

}
