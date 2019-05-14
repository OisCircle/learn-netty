//import com.sun.xml.internal.stream.util.BufferAllocator;
//import io.netty.buffer.*;
//import io.netty.handler.codec.MessageAggregationException;
//import io.netty.util.ByteProcessor;
//import io.netty.util.CharsetUtil;
//import org.junit.Test;
//
//import java.io.BufferedReader;
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.nio.ByteBuffer;
//import java.nio.CharBuffer;
//import java.nio.charset.Charset;
//import java.util.Arrays;
//import java.util.stream.Stream;
//
//
///**
// * <p>
// *
// * </p>
// *
// * @author O
// * @version 1.0
// * @since 2018/8/19
// */
//public class ByteBufTest {
//	@Test
//	public void heapByteBufTest() {
//		ByteBuf buf = new UnpooledHeapByteBuf(ByteBufAllocator.DEFAULT, 10, 50);
//		buf.writeBytes("Hello Netty".getBytes());
//		if (buf.hasArray()) {
//			byte[] bytes = buf.array();
//			int length = buf.readableBytes();
//			int offset = buf.readerIndex() + buf.arrayOffset();
//			System.out.println("this is backing array");
//			System.out.println(Arrays.toString(bytes));
//
//			ByteBuffer nioBuf = ByteBuffer.wrap(bytes);
//
//			for (int i = 0; i < nioBuf.remaining(); i++) {
//				System.out.println((char) nioBuf.get());
//			}
//			System.out.println(buf.toString(CharsetUtil.UTF_8));
//		} else {
//			System.out.println("this is direct bytebuf");
//			System.out.println(String.valueOf(buf));
//		}
//	}
//
//	@Test
//	public void directByteBufTest() {
//		ByteBuf buf = new UnpooledDirectByteBuf(ByteBufAllocator.DEFAULT, 16, 50);
//		buf.writeBytes("Hello Netty".getBytes());
//		if (buf.hasArray()) {
//			byte[] bytes = buf.array();
//			int length = buf.readableBytes();
//			int offset = buf.readerIndex() + buf.arrayOffset();
//			System.out.println("this is backing array ... ");
//			System.out.println(Arrays.toString(bytes));
//
//			ByteBuffer nioBuf = ByteBuffer.wrap(bytes);
//
//			for (int i = 0; i < nioBuf.remaining(); i++) {
//				System.out.println((char) nioBuf.get());
//			}
//			System.out.println(buf.toString(CharsetUtil.UTF_8));
//		} else {
//			System.out.println("this is direct bytebuf ... ");
//			System.out.println(String.valueOf(buf));
//		}
//	}
//
//	@Test
//	public void compositeByteBufTest() {
//		CompositeByteBuf message = Unpooled.compositeBuffer();
//
//
//		ByteBuf header = Unpooled.directBuffer();
//		header.writeBytes(Unpooled.copiedBuffer("Header", CharsetUtil.UTF_8));
//		ByteBuf content = Unpooled.buffer();
//		content.writeBytes(Unpooled.copiedBuffer("Content", CharsetUtil.UTF_8));
//		message.addComponents(header, content);
//
//
//		message.forEach(byteBuf -> System.out.println(byteBuf.toString(CharsetUtil.UTF_8)));
//
//		ByteBuf request = Unpooled.wrappedBuffer(header, content);
//
//		ByteBuf header2 = message.component(0);
//		System.out.println(header2.toString(CharsetUtil.UTF_8));
//
//		byte[] bytes = new byte[message.readableBytes()];
//		ByteBuf buf = message.readBytes(message.readableBytes());
//		System.out.println(buf.toString(CharsetUtil.UTF_8));
//		System.out.println(Arrays.toString(bytes));
//
//	}
//
//	@Test
//	public void byteOperationTest() {
//		ByteBuf buf = Unpooled.copiedBuffer("Netty", CharsetUtil.UTF_8);
//		ByteBuf buf2 = Unpooled.buffer(30);
//		buf2.writeBytes("tt".getBytes());
////		buf.setByte(0, 'h');
////		while (buf.readableBytes() > 0) {
////			System.out.println(buf.toString(CharsetUtil.UTF_8));
////		}
////		buf.writeInt(2);
////
////		System.out.println(buf.toString(CharsetUtil.UTF_8));
////		System.out.println(buf2.toString(CharsetUtil.UTF_8));
//
////		System.out.println(ByteBufUtil.indexOf(buf2, buf));
////		int index = buf.forEachByte(new ByteProcessor.IndexOfProcessor((byte)'N'));
////		int index2 = buf.forEachByte(new ByteProcessor.IndexNotOfProcessor(((byte) 'N')));
////		buf.forEachByte(ByteProcessor.FIND_LF);
////		System.out.println(index);
////		System.out.println(index2);
//
//		buf.readByte();
//		buf.readByte();
//		buf.readByte();
//		ByteBuf buf3 = buf.duplicate();
//		ByteBuf buf4 = buf.retain();
//
//		ByteBuf buf5 = buf.slice();
//
////		buf3.readByte();
////		buf4.readByte();
////		buf5.readByte();
////		buf3.readerIndex(0);
////		buf4.readerIndex(0);
////		buf5.readerIndex(0);
////		System.out.println(buf.toString(CharsetUtil.UTF_8));
////		System.out.println(buf3.toString(CharsetUtil.UTF_8));
////		System.out.println(buf4.toString(CharsetUtil.UTF_8));
////		System.out.println(buf5.toString(CharsetUtil.UTF_8));
//
//		ByteBuf buf6 = ByteBufAllocator.DEFAULT.buffer();
//
//		buf6 = ByteBufAllocator.DEFAULT.directBuffer();
//
//		ByteBufHolder holder = new DefaultByteBufHolder(buf6);
//		ByteBuf buf7 = Unpooled.directBuffer();
//		System.out.println(buf7.alloc());
//	}
//}
