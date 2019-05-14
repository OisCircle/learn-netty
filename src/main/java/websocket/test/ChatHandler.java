package websocket.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.concurrent.ImmediateEventExecutor;

/**
 * <p>
 *
 * </p>
 *
 * @author O
 * @version 1.0
 * @since 2018/11/28
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
	/**
	 * 根据服务器传进来的openId，分配到不同的group
	 */
	private static final ChannelGroup GROUP = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		//retain增加引用计数，防止接下来的调用引用失效
		System.out.println("服务器接收到来自 " + ctx.channel().id() + " 的消息： " + msg.text());
		GROUP.writeAndFlush(msg.retain());
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE) {
			System.out.println("客户端 :" + ctx.channel().id() + " 加入聊天...");
			GROUP.add(ctx.channel());
		} else {
			super.userEventTriggered(ctx, evt);
		}
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("客户: "+ctx.channel().id()+" 已下线...");

		super.channelInactive(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("异常情况...");
		cause.printStackTrace();
		super.exceptionCaught(ctx, cause);
	}
}
