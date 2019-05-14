package websocket.test;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * <p>
 *
 * </p>
 *
 * @author O
 * @version 1.0
 * @since 2018/11/28
 */
public class ChatInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) {
		ch.pipeline().addLast(new HttpServerCodec());
		ch.pipeline().addLast(new HttpObjectAggregator(65536));
		ch.pipeline().addLast(new ChunkedWriteHandler());
		ch.pipeline().addLast(new UriParamHandler());
		ch.pipeline().addLast(new WebSocketServerProtocolHandler("/"));
		ch.pipeline().addLast(new ChatHandler());
	}
}
