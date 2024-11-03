package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

public class Simulation {
    List<Animal> animals;
    List<MoveDirection> moves;
    int moveIndex;

    public Simulation(List<Vector2d> positions, List<MoveDirection> moves) {
        this.moves = moves;

        animals = new ArrayList<Animal>();
        for(Vector2d pos : positions) {
            animals.add(new Animal(pos));
        }
        this.moveIndex = 0;
    }

    public void run() {
        while(true) {
            try {
                Animal a = move_next();
                System.out.println("Zwierzę " + previousAnimalIndex() + ": " + a);
            } catch(IndexOutOfBoundsException e) {
                break;
            }
        }
    }

    public Animal move_next() {
        if(moveIndex == moves.size()){
            throw new IndexOutOfBoundsException("Simulation is out of moves");
        }

        Animal a = animals.get(currentAnimalIndex());
        a.move(moves.get(moveIndex));
        moveIndex++;
        return a;
    }

    public int currentAnimalIndex() {
        return moveIndex % animals.size();
    }

    public int previousAnimalIndex() {
        return currentAnimalIndex() == 0 ? animals.size() - 1 : currentAnimalIndex() - 1;
    }

    public int currentMoveIndex() {
        return moveIndex;
    }

    public int totalMoves() {
        return moves.size();
    }

    public int totalAnimals() {
        return animals.size();
    }
}
