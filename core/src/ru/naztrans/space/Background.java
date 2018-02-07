package ru.naztrans.space;


import com.badlogic.gdx.graphics.g2d.TextureRegion;


import ru.naztrans.space.engine.math.Rect;
import ru.naztrans.space.engine.Sprite;

public class Background extends Sprite {

    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
        pos.set(worldBounds.pos);
    }
}
