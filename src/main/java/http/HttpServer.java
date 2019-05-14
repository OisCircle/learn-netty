package http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.net.InetSocketAddress;

/**
 * <p>
 *
 * </p>
 *
 * @author O
 * @version 1.0
 * @since 2018/8/19
 */
public class HttpServer {
	private static final int PORT = 8080;

	private void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(group)
					.channel(NioServerSocketChannel.class)
					.localAddress(new InetSocketAddress(PORT))
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new HttpServerCodec())
									.addLast(new HttpObjectAggregator(Integer.MAX_VALUE))
									.addLast(new HttpHandler());
						}
					});
			ChannelFuture future = b.bind().sync();
			future.addListener(f -> {
				System.out.println("http server bind " + (f.isSuccess() ? "success" : "failed") + " ... ");
			});
			future.channel().closeFuture().sync();

		} finally {
			group.shutdownGracefully().sync();
		}


	}

	public static void main(String[] args) throws Exception {
		new HttpServer().start();
	}
}
