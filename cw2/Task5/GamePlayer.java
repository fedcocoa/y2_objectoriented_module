import java.io.*;

public class GamePlayer {

  static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		System.out.print("Card (c) or Die (d) game? ");
    String ans=br.readLine();

    if (ans.equals("c")) {
      CardGame cardGame = new CardGame();
      cardGame.playGame();
    }

    else if (ans.equals("d")) {
      DieGame dieGame = new DieGame();
      dieGame.playGame();
    }

    else System.out.println("Input not understood");
	}
}
