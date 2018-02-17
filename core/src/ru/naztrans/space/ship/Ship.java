package ru.naztrans.space.ship;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.naztrans.space.bullet.Bullet;
import ru.naztrans.space.engine.Sprite;
import ru.naztrans.space.engine.math.Rect;
import ru.naztrans.space.bullet.BulletPool;
import ru.naztrans.space.explosion.Explosion;
import ru.naztrans.space.explosion.ExplosionPool;

/**
 * Базовый класс для кораблей
 */

public abstract class Ship extends Sprite {

    private static final float DAMAGE_ANIMATE_INTERVAL=0.1f;
    private float damageAnimateTimer=DAMAGE_ANIMATE_INTERVAL;
    protected final Vector2 v = new Vector2(); // скорость корабля
    protected Rect worldBounds; // границы мира

    protected int hp; //количество жизней
    protected ExplosionPool explosionPool;
    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;


    protected final Vector2 bulletV = new Vector2(); // скорость пули
    protected float bulletHeight; // высота пули
    protected  int bulletDamage; // урон

    protected float reloadInterval; // время перезарядки
    protected float reloadTimer; // таймер для стрельбы
    protected Sound fireSound;

    public Ship(TextureRegion region, int rows, int cols, int frames, BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Sound fireSound) {
        super(region, rows, cols, frames);
        this.bulletPool=bulletPool;
        this.explosionPool=explosionPool;
        this.worldBounds=worldBounds;
        this.fireSound=fireSound;
    }
    public Ship(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Sound fireSound) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
        this.fireSound=fireSound;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    protected void fire() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, bulletDamage);
        fireSound.play();
    }
    public void boom(){
        Explosion explosion=explosionPool.obtain();
        explosion.set(getHeight(), pos);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        damageAnimateTimer+=delta;
        if (damageAnimateTimer>=DAMAGE_ANIMATE_INTERVAL){
            frame = 0;
        }
    }

    public int getBulletDamage() {
        return bulletDamage;
    }

    public void damage(int damage){
        frame=1;
        damageAnimateTimer=0;

    }
}