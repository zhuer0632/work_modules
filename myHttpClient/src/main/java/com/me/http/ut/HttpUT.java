package com.me.http.ut;


import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.me.ut.string.StringUT;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


/**
 * 对httpComponents的简单封装
 *
 * @author ZHU
 */
public class HttpUT
{

    private static final Logger logger = Logger.getLogger(HttpUT.class);
    private ThreadLocal<HttpClient> client = new ThreadLocal<HttpClient>();
    private static List<Header> headers = new ArrayList<Header>();

    /**
     * 根据响应头确定返回的编码，可能为空
     *
     * @param entity
     * @return
     */
    public static String getCharSet(HttpEntity entity)
    {
        String code = "";
        ContentType contentType = ContentType.getOrDefault(entity);
        if (contentType != null && contentType.getCharset() != null
                && StringUtils.isNotEmpty(contentType.getCharset().name()))
        {
            code = contentType.getCharset().name();
        }
        return code;
    }


    /**
     * 检查指定的编码是否正确
     *
     * @param incode
     * @param outcode
     */
    private static strictfp void checkCode(String incode,
                                           String outcode)
    {
        if (!CharSets.charlist.contains(outcode))
        {
            throw new RuntimeException("请求编码不正确");
        }
        if (!CharSets.charlist.contains(outcode))
        {
            throw new RuntimeException("响应编码不正确");
        }
    }


    /**
     * 指定的文件资源保存到指定的文件中
     */
    public static void get_net2file(String url,
                                    String filepath)
    {
        @SuppressWarnings("unused")
        Header[] heads = null;
        HttpClient http = ConnectionManager.getHttpClient();
        HttpResponse response = null;
        try
        {
            URIBuilder uriBuilder = new URIBuilder(url);
            // 构建请求对象
            URI uri = uriBuilder.build();
            HttpGet get = new HttpGet(uri);

            // 设置cookie策略
            get.getParams().setParameter(ClientPNames.COOKIE_POLICY,
                    CookiePolicy.BROWSER_COMPATIBILITY);

            // (2) 使用HttpClient发送get请求，获得返回结果HttpResponse
            response = http.execute(get);
            HttpEntity entity = response.getEntity();

            InputStream input = entity.getContent();
            OutputStream output = new FileOutputStream(filepath);
            IOUtils.copy(input,
                    output);
        } catch (Exception e)
        {
            Dump.dump("正在请求：" + url);
            e.printStackTrace();
        } finally
        {
            HttpClientUtils.closeQuietly(response);
        }
    }


    /**
     * 执行带有参数的get请求，请求参数已经包含在了url中，并且是已经编码过的。
     * <p/>
     * <br>
     * 响应头中如果声明了响应内容的编码则使用声明的编码，否则使用输入参数中指定的outcode
     *
     * @param url
     * @param outcode
     * @return
     */
    public static TextHttpResponse get_url(String url,
                                           String outcode)
    {
        if (!CharSets.charlist.contains(outcode))
        {
            throw new RuntimeException("响应编码不正确");
        }

        Header[] heads_res = null;
        HttpClient http = ConnectionManager.getHttpClient();
        HttpResponse response = null;
        TextHttpResponse out = new TextHttpResponse();
        try
        {
            URIBuilder uriBuilder = new URIBuilder(url);

            // 构建请求对象
            URI uri = uriBuilder.build();
            HttpGet get = getDecoratorGet(new HttpGet(uri), url);

            // 设置cookie策略
            get.getParams().setParameter(ClientPNames.COOKIE_POLICY,
                    CookiePolicy.BROWSER_COMPATIBILITY);

            // (2) 使用HttpClient发送get请求，获得返回结果HttpResponse
            response = http.execute(get);
            HttpEntity entity = response.getEntity();

            String out_str = "";

            // 如果没有返回编码，就使用自己指定的编码
            if (StringUtils.isEmpty(getCharSet(entity)))
            {
                out_str = EntityUtils.toString(entity,
                        outcode);
            }
            else
            {
                out_str = EntityUtils.toString(entity);
            }

            out.setContent(out_str);
            if (response.getStatusLine().getStatusCode() != 200)
            {
                logger.debug("出现不正确的返回编码" + response.getStatusLine().getStatusCode() + "==" + url);
//                if (response.getStatusLine().getStatusCode() == 301 || response.getStatusLine().getStatusCode() == 302)
//                {
//                    //重新发起请求
//                    String location = response.getHeaders("Location").toString();
//                    return HttpUT.get_url(location, outcode);
//                }
//                else
//                {
//                    logger.debug("出现不正确的返回编码"+response.getStatusLine().getStatusCode() +"=="+url);
//                }
            }
            // 获取所有的响应http头
            heads_res = response.getAllHeaders();
            out.setHeaders(heads_res);
            out.setCode(response.getStatusLine().getStatusCode());
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            HttpClientUtils.closeQuietly(response);
        }
        return out;
    }


