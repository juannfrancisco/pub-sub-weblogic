package cl.zenta.ar.poc.services;

import java.util.ArrayList;
import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;

public class PubSubServices {

	private static final String PROJECT_ID = "basket-analytics-232302";

	public void sendMessage() throws Exception {
		//
		String topicId = "messages";
		int messageCount = 1;
		ProjectTopicName topicName = ProjectTopicName.of(PROJECT_ID, topicId);
		Publisher publisher = null;
		List<ApiFuture<String>> futures = new ArrayList<ApiFuture<String>>();

		try {
			// Create a publisher instance with default settings bound to the topic
			publisher = Publisher.newBuilder(topicName).build();

			for (int i = 0; i < messageCount; i++) {
				String message = "message-" + i;

				// convert message to bytes
				ByteString data = ByteString.copyFromUtf8(message);
				PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

				// Schedule a message to be published. Messages are automatically batched.
				ApiFuture<String> future = publisher.publish(pubsubMessage);
				futures.add(future);
			}
		} finally {
			// Wait on any pending requests
			List<String> messageIds = ApiFutures.allAsList(futures).get();

			for (String messageId : messageIds) {
				System.out.println(messageId);
			}

			if (publisher != null) {
				// When finished with the publisher, shutdown to free up resources.
				publisher.shutdown();
			}
		}
	}

}
