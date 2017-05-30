package GameState;

public abstract class InitController extends Controller{
	protected InitGameplayState game;
	
	public InitController(ArenaState arena, InitGameplayState game) {
		super(arena);
		this.game = game;
	}
}
