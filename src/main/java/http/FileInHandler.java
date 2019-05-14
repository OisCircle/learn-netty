package http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * <p>
 *
 * </p>
 *
 * @author O
 * @version 1.0
 * @since 2018/9/1
 */
public class FileInHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			System.out.println("------------------------");
			String result = (String) msg;

			System.out.println("FileInHandler::channelRead");
			System.out.println("result : " + result);




			/*
			 * 如果从context调用write, 从当前handler返回
			 *
			 * 如果从channel/pipeline调用write, 从整个outboundHandler开始返回
			 * ctx.pipeline().write(result)
			 * ctx.channel().write(result)
			 */
//			ctx.write(result);
			ctx.pipeline().write(result);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("------------------------");
		}
	}
}
