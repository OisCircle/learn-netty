package allocator;

import io.netty.buffer.ByteBuf;

/**
 * <p>
 *
 * </p>
 *
 * @author O
 * @version 1.0
 * @since 2018/8/23
 */
public class MyUnpooledByteBufAllocator extends MyAbstractByteBufAllocator implements MyByteBufAllocator {
	static final MyUnpooledByteBufAllocator DEFAULT= new MyUnpooledByteBufAllocator();
	@Override
	public ByteBuf heapBuf(int initialCapacity) {
		return newHeapBuf(initialCapacity);
	}

	@Override
	public ByteBuf directBuf(int initialCapacity) {
		return newDirectBuf(initialCapacity);
	}

	@Override
	protected ByteBuf newHeapBuf(int initialCapacity) {
		return null;
	}

	@Override
	protected ByteBuf newDirectBuf(int initialCapacity) {
		return null;
	}
}
