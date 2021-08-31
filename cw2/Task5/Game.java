import java.io.*;

public abstract class Game {

	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
  	RandomInterface r =new LinearCongruentialGenerator();

	abstract void initialiseGame() throws Exception;
	abstract void mainGame() throws Exception;
	abstract void declareWinner() throws Exception;

	public void playGame() throws Exception {
		initialiseGame();
		mainGame();
		declareWinner();
	}
}