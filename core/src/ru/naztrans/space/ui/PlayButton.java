package ru.naztrans.space.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.naztrans.space.engine.UserInterface.ScaledTouchUpButton;
import ru.naztrans.space.engine.math.Rect;
import ru.naztrans.space.engine.ActionListener;

/**
 * Created by alexk on 03.02.2018.
 */

public class PlayButton extends ScaledTouchUpButton {

    public PlayButton(TextureAtlas atlas, float pressScale, ActionListener actionListener) {
        super(atlas.findRegion("btPlay"), pressScale, actionListener);
    }


    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setLeft(worldBounds.getLeft());
    }
}