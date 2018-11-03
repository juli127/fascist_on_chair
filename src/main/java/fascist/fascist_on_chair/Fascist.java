package fascist.fascist_on_chair;

import java.util.concurrent.TimeUnit;

public class Fascist implements Runnable {

	private String name;
	private Chair chair;
	private ThreadMusic tMusic;

	public Fascist(String name, Chair chair, ThreadMusic tMusic) {
		this.name = name;
		this.chair = chair;
		this.tMusic = tMusic;
	}

	public void run() {
		while (true) {
			while (!tMusic.isPlaying()) {
				synchronized (chair) {
					chair.occupy();
					System.out.println("	Fascist " + name + ": is sitting on the chair for 3 sec....!!!!!");
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("	Fascist " + name + ": leaves chair ");
					chair.free();
					tMusic.setIsPlaying(true);
					tMusic.run();
					System.out.println("	Fascist " + name + ": exit game");
					return;
				}
			}
		}
	}
}
