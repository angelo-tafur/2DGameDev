package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Level;
import com.mygdx.game.util.Assets;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.Enums.*;
import com.mygdx.game.util.Utils;


public class Player {
    public final static String TAG = Player.class.getName();

    private Level level;
    private Assets.NinjaAssets ninjaAssets;

    private Vector2 spawnLocation;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 lastFramePosition;
    private float width;
    private float height;

    private Direction direction;
    private MovingState movingState;
    private JumpState jumpState;
    private long runningStartTime;
    private long jumpStartTime;
    private long throwStartTime;

    public boolean jumpButtonPressed;
    public boolean leftButtonPressed;
    public boolean rightButtonPressed;
    public boolean throwButtonPressed;

    public Rectangle playerBounds;

    private boolean throwing;

    public Player(Vector2 newSpawnLocation, Level newLevel) {
        level = newLevel;
        ninjaAssets = Assets.getInstance().ninjaAssets;
        spawnLocation = newSpawnLocation;
        position = new Vector2();
        velocity = new Vector2();
        lastFramePosition = new Vector2();
        throwing = false;
//        alive = true;
        init();
    }

    public void init() {
        //TODO Qua dovrei inizialiazzare il numero di vite e di munizioni del player

        //TODO chiamo respawn quando deve sono ricomparire nello schermo dopo la morte
        respawn();
    }

    public void respawn(){
        position.set(spawnLocation);
        lastFramePosition.set(spawnLocation);
        velocity.setZero();
        jumpState = JumpState.FALLING;
        direction = Direction.RIGHT;
        movingState = MovingState.IDLE;
//        alive = true;
    }

    public void update(float delta, Array<Platform> platforms) {

        lastFramePosition.set(position);
        velocity.y -= Constants.GRAVITY_ACCELERATION;
        position.mulAdd(velocity, delta);

        //RESPAWN
        if (position.y < Constants.KILL_PLANE) {
            //TODO quando terrai conto  delle vite, prima di chiamare respawn devi vedere se hai finito le vite
            respawn();
        }
        throwing = !isThrowFinished();

        if (jumpState != JumpState.JUMPING) {
            jumpState = JumpState.FALLING;
            for (Platform platform : platforms) {
                if (landedOnPlatform(platform)) {
                    jumpState = JumpState.GROUNDED;
                    velocity.setZero();
                    position.y = platform.top;
                }

            }
        }

        //COLLISION WITH ZOMBIES
        playerBounds = new Rectangle(
                position.x,
                position.y,
                width * 3/4,
                height
        );

        for (Zombie zombie : level.getZombies()) {
            if (playerBounds.overlaps(zombie.getZombieBounds()) && zombie.active) {
                if (position.x < zombie.getPosition().x) {
                    zombie.startAttack(Direction.LEFT);
                    //TODO death();
                } else {
                    zombie.startAttack(Direction.RIGHT);
                    //TODO death();
                }
            } else {
                zombie.stopAttack();
            }
        }

        //MOVING LEFT/RIGHT
        boolean left = Gdx.input.isKeyPressed(Keys.LEFT) || leftButtonPressed;
        boolean right = Gdx.input.isKeyPressed(Keys.RIGHT) || rightButtonPressed;

        if (left && !right && (isThrowFinished() || movingState != MovingState.IDLE) ) {
            if (position.x >= Constants.HORIZONTAL_LEFT_BOUND) {
                moveLeft(delta);
            }
        } else if (right && !left && (isThrowFinished() || movingState != MovingState.IDLE)) {
            if (position.x <= Constants.HORIZONTAL_RIGHT_BOUND) {
                moveRight(delta);
            }
        } else {
            movingState = MovingState.IDLE;
        }

        //JUMP
        if (Gdx.input.isKeyPressed(Keys.SPACE) || jumpButtonPressed) {
            switch (jumpState) {
                case GROUNDED:
                    startJump();
                    break;
                case JUMPING:
                    continueJump();
            }
        } else {
            endJump();
        }

        //THROW KUNAi
        if (Gdx.input.isKeyJustPressed(Keys.X) ){
            throwKunai();
        }
    }

