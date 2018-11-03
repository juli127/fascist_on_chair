package fascist.fascist_on_chair;

public class ThreadMusic extends Thread {

	private volatile boolean musicIsPlaying = true;

	public void setIsPlaying(boolean musicIsPlaying) {
		this.musicIsPlaying = musicIsPlaying;
	}

	public boolean isPlaying() {
		return musicIsPlaying;
	}

	@Override
	public void run() {
		while (musicIsPlaying) {
			synchronized (this) {
				try {
					System.out.println("Music is playing....waiting for stop music and start game.....");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Music is stopped, can start game ====================================");
	}

	public synchronized void stopPlaying() {
		musicIsPlaying = false;
		notify();
	}
}
