package ru.naztrans.space.ship;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.naztrans.space.bullet.BulletPool;
import ru.naztrans.space.engine.Sprite;
import ru.naztrans.space.engine.math.Rect;
import ru.naztrans.space.engine.utils.Regions;

/**
 * Created by alexk on 14.02.2018.
 */

public class EnemyShip extends Ship {
    private final float SHIP_HEIGHT=  0.10f;
    private TextureAtlas atlas;
    private Vector2 velosity;
    private Rect worldBounds;

    EnemyShip(TextureAtlas atlas, BulletPool bp, Sound fireSound){
        super(atlas.findRegion("enemy0"), 1, 2, 2);
        this.atlas=atlas;
        setHeightProportion(SHIP_HEIGHT);
        velosity=new Vector2();
        worldBounds=new Rect();

    }
    public void set (int texture,
                     float x,
                     float vy,
                        Rect worldBounds
                     )
    {
        this.regions= Regions.split(atlas.findRegion("enemy"+texture), 1, 2, 2);

        setLeft(x-this.getHalfWidth());
        velosity.set(0,-vy);
        this.worldBounds=worldBounds;
        setBottom(worldBounds.getTop());
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(velosity,delta);
        if (this.getTop()<worldBounds.getBottom()){
            this.isDestroyed=true;
        }
    }

}
