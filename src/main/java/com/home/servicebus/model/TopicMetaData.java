package com.home.servicebus.model;

import com.microsoft.azure.servicebus.management.EntityStatus;

import java.time.Duration;
import java.util.List;

public class TopicMetaData {

    Duration duplicationDetectionHistoryTimeWindow;
    Duration defaultMessageTimeToLive;
    Duration autoDeleteOnIdle;
    long maxSizeInMB = 1024;
    long maxMessageSizeInKilobytes= 10240;

    public long getMaxMessageSizeInKilobytes() {
        return maxMessageSizeInKilobytes;
    }

    public void setMaxMessageSizeInKilobytes(long maxMessageSizeInKilobytes) {
        this.maxMessageSizeInKilobytes = maxMessageSizeInKilobytes;
    }

    boolean requiresDuplicateDetection = false;
    boolean enableBatchedOperations = true;
    boolean enablePartitioning = false;
    EntityStatus status = EntityStatus.Active;

    List<AccessPolicy> accessPolicyList = null;

    public List<AccessPolicy> getAccessPolicyList() {
        return accessPolicyList;
    }

    public void setAccessPolicyList(List<AccessPolicy> accessPolicyList) {
        this.accessPolicyList = accessPolicyList;
    }

    public Duration getDuplicationDetectionHistoryTimeWindow() {
        return duplicationDetectionHistoryTimeWindow;
    }

    public void setDuplicationDetectionHistoryTimeWindow(Duration duplicationDetectionHistoryTimeWindow) {
        this.duplicationDetectionHistoryTimeWindow = duplicationDetectionHistoryTimeWindow;
    }

    public Duration getDefaultMessageTimeToLive() {
        return defaultMessageTimeToLive;
    }

    public void setDefaultMessageTimeToLive(Duration defaultMessageTimeToLive) {
        this.defaultMessageTimeToLive = defaultMessageTimeToLive;
    }

    public Duration getAutoDeleteOnIdle() {
        return autoDeleteOnIdle;
    }

    public void setAutoDeleteOnIdle(Duration autoDeleteOnIdle) {
        this.autoDeleteOnIdle = autoDeleteOnIdle;
    }

    public long getMaxSizeInMB() {
        return maxSizeInMB;
    }

    public void setMaxSizeInMB(long maxSizeInMB) {
        this.maxSizeInMB = maxSizeInMB;
    }

    public boolean isRequiresDuplicateDetection() {
        return requiresDuplicateDetection;
    }

    public void setRequiresDuplicateDetection(boolean requiresDuplicateDetection) {
        this.requiresDuplicateDetection = requiresDuplicateDetection;
    }

    public boolean isEnableBatchedOperations() {
        return enableBatchedOperations;
    }

    public void setEnableBatchedOperations(boolean enableBatchedOperations) {
        this.enableBatchedOperations = enableBatchedOperations;
    }

    public boolean isEnablePartitioning() {
        return enablePartitioning;
    }

    public void setEnablePartitioning(boolean enablePartitioning) {
        this.enablePartitioning = enablePartitioning;
    }

    public EntityStatus getStatus() {
        return status;
    }

    public void setStatus(EntityStatus status) {
        this.status = status;
    }


}
