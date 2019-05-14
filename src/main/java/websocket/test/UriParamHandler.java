package websocket.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 获取参数并修改uri为 '/'，以便对应ws后缀
 * </p>
 *
 * @author O
 * @version 1.0
 * @since 2018/11/28
 */
public class UriParamHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
		String uri = request.uri();
		Map<String, String> params = uriToMap(uri);
		System.out.println("params: " + params);

		request = request.setUri("/");
		ctx.fireChannelRead(request.retain());
	}

	private Map<String, String> uriToMap(String paramStr) {
		String[] params = paramStr.split("&");
		Map<String, String> resMap = new HashMap<>(8);
		for (int i = 0; i < params.length; i++) {
			String[] param = params[i].split("=");
			if (param.length >= 2) {
				String key = param[0];
				String value = param[1];
				for (int j = 2; j < param.length; j++) {
					value += "=" + param[j];
				}
				resMap.put(key, value);
			}
		}
		return resMap;
	}
}
