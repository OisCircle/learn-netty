package http;

import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import javax.annotation.Resource;
import java.net.SocketAddress;

/**
 * <p>
 *
 * </p>
 *
 * @author O
 * @version 1.0
 * @since 2018/9/1
 */
public class FileOutHandler extends ChannelOutboundHandlerAdapter {
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		System.out.println("--------------------");
		String result = (String) msg;
		try {

			System.out.println("FileOutHandler::write");
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.copiedBuffer(result, CharsetUtil.UTF_8));
			response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json; charset=UTF-8");

			ChannelFuture future = ctx.write(response);
			/*
			 *要添加监听器才能正常发送消息
			 * why???
			 *  */
			future.addListener(ChannelFutureListener.CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("references count (result) : " + ReferenceCountUtil.refCnt(result));

			System.out.println("--------------------");
		}
	}
}
