package com.tyxj.common.transfor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.transform.ResultTransformer;

/**第一个为key  第二个为value
 * @author 
 * @project 
 * @create_date 2014-8-5 上午9:32:03
 */
public class MapResultTransformer implements ResultTransformer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1484488468026814715L;

	public MapResultTransformer() {
	}

	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map result = new HashMap(1);
		if (tuple.length > 1) {
			result.put(tuple[0], tuple[1]);//默认第一个为
		}
		return result;
	}

	public List transformList(List collection) {
		return collection;
	}

}
