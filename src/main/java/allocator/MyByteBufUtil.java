package allocator;

/**
 * <p>
 *
 * </p>
 *
 * @author O
 * @version 1.0
 * @since 2018/8/23
 */
public final class MyByteBufUtil {
	static final MyByteBufAllocator DEFAULT;
	static{
		DEFAULT = MyPooledByteBufAllocator.DEFAULT;
	}
}
