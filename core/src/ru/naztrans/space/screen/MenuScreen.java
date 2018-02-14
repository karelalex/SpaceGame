package ru.naztrans.space.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.math.Vector2;

import ru.naztrans.space.Background;
import ru.naztrans.space.ui.ExitButton;
import ru.naztrans.space.ui.PlayButton;
import ru.naztrans.space.engine.ActionListener;
import ru.naztrans.space.engine.math.Rect;
import ru.naztrans.space.engine.Base2DScreen;
import ru.naztrans.space.engine.math.Rnd;
import ru.naztrans.space.star.Star;


public class MenuScreen extends Base2DScreen implements ActionListener{

    private static final float BUTTON_HEIGHT = 0.15f;
    private static final float BUTTON_PRESS_SCALE = 0.9f;
    private static final int NUMBER_OF_STARS = 100;
    private Texture backgroundTexture;
    private Background background;
    private TextureAtlas atlas;
    private ExitButton exit;
    private PlayButton play;
    private Star[] stars;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        backgroundTexture = new Texture("textures/sky.jpg");
        background = new Background(new TextureRegion(backgroundTexture));
        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        exit = new ExitButton(atlas, BUTTON_PRESS_SCALE, this);
        exit.setHeightProportion(BUTTON_HEIGHT);
        play = new PlayButton(atlas, BUTTON_PRESS_SCALE, this);
        play.setHeightProportion(BUTTON_HEIGHT);
        stars=new Star[NUMBER_OF_STARS];
        for(int i=0; i<NUMBER_OF_STARS; i++) {
            stars[i]=new Star(atlas, Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.5f, -0.1f), 0.01f);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }
    public void update(float delta){
        for (int i=0; i<NUMBER_OF_STARS; i++)
        {stars[i].update(delta);}
    }
    public void draw() {
        Gdx.gl.glClearColor(0.7f, 0.3f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);

        for (int i=0; i<NUMBER_OF_STARS; i++)
        {stars[i].draw(batch);}
        exit.draw(batch);
        play.draw(batch);
        batch.end();
    }
    @Override
    protected void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        exit.resize(worldBounds);
        play.resize(worldBounds);
        for (int i=0; i<NUMBER_OF_STARS; i++)
        {stars[i].resize(worldBounds);}
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {

        super.touchUp(touch, pointer);
        exit.touchUp(touch, pointer);
        play.touchUp(touch, pointer);
    }

    @Override
    protected void touchDown(Vector2 touch, int pointer) {
        super.touchDown(touch, pointer);
        exit.touchDown(touch, pointer);
        play.touchDown(touch, pointer);
    }

    @Override
    public void actionPerformed(Object src) {
        if (src == exit) {
            Gdx.app.exit();
        } else if (src == play) {
            game.setScreen(new GameScreen(game));
        } else {
            throw new RuntimeException("Unknown src " + src);
        }
    }
}
