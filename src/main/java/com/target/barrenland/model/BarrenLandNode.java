package com.target.barrenland.model;

import java.util.LinkedList;

public class BarrenLandNode {
    private BarrenLandNode prev;
    private BarrenLandNode next;
    private int x;
    private int y;
    private int value;

    public BarrenLandNode(BarrenLandNode prev, BarrenLandNode next, int x, int y, int value) {
        this.prev = prev;
        this.next = next;
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public BarrenLandNode getPrev() {
        return prev;
    }

    public void setPrev(BarrenLandNode prev) {
        this.prev = prev;
    }

    public BarrenLandNode getNext() {
        return next;
    }

    public void setNext(BarrenLandNode next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BarrenLandNode() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void delete() {
        prev.setNext(next);
        next.setPrev(prev);
    }
}
