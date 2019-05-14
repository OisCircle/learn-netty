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
public abstract class MyAbstractByteBufAllocator implements MyByteBufAllocator {
	@Override
	public ByteBuf heapBuf(int initialCapacity) {
		return newHeapBuf(16);
	}

	@Override
	public ByteBuf directBuf(int initialCapacity) {
		return newDirectBuf(16);
	}

	protected abstract ByteBuf newHeapBuf(int initialCapacity);
	protected abstract ByteBuf newDirectBuf(int initialCapacity);
}
