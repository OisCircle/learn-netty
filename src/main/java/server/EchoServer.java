package server;

import handler.EchoServerInHandler;
import handler.EchoServerOutHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

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
public class EchoServer {
	private int port;

	private EchoServer(int port) {
		this.port = port;
	}

	public static void main(String[] args) throws Exception {
		new EchoServer(8080).start();
	}

	private void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(group)
					.channel(NioServerSocketChannel.class)
					.localAddress(new InetSocketAddress(port))
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							//http协议编解码器
							ch.pipeline().addLast(new HttpServerCodec());
							ch.pipeline().addLast(new EchoServerInHandler());
						}
					});

			ChannelFuture future = b.bind();
			future.addListener(f -> {
				if (f.isSuccess()) {
					System.out.println("echo server bind success ... ");
				} else {
					System.out.println("echo server bind failed ... ");
				}
			});



			future.channel().closeFuture().sync();
			System.out.println("EchoServer shutdown ... ");
		} finally {
			group.shutdownGracefully().sync();
		}
	}
}
