package ru.naztrans.space.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.naztrans.space.engine.ActionListener;
import ru.naztrans.space.engine.UserInterface.ScaledTouchUpButton;

/**
 * Created by alexk on 21.02.2018.
 */

public class NewGameButton extends ScaledTouchUpButton {
    private static final float HEIGHT = 0.05f;
    private static final float TOP= -0.012f;
    private static final float PRESS_SCALE = 0.9f;
    public NewGameButton (TextureAtlas atlas, ActionListener actionListener){
        super(atlas.findRegion("button_new_game"), PRESS_SCALE,actionListener);
        setHeightProportion(HEIGHT);
        setTop(TOP);
    }
}
