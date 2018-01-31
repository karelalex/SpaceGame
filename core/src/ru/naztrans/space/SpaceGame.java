package ru.naztrans.space;

//import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpaceGame extends Game {
	@Override
	public void create() {
	 setScreen(new MenuScreen(this));
	}

	/*SpriteBatch batch;
	Texture shipsImg;
	int x;
	Texture background;
	TextureRegion ship;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		shipsImg = new Texture("ships.png");
		background=new Texture("sky.jpg");
		ship=new TextureRegion(shipsImg,0,2080,256,278);
		//x=0;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		x++;
		if (x>700) {x=-256;}
		batch.begin();
		batch.draw(background,0,0);
		batch.draw(ship, x, 30);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shipsImg.dispose();
	}*/
}
