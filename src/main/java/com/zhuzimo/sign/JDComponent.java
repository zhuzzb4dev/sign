package com.zhuzimo.sign;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;

@Component
@Slf4j
@Getter
@Setter
public class JDComponent {

    @Resource
    private RestTemplate restTemplate;

    @Value("${jd.signUrl:https://api.m.jd.com/client.action}")
    private String jdSignUrl;

    @Value("${jd.ptKey:AAJkxJG-321}")
    private String ptKey;

    @Value("${jd.ptPin:123}")
    private String ptPin;

    @Resource
    private PtEntityDao ptEntityDao;

    @Scheduled(cron = "${task.sign.corn}")
    public void sign() {
        log.info("定时任务执行-开始");
        Iterable<PtEntity> all = ptEntityDao.findAll();
        all.forEach(entity -> singleSign(entity));
        log.info("定时任务执行-结束");
    }

    private void singleSign(PtEntity entity) {
        log.info("准备签到：" + entity.getName());
        HttpHeaders headers = new HttpHeaders();
        ArrayList<String> cookies = new ArrayList<>();
        cookies.add("pt_key=" + entity.getKey());
        cookies.add("pt_pin=" + entity.getPin());
        headers.put(HttpHeaders.COOKIE, cookies);
        headers.put("User-Agent", Arrays.asList("okhttp/3.12.1;jdmall;android;version/10.3.4;build/92451;"));

        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity(null, headers);

        ResponseEntity<String> result = restTemplate.exchange(jdSignUrl+"?functionId=signBeanAct&appid=ld&client=apple", HttpMethod.POST, request, String.class);
        log.info(result.getBody());
    }
}
