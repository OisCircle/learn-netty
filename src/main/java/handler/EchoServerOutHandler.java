package handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.net.SocketAddress;

/**
 * <p>
 *
 * </p>
 *
 * @author O
 * @version 1.0
 * @since 2018/8/15
 */
@ChannelHandler.Sharable
public class EchoServerOutHandler extends ChannelOutboundHandlerAdapter {
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		super.handlerAdded(ctx);
		System.out.println("out bound handler added ... ");
	}

	@Override
	public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
		super.bind(ctx, localAddress, promise);
		System.out.println("out bound handler bind ... ");
	}

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		super.write(ctx, msg, promise);
		System.out.println("out bound write ... ");
	}

	@Override
	public void flush(ChannelHandlerContext ctx) throws Exception {
		super.flush(ctx);
		System.out.println("out bound flush ... ");
	}
}
