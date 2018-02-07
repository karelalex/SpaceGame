package ru.naztrans.space.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.naztrans.space.engine.UserInterface.ScaledTouchUpButton;
import ru.naztrans.space.engine.math.Rect;
import ru.naztrans.space.engine.ActionListener;

/**
 * Created by alexk on 03.02.2018.
 */

public class ExitButton extends ScaledTouchUpButton {
    public ExitButton(TextureAtlas atlas, float pressScale, ActionListener actionListener) {
        super(atlas.findRegion("btExit"), pressScale, actionListener);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setRight(worldBounds.getRight());
    }
}
