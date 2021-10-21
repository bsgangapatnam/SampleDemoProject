package com.martian.robots;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

import static com.martian.robots.Instruction.F;
import static com.martian.robots.Instruction.R;

public class RobotState {

    private Queue<Instruction> instructions;
    private Orientation orientation;
    private Point currentPosition;
    private Point previousPosition;
    private boolean isLost;

    public RobotState(Queue<Instruction> instructions, Orientation orientation, Point currentPosition, Point previousPosition, boolean isLost) {
        this.instructions = instructions;
        this.orientation = orientation;
        this.currentPosition = currentPosition;
        this.previousPosition = previousPosition;
        this.isLost = isLost;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public Point getPreviousPosition() {
        return previousPosition;
    }

    public void setLostState(boolean isLost) {
        this.isLost = isLost;
    }

    public Queue<Instruction> getInstructions() {
        return instructions;
    }

    public void addInstructions(Queue<Instruction> instructions) {
        this.instructions.addAll(instructions);
    }

    public Instruction dequeueNextInstruction() {
        assert(!instructions.isEmpty());
        return instructions.poll();
    }


    public boolean canExecuteNextInstruction() {
        return (instructions.size() > 0);
    }

    public void executeNextInstruction() {
        assert(!instructions.isEmpty());

        Instruction i = dequeueNextInstruction();

        switch (i) {
            case F:
                previousPosition = currentPosition;
                currentPosition = move(i);
                break;
            default:
                orientation = rotate(i);
        }
    }

    public Point peekNextInstructionExecutionPositionResult() {
        assert(!instructions.isEmpty());
        return move(instructions.peek());
    }

    private Orientation rotate(Instruction i) {
        if(i == R) {
            return Orientation.clockwiseRotate(orientation);
        } else {
            return Orientation.antiClockwiseRotate(orientation);
        }
    }

    private Point move(Instruction i) {
        int x = currentPosition.x;
        int y = currentPosition.y;

        if(i == F) {
            switch (getOrientation()) {
                case N: return new Point(x, y + 1);
                case S: return new Point(x, y - 1);
                case E: return new Point(x + 1, y);
                case W: return new Point(x - 1, y);
            }
        }
        return currentPosition;
    }

    @Override
    public String toString() {
        if(isLost) {
            return String.format("%d %d %s LOST", previousPosition.x, previousPosition.y, orientation.toString());
        } else {
            return String.format("%d %d %s", currentPosition.x, currentPosition.y, orientation.toString());
        }
    }
}
