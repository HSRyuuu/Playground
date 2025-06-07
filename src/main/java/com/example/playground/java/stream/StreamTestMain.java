package com.example.playground.java.stream;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public class StreamTestMain {

    public static void main(String[] args) {
        Item[] arr = new Item[]{
                new Item(100, "ITEM1"),
                new Item(200, "ITEM2"),
                new Item(300, "ITEM3"),
                new Item(400, "ITEM4"),
                new Item(500, "ITEM5"),
                new Item(600, "ITEM6"),
                new Item(700, "ITEM7"),
        };
        List<Item> items = new ArrayList<>(List.of(arr));

        List<Integer> list = items.stream()
                .filter(i -> Item.log(i).getCount() > 300)
                .map(i -> Item.log(i).getCount())
                .limit(2)
                .toList();
        System.out.println(list);

        System.out.println("######");

        List<Integer> list2 = items.stream()
                .filter(i -> Item.log(i).getCount() > 300)
                .map(i -> Item.log(i).getCount())
                .sorted((a,b) -> b-a)
                .limit(2)
                .toList();
        System.out.println(list2);

    }

    @AllArgsConstructor
    @Getter
    static class Item{
        private int count;
        private String name;

        public static Item log(Item item){
            System.out.println(item);
            return item;
        }

        @Override
        public String toString(){
            return "(" + count + ", " + name + ")";
        }
    }
}
