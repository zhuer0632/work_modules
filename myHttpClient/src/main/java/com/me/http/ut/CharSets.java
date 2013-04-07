package com.me.http.ut;


import java.util.ArrayList;
import java.util.List;


public class CharSets
{

    public static final String UTF_8 = "UTF-8";
    public static final String GBK = "GBK";
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String GB2312 = "GB2312";

    public static List<String> charlist=new ArrayList<String>();

    static
    {
        charlist.add(UTF_8);
        charlist.add(GBK);
        charlist.add(ISO_8859_1);
        charlist.add(GB2312);
    }

}
