package GameState;

public class GameStateManager {

	private GameState[] gameStates;
	private int currentState;
	private static int previousState;

	public static final int NUMGAMESTATES = 5;
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	public static final int LEVEL2STATE = 2;
	public static final int GAMEOVERSTATE = 3;
	public static final int LOADINGSTATE = 4;

	public GameStateManager() {

		gameStates = new GameState[NUMGAMESTATES];
		
		previousState = MENUSTATE;
		currentState = MENUSTATE;
		loadState(currentState);

	}

	private void loadState(int state) {
		if (state == MENUSTATE) {
			gameStates[state] = new MenuState(this);
		}
		if (state == LEVEL1STATE) {
			gameStates[state] = new Level1State(this);
		}
		
		if(state == LEVEL2STATE){
			gameStates[state] = new Level2State(this);
		}
		
		if (state == GAMEOVERSTATE){
			gameStates[state] = new GameOverState(this);
		}
		
		if (state == LOADINGSTATE){
			gameStates[state] = new LoadingState(this);
		}
	}

	private void unloadState(int state) {
		gameStates[state] = null;
	}

	public void setState(int state) {
		unloadState(currentState);
		previousState = currentState;
		currentState = state;
		loadState(currentState);
	}

	public void update() {
		try {
			gameStates[currentState].update();
		} catch (Exception e) {}
	}

	public void draw(java.awt.Graphics2D g) {
		try {
			gameStates[currentState].draw(g);
		} catch (Exception e) {}
	}

	public void keyPressed(int k) {
		gameStates[currentState].keyPressed(k);
	}

	public void keyReleased(int k) {
		gameStates[currentState].keyReleased(k);
	}

	public static int getPreviousState(){
		return previousState;
	}
	
}