    /**
     * 执行带有参数的get请求,请求参数写在Map中。根据指定的incode进行编码。
     * <p/>
     * <br>
     * 请求参数按照incode进行编码 <br>
     * 响应头中如果声明了响应内容的编码则使用声明的编码，否则使用输入参数中指定的outcode
     * <p/>
     * 格式：http://www.baidu.com/s? (除了参数之外的url部分,必须以?结尾)
     *
     * @param incode
     * @param outcode
     * @return
     */
    public static TextHttpResponse get_params(String url,
                                              Map<String, String> params,
                                              String incode,
                                              String outcode)
    {
        if (!CharSets.charlist.contains(outcode))
        {
            throw new RuntimeException("响应编码不正确");
        }

        if (url == null || !url.endsWith("?"))
        {
            throw new RuntimeException("参数url格式不正确");
        }

        Header[] heads = null;
        HttpClient http = ConnectionManager.getHttpClient();
        HttpResponse response = null;
        TextHttpResponse out = new TextHttpResponse();
        try
        {

            URIBuilder uriBuilder;
            String queryStr = "";
            List<NameValuePair> paramss = new ArrayList<NameValuePair>();
            if (params != null && !params.isEmpty())
            {
                Set<String> keyset = params.keySet();
                Iterator<String> it = keyset.iterator();
                while (it.hasNext())
                {
                    String key = it.next();
                    String value = params.get(key);
                    paramss.add(new BasicNameValuePair(key, value));
                }
                queryStr = URLEncodedUtils.format(paramss,
                        incode);
            }
            if (!StringUtils.isEmpty(queryStr))
            {
                url = url + "?" + queryStr;
            }
            uriBuilder = new URIBuilder(url);
            // 构建请求对象
            URI uri = uriBuilder.build();
            HttpGet get = getDecoratorGet(new HttpGet(uri), url);

            // 设置cookie策略
            get.getParams().setParameter(ClientPNames.COOKIE_POLICY,
                    CookiePolicy.BROWSER_COMPATIBILITY);

            // (2) 使用HttpClient发送get请求，获得返回结果HttpResponse
            response = http.execute(get);
            HttpEntity entity = response.getEntity();
            String out_str = "";

            // 如果没有返回编码，就使用自己指定的编码
            if (StringUtils.isEmpty(getCharSet(entity)))
            {
                out_str = EntityUtils.toString(entity,
                        outcode);
            }
            else
            {
                out_str = EntityUtils.toString(entity);
            }

            out.setContent(out_str);
            if (response.getStatusLine().getStatusCode() != 200)
            {
                throw new RuntimeException(out_str);
            }

            // 获取所有的响应http头
            heads = response.getAllHeaders();
            out.setHeaders(heads);

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            HttpClientUtils.closeQuietly(response);
        }
        return out;
    }


