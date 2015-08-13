
public class Main {

	public static void main(String[] args){
		GameInfo gameInfo = new GameInfo();
		View view = new View();
		new GameController(gameInfo, view).launch();
	}
}
