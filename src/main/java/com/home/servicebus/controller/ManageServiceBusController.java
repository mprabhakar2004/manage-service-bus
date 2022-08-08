package com.home.servicebus.controller;

import com.home.servicebus.model.*;
import com.home.servicebus.service.ServiceBusProvisionerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provision")
public class ManageServiceBusController {

    private final ServiceBusProvisionerService serviceBusProvisionerService;

    public ManageServiceBusController(ServiceBusProvisionerService serviceBusProvisionerService) {
        this.serviceBusProvisionerService = serviceBusProvisionerService;
    }

    @GetMapping("/topic/{topicName}")
    TopicInfo getTopic(@PathVariable String topicName) {
        return serviceBusProvisionerService.getTopicDetails(topicName);
    }

    @PostMapping("/topic/{topicName}")
    TopicInfo createTopic(@PathVariable String topicName, @RequestBody TopicMetaData topicMetaData) {
        return serviceBusProvisionerService.createTopic(topicName, topicMetaData);
    }

    @GetMapping("/topic/{topicName}/sasPolicy/{policyName}")
    SasPolicyInfo getTopicSasPolicy(@PathVariable String topicName, @PathVariable String policyName) {
        return serviceBusProvisionerService.getTopicSasPolicyInfo(topicName, policyName);
    }

    @PostMapping("/topic/{topicName}/sasPolicy")
    AccessPolicy createTopicPolicy(@PathVariable String topicName, @RequestBody AccessPolicy accessPolicy) {
        return serviceBusProvisionerService.createTopicSasAccessRule(topicName, accessPolicy);
    }

    @PostMapping("/topic/{topicName}/subscription/{subscriptionName}")
    public void createSubscriptionForTopic(@PathVariable String topicName, @PathVariable String subscriptionName) {
        //TODO need to accept other properties via payload
        serviceBusProvisionerService.createSubscription(topicName, subscriptionName);
    }

    @GetMapping("/queue/{queueName}")
    QueueInfo getQueue(@PathVariable String queueName) {
        return serviceBusProvisionerService.getQueueDetails(queueName);
    }

    @PostMapping("/queue/{queueName}")
    QueueInfo createQueue(@PathVariable String queueName, @RequestBody QueueMetaData queueMetaData) {
        return serviceBusProvisionerService.createQueue(queueName, queueMetaData);
    }

    @GetMapping("/queue/{queueName}/sasPolicy/{policyName}")
    SasPolicyInfo getQueueSasPolicy(@PathVariable String queueName, @PathVariable String policyName) {
        return serviceBusProvisionerService.getQueueSasPolicyInfo(queueName, policyName);
    }


    @PostMapping("/queue/{queueName}/sasPolicy")
    AccessPolicy createQueuePolicy(@PathVariable String queueName, @RequestBody AccessPolicy accessPolicy) {
        return serviceBusProvisionerService.createQueueSasAccessRule(queueName, accessPolicy);
    }

}
