package com.ioalzx.animeimagerestapi;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.tmt.v20180321.TmtClient;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateRequest;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateResponse;

;

public class TencentSDKTests {

    public static void main(String [] args) {
        try{

            Credential cred = new Credential("AKID3wfEcp4ncR5ub8tIJDyZ9bsyuOkxbC5p", "kRzMZaR7HOMBpOm0kMmV4ThkjZEi5I1e");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("tmt.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            TmtClient client = new TmtClient(cred, "ap-guangzhou", clientProfile);

            TextTranslateRequest req = new TextTranslateRequest();
            req.setSourceText("'贞德' '天气' 手机");
            req.setSource("auto");
            req.setTarget("en");
            req.setProjectId(0L);

            long startTime=System.currentTimeMillis();

            TextTranslateResponse resp = client.TextTranslate(req);

            long endTime=System.currentTimeMillis();
            System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
            System.out.println(TextTranslateResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

}