    /**
     * 对httpget对象设置header
     *
     * @param httpGet
     * @param url
     * @return
     */
    private static HttpGet getDecoratorGet(HttpGet httpGet, String url)
    {

        if (!StringUT.isEmpty(headers))
        {
            httpGet.removeHeaders("Host");
            httpGet.removeHeaders("Cookie");
            httpGet.removeHeaders("User-Agent");
            httpGet.removeHeaders("Referer");


            //添加上需要的
//            Accept	text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
//
//            httpGet.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//            httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:17.0) Gecko/20100101 Firefox/17.0");
//            httpGet.addHeader("Host","www.oschina.net");
//            httpGet.addHeader("Cookie","oscid=k5bql9%2FuKid9JYoQ7VF19QcxnGCAWdYT6%2BV%2BgIpTVLYCIFNGoe4CSCXT0UGz4FejPjaqsL95lhIFXpY0ST4wab13BhqnCX4ImghtKQQE3GhBP7RMT1ihr%2BsIy9RU4DtH");
//            httpGet.addHeader("Connection","keep-alive");
//            httpGet.addHeader("Cache-Control","max-age=0");
//            httpGet.addHeader("Accept-Language","zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
//            httpGet.addHeader("Accept-Encoding","gzip, deflate");
//            return   httpGet;
            for (Header header : headers)
            {
                httpGet.addHeader(header);
            }
        }
        return httpGet;
    }


