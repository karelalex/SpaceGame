package ru.naztrans.space.ship;

import com.badlogic.gdx.audio.Sound;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.naztrans.space.bullet.BulletPool;

import ru.naztrans.space.engine.math.Rect;

import ru.naztrans.space.explosion.ExplosionPool;

/**
 * Created by alexk on 14.02.2018.
 */

public class EnemyShip extends Ship {

    private enum State {DESCENT, FIGHT}

    private MainShip mainShip;
    private State state;


    private Vector2 descentV = new Vector2(0, -0.15f);
    private Vector2 v0 = new Vector2();
    private float smallReloadInterval;
    private float smallReloadTimer;

    public int getCollisionDamage() {
        return collisionDamage;
    }

    private int collisionDamage;
    private int maxBulletCountAtTime;
    private int fireCounter;

    public EnemyShip(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds,  Sound shootSound, MainShip mainShip) {
        super(bulletPool, explosionPool, worldBounds, shootSound);
        this.mainShip = mainShip;
        this.v.set(v0);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        pos.mulAdd(v, delta);
        switch (state) {
            case DESCENT:
                if (getTop() <= worldBounds.getTop()) {
                    v.set(v0);
                    state = State.FIGHT;
                }
                break;
            case FIGHT:
                reloadTimer += delta;

                if (reloadTimer >= reloadInterval) {
                    reloadTimer = 0f;
                    fire();
                    fireCounter=1;
                    System.out.println("firecounter "+fireCounter);
                    System.out.println("maxbullet^ " + maxBulletCountAtTime);
                }
                if(fireCounter<maxBulletCountAtTime){
                    smallReloadTimer+=delta;

                    if(smallReloadTimer>=smallReloadInterval){
                        smallReloadTimer=0f;
                        fire();
                        fireCounter++;
                    }
                }
                if (getTop() < worldBounds.getBottom()) {
                    mainShip.damage(bulletDamage*2);
                    //boom();
                    setDestroyed(true);
                }
                break;
        }
        checkBounds();
    }

    public void set(
            TextureRegion[] regions,
            float vx, float vy,
            TextureRegion bulletRegion,
            float bulletHeight,
            float bulletVY,
            int bulletDamage,
            int collisionDamage,
            float reloadInterval,
            float smallReloadInterval,
            int maxBulletCountAtTime,
            float height,
            int hp

    ) {
        this.regions = regions;
        this.v0.set(vx,vy);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0f, bulletVY);
        this.bulletDamage = bulletDamage;
        this.reloadInterval = reloadInterval;
        this.collisionDamage=collisionDamage;
        this.smallReloadInterval=smallReloadInterval;
        this.hp = hp;
        this.maxBulletCountAtTime=maxBulletCountAtTime;
        setHeightProportion(height);
        v.set(descentV);
        state = State.DESCENT;
        reloadTimer = reloadInterval;
    }
    private void checkBounds(){
        if (this.getLeft()<=worldBounds.getLeft() || this.getRight()>=worldBounds.getRight()){
            this.v.set(-v.x, v.y);
        }
    }
    public boolean isBulletCollision(Rect bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop() < pos.y
        );
    }

}
