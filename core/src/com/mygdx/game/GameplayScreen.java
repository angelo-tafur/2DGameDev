package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.overlays.OnscreenControls;
import com.mygdx.game.util.Assets;
import com.mygdx.game.util.ChaseCam;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.LevelLoader;

public class GameplayScreen extends ScreenAdapter{
    public static final String TAG = GameplayScreen.class.getName();

    private SpriteBatch batch;
    private OnscreenControls onscreenControls;
    private ChaseCam chaseCam;
    private Level level;

    @Override
    public void show() {
        AssetManager manager = new AssetManager();
        Assets.getInstance().init(manager);

        batch = new SpriteBatch();
        chaseCam = new ChaseCam();

        onscreenControls = new OnscreenControls();

        if (onMobile()) {
            Gdx.input.setInputProcessor(onscreenControls);
        }

        startNewLevel();
    }
    private boolean onMobile() {
        return Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS;
    }

    @Override
    public void render(float delta) {
        level.update(delta);
        chaseCam.update(delta, level);

        Gdx.gl.glClearColor(Constants.BG_COLOR.r,
                Constants.BG_COLOR.g,
                Constants.BG_COLOR.b,
                Constants.BG_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        level.render(batch);

        if (onMobile()) {
            onscreenControls.render(batch);
        }

        startNewLevel();

    }

    private void startNewLevel() {

//        level = Level.debugLevel();

        String levelName = "levels/levelOne.dt";
        level = LevelLoader.load(levelName);

        chaseCam.camera = level.viewport.getCamera();
        chaseCam.target = level.getPlayer();
        onscreenControls.player = level.getPlayer();
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void resize(int width, int height) {
        level.viewport.update(width, height, true);
        chaseCam.camera = level.viewport.getCamera();

        onscreenControls.viewport.update(width, height, true);
        onscreenControls.recalculateButtonPositions();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public void levelComplete() {
        startNewLevel();
    }

    public void levelFailed() {
        startNewLevel();
    }
}
