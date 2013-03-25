import javax.jms.Connection;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Queue {
	public static void main(String[] args) {
		try { 
			ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory("vm:localhost?broker.persistent=false");
			Connection connection = cf.createConnection();
			connection.start();
			System.out.println("Broker created");
		} catch (Exception e) { }
	}
}
