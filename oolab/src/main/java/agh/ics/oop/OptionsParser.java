package agh.ics.oop;

import java.util.List;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.Arrays;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] args) throws IllegalArgumentException {
        List<MoveDirection> dirs = new ArrayList<MoveDirection>();
        for(String arg : args) {
            switch (arg) {
                case "f": dirs.add(MoveDirection.FORWARD); break;
                case "b": dirs.add(MoveDirection.BACKWARD); break;
                case "l": dirs.add(MoveDirection.LEFT); break;
                case "r": dirs.add(MoveDirection.RIGHT); break;
                default:
                    throw new IllegalArgumentException(arg + "is not legal move specification.");
            };
        }
        return dirs;
    }
}
