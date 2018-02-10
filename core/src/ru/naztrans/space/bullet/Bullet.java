package ru.naztrans.space.bullet;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.naztrans.space.engine.Sprite;
import ru.naztrans.space.engine.math.Rect;

/**
 * Created by alexk on 10.02.2018.
 */

public class Bullet extends Sprite {
    private final Vector2 v = new Vector2();
    private Rect worldBounds;

    public Bullet (TextureAtlas atlas) {
        super(atlas.findRegion("bulletMainShip"));
        this.worldBounds=worldBounds;
        pos.set(0, 0);
        v.set(0, 0.9f);
        setHeightProportion(0.02f);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        checkAndHandleBounds();
    }

    protected void checkAndHandleBounds() {
        if (getRight() < worldBounds.getLeft()) isDestroyed=true;
        if (getLeft() > worldBounds.getRight())  isDestroyed=true;
        if (getTop() < worldBounds.getBottom())  isDestroyed=true;
        if (getBottom() > worldBounds.getTop())  isDestroyed=true;
    }
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;

    }


}
