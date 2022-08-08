package com.home.servicebus.model;

import java.util.ArrayList;
import java.util.List;

public class QueueInfo {
    private String queueName;
    private QueueMetaData queueMetaData;

    private List<AccessPolicy> accessPolicyList;

    public List<AccessPolicy> getAccessPolicyList() {
        if (accessPolicyList == null){
            accessPolicyList = new ArrayList<>();
        }
        return accessPolicyList;
    }

    public void setAccessPolicyList(List<AccessPolicy> accessPolicyList) {
        this.accessPolicyList = accessPolicyList;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public QueueMetaData getQueueMetaData() {
        return queueMetaData;
    }

    public void setQueueMetaData(QueueMetaData queueMetaData) {
        this.queueMetaData = queueMetaData;
    }
}
