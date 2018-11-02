package com.mygdx.game.util;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.mygdx.game.Level;
import com.mygdx.game.entities.Player;

public class ChaseCam {

    public static final String TAG = ChaseCam.class.getName();

    public Camera camera;
    public Player target;
    private Boolean following;

    public ChaseCam() {
        following = true;
    }


    public void update(float delta, Level level) {


        if (Gdx.input.isKeyJustPressed(Keys.Z)) {
            following = !following;
        }

        if (following) {
            if (target.getPosition().y - level.getViewport().getWorldHeight() / 2.2f > Constants.VERTICAL_BOTTOM_BOUND) {
                camera.position.y = target.getPosition().y;
            }
            if ((target.getPosition().x - level.getViewport().getWorldWidth() / 3 > Constants.HORIZONTAL_LEFT_BOUND) &&
                    (target.getPosition().x  + level.getViewport().getWorldWidth() / 3 < Constants.HORIZONTAL_RIGHT_BOUND)){
                camera.position.x = target.getPosition().x;
            }

        } else {
            if (Gdx.input.isKeyPressed(Keys.A)) {
                camera.position.x -= delta * Constants.CHASE_CAM_MOVE_SPEED;
            }
            if (Gdx.input.isKeyPressed(Keys.D)) {
                camera.position.x += delta * Constants.CHASE_CAM_MOVE_SPEED;
            }
            if (Gdx.input.isKeyPressed(Keys.W)) {
                camera.position.y += delta * Constants.CHASE_CAM_MOVE_SPEED;
            }
            if (Gdx.input.isKeyPressed(Keys.S)) {
                camera.position.y -= delta * Constants.CHASE_CAM_MOVE_SPEED;
            }
        }
    }
}
