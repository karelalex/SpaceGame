package ru.naztrans.space.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import ru.naztrans.space.engine.Base2DScreen;

/**
 * Created by alexk on 07.02.2018.
 */

public class GameScreen extends Base2DScreen {


        public GameScreen(Game game) {
            super(game);
        }

        @Override
        public void show() {
            super.show();
        }

        @Override
        public void render(float delta) {
            super.render(delta);
            Gdx.gl.glClearColor(0.7f, 0.3f, 0.7f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();

            batch.end();
        }

        @Override
        public void dispose() {
            super.dispose();
        }
    }
