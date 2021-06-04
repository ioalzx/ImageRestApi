package com.ioalzx.animeimagerestapi.util;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.tmt.v20180321.TmtClient;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateRequest;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Future;

@Component
@Slf4j
public class TagTranslation {
    private TmtClient client;

    @Autowired
    private RedisOperator redis;

    @PostConstruct
    public void init() {
        String secretKey = "kRzMZaR7HOMBpOm0kMmV4ThkjZEi5I1e";
        String secretId = "AKID3wfEcp4ncR5ub8tIJDyZ9bsyuOkxbC5p";
        Credential cred = new Credential(secretId, secretKey);

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

        var tagCache = redis.get(tag);

        if (tagCache != null) {
            log.info(String.format("Tag Translation cache hit! ( %s, %s )", tag, tagCache));
            return new AsyncResult<>(tagCache);
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
            log.error(e.toString());
            return new AsyncResult<>(tag);
        }

        redis.set(tag, resp.getTargetText());
        return new AsyncResult<>(resp.getTargetText());

    }

}
