import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.MessageProducer;
import javax.jms.MessageConsumer;
import javax.jms.Destination;
import javax.jms.Session;
import javax.jms.Connection;
import javax.jms.Connection;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Worker implements Runnable {
	private ActiveMQConnectionFactory cf = null;
	private String queueName = null;

	Worker (ActiveMQConnectionFactory cf, String queueName) {
		this.cf = cf;
		this.queueName = queueName;
	}

	public void run() {
		System.out.println("Worker running..");
		try {
			Connection connection = cf.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(queueName);
			MessageConsumer consumer = session.createConsumer(destination);
			System.out.println("Reading message");
			consumeMessage(session, consumer);
		} catch (Exception e) { }
	}

	public void consumeMessage(Session session, MessageConsumer consumer) throws Exception {
		TextMessage message = (TextMessage)consumer.receive();
		if( message != null )
			System.out.println("<< " + message.getText());
	}
}
