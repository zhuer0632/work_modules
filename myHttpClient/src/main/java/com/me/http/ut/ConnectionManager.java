package com.me.http.ut;

import org.apache.http.HttpHost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SchemeSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;


public class  ConnectionManager {

    static final int TIMEOUT = 20000;//连接超时时间
        static final int SO_TIMEOUT = 60000;//数据传输超时

        public static DefaultHttpClient getHttpClient(){
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(
                    new Scheme("http",80,PlainSocketFactory.getSocketFactory()));
            schemeRegistry.register(
                    new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));

            PoolingClientConnectionManager  cm = new PoolingClientConnectionManager(schemeRegistry);
            cm.setMaxTotal(200);
            cm.setDefaultMaxPerRoute(20);

            HttpParams params = new BasicHttpParams();
            params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,TIMEOUT);
            params.setParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);

            DefaultHttpClient client = new DefaultHttpClient(cm,params);

            //下面两行是通过代理访问
//            HttpHost proxy = new HttpHost("127.0.0.1", 8087);
//            client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

            return client;
        }




}