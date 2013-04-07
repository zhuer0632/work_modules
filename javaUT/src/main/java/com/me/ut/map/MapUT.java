package com.me.ut.map;

import java.util.Iterator;
import java.util.Map;

import com.me.ut.string.StringUT;

public class MapUT {

    
    /**
     * 遍历map，如果发现value中的某一个值包含字符串fieldName，则返回key
     * @param map
     * @param fieldName
     * @return
     */
    public static String getItem(Map<String,String> map,String fieldName)
    {
	String out="";
	Iterator<String>it=map.keySet().iterator();
	
	while (it.hasNext()) {
	
	  String key=  it.next();
	  String value= map.get(key);
	  if(value.contains(fieldName))
	  {
	      out=key;
	      break;
	  }
	}
	if(StringUT.isEmpty(out))
	{
	    throw  new RuntimeException("指定的fieldName不存在");
	}
	return out;
    }
    
}
