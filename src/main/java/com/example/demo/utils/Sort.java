package com.example.demo.utils;

import org.springframework.stereotype.Component;

@Component
public class Sort {
    private int number;
    private int[] items;

    public Sort(int number, int[] items) {
        this.number = number;
        this.items = items;
    }

    public int[] getSortItems() {
        return items;
    }

}
