import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;
import javax.jms.Destination;
import javax.jms.DeliveryMode;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQConnection;

public class WorkGenerator implements Runnable {
	private ActiveMQConnectionFactory cf = null;
	private String queueName = null;

	WorkGenerator (ActiveMQConnectionFactory cf, String queueName) {
		this.cf = cf;
		this.queueName = queueName;
	}

	public void run() {
		System.out.println("WorkGenerator running..");
		try {
			Connection connection = cf.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue(queueName);
			MessageProducer producer = session.createProducer(destination);

			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			sendMessage(session, producer);
		} catch (Exception e) {}
	}

	public void sendMessage(Session session, MessageProducer producer) throws Exception {
		String text = "howdyhowdy";
		TextMessage message = session.createTextMessage(text);
		System.out.println("* Sending message with text '" + text + "'");
		producer.send(message);
		System.out.println(">> " + text);
	}
}
