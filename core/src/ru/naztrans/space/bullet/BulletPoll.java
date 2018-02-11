package ru.naztrans.space.bullet;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.naztrans.space.engine.Sprite;

import ru.naztrans.space.engine.pool.SpritesPool;


/**
 * Created by alexk on 10.02.2018.
 */

public class BulletPoll extends SpritesPool {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
