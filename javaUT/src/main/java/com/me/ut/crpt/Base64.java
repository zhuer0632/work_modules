package com.me.ut.crpt;


import java.io.UnsupportedEncodingException;



public class Base64
{

    public static String decode(String str,
                                String charcode)
    {
        String out = "";
        try
        {
            out = org.apache.commons.codec.binary.Base64.encodeBase64String(str.getBytes(charcode));
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return out;
    }


    public static String encode(String str,
                                String charcode)
    {
        String out = "";
        try
        {
            out = org.apache.commons.codec.binary.Base64.encodeBase64String(str.getBytes(charcode));
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return out;
    }
    

}
