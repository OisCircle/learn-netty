//import codec.decoder.EchoByteToIntDecoder;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.embedded.EmbeddedChannel;
//import org.junit.Test;
//
//import static org.junit.Assert.assertTrue;
//
///**
// * <p>
// *
// * </p>
// *
// * @author O
// * @version 1.0
// * @since 2018/8/17
// */
//public class EchoByteToIntDecoderTest {
//	@Test
//	public void test1() {
//		EmbeddedChannel channel = new EmbeddedChannel(new EchoByteToIntDecoder());
//		ByteBuf input = Unpooled.buffer(20);
//		for (int i = 0; i < 20 / 4; i++) {
//			input.writeInt(i);
//		}
//		channel.writeInbound(input.retain());
//		assertTrue(channel.finish());
//
//		for (int i = 0; i < 20 / 4; i++) {
//			System.out.println("read : " + channel.readInbound().toString());
//		}
//	}
//}
