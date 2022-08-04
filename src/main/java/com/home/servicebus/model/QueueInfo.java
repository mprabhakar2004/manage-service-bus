package com.home.servicebus.model;

public class QueueInfo {
    private String queueName;
    private QueueMetaData queueMetaData;

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
