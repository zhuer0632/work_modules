package com.me.ut.string;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.me.ut.date.DatetimeUT;

public class Dump {

    /**
     * 增强syso
     */
    @SuppressWarnings("unchecked")
    public static void print(Object obj) {
	
	
	if (obj == null) {
	    System.out.println("null");
	    return;
	}
	if (obj instanceof String) {
	    System.out.println(obj);
	    return;
	}

	if (obj instanceof Date) {
	    System.out.println(DatetimeUT.getDatetimeStamp((Date) obj));
	    return;
	}

	if (obj instanceof Boolean) {
	    System.out.println(String.valueOf(obj));
	    return;
	}

	if (obj instanceof List) {

	    List list = (List) obj;
	    if (list.size() > 0) {
		for (int i = 0; i < list.size(); i++) {
		    System.out.println(list.get(i));
		}
	    } else {
		System.out.println("空list");
	    }
	    return;
	}

	if (obj instanceof Map) {
	    Map map =(Map) obj;
	    if (map.size() == 0) {
		System.out.println("空map");
		return;
	    }

	    Iterator it = map.keySet().iterator();
	    while (it.hasNext()) {

		Object key = it.next();
		Object value = map.get(key);
		System.out.println("key:value=" + key + ":" + value);
	    }
	}
    }

}
