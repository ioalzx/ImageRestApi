package com.ioalzx.animeimagerestapi.util;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.tmt.v20180321.TmtClient;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateRequest;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Future;

@Component
@Slf4j
public class TagTranslation {
    private final String secretId = "AKID3wfEcp4ncR5ub8tIJDyZ9bsyuOkxbC5p";
    private final String secretKey = "kRzMZaR7HOMBpOm0kMmV4ThkjZEi5I1e";
    private TmtClient client;

    @PostConstruct
    public void init() {
        Credential cred = new Credential(this.secretId, this.secretKey);

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("tmt.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        this.client = new TmtClient(cred, "ap-guangzhou", clientProfile);
    }

    @Async("tagsTransThreadPool")
    public Future<String> getEn(String tag) {

        if (tag.matches("[a-zA-Z]+")) {
            return new AsyncResult<>(tag);
        }

        TextTranslateRequest req = new TextTranslateRequest();
        req.setSourceText(tag);
        req.setSource("auto");
        req.setTarget("en");
        req.setProjectId(0L);
        TextTranslateResponse resp;
        try {
            resp = this.client.TextTranslate(req);
        } catch (TencentCloudSDKException e) {
            //System.out.println(e.getErrorCode());
            log.error(e.toString());
            return new AsyncResult<>(tag);
        }

        return new AsyncResult<>(resp.getTargetText());

    }

}
