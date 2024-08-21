spring.cloud.stream.kafka.bindings.<binding-name>.consumer.configuration.max.poll.interval.ms
spring.cloud.stream.kafka.bindings.<binding-name>.consumer.configuration.max.poll.records

to set a default value for all bindings

spring.cloud.stream.kafka.bindings.default.consumer.configuration.max.poll.interval.ms
spring.cloud.stream.kafka.bindings.default.consumer.configuration.max.poll.records


*********************************************************************************************************************
KAFKA CONNECT

https://kafka.apache.org/documentation/#connectconfigs
https://rmoff.net/2019/08/15/reset-kafka-connect-source-connector-offsets/
https://rmoff.net/2020/01/16/changing-the-logging-level-for-kafka-connect-dynamically/
*********************************************************************************************************************
https://blog.tarkalabs.com/handling-message-duplication-in-kafka-8f4b9b312f91
https://medium.com/stream-zero/understanding-message-delivery-in-kafka-with-multiple-partitions-f9df4a32d498
https://blog.devops.dev/building-a-real-time-data-pipeline-with-kafka-snowflake-dbt-airflow-and-tableau-aebb03c6157c



https://www.baeldung.com/spring-kafka

https://github.com/howtoprogram/apache-kafka-examples/blob/master/spring-kafka-example/src/test/java/com/howtoprogram/kafka/SpringKafkaExampleApplicationTests.java

https://stackoverflow.com/questions/45084688/spring-kafka-asynchronous-send-calls-block

https://github.com/spring-cloud/spring-cloud-stream/issues/795

https://www.programcreek.com/java-api-examples/index.php?api=org.springframework.kafka.support.SendResult

http://www.baeldung.com/spring-async
 org.springframework.messaging.converter.AbstractMessageConverter

We have observed kafka consumer group related issues in production.
                And below is our observation for those issues.
 
                Issue 1:
                                Account has observed lag for a particular topic.
                
	                Cause : We found out the topic has a no consumer group. So every new instance is creating an anonymous group 
	                                And old anonymous groups are idle and we have observed a lag.
	                                Even though all msg has been consumed with active anonymous groups but old anonymous groups are not deleted.
	                                After adding a group name we have only one group with zero lag.
	 
	                Soln : Most of the subscribers are multi instances in prod( we have atleast 3 minimum pods ), so adding group name is the best practise wherever necessary.
 
               Issue 2: 
                                After upgrading prod env from 22-07 to 22-12. we have observed processing of old data again for some topic.
 
                                cause: We have observed a change in consumer group name for that topic.
                                                Whenever a new group name is added, by default it will start consuming from earliest.
                                                So, new consumer group start processing old messages also.
 
                                                To avoid that, we can set it to consume from latest for the new consumer group.
 
                                                Config:
                                                                spring.cloud.stream.kafka.bindings.<channelName>.consumer.startOffset = latest
                                                
                                                                startOffset
						The starting offset for new groups. Allowed values: earliest and latest. If the consumer group is set explicitly for the consumer 'binding' (through spring.cloud.stream.bindings.<channelName>.group), 'startOffset' is set to earliest. Otherwise, it is set to latest for the anonymous consumer group. Also see resetOffsets (earlier in this list).
						Default: null (equivalent to earliest).
                                                Soln : Please cross check while changing consumer groups if old group already in prod

We found one probable cause for this issue.
 
		We have added fix  ( adding consumer group "consumerGroup" to topic “myTopic” ).
		Previously it was anonymous group.
		
		So, after latest deployment it might reprocess all existing events in “myTopic” again
		and that caused to update billing cycles status to “Completed”.
 
                Spring doc ref:
                
                startOffset
		The starting offset for new groups. Allowed values: earliest and latest. If the consumer group is set explicitly for the consumer 'binding' (through spring.cloud.stream.bindings.<channelName>.group), 'startOffset' is set to earliest. Otherwise, it is set to latest for the anonymous consumer group. Also see resetOffsets (earlier in this list).
		Default: null (equivalent to earliest).
                https://cloud.spring.io/spring-cloud-static/spring-cloud-stream-binder-kafka/2.2.0.M1/spring-cloud-stream-binder-kafka.html
 
                Please set this startOffset, if we are changing any group name or creating a new group for topics which are already in prod if required.





Kafka Rebalance Issue **************************************************************************************************************************************


