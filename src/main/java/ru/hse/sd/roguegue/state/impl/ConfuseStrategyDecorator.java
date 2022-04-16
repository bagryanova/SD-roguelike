package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.logic.Move;
import ru.hse.sd.roguegue.state.MobStrategy;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.state.StrategyDecorator;

import java.util.Random;

public class ConfuseStrategyDecorator extends StrategyDecorator {
    int timeCnt = 10;
    Random random = new Random();

    public ConfuseStrategyDecorator(MobStrategy strategy) {
        super(strategy);
    }

    public Position getNewPosition(Position position) {
        Position curPosition = super.getNewPosition(position);
        if (timeCnt > 0) {
            curPosition = getConfusedPosition(position);
            timeCnt -= 1;
        }
        return curPosition;
    }

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
