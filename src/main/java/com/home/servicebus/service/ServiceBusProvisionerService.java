package com.home.servicebus.service;

import com.home.servicebus.model.*;

import java.util.List;

public interface ServiceBusProvisionerService {

    TopicInfo createTopic(String topicName, TopicMetaData queueMetaData);

    TopicInfo getTopicDetails(String topicName);

    AccessPolicy createTopicSasAccessRule(String topicName, AccessPolicy accessPolicy);

    SasPolicyInfo getTopicSasPolicyInfo(String topicName, String policyName);

    public void createSubscription(String topicName, String subscriptionName);

    QueueInfo createQueue(String queueName, QueueMetaData queueMetaData);

    QueueInfo getQueueDetails(String queueName);

    AccessPolicy createQueueSasAccessRule(String queueName, AccessPolicy accessPolicy);

    SasPolicyInfo getQueueSasPolicyInfo(String queueName, String policyName);
}
