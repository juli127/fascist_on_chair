package fascist.fascist_on_chair;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Game {

	private int countPlayers;
	private LinkedList<Thread> fascistThreads = new LinkedList<>();
	private ThreadMusic tMusic;

	public Game(int countPlayers) {
		this.countPlayers = countPlayers;
	}

	public void startGame() throws InterruptedException {

		Chair chair = new Chair();
		
		// create threads
		tMusic = new ThreadMusic();

		for (int i = 0; i < countPlayers; i++) {
			Thread t = new Thread(new Fascist(Integer.toString(i), chair, tMusic), "Fascist " + Integer.toString(i));
			fascistThreads.add(t);
		}
		
		// start all threads
		tMusic.start();
		for (Thread player : fascistThreads) {
			player.start();
		}

		// play game while there are any live players
		while (thereIsLivePlayer()) {
			if (tMusic.isPlaying()) {
				int randTimeToStopMusic = (int) (Math.random() * 1000);
				System.out.println("Music will be stopped in " + randTimeToStopMusic + " milliseconds...");
				TimeUnit.MILLISECONDS.sleep(randTimeToStopMusic);
				tMusic.stopPlaying();
			}
		}

		// be sure that all threads are finished
		for (Thread player : fascistThreads) {
			if (player.isAlive()) {
				System.out.println("be sure that thread "+ player.getName() + " is finishd: call player.join()");
				player.join();
			}
		}
		System.out.println("=====================================================================");
		System.out.println("Game is OVER!");
	}

	//////////////////////////////////////////////////////////////
	private boolean thereIsLivePlayer() {
		for (Thread player : fascistThreads) {
			if (player.isAlive()) {
				return true;
			}
		}
		return false;
	}
}
