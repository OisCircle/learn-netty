package http;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * <p>
 *
 * </p>
 *
 * @author O
 * @version 1.0
 * @since 2018/9/1
 */
public class FileOutHandler2 extends ChannelOutboundHandlerAdapter {
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		System.out.println("--------------------");
		try {
			System.out.println("FileOutHandler2::write");
			System.out.println("result: "+(String)msg);

			ctx.writeAndFlush(msg);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("--------------------");
		}
	}
}