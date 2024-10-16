package agh.ics.oop.model;

import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        MoveDirection[] dirs = new MoveDirection[args.length];
        int last = 0;
        for(String arg : args) {
            switch (arg) {
                case "f": dirs[last++] = MoveDirection.FORWARD; break;
                case "b": dirs[last++] = MoveDirection.BACKWARD; break;
                case "l": dirs[last++] = MoveDirection.LEFT; break;
                case "r": dirs[last++] = MoveDirection.RIGHT; break;
                default: break;
            };
        }
        return Arrays.copyOfRange(dirs, 0, last);
    }
}
