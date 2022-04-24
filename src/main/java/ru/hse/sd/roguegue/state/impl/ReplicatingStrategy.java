package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.logic.Move;
import ru.hse.sd.roguegue.state.MobStrategy;
import ru.hse.sd.roguegue.state.Position;

import java.util.Random;

public class ReplicatingStrategy extends MobStrategy {
    private Random random = new Random();
    //private int withoutReplicating = 0;
    private final int replicatingConstant = 5;

    public ReplicatingStrategy() {
        super.lives = 1;
        super.strength = 4;
    }

    @Override
    public Position getNewPosition(Position position) {
        int randDirection = random.nextInt(4);
        //withoutReplicating += 1;

        switch (randDirection) {
            case 0 -> {
                if (super.validatePosition(position.move(Move.UP))) {
                    System.out.println(randDirection);
                    return position.move(Move.UP);
                }
            }
            case 1 -> {
                if (super.validatePosition(position.move(Move.DOWN))) {
                    System.out.println(randDirection);
                    return position.move(Move.DOWN);
                }
            }
            case 2 -> {
                if (super.validatePosition(position.move(Move.LEFT))) {
                    System.out.println(randDirection);
                    return position.move(Move.LEFT);
                }
            }
            case 3 -> {
                if (super.validatePosition(position.move(Move.RIGHT))) {
                    System.out.println(randDirection);
                    return position.move(Move.RIGHT);
                }
            }
        }
        return position;
    }

    /*public boolean replicationTime() {
        if (withoutReplicating < replicatingConstant) {
            return false;
        }
        withoutReplicating = 0;
        return true;
    }*/

    public boolean replicationTime() {
        return (random.nextInt(10) == 5);
    }


}
