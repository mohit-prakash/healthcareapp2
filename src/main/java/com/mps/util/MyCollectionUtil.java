package com.mps.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MyCollectionUtil {
	static Map<Long,String> convertToMap(List<Object[]> list)
	{
		Map<Long,String> map = list.stream()
										.collect(
												Collectors.toMap
																(
																ob->Long.valueOf(ob[0].toString()) , 
																ob->ob[1].toString()
																)
												);
		return map;
	}
	
	static Map<Long,String> convertToMapFromList(List<Object[]> list)
	{
		Map<Long,String> map=list.stream().collect(Collectors.toMap(ob->Long.valueOf(ob[0].toString()) , ob->ob[1].toString()+" "+ob[2].toString()));
		return map;
	}
}
