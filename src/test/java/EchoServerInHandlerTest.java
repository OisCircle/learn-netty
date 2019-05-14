//import handler.EchoServerInHandler;
//import handler.FixedLengthFrameDecoder;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.embedded.EmbeddedChannel;
//import io.netty.util.CharsetUtil;
//import org.junit.Test;
//
//import java.util.stream.Stream;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertTrue;
//
///**
// * <p>
// *
// * </p>
// *
// * @author O
// * @version 1.0
// * @since 2018/8/16
// */
//
//public class EchoServerInHandlerTest {
//	@Test
//	public void test1() {
//		EmbeddedChannel channel = new EmbeddedChannel(new EchoServerInHandler());
//
//		channel.writeInbound(Unpooled.copiedBuffer("hello", CharsetUtil.UTF_8).retain());
//
//		assertTrue(channel.finish());
//
//		ByteBuf read = channel.readInbound();
//		ByteBuf read2 = channel.readInbound();
//		System.out.println(read);
//		System.out.println(read2);
//	}
//
//	@Test
//	public void testFramesDecoded() {
//		ByteBuf buf = Unpooled.buffer();
//		for (int i = 0; i < 9; i++) {
//			buf.writeByte(i);
//		}
//		ByteBuf input = buf.duplicate();
//		EmbeddedChannel channel = new EmbeddedChannel(
//				new FixedLengthFrameDecoder(3));
//// write bytes
//		assertTrue(channel.writeInbound(input.retain()));
//		assertTrue(channel.finish());
//// read messages
//		ByteBuf read = (ByteBuf) channel.readInbound();
//		assertEquals(buf.readSlice(3), read);
//		read.release();
//		read = (ByteBuf) channel.readInbound();
//		assertEquals(buf.readSlice(3), read);
//		read.release();
//		read = (ByteBuf) channel.readInbound();
//		assertEquals(buf.readSlice(3), read);
//		read.release();
//		assertNull(channel.readInbound());
//		buf.release();
//	}
//}
