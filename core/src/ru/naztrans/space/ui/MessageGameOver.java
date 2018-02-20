package ru.naztrans.space.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.naztrans.space.engine.Sprite;

/**
 * Created by alexk on 20.02.2018.
 */

public class MessageGameOver extends Sprite {

    private  static final float HEIGHT=0.07f;
    private static final float BOTTOM_MARGIN=0.09f;

    public MessageGameOver(TextureAtlas atlas){
        super(atlas.findRegion("message_game_over"));
        setHeightProportion(HEIGHT);
        setBottom(BOTTOM_MARGIN);
    }
}
