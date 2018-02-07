package ru.naztrans.space.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.naztrans.space.Background;
import ru.naztrans.space.engine.Base2DScreen;
import ru.naztrans.space.engine.math.Rect;
import ru.naztrans.space.engine.math.Rnd;
import ru.naztrans.space.star.Star;

/**
 * Created by alexk on 07.02.2018.
 */

public class GameScreen extends Base2DScreen {
    private static final int NUMBER_OF_STARS = 5;
    private Texture backgroundTexture;
    private Background background;
    private TextureAtlas atlas;
    private Star[] stars;

        public GameScreen(Game game) {
            super(game);
        }

        @Override
        public void show() {
            super.show();
            backgroundTexture = new Texture("sky.jpg");
            background = new Background(new TextureRegion(backgroundTexture));
            atlas = new TextureAtlas("menuAtlas.tpack");
            stars=new Star[NUMBER_OF_STARS];
            for(int i=0; i<NUMBER_OF_STARS; i++) {
                stars[i]=new Star(atlas, Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.5f, -0.1f), 0.01f);
            }
        }

        @Override
        public void render(float delta) {
            super.render(delta);
            Gdx.gl.glClearColor(0.7f, 0.3f, 0.7f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            background.draw(batch);

            for (int i=0; i<NUMBER_OF_STARS; i++)
            {
                    stars[i].update(delta);
                    stars[i].draw(batch);}
            batch.end();
        }
    @Override
    protected void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);

        for (int i=0; i<NUMBER_OF_STARS; i++)
        {stars[i].resize(worldBounds);}
    }
        @Override
        public void dispose() {
            super.dispose();
        }
    }
