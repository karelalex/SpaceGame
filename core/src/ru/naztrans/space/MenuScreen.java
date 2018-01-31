package ru.naztrans.space;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.naztrans.space.engine.Base2DScreen;

/**
 * Created by alexk on 31.01.2018.
 */

public class MenuScreen extends Base2DScreen{
    private SpriteBatch batch;

    Texture shipsImg;
    int x;
    Texture background;
    TextureRegion ship;
    Vector2 place;
    Vector2 speed;
    Vector2 target;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        batch=new SpriteBatch();
        background = new Texture("sky.jpg");
        place=new Vector2(200, 200);
        target=place.cpy();
        speed = new Vector2(-1,1);
        shipsImg = new Texture("ships.png");
        ship=new TextureRegion(shipsImg,0,2080,256,278);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        //System.out.println(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        speed=(target.cpy().sub(place)).nor().scl(150f);
        batch.begin();
        batch.draw(background,0,0);
        place.add(speed.cpy().scl(delta));
        batch.draw(ship, place.x-ship.getRegionWidth()/2, place.y-ship.getRegionHeight()/2);
        batch.end();
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        target.x=screenX;
        target.y=Gdx.graphics.getHeight()-screenY;
        return false;
    }



    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        background.dispose();
    }
}
