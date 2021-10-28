package ru.netology.repository;

import ru.netology.domain.Trip;

public class ProductRepository {
    private Trip[] items = new Trip[0];


    public void save(Trip item) {
        int length = items.length + 1;
        Trip[] tmp = new Trip[length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        items = tmp;
    }

    public Trip[] findAll() {
        return items;
    }

    public Trip findById(int id) {
        for (Trip item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removeById(int id) {
        int length = items.length - 1;
        Trip[] tmp = new Trip[length];
        int index = 0;
        for (Trip item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;
    }
}
