package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener{
    public static final String TAG = Assets.class.getName();

    private static Assets instance;

//    public CuteGirlAssets cuteGirlAssets;
    public NinjaAssets ninjaAssets;
    public ZombieAssets zombieAssets;
    public PlatformAssets platformAssets;
    public TransparentAssets transparentAssets;
    public OnscreenControlsAssets onscreenControlsAssets;
    public CoinAssets coinAssets;
    public KunaiAssets kunaiAssets;
    private AssetManager manager;

    public static Assets getInstance(){
        if (instance == null){
            instance = new Assets();
            return instance;
        } else {
            return instance;
        }
    }
    private Assets(){}

    public void init(AssetManager manager) {
        this.manager = manager;
//        manager.load(Constants.CUTEGIRL_ATLAS, TextureAtlas.class);
        manager.load(Constants.TILES_ATLAS, TextureAtlas.class);
        manager.load(Constants.ZOMBIE_ATLAS, TextureAtlas.class);
        manager.load(Constants.NINJA_ATLAS, TextureAtlas.class);
        manager.finishLoading();

//        TextureAtlas cuteGirlAtlas = manager.get(Constants.CUTEGIRL_ATLAS);
        TextureAtlas zombieAtlas = manager.get(Constants.ZOMBIE_ATLAS);
        TextureAtlas tileAtlas = manager.get(Constants.TILES_ATLAS);
        TextureAtlas ninjaAtlas = manager.get(Constants.NINJA_ATLAS);
//        cuteGirlAssets = new CuteGirlAssets(cuteGirlAtlas);
        zombieAssets = new ZombieAssets(zombieAtlas);
        platformAssets = new PlatformAssets(tileAtlas);
        transparentAssets = new TransparentAssets(tileAtlas);
        onscreenControlsAssets = new OnscreenControlsAssets(tileAtlas);
        coinAssets = new CoinAssets(tileAtlas);
        ninjaAssets = new NinjaAssets(ninjaAtlas);
        kunaiAssets = new KunaiAssets(ninjaAtlas);

    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset: " + asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }

//    public class CuteGirlAssets{
//
//        public final Animation idleAnimation;
//        public final Animation runAnimation;
//        public final Animation jumpAnimation;
//        public final Animation deadAnimation;
//
//        public CuteGirlAssets(TextureAtlas atlas){
//            Array<AtlasRegion> idleFrames = new Array<AtlasRegion>();
//            idleFrames.add(atlas.findRegion(Constants.IDLE_1));
////            idleFrames.add(atlas.findRegion(Constants.IDLE_2));
//            idleFrames.add(atlas.findRegion(Constants.IDLE_3));
////            idleFrames.add(atlas.findRegion(Constants.IDLE_4));
//            idleFrames.add(atlas.findRegion(Constants.IDLE_5));
////            idleFrames.add(atlas.findRegion(Constants.IDLE_6));
//            idleFrames.add(atlas.findRegion(Constants.IDLE_7));
////            idleFrames.add(atlas.findRegion(Constants.IDLE_8));
//            idleFrames.add(atlas.findRegion(Constants.IDLE_9));
////            idleFrames.add(atlas.findRegion(Constants.IDLE_10));
//            idleFrames.add(atlas.findRegion(Constants.IDLE_11));
////            idleFrames.add(atlas.findRegion(Constants.IDLE_12));
//            idleFrames.add(atlas.findRegion(Constants.IDLE_13));
////            idleFrames.add(atlas.findRegion(Constants.IDLE_14));
//            idleFrames.add(atlas.findRegion(Constants.IDLE_15));
////            idleFrames.add(atlas.findRegion(Constants.IDLE_16));
//
//            idleAnimation = new Animation(Constants.IDLE_LOOP_DURATION,
//                    idleFrames,
//                    Animation.PlayMode.LOOP);
//
//            //Array of AtlasRegions to hold the walking right frames
//            Array<AtlasRegion> runFrames = new Array<AtlasRegion>();
//            //Adding the proper frames to the array
//            runFrames.add(atlas.findRegion(Constants.RUN_1));
////            runFrames.add(atlas.findRegion(Constants.RUN_2));
//            runFrames.add(atlas.findRegion(Constants.RUN_3));
////            runFrames.add(atlas.findRegion(Constants.RUN_4));
//            runFrames.add(atlas.findRegion(Constants.RUN_5));
////            runFrames.add(atlas.findRegion(Constants.RUN_6));
//            runFrames.add(atlas.findRegion(Constants.RUN_7));
////            runFrames.add(atlas.findRegion(Constants.RUN_8));
//            runFrames.add(atlas.findRegion(Constants.RUN_9));
////            runFrames.add(atlas.findRegion(Constants.RUN_10));
//            runFrames.add(atlas.findRegion(Constants.RUN_11));
////            runFrames.add(atlas.findRegion(Constants.RUN_12));
//            runFrames.add(atlas.findRegion(Constants.RUN_13));
////            runFrames.add(atlas.findRegion(Constants.RUN_14));
//            runFrames.add(atlas.findRegion(Constants.RUN_15));
////            runFrames.add(atlas.findRegion(Constants.RUN_16));
//            runFrames.add(atlas.findRegion(Constants.RUN_17));
////            runFrames.add(atlas.findRegion(Constants.RUN_18));
//            runFrames.add(atlas.findRegion(Constants.RUN_19));
////            runFrames.add(atlas.findRegion(Constants.RUN_20));
//
//            runAnimation = new Animation(Constants.RUNNING_LOOP_DURATION,
//                    runFrames, Animation.PlayMode.LOOP);
//
//            Array<AtlasRegion> jumpFrames = new Array<AtlasRegion>();
//            jumpFrames.add(atlas.findRegion(Constants.JUMP_1));
////            jumpFrames.add(atlas.findRegion(Constants.JUMP_2));
//            jumpFrames.add(atlas.findRegion(Constants.JUMP_3));
////            jumpFrames.add(atlas.findRegion(Constants.JUMP_4));
//            jumpFrames.add(atlas.findRegion(Constants.JUMP_5));
////            jumpFrames.add(atlas.findRegion(Constants.JUMP_6));
//            jumpFrames.add(atlas.findRegion(Constants.JUMP_7));
////            jumpFrames.add(atlas.findRegion(Constants.JUMP_8));
//            jumpFrames.add(atlas.findRegion(Constants.JUMP_9));
////            jumpFrames.add(atlas.findRegion(Constants.JUMP_10));
//            jumpFrames.add(atlas.findRegion(Constants.JUMP_11));
////            jumpFrames.add(atlas.findRegion(Constants.JUMP_12));
//            jumpFrames.add(atlas.findRegion(Constants.JUMP_13));
////            jumpFrames.add(atlas.findRegion(Constants.JUMP_14));
//            jumpFrames.add(atlas.findRegion(Constants.JUMP_15));
////            jumpFrames.add(atlas.findRegion(Constants.JUMP_16));
//            jumpFrames.add(atlas.findRegion(Constants.JUMP_17));
////            jumpFrames.add(atlas.findRegion(Constants.JUMP_18));
//            jumpFrames.add(atlas.findRegion(Constants.JUMP_19));
////            jumpFrames.add(atlas.findRegion(Constants.JUMP_20));
//            jumpFrames.add(atlas.findRegion(Constants.JUMP_21));
////            jumpFrames.add(atlas.findRegion(Constants.JUMP_22));
//            jumpFrames.add(atlas.findRegion(Constants.JUMP_23));
////            jumpFrames.add(atlas.findRegion(Constants.JUMP_24));
//            jumpFrames.add(atlas.findRegion(Constants.JUMP_25));
////            jumpFrames.add(atlas.findRegion(Constants.JUMP_26));
//            jumpFrames.add(atlas.findRegion(Constants.JUMP_27));
////            jumpFrames.add(atlas.findRegion(Constants.JUMP_28));
//            jumpFrames.add(atlas.findRegion(Constants.JUMP_29));
////            jumpFrames.add(atlas.findRegion(Constants.JUMP_30));
//
//            jumpAnimation = new Animation(Constants.JUMPING_LOOP_DURATION,
//                    jumpFrames, Animation.PlayMode.NORMAL);
//
//            Array<AtlasRegion> deadFrames = new Array<AtlasRegion>();
//            deadFrames.add(atlas.findRegion(Constants.DEAD_1));
//            deadFrames.add(atlas.findRegion(Constants.DEAD_3));
//            deadFrames.add(atlas.findRegion(Constants.DEAD_5));
//            deadFrames.add(atlas.findRegion(Constants.DEAD_7));
//            deadFrames.add(atlas.findRegion(Constants.DEAD_9));
//            deadFrames.add(atlas.findRegion(Constants.DEAD_11));
//            deadFrames.add(atlas.findRegion(Constants.DEAD_13));
//            deadFrames.add(atlas.findRegion(Constants.DEAD_15));
//            deadFrames.add(atlas.findRegion(Constants.DEAD_17));
//            deadFrames.add(atlas.findRegion(Constants.DEAD_19));
//            deadFrames.add(atlas.findRegion(Constants.DEAD_21));
//            deadFrames.add(atlas.findRegion(Constants.DEAD_23));
//            deadFrames.add(atlas.findRegion(Constants.DEAD_25));
//            deadFrames.add(atlas.findRegion(Constants.DEAD_27));
//            deadFrames.add(atlas.findRegion(Constants.DEAD_29));
//
//            deadAnimation = new Animation(Constants.DEAD_LOOP_DURATION,
//                    deadFrames);
//        }
//
//    }

    public class ZombieAssets {
        public final Animation walkAnimation;
        public final Animation attackAnimation;
        public final Animation deadAnimation;

        public ZombieAssets(TextureAtlas atlas){

            //WALK ANIMATION
            Array<AtlasRegion> walkFrames = new Array<AtlasRegion>();
            walkFrames.add(atlas.findRegion(Constants.ZOMBIE_WALK_1));
            walkFrames.add(atlas.findRegion(Constants.ZOMBIE_WALK_2));
            walkFrames.add(atlas.findRegion(Constants.ZOMBIE_WALK_3));
            walkFrames.add(atlas.findRegion(Constants.ZOMBIE_WALK_4));
            walkFrames.add(atlas.findRegion(Constants.ZOMBIE_WALK_5));
            walkFrames.add(atlas.findRegion(Constants.ZOMBIE_WALK_6));
            walkFrames.add(atlas.findRegion(Constants.ZOMBIE_WALK_7));
            walkFrames.add(atlas.findRegion(Constants.ZOMBIE_WALK_8));
            walkFrames.add(atlas.findRegion(Constants.ZOMBIE_WALK_9));
            walkFrames.add(atlas.findRegion(Constants.ZOMBIE_WALK_10));

            walkAnimation = new Animation(Constants.ZOMBIE_WALK_LOOP_DURATION,
                    walkFrames, Animation.PlayMode.LOOP);

            //ATTACK ANIMATION

            Array<AtlasRegion> attackFrames = new Array<AtlasRegion>();
            attackFrames.add(atlas.findRegion(Constants.ZOMBIE_ATTACK_1));
            attackFrames.add(atlas.findRegion(Constants.ZOMBIE_ATTACK_2));
            attackFrames.add(atlas.findRegion(Constants.ZOMBIE_ATTACK_3));
            attackFrames.add(atlas.findRegion(Constants.ZOMBIE_ATTACK_4));
            attackFrames.add(atlas.findRegion(Constants.ZOMBIE_ATTACK_5));
            attackFrames.add(atlas.findRegion(Constants.ZOMBIE_ATTACK_6));
            attackFrames.add(atlas.findRegion(Constants.ZOMBIE_ATTACK_7));
            attackFrames.add(atlas.findRegion(Constants.ZOMBIE_ATTACK_8));

            attackAnimation = new Animation(Constants.ZOMBIE_ATTACK_LOOP_DURATION,
                    attackFrames, Animation.PlayMode.LOOP);

            Array<AtlasRegion> deadFrames = new Array<AtlasRegion>();
            deadFrames.add(atlas.findRegion("Dead (1)"));
            deadFrames.add(atlas.findRegion("Dead (2)"));
            deadFrames.add(atlas.findRegion("Dead (3)"));
            deadFrames.add(atlas.findRegion("Dead (4)"));
            deadFrames.add(atlas.findRegion("Dead (5)"));
            deadFrames.add(atlas.findRegion("Dead (6)"));
            deadFrames.add(atlas.findRegion("Dead (7)"));
            deadFrames.add(atlas.findRegion("Dead (8)"));
            deadFrames.add(atlas.findRegion("Dead (9)"));
            deadFrames.add(atlas.findRegion("Dead (10)"));
            deadFrames.add(atlas.findRegion("Dead (11)"));
            deadFrames.add(atlas.findRegion("Dead (12)"));
            deadAnimation = new Animation(.1f,
                    deadFrames, Animation.PlayMode.NORMAL);

        }
    }

    public class PlatformAssets{

        public final NinePatch platformNinePatch;
        public final NinePatch platformLeft;
        public final NinePatch platformCenter;
        public final NinePatch platformRight;
        public final NinePatch platformMidLeft;
        public final NinePatch platformMidCenter;
        public final NinePatch platformMidRight;
        public final NinePatch platformCornerLeft;
        public final NinePatch platformCornerTopLeft;

        public PlatformAssets(TextureAtlas atlas){
            int edge = Constants.PLATFORM_EDGE;
            AtlasRegion region = atlas.findRegion(Constants.PLATFORM_SPRITE);
            platformNinePatch = new NinePatch(region, edge, edge, edge, edge);
            region = atlas.findRegion(Constants.PLATFORM_LEFT);
            platformLeft = new NinePatch(region, edge, edge, edge, edge);
            region = atlas.findRegion(Constants.PLATFORM_CENTER);
            platformCenter = new NinePatch(region, edge, edge, edge, edge);
            region = atlas.findRegion(Constants.PLATFORM_RIGHT);
            platformRight = new NinePatch(region, edge, edge, edge, edge);
            region = atlas.findRegion(Constants.PLATFORM_MID_LEFT);
            platformMidLeft = new NinePatch(region, edge, edge, edge, edge);
            region = atlas.findRegion(Constants.PLATFORM_MID_CENTER);
            platformMidCenter = new NinePatch(region, edge, edge, edge, edge);
            region = atlas.findRegion(Constants.PLATFORM_MID_RIGHT);
            platformMidRight = new NinePatch(region, edge, edge, edge, edge);
            region = atlas.findRegion("Tile (11)");
            platformCornerLeft = new NinePatch(region, edge, edge, edge, edge);
            region = atlas.findRegion("Tile (10)");
            platformCornerTopLeft = new NinePatch(region, edge, edge, edge, edge);

        }
    }

    public class TransparentAssets {
        public final NinePatch acidSurface;
        public final NinePatch acid;
        public TransparentAssets(TextureAtlas atlas) {
            int edge = Constants.PLATFORM_EDGE;
            AtlasRegion region = atlas.findRegion(Constants.ACID_SURFACE_SPRITE);
            acidSurface = new NinePatch(region, edge, edge, edge, edge);
            region = atlas.findRegion(Constants.ACID_SPRITE);
            acid = new NinePatch(region, edge, edge, edge, edge);
        }
    }

    public class OnscreenControlsAssets {

        public final AtlasRegion moveRight;
        public final AtlasRegion moveLeft;
        public final AtlasRegion jump;
        public final AtlasRegion throwkunai;

        public OnscreenControlsAssets(TextureAtlas atlas) {
            moveRight = atlas.findRegion(Constants.MOVE_RIGHT_BUTTON);
            moveLeft = atlas.findRegion(Constants.MOVE_LEFT_BUTTON);
            jump = atlas.findRegion(Constants.JUMP_BUTTON);
            throwkunai = atlas.findRegion(Constants.THROW_BUTTON);
        }


    }

    public class CoinAssets {
        public final AtlasRegion coin;

        public CoinAssets(TextureAtlas atlas) {
            coin = atlas.findRegion(Constants.COIN_SPRITE);
        }
    }

    public class NinjaAssets{
        public final Animation idleAnimation;
        public final Animation runAnimation;
        public final Animation jumpAnimation;
        public final Animation throwAnimation;
        public final Animation jumpThrowAnimation;

        public NinjaAssets(TextureAtlas atlas) {

            //NINJA IDLE ANIMATION
            Array<AtlasRegion> idleFrames = new Array<AtlasRegion>();
            idleFrames.add(atlas.findRegion("Idle000"));
            idleFrames.add(atlas.findRegion("Idle001"));
            idleFrames.add(atlas.findRegion("Idle002"));
            idleFrames.add(atlas.findRegion("Idle003"));
            idleFrames.add(atlas.findRegion("Idle004"));
            idleFrames.add(atlas.findRegion("Idle005"));
            idleFrames.add(atlas.findRegion("Idle006"));
            idleFrames.add(atlas.findRegion("Idle007"));
            idleFrames.add(atlas.findRegion("Idle008"));
            idleFrames.add(atlas.findRegion("Idle009"));
            idleAnimation = new Animation(
                    Constants.IDLE_FRAME_DURATION, idleFrames, Animation.PlayMode.LOOP);

            //NINJA RUN ANIMATION
            Array<AtlasRegion> runFrames = new Array<AtlasRegion>();
            runFrames.add(atlas.findRegion("Run000"));
            runFrames.add(atlas.findRegion("Run001"));
            runFrames.add(atlas.findRegion("Run002"));
            runFrames.add(atlas.findRegion("Run003"));
            runFrames.add(atlas.findRegion("Run004"));
            runFrames.add(atlas.findRegion("Run005"));
            runFrames.add(atlas.findRegion("Run006"));
            runFrames.add(atlas.findRegion("Run007"));
            runFrames.add(atlas.findRegion("Run008"));
            runFrames.add(atlas.findRegion("Run009"));
            runAnimation = new Animation(
                    Constants.RUN_FRAME_DURATION, runFrames, Animation.PlayMode.LOOP);

            //NINJA JUMP ANIMATION
            Array<AtlasRegion> jumpFrames = new Array<AtlasRegion>();
            jumpFrames.add(atlas.findRegion("Jump000"));
            jumpFrames.add(atlas.findRegion("Jump001"));
            jumpFrames.add(atlas.findRegion("Jump002"));
            jumpFrames.add(atlas.findRegion("Jump003"));
            jumpFrames.add(atlas.findRegion("Jump004"));
            jumpFrames.add(atlas.findRegion("Jump005"));
            jumpFrames.add(atlas.findRegion("Jump006"));
            jumpFrames.add(atlas.findRegion("Jump007"));
            jumpFrames.add(atlas.findRegion("Jump008"));
            jumpFrames.add(atlas.findRegion("Jump009"));
            jumpAnimation = new Animation(
                    Constants.JUMP_FRAME_DURATION, jumpFrames, Animation.PlayMode.NORMAL);

            //NINJA THROW KUNAI ANIMATION
            Array<AtlasRegion> throwFrames = new Array<AtlasRegion>();
            throwFrames.add(atlas.findRegion("Throw000"));
            throwFrames.add(atlas.findRegion("Throw001"));
            throwFrames.add(atlas.findRegion("Throw002"));
            throwFrames.add(atlas.findRegion("Throw003"));
            throwFrames.add(atlas.findRegion("Throw004"));
            throwFrames.add(atlas.findRegion("Throw005"));
            throwFrames.add(atlas.findRegion("Throw006"));
            throwFrames.add(atlas.findRegion("Throw007"));
            throwFrames.add(atlas.findRegion("Throw008"));
            throwFrames.add(atlas.findRegion("Throw009"));

            throwAnimation = new Animation(
                    Constants.THROW_FRAME_DURATION, throwFrames, Animation.PlayMode.NORMAL);

            //NINJA JUMP THROW KUNAI
            Array<AtlasRegion> jumpThrowFrames = new Array<AtlasRegion>();
            jumpThrowFrames.add(atlas.findRegion("JumpThrow0"));
            jumpThrowFrames.add(atlas.findRegion("JumpThrow1"));
            jumpThrowFrames.add(atlas.findRegion("JumpThrow2"));
            jumpThrowFrames.add(atlas.findRegion("JumpThrow3"));
            jumpThrowFrames.add(atlas.findRegion("JumpThrow4"));
            jumpThrowFrames.add(atlas.findRegion("JumpThrow5"));
            jumpThrowFrames.add(atlas.findRegion("JumpThrow6"));
            jumpThrowFrames.add(atlas.findRegion("JumpThrow7"));
            jumpThrowFrames.add(atlas.findRegion("JumpThrow8"));
            jumpThrowFrames.add(atlas.findRegion("JumpThrow9"));

            jumpThrowAnimation = new Animation(
                    Constants.JUMPTHROW_FRAME_DURATION, jumpThrowFrames, Animation.PlayMode.NORMAL);


        }
    }

    public class KunaiAssets {

        public final AtlasRegion kunai;

        public KunaiAssets(TextureAtlas atlas) {
            kunai = atlas.findRegion(Constants.KUNAI_SPRITE);
        }

    }
}
