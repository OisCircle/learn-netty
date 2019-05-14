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
public interface MyByteBufAllocator {
	ByteBuf heapBuf(int initialCapacity);

	ByteBuf directBuf(int initialCapacity);
}
