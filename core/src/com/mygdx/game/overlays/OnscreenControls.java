package com.mygdx.game.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.entities.Player;
import com.mygdx.game.util.Assets;
import com.mygdx.game.util.Constants;


//TODO aggiungere il tasto per il lancio dei kunai
public class OnscreenControls extends InputAdapter {
    public static final String TAG = OnscreenControls.class.getName();
    public final Viewport viewport;
    public Player player;
    private Vector2 moveLeftCenter;
    private Vector2 moveRightCenter;
    private Vector2 jumpCenter;
    private Vector2 throwCenter;
    private int moveLeftPointer;
    private int moveRightPointer;
    private int jumpPointer;
    private int throwPointer;

    public OnscreenControls(){
        this.viewport = new ExtendViewport(
                Constants.ONSCREEN_CONTROLS_VIEWPORT_SIZE,
                Constants.ONSCREEN_CONTROLS_VIEWPORT_SIZE);

        moveLeftCenter = new Vector2();
        moveRightCenter = new Vector2();
        jumpCenter = new Vector2();
        throwCenter = new Vector2();
        recalculateButtonPositions();
    }


    public void render(SpriteBatch batch) {

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        if (!Gdx.input.isTouched(jumpPointer)) {
            player.jumpButtonPressed = false;
            jumpPointer = 0;
        }
        if (!Gdx.input.isTouched(moveLeftPointer)) {
            player.leftButtonPressed = false;
            moveLeftPointer = 0;
        }
        if (!Gdx.input.isTouched(moveRightPointer)) {
            player.rightButtonPressed = false;
            moveRightPointer = 0;
        }
        if (!Gdx.input.isTouched(throwPointer)) {
            player.throwButtonPressed = false;
            throwPointer = 0;
        }

        TextureAtlas.AtlasRegion region = Assets.getInstance().onscreenControlsAssets.moveLeft;
        batch.draw(
                region.getTexture(),
                moveLeftCenter.x - Constants.BUTTON_CENTER.x,
                moveLeftCenter.y - Constants.BUTTON_CENTER.y,
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                Constants.BUTTON_REGION_SCALE,
                Constants.BUTTON_REGION_SCALE,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);

        region = Assets.getInstance().onscreenControlsAssets.moveRight;
        batch.draw(
                region.getTexture(),
                moveRightCenter.x - Constants.BUTTON_CENTER.x,
                moveRightCenter.y - Constants.BUTTON_CENTER.y,
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                Constants.BUTTON_REGION_SCALE,
                Constants.BUTTON_REGION_SCALE,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);

        region = Assets.getInstance().onscreenControlsAssets.jump;
        batch.draw(
                region.getTexture(),
                jumpCenter.x - Constants.BUTTON_CENTER.x,
                jumpCenter.y - Constants.BUTTON_CENTER.y,
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                Constants.BUTTON_REGION_SCALE,
                Constants.BUTTON_REGION_SCALE,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);

        region = Assets.getInstance().onscreenControlsAssets.throwkunai;
        batch.draw(
                region.getTexture(),
                throwCenter.x - Constants.BUTTON_CENTER.x,
                throwCenter.y - Constants.BUTTON_CENTER.y,
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                Constants.BUTTON_REGION_SCALE,
                Constants.BUTTON_REGION_SCALE,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);


        batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector2 viewportPosition = viewport.unproject(new Vector2(screenX, screenY));

        if (viewportPosition.dst(throwCenter) < Constants.BUTTON_RADIUS) {

            player.throwKunai();

        } else if (viewportPosition.dst(jumpCenter) < Constants.BUTTON_RADIUS) {

            jumpPointer = pointer;
            player.jumpButtonPressed = true;

        } else if (viewportPosition.dst(moveLeftCenter) < Constants.BUTTON_RADIUS) {

            moveLeftPointer = pointer;
            player.leftButtonPressed = true;

        } else if (viewportPosition.dst(moveRightCenter) < Constants.BUTTON_RADIUS) {

            moveRightPointer = pointer;
            player.rightButtonPressed = true;

        }

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 viewportPosition = viewport.unproject(new Vector2(screenX, screenY));

        if (pointer == moveLeftPointer && viewportPosition.dst(moveRightCenter) < Constants.BUTTON_RADIUS) {

            // Inform Player that the left button is no longer pressed
            player.leftButtonPressed = false;

            // Inform player that the right button is now pressed
            player.rightButtonPressed = true;

            // Zero moveLeftPointer
            moveLeftPointer = 0;

            // Save moveRightPointer
            moveRightPointer = pointer;

        }

        if (pointer == moveRightPointer && viewportPosition.dst(moveLeftCenter) < Constants.BUTTON_RADIUS) {

            player.rightButtonPressed = false;
            player.leftButtonPressed = true;
            moveRightPointer = 0;
            moveLeftPointer = pointer;

        }

        return super.touchDragged(screenX, screenY, pointer);
    }

    public void recalculateButtonPositions() {
        moveLeftCenter.set(Constants.BUTTON_RADIUS, Constants.BUTTON_RADIUS * 1/2);
        moveRightCenter.set(Constants.BUTTON_RADIUS * 2, Constants.BUTTON_RADIUS * 1/2);

        jumpCenter.set(
                viewport.getWorldWidth() - Constants.BUTTON_RADIUS,
                Constants.BUTTON_RADIUS * 1/2
        );

        throwCenter.set(
                viewport.getWorldWidth() - Constants.BUTTON_RADIUS * 2,
                Constants.BUTTON_RADIUS * 1/2
        );

    }
}
