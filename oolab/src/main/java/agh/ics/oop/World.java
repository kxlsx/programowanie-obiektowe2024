package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.OptionsParser;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");
        run(OptionsParser.parse(args));
        System.out.println("system zakończył działanie");
    }

//    // Wersja 1
//    static void run() {
//        System.out.println("zwierzak idzie do przodu");
//    }

//    // Wersja 2
//    static void run(String[] args) {
//        System.out.println("zwierzak idzie do przodu");
//        if(args.length == 0) return;
//        System.out.println(args[0]);
//        for(int i = 1; i < args.length; i++) {
//            System.out.println(", " + args[i]);
//        }
//    }

//    // Wersja 3
//    static void run(String[] args) {
//        for(String arg : args) {
//            interpret_and_print(arg);
//        }
//    }
//
//    static void interpret_and_print(String arg) {
//        String msg = interpret(arg);
//        if(msg != null) {
//            System.out.println("Zwierzak " + msg);
//        }
//    }
//
//    static String interpret(String arg) {
//        return switch (arg) {
//            case "f": yield "idzie do przodu";
//            case "b": yield "idzie do tyłu";
//            case "r": yield "skręca w prawo";
//            case "l": yield "skręca w lewo";
//            default: yield null;
//        };
//    }

    // Wersja 3
    static void run(MoveDirection[] dirs) {
        for(MoveDirection dir : dirs) {
            System.out.println("Zwierzak " + interpret(dir));
        }
    }

    static String interpret(MoveDirection dir) {
        return switch (dir) {
            case FORWARD: yield "idzie do przodu";
            case BACKWARD: yield "idzie do tyłu";
            case RIGHT: yield "skręca w prawo";
            case LEFT: yield "skręca w lewo";
        };
    }
}
