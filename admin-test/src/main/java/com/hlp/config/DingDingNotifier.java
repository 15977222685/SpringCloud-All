package com.hlp.config;

import com.alibaba.fastjson.JSONObject;
import com.hlp.util.DingDingMessageUtil;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author 黄林潘
 * QQ 1677873521
 * @date 2022/1/17 18:45
 */
public class DingDingNotifier extends AbstractStatusChangeNotifier  {
    public DingDingNotifier(InstanceRepository repository) {
        super(repository);
    }
    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        String serviceName = instance.getRegistration().getName();
        String serviceUrl = instance.getRegistration().getServiceUrl();
        String status = instance.getStatusInfo().getStatus();
        Map<String, Object> details = instance.getStatusInfo().getDetails();
        StringBuilder str = new StringBuilder();
        str.append("系统警告 : 【" + serviceName + "】");
        str.append("【服务地址】" + serviceUrl);
        str.append("【状态】" + status);
        str.append("【详情】" + JSONObject.toJSONString(details));
        return Mono.fromRunnable(() -> {
            DingDingMessageUtil.sendTextMessage(str.toString());
        });
    }
}
