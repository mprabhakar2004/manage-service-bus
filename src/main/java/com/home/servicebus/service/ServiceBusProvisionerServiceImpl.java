package com.home.servicebus.service;

import com.home.servicebus.model.*;
import com.microsoft.azure.servicebus.management.*;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class ServiceBusProvisionerServiceImpl implements ServiceBusProvisionerService {
    ConnectionStringBuilder connectionStringBuilder;
    ManagementClientAsync managementClientAsync;

    public ServiceBusProvisionerServiceImpl(@Value("${sb.connectionString}") String sbConnectionString) {
        connectionStringBuilder = new ConnectionStringBuilder(sbConnectionString);
        managementClientAsync = new ManagementClientAsync(connectionStringBuilder);

    }


    @Override
    public TopicInfo createTopic(String topicName, TopicMetaData topicMetaData) {
        try {
            if (!managementClientAsync.topicExistsAsync(topicName).get()) {
                TopicDescription td = new TopicDescription(topicName);
                if (topicMetaData != null) {
                    td.setAutoDeleteOnIdle(topicMetaData.getAutoDeleteOnIdle());
                    td.setDefaultMessageTimeToLive(topicMetaData.getDefaultMessageTimeToLive());
                    td.setDuplicationDetectionHistoryTimeWindow(topicMetaData.getDuplicationDetectionHistoryTimeWindow());
                    td.setMaxSizeInMB(topicMetaData.getMaxSizeInMB());
                    td.setRequiresDuplicateDetection(topicMetaData.isRequiresDuplicateDetection());
                    if (topicMetaData.getAccessPolicyList() != null) {
                        ArrayList<AuthorizationRule> rules = new ArrayList<>();
                        for (AccessPolicy accessPolicy : topicMetaData.getAccessPolicyList()) {

                            rules.add(new SharedAccessAuthorizationRule(accessPolicy.getName(), accessPolicy.getAccessRights()));
                        }
                        td.setAuthorizationRules(rules);
                    }
                }
                TopicDescription tCreated = this.managementClientAsync.createTopicAsync(td).get();

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return getTopicDetails(topicName);
    }

    @Override
    public TopicInfo getTopicDetails(String topicName) {
        TopicInfo topicInfo = new TopicInfo();
        try {
            TopicDescription topic = managementClientAsync.getTopicAsync(topicName).get();
            topicInfo.setTopicName(topicName);
            TopicMetaData topicMetaData = new TopicMetaData();
            topicMetaData.setAutoDeleteOnIdle(topic.getAutoDeleteOnIdle());
            topicMetaData.setStatus(topic.getEntityStatus());
            topicMetaData.setDefaultMessageTimeToLive(topic.getDefaultMessageTimeToLive());
            topicMetaData.setMaxSizeInMB(topic.getMaxSizeInMB());
            topicMetaData.setDuplicationDetectionHistoryTimeWindow(topic.getDuplicationDetectionHistoryTimeWindow());
            topicMetaData.setRequiresDuplicateDetection(topic.isRequiresDuplicateDetection());
            topicInfo.setTopicMetaData(topicMetaData);
            topicInfo.getAccessPolicyList().addAll(getAccessPolicy(topic.getAuthorizationRules()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        return topicInfo;
    }

    @Override
    public AccessPolicy createTopicSasAccessRule(String topicName, AccessPolicy accessPolicy) {
        TopicDescription topic = null;
        try {
            topic = managementClientAsync.getTopicAsync(topicName).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        if (topic != null) {
            SharedAccessAuthorizationRule rule = new SharedAccessAuthorizationRule(accessPolicy.getName(), accessPolicy.getAccessRights());
            List<AuthorizationRule> authorizationRules = topic.getAuthorizationRules();
            if (authorizationRules == null) {
                authorizationRules = new ArrayList<>();
            }
            authorizationRules.add(rule);
            topic.setAuthorizationRules(authorizationRules);
            try {
                topic = managementClientAsync.updateTopicAsync(topic).get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return accessPolicy;
    }

    @Override
    public SasPolicyInfo getTopicSasPolicyInfo(String topicName, String policyName) {
        TopicDescription topic = null;
        SasPolicyInfo sasPolicyInfo = null;
        try {
            topic = managementClientAsync.getTopicAsync(topicName).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return getSasPolicyInfo(policyName, topic.getAuthorizationRules());
    }

    @Override
    public QueueInfo createQueue(String queueName, QueueMetaData queueMetaData) {
        try {
            if (!managementClientAsync.queueExistsAsync(queueName).get()) {
                QueueDescription q = new QueueDescription(queueName);
                if (queueMetaData != null) {
                    q.setAutoDeleteOnIdle(queueMetaData.getAutoDeleteOnIdle());
                    q.setDefaultMessageTimeToLive(queueMetaData.getDefaultMessageTimeToLive());
                    q.setDuplicationDetectionHistoryTimeWindow(queueMetaData.getDuplicationDetectionHistoryTimeWindow());
                    q.setEnableBatchedOperations(queueMetaData.isEnableBatchedOperations());
                    q.setEnableDeadLetteringOnMessageExpiration(queueMetaData.isEnableDeadLetteringOnMessageExpiration());
                    q.setEnablePartitioning(queueMetaData.isEnablePartitioning());
                    q.setForwardTo(queueMetaData.getForwardTo());
                    q.setForwardDeadLetteredMessagesTo(queueMetaData.getForwardDeadLetteredMessagesTo());
                    q.setLockDuration(queueMetaData.getLockDuration());
                    q.setMaxDeliveryCount(queueMetaData.getMaxDeliveryCount());
                    q.setMaxSizeInMB(queueMetaData.getMaxSizeInMB());
                    q.setRequiresDuplicateDetection(queueMetaData.isRequiresDuplicateDetection());
                    q.setRequiresSession(queueMetaData.isRequiresSession());
                    q.setUserMetadata(queueMetaData.getUserMetadata());
                    if (queueMetaData.getAccessPolicyList() != null) {
                        ArrayList<AuthorizationRule> rules = new ArrayList<>();
                        for (AccessPolicy accessPolicy : queueMetaData.getAccessPolicyList()) {

                            rules.add(new SharedAccessAuthorizationRule(accessPolicy.getName(), accessPolicy.getAccessRights()));
                        }
                        q.setAuthorizationRules(rules);
                    }
                }

                QueueDescription qCreated = this.managementClientAsync.createQueueAsync(q).get();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return getQueueDetails(queueName);
    }

    @Override
    public QueueInfo getQueueDetails(String queueName) {
        QueueInfo queueInfo = new QueueInfo();
        try {
            QueueDescription queue = managementClientAsync.getQueueAsync(queueName).get();
            queueInfo.setQueueName(queueName);
            QueueMetaData queueMetaData = new QueueMetaData();
            queueMetaData.setAutoDeleteOnIdle(queue.getAutoDeleteOnIdle());
            queueMetaData.setDuplicationDetectionHistoryTimeWindow(queue.getDuplicationDetectionHistoryTimeWindow());
            queueMetaData.setForwardTo(queue.getForwardTo());
            queueMetaData.setLockDuration(queue.getLockDuration());
            queueMetaData.setDefaultMessageTimeToLive(queue.getDefaultMessageTimeToLive());
            queueMetaData.setStatus(queue.getEntityStatus());
            queueMetaData.setForwardDeadLetteredMessagesTo(queue.getForwardDeadLetteredMessagesTo());
            queueMetaData.setMaxDeliveryCount(queue.getMaxDeliveryCount());
            queueMetaData.setMaxSizeInMB(queue.getMaxSizeInMB());
            queueMetaData.setUserMetadata(queue.getUserMetadata());
            queueMetaData.setEnableBatchedOperations(queue.isEnableBatchedOperations());
            queueMetaData.setEnablePartitioning(queue.isEnablePartitioning());
            queueMetaData.setEnableDeadLetteringOnMessageExpiration(queue.isEnableDeadLetteringOnMessageExpiration());
            queueMetaData.setRequiresSession(queue.isRequiresSession());
            queueMetaData.setRequiresDuplicateDetection(queue.isRequiresDuplicateDetection());
            queueInfo.setQueueMetaData(queueMetaData);
            queueInfo.getAccessPolicyList().addAll(getAccessPolicy(queue.getAuthorizationRules()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        return queueInfo;
    }

    @Override
    public AccessPolicy createQueueSasAccessRule(String queueName, AccessPolicy accessPolicy) {
        QueueDescription queue = null;
        try {
            queue = managementClientAsync.getQueueAsync(queueName).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        if (queue != null) {
            SharedAccessAuthorizationRule rule = new SharedAccessAuthorizationRule(accessPolicy.getName(), accessPolicy.getAccessRights());
            List<AuthorizationRule> authorizationRules = queue.getAuthorizationRules();
            if (authorizationRules == null) {
                authorizationRules = new ArrayList<>();
            }
            authorizationRules.add(rule);
            queue.setAuthorizationRules(authorizationRules);
            try {
                queue = managementClientAsync.updateQueueAsync(queue).get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return accessPolicy;
    }

    @Override
    public SasPolicyInfo getQueueSasPolicyInfo(String queueName, String policyName) {
        QueueDescription queue = null;
        SasPolicyInfo sasPolicyInfo = null;
        try {
            queue = managementClientAsync.getQueueAsync(queueName).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return getSasPolicyInfo(policyName, queue.getAuthorizationRules());
    }


    private List<AccessPolicy> getAccessPolicy(List<AuthorizationRule> authorizationRules) {
        List<AccessPolicy> accessPolicies = new ArrayList<>();
        if (authorizationRules != null) {
//           accessPolicies = new ArrayList<>();
            for (AuthorizationRule accessRule : authorizationRules) {
                AccessPolicy accessPolicy = new AccessPolicy();
                accessPolicy.setName(accessRule.getKeyName());
                accessPolicy.setAccessRights(accessRule.getRights());
                accessPolicies.add(accessPolicy);
            }
        }
        return accessPolicies;
    }

    private SasPolicyInfo getSasPolicyInfo(String policyName, List<AuthorizationRule> authorizationRules) {
        SasPolicyInfo sasPolicyInfo = null;
        if (authorizationRules != null) {
            Optional<AuthorizationRule> authorizationRule = authorizationRules.stream().filter(x -> x.getKeyName().equals(policyName)).findFirst();
            if (authorizationRule.isPresent()) {
                SharedAccessAuthorizationRule sharedAccessAuthorizationRule = (SharedAccessAuthorizationRule) authorizationRule.get();
                sasPolicyInfo = new SasPolicyInfo();
                sasPolicyInfo.setName(policyName);
                sasPolicyInfo.setPrimaryKey(sharedAccessAuthorizationRule.getPrimaryKey());
                sasPolicyInfo.setSecondaryKey(sharedAccessAuthorizationRule.getSecondaryKey());
                sasPolicyInfo.setAccessRights(sharedAccessAuthorizationRule.getRights());
            }
        }
        return sasPolicyInfo;
    }

    public void createSubscription(String topicName, String subscriptionName){
        //TODO Need to verify the topic availability

        SubscriptionDescription subscriptionDescription = new SubscriptionDescription(topicName, subscriptionName);
        //Hardcoded for now need to accept these thing from parameter
        subscriptionDescription.setAutoDeleteOnIdle(Duration.ofHours(1));
        subscriptionDescription.setDefaultMessageTimeToLive(Duration.ofDays(2));
        subscriptionDescription.setEnableBatchedOperations(false);
        subscriptionDescription.setEnableDeadLetteringOnMessageExpiration(true);
        subscriptionDescription.setEnableDeadLetteringOnFilterEvaluationException(false);
        subscriptionDescription.setForwardTo(null);
        subscriptionDescription.setForwardDeadLetteredMessagesTo(null);
        subscriptionDescription.setLockDuration(Duration.ofSeconds(45));
        subscriptionDescription.setMaxDeliveryCount(8);
        subscriptionDescription.setRequiresSession(true);
        subscriptionDescription.setUserMetadata("basicSubscriptionCrudTest");

        try {
            SubscriptionDescription createdS = this.managementClientAsync.createSubscriptionAsync(subscriptionDescription).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
