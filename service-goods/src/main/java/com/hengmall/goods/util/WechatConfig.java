package com.hengmall.goods.util;

import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;

import com.hengmall.goods.model.constitute.WechatRefundApiResult;

@SuppressWarnings("deprecation")
public class WechatConfig {

    private static SSLConnectionSocketFactory sslcsf;

    protected static String certName = "/cert/apiclient_cert.p12";
    protected static String mch_id = "1498205432";

    public static SSLConnectionSocketFactory getSslcsf() {
        if (null == sslcsf) {
            setSsslcsf();
        }
        return sslcsf;
    }

    private static void setSsslcsf() {
        try {

            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            Thread.currentThread().getContextClassLoader();
            InputStream instream = new WechatRefundApiResult().getClass().
                    getResourceAsStream(certName);

            try {
                keyStore.load(instream, mch_id.toCharArray());
            } finally {
                instream.close();
            }
            SSLContext sslcontext = SSLContexts.custom().
                    loadKeyMaterial(keyStore, mch_id.toCharArray()).build();
            sslcsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
