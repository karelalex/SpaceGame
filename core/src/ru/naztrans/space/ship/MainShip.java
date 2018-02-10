package ru.naztrans.space.ship;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.naztrans.space.bullet.Bullet;
import ru.naztrans.space.engine.Sprite;
import ru.naztrans.space.engine.math.Rect;
import ru.naztrans.space.bullet.BulletPoll;

/**
 * Created by alexk on 10.02.2018.
 */

public class MainShip extends Sprite {
    private static final float SHIP_HEIGHT = 0.15f;
    private static final float BOTTOM_MARGIN = 0.05f;

    private final Vector2 v0 = new Vector2(0.5f, 0.0f);

    public Vector2 getV() {
        return v;
    }

    private final Vector2 v = new Vector2();

    private boolean pressedLeft;
    private boolean pressedRight;

    private Rect worldBounds;
    private int currentPointer;
    private BulletPoll bulletPoll;

    public MainShip(TextureAtlas atlas, BulletPoll bp) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        setHeightProportion(SHIP_HEIGHT);
        this.bulletPoll=bp;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
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
        this.worldBounds = worldBounds;
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
    void fire(){
        Bullet b=(Bullet)bulletPoll.obtain();
        b.setBottom(this.getTop()-0.02f);
        b.setLeft(this.getLeft()+this.getHalfWidth()-b.getHalfWidth());
        b.resize(worldBounds);
    }
}