    /**
     * 对httpPost对象设置header
     *
     * @param httpPost
     * @return
     */
    private static HttpPost getDecoratorPost(HttpPost httpPost)
    {
        if (1==1)
        {
            httpPost.removeHeaders("Host");
            httpPost.removeHeaders("Cookie");
            httpPost.removeHeaders("User-Agent");
            httpPost.removeHeaders("Referer");

//            httpPost.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//            httpPost.addHeader("Accept-Encoding","gzip, deflate");
//            httpPost.addHeader("Accept-Language","zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
//            httpPost.addHeader("Cache-Control","no-cache");
//            httpPost.addHeader("Connection","keep-alive");
//        map.put("Content-Length","61");
//            httpPost.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
            httpPost.addHeader("Cookie","ts_refer=xui.ptlogin2.qq.com/cgi-bin/qlogin; ts_uid=8522304802; pt2gguin=o1929513424; pgv_pvid=9075520744; o_cookie=1929513424; ptui_loginuin=1929513424; RK=Bygy0tr7/W; pgv_pvi=1729684090; wbilang_10000=zh_CN; ts_last=/zhushaolong321/mine; ts_sid=6245090632; wb_regf=%3B0%3B%3Bxui.ptlogin2.qq.com%3B0; wbilang_1929513424=zh_CN; luin=o1929513424; lskey=00010000d58b4d191718562dc6b1aa641f18a921ce369141f29f1483453e0cbb76a18f9d3826c314775bebd2; mb_reg_from=8; pgv_info=ssid=s6931528872");
//            httpPost.addHeader("Host","api.t.qq.com");
//            httpPost.addHeader("Pragma","no-cache");
            httpPost.addHeader("Referer","http://api.t.qq.com/proxy.html");
            httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:17.0) Gecko/20100101 Firefox/17.0");
//            httpPost.addHeader("rf","http://t.qq.com/zhushaolong321/mine");

                return httpPost;
        }
        if (!StringUT.isEmpty(headers))
        {
            httpPost.removeHeaders("Host");
            httpPost.removeHeaders("Cookie");
            httpPost.removeHeaders("User-Agent");
            httpPost.removeHeaders("Referer");


            //添加上需要的
//            Accept	text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
//
//            httpGet.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//            httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:17.0) Gecko/20100101 Firefox/17.0");
//            httpGet.addHeader("Host","www.oschina.net");
//            httpGet.addHeader("Cookie","oscid=k5bql9%2FuKid9JYoQ7VF19QcxnGCAWdYT6%2BV%2BgIpTVLYCIFNGoe4CSCXT0UGz4FejPjaqsL95lhIFXpY0ST4wab13BhqnCX4ImghtKQQE3GhBP7RMT1ihr%2BsIy9RU4DtH");
//            httpGet.addHeader("Connection","keep-alive");
//            httpGet.addHeader("Cache-Control","max-age=0");
//            httpGet.addHeader("Accept-Language","zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
//            httpGet.addHeader("Accept-Encoding","gzip, deflate");
//            return   httpGet;
            for (Header header : headers)
            {
                httpPost.addHeader(header);
            }
        }
        return httpPost;

    }

    /**
     * 执行带有参数的post请求
     * <p/>
     * <br>
     * 请求参数按照incode进行编码 <br>
     * 响应头中如果声明了响应内容的编码则使用声明的编码，否则使用输入参数中指定的outcode
     *
     * @param url
     * @param params
     * @param incode
     * @param outcode
     * @return
     */
    public static TextHttpResponse post(String url,
                                        Map<String, String> params,
                                        String incode,
                                        String outcode)
    {
        checkCode(incode,
                outcode);

        Header[] heads = null;
        HttpClient http = ConnectionManager.getHttpClient();
        HttpResponse response = null;
        TextHttpResponse out = new TextHttpResponse();
        try
        {
            // (1)创建查询参数
            List<NameValuePair> paramss = new ArrayList<NameValuePair>();
            if (params != null && !params.isEmpty())
            {
                Set<String> keyset = params.keySet();
                Iterator<String> it = keyset.iterator();
                while (it.hasNext())
                {
                    String key = it.next();
                    String value = params.get(key);
                    paramss.add(new BasicNameValuePair(key, value));
                }
            }
            else
            {
                throw new RuntimeException("请求参数不能为空");
            }

            // (2) 创建post实例
            HttpPost post = getDecoratorPost(new HttpPost(url));
            post.setEntity(new UrlEncodedFormEntity(paramss, incode));

            // 设置cookie策略
            post.getParams().setParameter(ClientPNames.COOKIE_POLICY,
                    CookiePolicy.BROWSER_COMPATIBILITY);

            // (2) 使用HttpClient发送post请求，获得返回结果HttpResponse
            response = http.execute(post);

            // (3) 读取返回结果
            HttpEntity entity = response.getEntity();

            String out_str = "";
            if (StringUtils.isEmpty(getCharSet(entity)))
            {
                out_str = EntityUtils.toString(entity,
                        outcode);
            }
            else
            {
                out_str = EntityUtils.toString(entity);
            }

            out.setContent(out_str);
            if (response.getStatusLine().getStatusCode() != 200)
            {
                throw new RuntimeException(out_str);
            }
            // 取得heads
            heads = response.getAllHeaders();
            out.setHeaders(heads);

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            HttpClientUtils.closeQuietly(response);
        }
        return out;
    }

    /**
     * 执行不带参数的post请求
     * <p/>
     * <br>
     * 请求参数按照incode进行编码 <br>
     * 响应头中如果声明了响应内容的编码则使用声明的编码，否则使用输入参数中指定的outcode
     *
     * @param url
     * @param incode
     * @param outcode
     * @return
     */
    public static TextHttpResponse post(String url,
                                        String incode,
                                        String outcode)
    {
        checkCode(incode,
                outcode);
        Header[] heads = null;
        HttpClient http = ConnectionManager.getHttpClient();
        HttpResponse response = null;
        TextHttpResponse out = new TextHttpResponse();
        try
        {
            // (2) 创建post实例
            HttpPost post = getDecoratorPost(new HttpPost(url));

            // 设置cookie策略
            post.getParams().setParameter(ClientPNames.COOKIE_POLICY,
                    CookiePolicy.BROWSER_COMPATIBILITY);

            // (2) 使用HttpClient发送post请求，获得返回结果HttpResponse
            response = http.execute(post);

            // (3) 读取返回结果
            HttpEntity entity = response.getEntity();

            String out_str = "";
            if (StringUtils.isEmpty(getCharSet(entity)))
            {
                out_str = EntityUtils.toString(entity,
                        outcode);
            }
            else
            {
                out_str = EntityUtils.toString(entity);
            }

            out.setContent(out_str);
            if (response.getStatusLine().getStatusCode() != 200)
            {
                throw new RuntimeException(out_str);
            }
            // 取得heads
            heads = response.getAllHeaders();
            out.setHeaders(heads);

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            HttpClientUtils.closeQuietly(response);
        }
        return out;
    }

    public static void addHeaders(Map<String, String> headerss)
    {
        if (!StringUT.isEmpty(headers))
        {
            for (int i = 0; i < headers.size(); i++)
            {
                Header header1 = headers.get(i);
                headers.remove(header1);
                --i;
            }
        }

        Iterator<String> it = headerss.keySet().iterator();
        while (it.hasNext())
        {
            String key = it.next();
            String value = headerss.get(key);
            Header header = new BasicHeader(key, value);
            headers.add(header);
        }
    }

    public static void resetHeader()
    {
        headers.clear();
        headers = null;
    }
}
