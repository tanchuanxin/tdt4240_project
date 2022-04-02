package com.tnig.game.view.guis;

public interface GUI {
    public void render(float delta);
    public void resize(int width, int height);
    public void dispose();
}
