package com.target.barrenland.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarrenLandNode {
    private BarrenLandNode prev;
    private BarrenLandNode next;
    private int x;
    private int y;
    private int value;
    private boolean counted;

    public boolean hasNext() {
        return this.next != null;
    }

    public void delete() {
        if (prev != null) {
            prev.setNext(next);
        }
        if (next != null) {
            next.setPrev(prev);
        }
    }
}
