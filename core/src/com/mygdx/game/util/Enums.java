package com.mygdx.game.util;

public class Enums {

    public enum MovingState {
        IDLE,
        WALKING,
        RUNNING
    }

    public enum Direction {
        LEFT(true),
        RIGHT(false);

        public boolean value;

        Direction(boolean value){
            this.value = value;
        }
        public static Direction getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }

    public enum JumpState {
        JUMPING,
        FALLING,
        GROUNDED
    }

    public enum TileType {
        LEFT,
        CENTER,
        RIGHT,
        CORNER_LEFT,
        SOLO
    }

    public enum ObjectType {
        ACID_SURFACE,
        ACID,
        MID_LEFT,
        MID_CENTER,
        MID_RIGHT,
        CORNER_LEFT
    }
}
