package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.MessageType;
import no.hvl.dat110.messages.PublishMsg;
import no.hvl.dat110.common.TODO;

public class DisplayDevice {
	
	private static final int COUNT = 10;
	private static final String USER = "display";
		
	public static void main (String[] args) {
		System.out.println("Display starting ...");

		Client client = new Client(USER, Common.BROKERHOST, Common.BROKERPORT);
		
		client.connect();
		client.createTopic(Common.TEMPTOPIC);
		client.subscribe(Common.TEMPTOPIC);
		
		for(int i = 0; i < COUNT; i++) {
			Message msg = client.receive();

			if(msg.getType() == MessageType.PUBLISH) {
				String temp = ((PublishMsg) msg).getMessage();
				
				System.out.println("DISPLAY: " + temp);
			}
		}
		
		client.unsubscribe(Common.TEMPTOPIC);
		client.disconnect();
		
		System.out.println("Display stopping ... ");
		
	}
}
