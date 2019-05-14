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
public class MyPooledByteBufAllocator extends MyAbstractByteBufAllocator implements MyByteBufAllocator{
	static final MyPooledByteBufAllocator DEFAULT = new MyPooledByteBufAllocator();
	@Override
	public ByteBuf heapBuf(int initialCapacity) {
		return super.heapBuf(initialCapacity);
	}

	@Override
	public ByteBuf directBuf(int initialCapacity) {
		return super.directBuf(initialCapacity);
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
