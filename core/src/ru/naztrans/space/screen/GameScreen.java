package ru.naztrans.space.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.naztrans.space.Background;
import ru.naztrans.space.engine.Base2DScreen;
import ru.naztrans.space.engine.math.Rect;
import ru.naztrans.space.engine.math.Rnd;
import ru.naztrans.space.bullet.BulletPoll;
import ru.naztrans.space.ship.MainShip;
import ru.naztrans.space.star.Star;


/**
 * Created by alexk on 07.02.2018.
 */

public class GameScreen extends Base2DScreen {
    private static final int NUMBER_OF_STARS = 5;
    private Texture backgroundTexture;
    private Background background;
    private TextureAtlas atlas;
    private MainShip mainShip;
    private Star[] stars;
    private BulletPoll bp;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        backgroundTexture = new Texture("sky.jpg");
        background = new Background(new TextureRegion(backgroundTexture));
        atlas = new TextureAtlas("mainAtlas.tpack");
        bp=new BulletPoll(atlas);
        mainShip = new MainShip(atlas, bp);
        stars = new Star[NUMBER_OF_STARS];
        for (int i = 0; i < NUMBER_OF_STARS; i++) {
            stars[i] = new Star(atlas, Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.5f, -0.1f), 0.01f);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();

    }

    public void draw() {
        Gdx.gl.glClearColor(0.7f, 0.3f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);

        for (int i = 0; i < NUMBER_OF_STARS; i++) {

            stars[i].draw(batch);
        }
        mainShip.draw(batch);
        bp.drawActiveObjects(batch);
        batch.end();
    }

    public void update(float delta) {
        for (int i = 0; i < NUMBER_OF_STARS; i++) {
            stars[i].update(delta);
        }
        mainShip.update(delta);
        bp.updateActiveObjects(delta);
        bp.freeAllDestroyedActiveObjects();
    }

    @Override
    protected void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);

        for (int i = 0; i < NUMBER_OF_STARS; i++) {
            stars[i].resize(worldBounds);
        }
        mainShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        backgroundTexture.dispose();
        atlas.dispose();
        bp.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }

    @Override
    protected void touchDown(Vector2 touch, int pointer) {
        mainShip.touchDown(touch, pointer);
    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {
        mainShip.touchUp(touch, pointer);
    }
}
