import javax.jms.Connection;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Demo {
	public static void main(String[] args) {
		ActiveMQConnectionFactory cf = null;
		try { 
			cf = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
		} catch (Exception e) {
			System.out.println("There was some sort of error..");
			System.exit(0);
		}
		System.out.println("\nCreating work...\n");
		WorkGenerator wg = new WorkGenerator(cf, "DEMO");
		Thread t = new Thread(wg);
		t.start();
		Worker w = new Worker(cf, "DEMO");
		Thread t2 = new Thread(w);
		t2.start();
	}
}
