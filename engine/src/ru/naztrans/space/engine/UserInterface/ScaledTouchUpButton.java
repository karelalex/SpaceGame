package ru.naztrans.space.engine.UserInterface;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.naztrans.space.engine.ActionListener;

import ru.naztrans.space.engine.Sprite;


/**
 * Created by alexk on 07.02.2018.
 */

public class ScaledTouchUpButton extends Sprite {
    private int pointer;
    private boolean pressed;
    private float pressScale;
    private final ActionListener actionListener;

    public ScaledTouchUpButton(TextureRegion region, float pressScale, ActionListener actionListener) {
        super(region);
        this.pressScale = pressScale;
        this.actionListener = actionListener;
    }

    @Override
    public void touchDown(Vector2 touch, int pointer) {
        if (pressed || !isMe(touch)) {
            return;
        }
        this.pointer = pointer;
        scale = pressScale;
        pressed = true;
    }

    @Override
    public void touchUp(Vector2 touch, int pointer) {
        if (this.pointer != pointer || !pressed) {
            return;
        }
        if (isMe(touch)) {
            actionListener.actionPerformed(this);
        }
        pressed = false;
        scale = 1f;
    }
}
