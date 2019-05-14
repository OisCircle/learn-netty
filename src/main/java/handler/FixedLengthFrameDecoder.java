package handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author O
 */
public class FixedLengthFrameDecoder extends ByteToMessageDecoder {
	private final int frameLength;

	public FixedLengthFrameDecoder(int frameLength) {
		if (frameLength <= 0) {
			throw new IllegalArgumentException(
					"frameLength must be a positive integer: " + frameLength);
		}
		this.frameLength = frameLength;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
	                      List<Object> out) throws Exception {
		while (in.readableBytes() >= frameLength) {
			ByteBuf buf = in.readBytes(frameLength);
			out.add(buf);
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		super.channelRead(ctx, msg);
		ByteBuf buf = (ByteBuf) msg;
		System.out.println("received ... ");
		System.out.println(buf);
	}
}