package ru.naztrans.space;

import com.badlogic.gdx.Game;
import ru.naztrans.space.screen.MenuScreen;

public class Star2DGame extends Game {
    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}
