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


    EnemyShip(TextureAtlas atlas, BulletPool bp, Sound fireSound){
        super(atlas.findRegion("enemy0"), 1, 2, 2);
        this.atlas=atlas;
        setHeightProportion(SHIP_HEIGHT);
        velosity=new Vector2();
        this.bulletPool=bp;
        this.fireSound=fireSound;
        this.bulletRegion = atlas.findRegion("bulletEnemy");
        this.bulletHeight = 0.01f;
        this.bulletV.set(0, -0.5f);
        this.bulletDamage = 1;
        this.reloadInterval = 0.4f;

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
        reloadTimer+=delta;
        if (reloadTimer>=reloadInterval){
            reloadTimer=0;
            fire();
        }
    }

}
