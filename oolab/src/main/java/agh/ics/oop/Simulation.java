package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

public class Simulation<T, P> {
    WorldMap<T, P> map;
    List<T> objs;
    List<MoveDirection> moves;
    int moveIndex;

    public Simulation(List<T> objs, List<MoveDirection> moves, WorldMap<T, P> map) {
        this.map = map;
        this.moves = moves;
        this.objs = new ArrayList<>(objs);

        for(T obj : this.objs) {
            map.place(obj);
        }
        this.moveIndex = 0;
    }

    public void run() {
        while(true) {
            try {
                move_next();
                System.out.println(map);
            } catch(IndexOutOfBoundsException e) {
                break;
            }
        }
    }

    public T move_next() {
        if(moveIndex == moves.size()){
            throw new IndexOutOfBoundsException("Simulation is out of moves");
        }

        T a = objs.get(currentObjIndex());
        map.move(a, moves.get(moveIndex));
        moveIndex++;
        return a;
    }

    public int currentObjIndex() {
        return moveIndex % objs.size();
    }

    public int previousObjIndex() {
        return currentObjIndex() == 0 ? objs.size() - 1 : currentObjIndex() - 1;
    }

    public int currentMoveIndex() {
        return moveIndex;
    }

    public int totalMoves() {
        return moves.size();
    }

    public int totalObjs() {
        return objs.size();
    }
}