    public void throwKunai(){
        if (!isThrowFinished()){ return; }

        if (movingState == MovingState.RUNNING && jumpState == JumpState.GROUNDED){
            movingState = MovingState.IDLE;
        }
        throwStartTime = TimeUtils.nanoTime();
        throwing = true;
        Vector2 kunaiPosition;
        if (direction == Direction.RIGHT) {
            kunaiPosition = new Vector2(
                    position.x + Constants.KUNAI_OFFSET.x,
                    position.y + Constants.KUNAI_OFFSET.y
            );
        } else {
            kunaiPosition = new Vector2(
                    position.x - Constants.KUNAI_OFFSET.x,
                    position.y + Constants.KUNAI_OFFSET.y
            );
        }
        level.throwKunai(kunaiPosition, direction);
    }
    private boolean isThrowFinished() {
        float elapsedTime = Utils.secondsSince(throwStartTime);
        return Assets.getInstance().ninjaAssets.throwAnimation.isAnimationFinished(elapsedTime);
    }

    private void moveLeft(float delta) {
        if (jumpState == JumpState.GROUNDED && movingState != MovingState.RUNNING) {
            runningStartTime = TimeUtils.nanoTime();
        }
        movingState = MovingState.RUNNING;
        direction = Direction.LEFT;
        position.x -= delta * Constants.MOVE_SPEED_HORIZONTAL;
    }
    private void moveRight(float delta) {
        if (jumpState == JumpState.GROUNDED && movingState != MovingState.RUNNING) {
            runningStartTime = TimeUtils.nanoTime();
        }
        movingState = MovingState.RUNNING;
        direction = Direction.RIGHT;
        position.x += delta * Constants.MOVE_SPEED_HORIZONTAL;
    }

    private void startJump() {
        jumpState = JumpState.JUMPING;
        jumpStartTime = TimeUtils.nanoTime();
        continueJump();
    }
    private void continueJump() {
        if (jumpState == JumpState.JUMPING) {
            if (Utils.secondsSince(jumpStartTime) < Constants.MAX_JUMP_DURATION) {
                velocity.y = Constants.JUMP_SPEED;
            } else {
                endJump();
            }
        }
    }
    private void endJump() {
        if (jumpState == JumpState.JUMPING) {
            jumpState = JumpState.FALLING;
        }
    }
    private boolean landedOnPlatform(Platform platform) {
        boolean leftFootIn = false;
        boolean rightFootIn = false;
        boolean straddle = false;

        if (lastFramePosition.y >= platform.top &&
                position.y < platform.top) {

            float leftFoot = position.x + 5;
            float rightFoot = position.x + width - 5;

            leftFootIn = (platform.left < leftFoot && platform.right > leftFoot);
            rightFootIn = (platform.left < rightFoot && platform.right > rightFoot);
            straddle = (platform.left > leftFoot && platform.right < rightFoot);
        }
        return leftFootIn || rightFootIn || straddle;
    }



    public void render(SpriteBatch batch) {

        TextureRegion region;

        if (jumpState != JumpState.GROUNDED && isThrowFinished()) {
            region = ninjaAssets.jumpAnimation.getKeyFrame(Utils.secondsSince(jumpStartTime));
        } else if (jumpState != JumpState.GROUNDED) {
            region = ninjaAssets.jumpThrowAnimation.getKeyFrame(Utils.secondsSince(throwStartTime));
        } else if (movingState == MovingState.RUNNING && isThrowFinished()) {
            region = ninjaAssets.runAnimation.getKeyFrame(Utils.secondsSince(runningStartTime));
        } else if (movingState == MovingState.IDLE && isThrowFinished()){
            region = ninjaAssets.idleAnimation.getKeyFrame(MathUtils.nanoToSec * TimeUtils.nanoTime());
        } else {
            region = ninjaAssets.throwAnimation.getKeyFrame(Utils.secondsSince(throwStartTime));
        }

        width = region.getRegionWidth() * 0.18f;
        height = region.getRegionHeight() * 0.18f;

        batch.draw(
                region.getTexture(),
                position.x,
                position.y,
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                .18f,
                .18f,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                direction.value,
                false);

    }

    public Vector2 getPosition() {
        return position;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
