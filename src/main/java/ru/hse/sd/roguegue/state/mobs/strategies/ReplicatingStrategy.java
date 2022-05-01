package ru.hse.sd.roguegue.state.mobs.strategies;

import ru.hse.sd.roguegue.logic.Move;
import ru.hse.sd.roguegue.state.mobs.MobStrategy;
import ru.hse.sd.roguegue.state.Position;

import java.util.Random;

public class ReplicatingStrategy extends MobStrategy {
    private Random random = new Random();

    public ReplicatingStrategy() {}

    /**
     * @param position
     * select random direction and try to make move
     * if it's possible return updated position
     * otherwise return the initial one
     */
    @Override
    public Position getNewPosition(Position position) {
        int randDirection = random.nextInt(4);

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

    /**
     * @return true if it's time to create a clone
     */
    public boolean replicationTime() {
        return (random.nextInt(10) == 5);
    }


}
