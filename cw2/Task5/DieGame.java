
import java.util.*;

public class DieGame extends Game {

	HashSet<Integer> numbersRolled=new HashSet<Integer>();

	void initialiseGame() throws Exception{
	}

	void mainGame() throws Exception{
		for (int i=0; i<2; i++) {
			System.out.println("Hit <RETURN> to roll the die");
			br.readLine();
			int dieRoll=(int)(r.next() * 6) + 1;
	
			System.out.println("You rolled " + dieRoll);
			numbersRolled.add(dieRoll);
		  }
	
		  // Display the numbers rolled
		  System.out.println("Numbers rolled: " + numbersRolled);
	}

	void declareWinner() throws Exception {
		if (numbersRolled.contains(1)) {
			System.out.println("You won!");
		  }
		  else System.out.println("You lost!");
	}
}
