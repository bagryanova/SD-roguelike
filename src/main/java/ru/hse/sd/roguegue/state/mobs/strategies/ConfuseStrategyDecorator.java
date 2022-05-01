package ru.hse.sd.roguegue.state.mobs.strategies;

import ru.hse.sd.roguegue.logic.Move;
import ru.hse.sd.roguegue.state.mobs.MobStrategy;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.state.StrategyDecorator;

import java.util.Random;

/**
 * Class for adding temporary confused behaviour to the current mob's strategy
 */
public class ConfuseStrategyDecorator extends StrategyDecorator {
    private int timeCnt = 10;
    private Random random = new Random();

    public ConfuseStrategyDecorator(MobStrategy strategy) {
        super(strategy);
    }

    /**
     * Decorated method. Returns confused position in the first 10 steps after applying decoration.
     * After 10 steps has usual strategy method's behaviour
     */
    public Position getNewPosition(Position position) {
        Position curPosition = super.getNewPosition(position);
        if (timeCnt > 0) {
            curPosition = getConfusedPosition(position);
            timeCnt -= 1;
        }
        return curPosition;
    }

    @Override
    public MobStrategy tryRemoveDecorator() {
        if (timeCnt == 0) {
            return super.tryRemoveDecorator();
        } else {
            return this;
        }
    }

    /**
     * Returns adjacent cell in a random direction if it's possible to make move there.
     * Otherwise returns given position.
     */
    private Position getConfusedPosition(Position position) {
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
}
