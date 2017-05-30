package TileMap;

import java.util.ArrayList;

import Sprite.Gif;

public class CreditsPage extends Background{
	public boolean visible;
	
	public CreditsPage(String name) {
		super(name);
		setVisible(false);
	}
	
	@Override
	public void initGifs() {
		gifs = new ArrayList<Gif>();
		gifs.add(new Gif(150, 15, "Anime", 10));
		gifs.add(new Gif (300, 200, "Anime1", 10));
		gifs.add(new Gif (600, 250, "Anime2", 10));
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
