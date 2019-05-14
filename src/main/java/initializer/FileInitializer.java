package initializer;

import http.FileInHandler;
import http.FileOutHandler;
import http.FileOutHandler2;
import http.HttpHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * <p>
 *
 * </p>
 *
 * @author O
 * @version 1.0
 * @since 2018/9/1
 */
public class FileInitializer extends ChannelInitializer {
	@Override
	protected void initChannel(Channel ch) {
		ch.pipeline()
				.addLast(new HttpServerCodec())
				.addLast(new HttpObjectAggregator(Integer.MAX_VALUE))
				/*
				 * out handler不放在这里, 无法传递???
				 * reason :当开始outbound时, 从当前inboundHandler开始往回调, 如果
				 * 放在最后一个,则outbound时不经过
				 */
				.addLast(new FileOutHandler())
				.addLast(new HttpHandler())
				.addLast(new FileInHandler())
				.addLast(new FileOutHandler2())
		;

	}
}
