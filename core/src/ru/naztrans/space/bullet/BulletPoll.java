package ru.naztrans.space.bullet;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.naztrans.space.engine.Sprite;

import ru.naztrans.space.engine.pool.SpritesPool;


/**
 * Created by alexk on 10.02.2018.
 */

public class BulletPoll extends SpritesPool {
    private TextureAtlas atlas;
    public BulletPoll (TextureAtlas atlas){
        this.atlas=atlas;
    }
    @Override
    protected Sprite newObject() {
        Sprite b;
        b = new Bullet(atlas);
        return b;
    }
}
