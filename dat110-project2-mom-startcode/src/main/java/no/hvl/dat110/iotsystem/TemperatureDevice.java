package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.TODO;

public class TemperatureDevice {

	private static final int COUNT = 10;
	private static final String USER = "sensor";

	public static void main(String[] args) {
		// simulated / virtual temperature sensor
		TemperatureSensor sn = new TemperatureSensor();
		Client client = new Client(USER, Common.BROKERHOST, Common.BROKERPORT);

		client.connect();

		for(int i = 0; i < COUNT; i++) {
			int temp = sn.read();

			System.out.println("READING: " + temp);
			
			client.publish(Common.TEMPTOPIC, "" + temp);
		}
		
		client.disconnect();

		System.out.println("Temperature device stopping ... ");
	}
}
