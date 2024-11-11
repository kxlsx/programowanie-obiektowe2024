package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TextMap implements WorldNumberPositionMap<String, Integer>{
    private final ArrayList<String> texts;
    private final Map<String, Integer> textIndexes;

    public TextMap() {
        this.texts = new ArrayList<>();
        this.textIndexes = new HashMap<>();
    }

    public boolean place(String text) {
        if(textIndexes.get(text) != null) {
            return false;
        }
        textIndexes.put(text, textCount());
        texts.add(text);
        return true;
    }

    public void move(String text, MoveDirection direction) {
        Integer pos = textIndexes.get(text);
        if(pos == null) {
            return;
        }

        int vector = switch(direction) {
            case FORWARD, RIGHT-> 1;
            case BACKWARD, LEFT -> -1;
        };

        Integer newPos = pos + vector;
        if(!canMoveTo(newPos)) {
            return;
        }

        String otherText = objectAt(newPos);
        texts.set(newPos, text);
        textIndexes.put(text, newPos);

        texts.set(pos, otherText);
        textIndexes.put(otherText, pos);
    }

    public boolean isOccupied(Integer position) {
        return position < textCount();
    }

    public String objectAt(Integer position) {
        if(!isOccupied(position)) {
            return null;
        }
        return texts.get(position);
    }

    public boolean canMoveTo(Integer position) {
        return position >= 0 && position < textCount();
    }

    public int textCount(){
        return this.texts.size();
    }

    @Override
    public String toString() {
        String res = "";
        for(String text : texts) {
            res = res + text + ";";
        }
        return res;
    }
}
