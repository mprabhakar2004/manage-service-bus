package com.home.servicebus.model;

import com.microsoft.azure.servicebus.management.EntityStatus;

import java.time.Duration;
import java.util.List;

public class QueueMetaData {

    Duration duplicationDetectionHistoryTimeWindow;
    Duration lockDuration;
    Duration defaultMessageTimeToLive;
    Duration autoDeleteOnIdle;
    int maxDeliveryCount;
    String forwardTo = null;
    String forwardDeadLetteredMessagesTo = null;
    String userMetadata = null;
    long maxSizeInMB = 1024;
    boolean requiresDuplicateDetection = false;
    boolean enableDeadLetteringOnMessageExpiration = false;
    boolean requiresSession = false;
    boolean enableBatchedOperations = true;
    boolean enablePartitioning = false;

    EntityStatus status = EntityStatus.Active;
    List<AccessPolicy> accessPolicyList = null;

    public Duration getDuplicationDetectionHistoryTimeWindow() {
        return duplicationDetectionHistoryTimeWindow;
    }

    public void setDuplicationDetectionHistoryTimeWindow(Duration duplicationDetectionHistoryTimeWindow) {
        this.duplicationDetectionHistoryTimeWindow = duplicationDetectionHistoryTimeWindow;
    }

    public Duration getLockDuration() {
        return lockDuration;
    }

    public void setLockDuration(Duration lockDuration) {
        this.lockDuration = lockDuration;
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

    public int getMaxDeliveryCount() {
        return maxDeliveryCount;
    }

    public void setMaxDeliveryCount(int maxDeliveryCount) {
        this.maxDeliveryCount = maxDeliveryCount;
    }

    public String getForwardTo() {
        return forwardTo;
    }

    public void setForwardTo(String forwardTo) {
        this.forwardTo = forwardTo;
    }

    public String getForwardDeadLetteredMessagesTo() {
        return forwardDeadLetteredMessagesTo;
    }

    public void setForwardDeadLetteredMessagesTo(String forwardDeadLetteredMessagesTo) {
        this.forwardDeadLetteredMessagesTo = forwardDeadLetteredMessagesTo;
    }

    public String getUserMetadata() {
        return userMetadata;
    }

    public void setUserMetadata(String userMetadata) {
        this.userMetadata = userMetadata;
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

    public boolean isEnableDeadLetteringOnMessageExpiration() {
        return enableDeadLetteringOnMessageExpiration;
    }

    public void setEnableDeadLetteringOnMessageExpiration(boolean enableDeadLetteringOnMessageExpiration) {
        this.enableDeadLetteringOnMessageExpiration = enableDeadLetteringOnMessageExpiration;
    }

    public boolean isRequiresSession() {
        return requiresSession;
    }

    public void setRequiresSession(boolean requiresSession) {
        this.requiresSession = requiresSession;
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

    public List<AccessPolicy> getAccessPolicyList() {
        return accessPolicyList;
    }

    public void setAccessPolicyList(List<AccessPolicy> accessPolicyList) {
        this.accessPolicyList = accessPolicyList;
    }
}