2021-10-12 10:41:21 
2021-10-12 05:11:21.160   INFO 6 CID:  UID:  RID: --- [cation_consumer] s.consumer.internals.AbstractCoordinator : [Consumer clientId=consumer-staging_notification_consumer-10, groupId=staging_notification_consumer] Member consumer-staging_notification_consumer-10-8a7eb399-c4e0-4443-b330-bfac42ca89ae sending LeaveGroup request to coordinator kafka5:9092 (id: 2147483642 rack: null) due to consumer poll timeout has expired. This means the time between subsequent calls to poll() was longer than the configured max.poll.interval.ms, which typically implies that the poll loop is spending too much time processing messages. You can address this either by increasing max.poll.interval.ms or by reducing the maximum size of batches returned in poll() with max.poll.records.
2021-10-12 05:10:27.340   INFO 6 CID:4967  UID:  RID:17c72e2d517-4d5e --- [ntainer#9-0-C-1] s.consumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-staging_notification_consumer-9, groupId=staging_notification_consumer] Giving away all assigned partitions as lost since generation has been reset,indicating that consumer is no longer part of the group
2021-10-12 10:40:27 
2021-10-12 05:10:27.340   INFO 6 CID:4967  UID:  RID:17c72e2d517-4d5e --- [ntainer#9-0-C-1] s.consumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-staging_notification_consumer-9, groupId=staging_notification_consumer] Lost previously assigned partitions staging_notification_topic_app_low_mobi_3119-4, staging_notification_topic_app_low_mobi_2023-4, staging_notification_topic_app_low_mobi_5170-9, staging_notification_topic_app_low_mobi_3540-9, staging_notification_topic_app_low_mobi_5722-9,
2021-10-12 10:40:37 
2021-10-12 05:10:37.247   INFO 6 CID:  UID:  RID: --- [cation_consumer] s.consumer.internals.AbstractCoordinator : [Consumer clientId=consumer-staging_notification_consumer-12, groupId=staging_notification_consumer] Attempt to heartbeat failed since group is rebalancing
2021-10-12 10:40:37 
2021-10-12 05:11:56.922   INFO 6 CID:4967  UID:  RID:17c72e2579c-22e6 --- [ntainer#9-3-C-1] s.consumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-staging_notification_consumer-12, groupId=staging_notification_consumer] Failing OffsetCommit request since the consumer is not part of an active group
2021-10-12 10:41:56 
2021-10-12 05:11:56.923  ERROR 6 CID:4967  UID:  RID:17c72e2579c-22e6 --- [ntainer#9-3-C-1] essageListenerContainer$ListenerConsumer : Consumer exception
2021-10-12 10:41:56 
java.lang.IllegalStateException: This error handler cannot process 'org.apache.kafka.clients.consumer.CommitFailedException's; no record information is available
2021-10-12 10:41:56 
    at org.springframework.kafka.listener.SeekUtils.seekOrRecover(SeekUtils.java:151)
2021-10-12 10:41:56 
    at org.springframework.kafka.listener.SeekToCurrentErrorHandler.handle(SeekToCurrentErrorHandler.java:113)
2021-10-12 10:41:56 
    at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.handleConsumerException(KafkaMessageListenerContainer.java:1427)
2021-10-12 10:41:56 
    at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:1124)
2021-10-12 10:41:56 
    at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
2021-10-12 10:41:56 
    at java.util.concurrent.FutureTask.run(FutureTask.java:266)
2021-10-12 10:41:56 
    at java.lang.Thread.run(Thread.java:748)
2021-10-12 10:41:56 
Caused by: org.apache.kafka.clients.consumer.CommitFailedException: Offset commit cannot be completed since the consumer is not part of an active group for auto partition assignment; it is likely that the consumer was kicked out of the group.
2021-10-12 10:41:56 
    at org.apache.kafka.clients.consumer.internals.ConsumerCoordinator.sendOffsetCommitRequest(ConsumerCoordinator.java:1134)
2021-10-12 10:41:56 
    at org.apache.kafka.clients.consumer.internals.ConsumerCoordinator.commitOffsetsSync(ConsumerCoordinator.java:999)
2021-10-12 10:41:56 
    at org.apache.kafka.clients.consumer.KafkaConsumer.commitSync(KafkaConsumer.java:1504)
2021-10-12 10:41:56 
    at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doCommitSync(KafkaMessageListenerContainer.java:2396)
2021-10-12 10:41:56 
    at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.commitSync(KafkaMessageListenerContainer.java:2391)
2021-10-12 10:41:56 
    at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.commitIfNecessary(KafkaMessageListenerContainer.java:2377)
2021-10-12 10:41:56 
    at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.processCommits(KafkaMessageListenerContainer.java:2191)
2021-10-12 10:41:56 
    at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.pollAndInvoke(KafkaMessageListenerContainer.java:1149)
2021-10-12 10:41:56 
    at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:1075)
2021-10-12 10:41:56 
    ... 3 common frames omitted
2021-10-12 10:41:58 
2021-10-12 05:11:58.442   INFO 6 CID:4967  UID:  RID:17c72e2579c-22e6 --- [ntainer#9-3-C-1] s.consumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-staging_notification_consumer-12, groupId=staging_notification_consumer] Giving away all assigned partitions as lost since generation has been reset,indicating that consumer is no longer part of the group
021-10-12 10:58:36  
2021-10-12 05:28:36.039   INFO 6 CID:4412  UID:  RID:17c72ebadab-64d7 --- [ntainer#3-1-C-1] s.consumer.internals.AbstractCoordinator : [Consumer clientId=consumer-staging_notification_consumer-17, groupId=staging_notification_consumer] Join group failed with org.apache.kafka.common.errors.RebalanceInProgressException: The group is rebalancing, so a rejoin is needed.
2021-10-12 10:58:36 
2021-10-12 05:28:36.044   INFO 6 CID:4412  UID:  RID:17c72ebadab-64d7 --- [ntainer#3-1-C-1] s.consumer.internals.AbstractCoordinator : [Consumer clientId=consumer-staging_notification_consumer-17, groupId=staging_notification_consumer] (Re-)joining group
021-10-12 10:58:36  
2021-10-12 05:28:36.039   INFO 6 CID:4412  UID:  RID:17c72ebadab-64d7 --- [ntainer#3-1-C-1] s.consumer.internals.AbstractCoordinator : [Consumer clientId=consumer-staging_notification_consumer-17, groupId=staging_notification_consumer] Join group failed with org.apache.kafka.common.errors.RebalanceInProgressException: The group is rebalancing, so a rejoin is needed.
2021-10-12 10:58:36 
2021-10-12 05:28:36.044   INFO 6 CID:4412  UID:  RID:17c72ebadab-64d7 --- [ntainer#3-1-C-1] s.consumer.internals.AbstractCoordinator : [Consumer clientId=consumer-staging_notification_consumer-17, groupId=staging_notification_consumer] (Re-)joining group
2021-10-12 11:05:36 
2021-10-12 05:35:36.955   INFO 6 CID:3435  UID:  RID:17c72f29913-886c --- [ntainer#7-3-C-1] s.consumer.internals.AbstractCoordinator : [Consumer clientId=consumer-staging_notification_consumer-5, groupId=staging_notification_consumer] Join group failed with org.apache.kafka.common.errors.DisconnectException


The first log indicates that the time between subsequent calls to poll() was longer than the configured max.poll.interval.ms (five minutes default).
When this happens, the consumer client will actively initiate a LeaveGroup request to the coordinator to trigger rebalance.
You can address this either by increasing max.poll.interval.ms or by reducing the maximum size of batches returned in poll() with max.poll.records.
Of course, a better way is to check the reason for the slow processing of the program and optimize it.


https://medium.com/bakdata/solving-my-weird-kafka-rebalancing-problems-c05e99535435
https://dzone.com/articles/kafka-streams-tips-on-how-to-decrease-rebalancing
https://redpanda.com/guides/kafka-performance/kafka-rebalancing
https://medium.com/@huynhquangthao/how-to-survive-the-kafka-rebalancing-protocol-6a6bbcf56784
https://medium.com/streamthoughts/apache-kafka-rebalance-protocol-or-the-magic-behind-your-streams-applications-e94baf68e4f2
https://medium.com/streamthoughts/apache-kafka-rebalance-protocol-or-the-magic-behind-your-streams-applications-e94baf68e4f2
https://medium.com/@cobch7/kafka-exception-handling-and-retry-mechanism-a911541321fe
https://medium.com/streamthoughts/understanding-kafka-partition-assignment-strategies-and-how-to-write-your-own-custom-assignor-ebeda1fc06f3
https://stackoverflow.com/questions/39730126/difference-between-session-timeout-ms-and-max-poll-interval-ms-for-kafka-0-10
https://dev.to/thriving-dev/reduce-rebalance-downtime-by-x450-for-stateless-kafka-streams-apps-simple-steps-4mi6
https://www.linkedin.com/pulse/kafka-consumer-group-rebalance-1-2-rob-golder/
https://www.linkedin.com/pulse/kafka-consumer-group-rebalance-2-rob-golder/
https://medium.com/bakdata/solving-my-weird-kafka-rebalancing-problems-c05e99535435

