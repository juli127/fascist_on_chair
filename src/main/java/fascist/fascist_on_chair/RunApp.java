package fascist.fascist_on_chair;

import java.util.Scanner;

public class RunApp {
	public static void main(String[] args) throws InterruptedException {
		int countPlayers;

		// get input parameters
		System.out.println("Enter count of players:");
		Scanner sc = new Scanner(System.in);
		while (!sc.hasNextInt()) {
			System.out.println("Enter correct count of players:");
			sc = new Scanner(System.in);
		}
		countPlayers = sc.nextInt();

		Game game = new Game(countPlayers);
		game.startGame();
		
		sc.close();
	}
}
