package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Level;
import com.mygdx.game.util.Assets;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.Enums.Direction;
import com.mygdx.game.util.Utils;



public class Zombie {

    public static final String TAG = Zombie.class.getName();

    private Level level;
    private Assets.ZombieAssets zombieAssets;
    private Vector2 position;
    private Vector2 spawnLocation;
    private Platform platform;
    private Direction direction;
    private long startTime;
    private long deadStartTime;
    private boolean attacking;
    private Rectangle zombieBounds;
    public int health;
    public boolean active;

    public Zombie(Platform newPlatform, Level newLevel){

        platform = newPlatform;
        position = new Vector2(platform.left, platform.top + Constants.ZOMBIE_EYES_HEIGHT);
        level = newLevel;
        zombieAssets = Assets.getInstance().zombieAssets;
        startTime = TimeUtils.nanoTime();
        attacking = false;
        health = 3;
        active = true;
        //TODO rimuovere questa riga dopo il debug
        spawnLocation = position;
        zombieBounds = new Rectangle();
        init();
    }

    public void init(){
        respawn();
    }

    public void respawn(){
        position.set(spawnLocation);
        direction = Direction.getRandom();
        Gdx.app.log(TAG, "direction value = "+ direction);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void update(float delta) {
        if (!attacking && active){
            switch (direction) {
                case LEFT:
                    position.x -= delta * Constants.ZOMBIE_SPEED_HORIZONTAL;
                    break;
                case RIGHT:
                    position.x += delta * Constants.ZOMBIE_SPEED_HORIZONTAL;
                    break;
            }

            if (position.x <= platform.left) {
                position.x = platform.left;
                direction = Direction.RIGHT;
            } else if (position.x >= platform.right) {
                position.x = platform.right;
                direction = Direction.LEFT;
            }
        }

        //COLLISION
        zombieBounds = new Rectangle(
                position.x - Constants.ZOMBIE_COLLISION_RANGE,
                position.y - Constants.ZOMBIE_EYES_HEIGHT,
                Constants.ZOMBIE_COLLISION_RANGE * 4,
                Constants.ZOMBIE_COLLISION_RANGE * 4
        );
    }

    public void startAttack(Direction direction){
        this.direction = direction;
        attacking = true;
    }
    public void stopAttack(){
        attacking = false;
    }

    public void startDead(){
        if(active){
            deadStartTime = TimeUtils.nanoTime();
        }
        active = false;
    }

    public void render(SpriteBatch batch) {
        TextureRegion region;

        if (!attacking && active){
            region = zombieAssets.walkAnimation.getKeyFrame(
                    Utils.secondsSince(startTime));

            batch.draw(region.getTexture(),
                    position.x - Constants.ZOMBIE_OFFSET.x,
                    position.y - Constants.ZOMBIE_OFFSET.y,
                    0,
                    0,
                    region.getRegionWidth(),
                    region.getRegionHeight(),
                    .35f,
                    .35f,
                    0,
                    region.getRegionX(),
                    region.getRegionY(),
                    region.getRegionWidth(),
                    region.getRegionHeight(),
                    direction.value,
                    false);

        } else if (active){
            region = zombieAssets.attackAnimation.getKeyFrame(
                    MathUtils.nanoToSec * TimeUtils.nanoTime());
            batch.draw(region.getTexture(),
                    position.x - Constants.ZOMBIE_OFFSET.x,
                    position.y - Constants.ZOMBIE_OFFSET.y,
                    0,
                    0,
                    region.getRegionWidth(),
                    region.getRegionHeight(),
                    .35f,
                    .35f,
                    0,
                    region.getRegionX(),
                    region.getRegionY(),
                    region.getRegionWidth(),
                    region.getRegionHeight(),
                    direction.value,
                    false);
        } else {
            region = zombieAssets.deadAnimation.getKeyFrame(
                    Utils.secondsSince(deadStartTime));
            batch.draw(region.getTexture(),
                    position.x - Constants.ZOMBIE_OFFSET.x,
                    position.y - Constants.ZOMBIE_OFFSET.y - 10,
                    0,
                    0,
                    region.getRegionWidth(),
                    region.getRegionHeight(),
                    .35f,
                    .35f,
                    0,
                    region.getRegionX(),
                    region.getRegionY(),
                    region.getRegionWidth(),
                    region.getRegionHeight(),
                    direction.value,
                    false);
        }


    }

    public boolean isDeadFinish(){
        float elapsedTime = Utils.secondsSince(deadStartTime);
        return Assets.getInstance().ninjaAssets.throwAnimation.isAnimationFinished(elapsedTime);
    }

    public Rectangle getZombieBounds() {
        return zombieBounds;
    }
}
