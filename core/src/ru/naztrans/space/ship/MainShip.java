package ru.naztrans.space.ship;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.sun.org.apache.regexp.internal.RE;


import ru.naztrans.space.engine.math.Rect;
import ru.naztrans.space.bullet.BulletPool;
import ru.naztrans.space.explosion.ExplosionPool;

/**
 * Created by alexk on 10.02.2018.
 */

public class MainShip extends Ship {
    private static final float SHIP_HEIGHT = 0.15f;
    private static final float BOTTOM_MARGIN = 0.05f;

    private final Vector2 v0 = new Vector2(0.5f, 0.0f);

    public Vector2 getV() {
        return v;
    }



    private boolean pressedLeft;
    private boolean pressedRight;


    private int currentPointer;


    public MainShip(TextureAtlas atlas, BulletPool bp, ExplosionPool explosionPool, Rect worldBounds, Sound fireSound) {
        super(atlas.findRegion("main_ship"), 1, 2, 2, bp, explosionPool, worldBounds, fireSound);
        setHeightProportion(SHIP_HEIGHT);


        this.bulletRegion = atlas.findRegion("bulletMainShip");

    }
    public void  setToNewGame(){
        pos.x=worldBounds.pos.x;
        this.bulletHeight = 0.01f;
        this.bulletV.set(0,  0.5f);
        this.bulletDamage = 1;
        this.reloadInterval = 0.2f;
        this.hp=100;
        setDestroyed(false);
    }
    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        reloadTimer+=delta;
        if (reloadTimer>=reloadInterval){
            reloadTimer=0;
            fire();
        }
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            stop();
        }
        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            stop();
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom() + BOTTOM_MARGIN);
    }

    public void keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
        }
    }

    public void keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
            case Input.Keys.UP:
                fire();
                break;
        }
    }

    @Override
    public void touchDown(Vector2 touch, int pointer) {
        if (worldBounds.pos.x > touch.x) {
            moveLeft();
        } else {
            moveRight();
        }
        currentPointer=pointer;
    }

    @Override
    public void touchUp(Vector2 touch, int pointer) {
        if (pointer==currentPointer) {
            stop();
        }
    }

    private void moveRight() {
        v.set(v0);
    }

    private void moveLeft() {
        v.set(v0).rotate(180);
    }

    private void stop() {
        v.setZero();
    }
    public boolean isBulletCollision(Rect bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getTop() < getBottom()
                || bullet.getBottom() > pos.y
        );
    }

}
