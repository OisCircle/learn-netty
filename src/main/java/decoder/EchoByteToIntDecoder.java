package decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author O
 * @version 1.0
 * @since 2018/8/17
 */
public class EchoByteToIntDecoder extends ByteToMessageDecoder {
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		while (in.readableBytes() >= 4) {
			out.add(in.readInt());
		}
	}
}
