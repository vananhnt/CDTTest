package cfg.utils;

import org.objenesis.strategy.StdInstantiatorStrategy;

import com.esotericsoftware.kryo.Kryo;

/**
 * @author va
 */

public class Cloner  {
	
	@SuppressWarnings("deprecation")
	public static <T> T clone(T object) {
		
		Kryo kryo = new Kryo();
		try {
			kryo.setAsmEnabled(true);
			// kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
			((Kryo.DefaultInstantiatorStrategy) kryo.getInstantiatorStrategy()).setFallbackInstantiatorStrategy(new StdInstantiatorStrategy());
			return kryo.copy(object);
		}
		finally {
			
			kryo = null;
		}
	}
}
