package http;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * <p>
 *
 * </p>
 *
 * @author O
 * @version 1.0
 * @since 2018/8/19
 */
public class HttpHandler extends ChannelInboundHandlerAdapter {
	private String result = "";

	/**
	 * 收到消息时，返回信息
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
					if (!(msg instanceof FullHttpRequest)) {
						result = "未知请求!";
						ctx.fireChannelRead(result);
						return;
					}
					FullHttpRequest httpRequest = (FullHttpRequest) msg;
					try {
						System.out.println("-----------------------");
						System.out.println("UriParamHandler::channelRead");
						JSONObject jsonObject = new JSONObject();
						String uri = httpRequest.uri();
						System.out.println("uri : " + uri);
						String body = getBody(httpRequest);
						System.out.println("body : " + body);
						HttpMethod method = httpRequest.method();
						System.out.println("接收到:" + method + " 请求");
						//如果是POST请求
						if (HttpMethod.POST.equals(method)) {
							//接受到的消息，做业务逻辑处理...
							jsonObject.put("请求类型", method.name());
							result = jsonObject.toJSONString();
//				send(ctx, result, HttpResponseStatus.OK);


				/*
				 *转发给下一个handler
				 *如果是ctx.pipeline().fireChannelRead(result), 则
				 * 从第一个inboundHandler重新开始传播
				 * */
				System.out.println("ctx.fireChannelRead(result);");
				ctx.fireChannelRead(result);
			}

		} catch (Exception e) {
			System.out.println("处理请求失败!");
			e.printStackTrace();
		} finally {
			//释放请求
			ReferenceCountUtil.release(httpRequest);
		}
	}

	/**
	 * 获取body参数
	 *
	 * @param request r
	 * @return str
	 */
	private String getBody(FullHttpRequest request) {
		ByteBuf buf = request.content();
		return buf.toString(CharsetUtil.UTF_8);
	}

	/**
	 * 发送的返回值
	 *
	 * @param ctx     返回
	 * @param context 消息
	 * @param status  状态
	 */

}
