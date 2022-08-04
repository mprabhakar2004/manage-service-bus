package com.home.servicebus.controller;

import com.home.servicebus.model.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provision")
public class ManageServiceBusController {

    @GetMapping("/topic/{topicName}")
    TopicInfo getTopic(@PathVariable String topicName) {
        return new TopicInfo();
    }

    @PostMapping("/topic/{topicName}")
    void createTopic(@PathVariable String topicName, @RequestBody TopicMetaData topicMetaData) {

    }

    @GetMapping("/topic/{topicName}/sasPolicy/{policyName}")
    SasPolicyInfo getTopicSasPolicy(@PathVariable String topicName, @PathVariable String policyName) {
        return new SasPolicyInfo();
    }

    @PostMapping("/topic/{topicName}/sasPolicy/{policyName}")
    void createTopicPolicy(@PathVariable String topicName, @PathVariable String policyName, @RequestBody AccessRight accessRight) {

    }

    @GetMapping("/queue/{queueName}")
    QueueInfo getQueue(@PathVariable String queueName) {
        return new QueueInfo();
    }

    @PostMapping("/queue/{queueName}")
    void createQueue(@PathVariable String queueName, @RequestBody QueueMetaData queueMetaData) {

    }

    @GetMapping("/queue/{queueName}/sasPolicy/{policyName}")
    SasPolicyInfo getQueueSasPolicy(@PathVariable String queueName, @PathVariable String policyName) {
        return new SasPolicyInfo();
    }

    @PostMapping("/queue/{queueName}/sasPolicy/{policyName}")
    void createQueuePolicy(@PathVariable String queueName, @PathVariable String policyName, @RequestBody AccessRight accessRight) {

    }
}
