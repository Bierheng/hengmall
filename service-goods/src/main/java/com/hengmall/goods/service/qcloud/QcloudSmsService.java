package com.hengmall.goods.service.qcloud;

import java.io.IOException;
import java.util.Properties;

import javax.xml.ws.http.HTTPException;

import org.json.JSONException;
import org.springframework.stereotype.Service;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.hengmall.goods.service.BaseService;

/**
 * 腾讯云短信SDK
 */
@Service
public class QcloudSmsService extends BaseService {

    /**
     * 腾讯云短信发送
     *
     * @param msg         消息模板
     * @param phoneNumber 需要发送短信的手机号码
     */
    public SmsSingleSenderResult qcloudSms(String msg, String phoneNumber) {

        Properties prop = new Properties();
        try {
            //加载properties配置文件
            prop.load(QcloudSmsService.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int appid = Integer.parseInt(prop.getProperty("sms.appid"));
        String appkey = prop.getProperty("sms.appkey");
        int templateId = Integer.parseInt(prop.getProperty("sms.templateId"));
        // 签名
        String smsSign = prop.getProperty("sms.smsSign");

        // 单发短信
        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.send(0, "86", phoneNumber,msg, "", "");
            return result;
            //result = {"result":0,"errmsg":"OK","ext":"","sid":"8:kEhwkrUnZ8b1kM0eAwK20180523","fee":1}
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        } catch (com.github.qcloudsms.httpclient.HTTPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
}
