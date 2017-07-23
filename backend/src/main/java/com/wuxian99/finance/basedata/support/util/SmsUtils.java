package com.wuxian99.finance.basedata.support.util;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 17/6/7.
 * 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可)
 * 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar
 * 2:aliyun-java-sdk-dysmsapi.jar
 *
 * 备注:Demo工程编码采用UTF-8
 * 国际短信发送请勿参照此DEMO
 */
public class SmsUtils {

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIoBFUqe6zLvGE";
    static final String accessKeySecret = "tq3PDP9xP7EdrfS5ZwnrMEDQB3Bj43";

    //短信类型与模板映射  防止对应用层暴露太多阿里云短信的细节
    static Map<SmsSendType,String> smsSendTypeMap = new HashMap<>();

    static {
        //key=发送类型  value=短信模板
        smsSendTypeMap.put(SmsSendType.Login,"SMS_78895054");
    }

    public static SendSmsResponse sendSms(SmsSendRequest smsSendRequest) {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        try {
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(smsSendRequest.getPhoneNumbers());
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("阿里云短信测试专用");
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(smsSendTypeMap.get(smsSendRequest.getSmsSendType()));
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam(JSON.toJSONString(smsSendRequest.getParamMap()));

            //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");

            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId(smsSendRequest.getOutId());

            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

            return sendSmsResponse;
        } catch (ClientException e) {
            throw new IllegalStateException("sms error",e);
        }
    }

    public static class SmsSendRequest{

        private String phoneNumbers;
        private SmsSendType smsSendType;
        private Map<String,String> paramMap;
        private String outId;

        public static SmsSendRequest build(String phoneNumbers,SmsSendType smsSendType,Map<String,String> paramMap){
            SmsSendRequest smsSendRequest = new SmsSendRequest();
            smsSendRequest.setPhoneNumbers(phoneNumbers);
            smsSendRequest.setSmsSendType(smsSendType);
            smsSendRequest.setParamMap(paramMap);
            return smsSendRequest;
        }

//        public static SmsSendRequest build(String phoneNumbers,SmsSendType smsSendType,String... params){
//            SmsSendRequest smsSendRequest = new SmsSendRequest();
//            smsSendRequest.setPhoneNumbers(phoneNumbers);
//            smsSendRequest.setSmsSendType(smsSendType);
//            smsSendRequest.setParamMap(paramMap);
//            return smsSendRequest;
//        }

        public String getPhoneNumbers() {
            return phoneNumbers;
        }

        public void setPhoneNumbers(String phoneNumbers) {
            this.phoneNumbers = phoneNumbers;
        }

        public SmsSendType getSmsSendType() {
            return smsSendType;
        }

        public void setSmsSendType(SmsSendType smsSendType) {
            this.smsSendType = smsSendType;
        }

        public Map<String, String> getParamMap() {
            return paramMap;
        }

        public void setParamMap(Map<String, String> paramMap) {
            this.paramMap = paramMap;
        }

        public String getOutId() {
            return outId;
        }

        public void setOutId(String outId) {
            this.outId = outId;
        }
    }

    public static enum SmsSendType{
        Login
    }

    public static void main(String[] args) throws ClientException {
        Map<String,String> map = new HashMap<>();
        map.put("code","123456");
        SmsSendRequest smsSendRequest = SmsSendRequest.build("13482177122",SmsSendType.Login,map);
        SmsUtils.sendSms(smsSendRequest);
    }
}
