package com.mygdx.game.util;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Constants {

    public static final Color BG_COLOR = Color.LIGHT_GRAY;
    public static final float WORLD_SIZE = 480;
    public static final float KILL_PLANE = -200;
    public static final float HORIZONTAL_LEFT_BOUND = -50;
    public static final float HORIZONTAL_RIGHT_BOUND = 1100;
    public static final float VERTICAL_TOP_BOUND = 1100;
    public static final float VERTICAL_BOTTOM_BOUND = -100;

    public static final float MOVE_SPEED_HORIZONTAL = 180f;
    public static final float GRAVITY_ACCELERATION = 10;
    public static final float JUMP_SPEED = 250;
    public static final float MAX_JUMP_DURATION = .1f;


    public static final float CUTEGIRL_HEIGHT = 50;
    public static final float CUTEGIRL_WIDTH = 46;
    public static final float CUTEGIRL_EYES_HEIGHT = 40;
    public static final Vector2 CUTEGIRL_OFFSET = new Vector2(20, 42);
    public static final float CUTEGIRL_STANCE_WIDTH = CUTEGIRL_WIDTH * 1/4;
    public static final float CUTEGILR_COLLISION_WIDTH = (CUTEGIRL_WIDTH * 3/4) -5;
    public static final float CUTEGILR_COLLISION_HEIGHT = CUTEGIRL_EYES_HEIGHT;

    public static final float IDLE_LOOP_DURATION = .1f;
    public static final float RUNNING_LOOP_DURATION = 0.1f;
    public static final float JUMPING_LOOP_DURATION = 0.1f;
    public static final float DEAD_LOOP_DURATION = 0.5f;


//  nomi degli Atlas
    public static final String CUTEGIRL_ATLAS = "images/cutegirl.pack.atlas";
    public static final String TILES_ATLAS = "images/tiles.pack.atlas";
    public static final String ZOMBIE_ATLAS = "images/zombie.pack.atlas";
    public static final String NINJA_ATLAS = "images/ninja.pack.atlas";

//    nomi delle Atlas region
//    public static final String IDLE_1 = "Idle (1)";
//    public static final String IDLE_2 = "Idle (2)";
//    public static final String IDLE_3 = "Idle (3)";
//    public static final String IDLE_4 = "Idle (4)";
//    public static final String IDLE_5 = "Idle (5)";
//    public static final String IDLE_6 = "Idle (6)";
//    public static final String IDLE_7 = "Idle (7)";
//    public static final String IDLE_8 = "Idle (8)";
//    public static final String IDLE_9 = "Idle (9)";
//    public static final String IDLE_10 = "Idle (10)";
//    public static final String IDLE_11 = "Idle (11)";
//    public static final String IDLE_12 = "Idle (12)";
//    public static final String IDLE_13 = "Idle (13)";
//    public static final String IDLE_14 = "Idle (14)";
//    public static final String IDLE_15 = "Idle (15)";
//    public static final String IDLE_16 = "Idle (16)";

//    public static final String RUN_1 = "Run (1)";
//    public static final String RUN_2 = "Run (2)";
//    public static final String RUN_3 = "Run (3)";
//    public static final String RUN_4 = "Run (4)";
//    public static final String RUN_5 = "Run (5)";
//    public static final String RUN_6 = "Run (6)";
//    public static final String RUN_7 = "Run (7)";
//    public static final String RUN_8 = "Run (8)";
//    public static final String RUN_9 = "Run (9)";
//    public static final String RUN_10 = "Run (10)";
//    public static final String RUN_11 = "Run (11)";
//    public static final String RUN_12 = "Run (12)";
//    public static final String RUN_13 = "Run (13)";
//    public static final String RUN_14 = "Run (14)";
//    public static final String RUN_15 = "Run (15)";
//    public static final String RUN_16 = "Run (16)";
//    public static final String RUN_17 = "Run (17)";
//    public static final String RUN_18 = "Run (18)";
//    public static final String RUN_19 = "Run (19)";
//    public static final String RUN_20 = "Run (20)";

//    public static final String JUMP_1 = "Jump (1)";
//    public static final String JUMP_2 = "Jump (2)";
//    public static final String JUMP_3 = "Jump (3)";
//    public static final String JUMP_4 = "Jump (4)";
//    public static final String JUMP_5 = "Jump (5)";
//    public static final String JUMP_6 = "Jump (6)";
//    public static final String JUMP_7 = "Jump (7)";
//    public static final String JUMP_8 = "Jump (8)";
//    public static final String JUMP_9 = "Jump (9)";
//    public static final String JUMP_10 = "Jump (10)";
//    public static final String JUMP_11 = "Jump (11)";
//    public static final String JUMP_12 = "Jump (12)";
//    public static final String JUMP_13 = "Jump (13)";
//    public static final String JUMP_14 = "Jump (14)";
//    public static final String JUMP_15 = "Jump (15)";
//    public static final String JUMP_16 = "Jump (16)";
//    public static final String JUMP_17 = "Jump (17)";
//    public static final String JUMP_18 = "Jump (18)";
//    public static final String JUMP_19 = "Jump (19)";
//    public static final String JUMP_20 = "Jump (20)";
//    public static final String JUMP_21 = "Jump (21)";
//    public static final String JUMP_22 = "Jump (22)";
//    public static final String JUMP_23 = "Jump (23)";
//    public static final String JUMP_24 = "Jump (24)";
//    public static final String JUMP_25 = "Jump (25)";
//    public static final String JUMP_26 = "Jump (26)";
//    public static final String JUMP_27 = "Jump (27)";
//    public static final String JUMP_28 = "Jump (28)";
//    public static final String JUMP_29 = "Jump (29)";
//    public static final String JUMP_30 = "Jump (30)";

//    public static final String DEAD_1 = "Dead (1)";
//    public static final String DEAD_3 = "Dead (3)";
//    public static final String DEAD_5 = "Dead (5)";
//    public static final String DEAD_7 = "Dead (7)";
//    public static final String DEAD_9 = "Dead (9)";
//    public static final String DEAD_11 = "Dead (11)";
//    public static final String DEAD_13 = "Dead (13)";
//    public static final String DEAD_15 = "Dead (15)";
//    public static final String DEAD_17 = "Dead (17)";
//    public static final String DEAD_19 = "Dead (19)";
//    public static final String DEAD_21 = "Dead (21)";
//    public static final String DEAD_23 = "Dead (23)";
//    public static final String DEAD_25 = "Dead (25)";
//    public static final String DEAD_27 = "Dead (27)";
//    public static final String DEAD_29 = "Dead (29)";



    //ZOMBIE CONSTANTS

    public static final Vector2 ZOMBIE_OFFSET = new Vector2(20, 65);
    public static final float ZOMBIE_WIDTH = 71;
    public static final float ZOMBIE_HEIGHT = 86;
    public static final float ZOMBIE_EYES_HEIGHT = 60;
    public static final float ZOMBIE_COLLISION_RANGE = 10;
    public static final float ZOMBIE_SPEED_HORIZONTAL = 15f;

    public static final float ZOMBIE_WALK_LOOP_DURATION = 0.12f;
    public static final float ZOMBIE_ATTACK_LOOP_DURATION = 0.12f;

    //ZOMBIE SPRITES
    //ZOMBIE WALK FRAMES
    public static final String ZOMBIE_WALK_1 = "Walk (1)";
    public static final String ZOMBIE_WALK_2 = "Walk (2)";
    public static final String ZOMBIE_WALK_3 = "Walk (3)";
    public static final String ZOMBIE_WALK_4 = "Walk (4)";
    public static final String ZOMBIE_WALK_5 = "Walk (5)";
    public static final String ZOMBIE_WALK_6 = "Walk (6)";
    public static final String ZOMBIE_WALK_7 = "Walk (7)";
    public static final String ZOMBIE_WALK_8 = "Walk (8)";
    public static final String ZOMBIE_WALK_9 = "Walk (9)";
    public static final String ZOMBIE_WALK_10 = "Walk (10)";
    //ZOMBIE ATTACK FRAMES
    public static final String ZOMBIE_ATTACK_1 = "Attack (1)";
    public static final String ZOMBIE_ATTACK_2 = "Attack (2)";
    public static final String ZOMBIE_ATTACK_3 = "Attack (3)";
    public static final String ZOMBIE_ATTACK_4 = "Attack (4)";
    public static final String ZOMBIE_ATTACK_5 = "Attack (5)";
    public static final String ZOMBIE_ATTACK_6 = "Attack (6)";
    public static final String ZOMBIE_ATTACK_7 = "Attack (7)";
    public static final String ZOMBIE_ATTACK_8 = "Attack (8)";

    //PLATFORMS SPRITES
    public static final String PLATFORM_SPRITE = "Tile (15)";
    public static final String PLATFORM_LEFT = "Tile (1)";
    public static final String PLATFORM_CENTER = "Tile (2)";
    public static final String PLATFORM_RIGHT = "Tile (3)";
    public static final String PLATFORM_MID_LEFT = "Tile (4)";
    public static final String PLATFORM_MID_CENTER = "Tile (5)";
    public static final String PLATFORM_MID_RIGHT = "Tile (6)";
    public static final int PLATFORM_EDGE = 10;
    public static final float PLATFORM_WIDTH = 100;
    public static final float PLATFORM_HEIGTH = 100;

    public static final String ACID_SURFACE_SPRITE = "Acid (1)";
    public static final String ACID_SPRITE = "Acid (2)";


    //CHASECAM CONSTANTS
    public static final float CHASE_CAM_MOVE_SPEED = WORLD_SIZE;
    public static final float ONSCREEN_CONTROLS_VIEWPORT_SIZE = 200;
    public static final String MOVE_LEFT_BUTTON = "buttonLeft";
    public static final String MOVE_RIGHT_BUTTON = "buttonRight";
    public static final String JUMP_BUTTON = "buttonJump";
    public static final String THROW_BUTTON = "buttonThrow";
    public static final Vector2 BUTTON_CENTER = new Vector2(10, 10);
    public static final float BUTTON_RADIUS = 30;
    public static final float BUTTON_REGION_SCALE = 0.15f;

    public static final String COIN_SPRITE = "coin";

    //NINJA CONSTANTS
    public static final float NINJA_WIDTH = 46.4f;
    public static final float NINJA_HEIGHT = 87.8f;
    public static final float NINJA_STANCE_WIDTH = 10;
    public static final Vector2 NINJA_OFFSET = new Vector2(20, 100);
    //KUNAI CONSTANTS
    public static final String KUNAI_SPRITE = "Kunai";
    public static final float KUNAI_MOVE_SPEED = 300;
    public static final Vector2 KUNAI_OFFSET = new Vector2(40,45);

    //NINJA ANIMATIONS DURATION
    public static final float IDLE_FRAME_DURATION = 0.08f;
    public static final float RUN_FRAME_DURATION = 0.07f;
    public static final float JUMP_FRAME_DURATION = 0.1f;
    public static final float THROW_FRAME_DURATION = 0.05f;
    public static final float JUMPTHROW_FRAME_DURATION = 0.05f;


    // LEVEL LOADER CONSTANTS
    public static final String LEVEL_DIR = "levels";
    public static final String LEVEL_FILE_EXTENSION = "dt";
    public static final String LEVEL_COMPOSITE = "composite";
    public static final String LEVEL_9PATCHES = "sImage9patchs";
    public static final String LEVEL_IMAGES = "sImages";
    public static final String LEVEL_ERROR_MESSAGE = "There was a problem loading the level.";
    public static final String LEVEL_IMAGENAME_KEY = "imageName";
    public static final String LEVEL_X_KEY = "x";
    public static final String LEVEL_Y_KEY = "y";
    public static final String LEVEL_WIDTH_KEY = "width";
    public static final String LEVEL_HEIGHT_KEY = "height";
    public static final String LEVEL_IDENTIFIER_KEY = "itemIdentifier";
    public static final String LEVEL_ENEMY_TAG = "Enemy";

}
