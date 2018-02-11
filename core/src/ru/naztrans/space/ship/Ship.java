package ru.naztrans.space.ship;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.naztrans.space.bullet.Bullet;
import ru.naztrans.space.engine.Sprite;
import ru.naztrans.space.engine.math.Rect;
import ru.naztrans.space.bullet.BulletPool;

/**
 * Базовый класс для кораблей
 */

public abstract class Ship extends Sprite {

    protected final Vector2 v = new Vector2(); // скорость корабля
    protected Rect worldBounds; // границы мира

    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;


    protected final Vector2 bulletV = new Vector2(); // скорость пули
    protected float bulletHeight; // высота пули
    protected  int bulletDamage; // урон

    protected float reloadInterval; // время перезарядки
    protected float reloadTimer; // таймер для стрельбы

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    protected void fire() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, bulletDamage);
    }
}