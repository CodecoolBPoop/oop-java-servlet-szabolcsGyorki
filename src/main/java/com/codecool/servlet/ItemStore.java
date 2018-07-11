package com.codecool.servlet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

class ItemStore {

    static List<Item> getItemList(HttpSession session) {
        return (ArrayList<Item>)session.getAttribute("itemList");
    }

    static void add(Item item, HttpSession session) {
        List<Item> itemList = (ArrayList<Item>)session.getAttribute("itemList");
        if (itemList == null) {
            itemList = new ArrayList<>();
        }
        itemList.add(item);
        session.setAttribute("itemList", itemList);
    }

    static void remove(Item item, HttpSession session) {
        List<Item> itemList = (ArrayList<Item>)session.getAttribute("itemList");
        itemList.remove(item);
        session.setAttribute("itemList", itemList);
    }

    static boolean listIsEmpty(HttpSession session) {
        return session.getAttribute("itemList") == null;
    }
}
