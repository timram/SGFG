package GameState;

public abstract class MainController extends Controller{
	protected MainGameplayState game;
	
	public MainController(ArenaState arena, MainGameplayState game) {
		super(arena);
		this.game = game;
	}
}
