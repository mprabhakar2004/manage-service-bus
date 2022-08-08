package com.home.servicebus.model;

import java.util.ArrayList;
import java.util.List;

public class TopicInfo {
    private String topicName;

    private TopicMetaData topicMetaData;

    public TopicMetaData getTopicMetaData() {
        return topicMetaData;
    }

    public void setTopicMetaData(TopicMetaData topicMetaData) {
        this.topicMetaData = topicMetaData;
    }

    private List<AccessPolicy> accessPolicyList;

    public List<AccessPolicy> getAccessPolicyList() {
        if (accessPolicyList == null) {
            accessPolicyList = new ArrayList<>();
        }
        return accessPolicyList;
    }

    public void setAccessPolicyList(List<AccessPolicy> accessPolicyList) {
        this.accessPolicyList = accessPolicyList;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}