package com.me.http.ut;


import java.util.Hashtable;

import org.apache.http.Header;



public class TextHttpResponse
{

    private Hashtable<String, String> heads;
    private String content;
    private  int  code;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public Hashtable<String, String> getHeads()
    {
        return heads;
    }


    public void setHeads(Hashtable<String, String> heads)
    {
        this.heads = heads;
    }


    public String getContent()
    {
        return content;
    }


    public void setContent(String content)
    {
        this.content = content;
    }


    public void setHeaders(Header[] heads2)
    {
        if (heads == null)
        {
            heads = new Hashtable<String, String>();
        }
        for (int i = 0; i < heads2.length; i++)
        {
            heads.put(heads2[i].getName(),
                      heads2[i].getValue());
        }
    }

}
