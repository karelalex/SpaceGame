package ru.naztrans.space.ship;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.naztrans.space.bullet.BulletPool;
import ru.naztrans.space.engine.math.Rect;
import ru.naztrans.space.engine.pool.SpritesPool;

/**
 * Created by alexk on 14.02.2018.
 */

public class EnemyShipPool extends SpritesPool<EnemyShip> {
    private TextureAtlas atlas;
    private BulletPool bp;
    private Sound fs;
    private float interval;
    private Rect worldBounds;

    public EnemyShipPool (TextureAtlas atlas, BulletPool bp, Sound fireSound){
        this.atlas=atlas;
        this.bp=bp;
        this.fs=fireSound;
        worldBounds=new Rect();
    }



    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(atlas, bp, fs);
    }
    public void update(float delta){
        interval+=delta;
        if (interval>2f){
            interval=0;
            EnemyShip e=this.obtain();
            e.set(0,0,0.1f,worldBounds);
        }
    }

    @Override
    public EnemyShip obtain() {
        return super.obtain();
    }

    public void resize(Rect worldBounds){
        this.worldBounds.set(worldBounds);
    }
}
