package com.example.shop.service;

import org.springframework.stereotype.Service;
import com.example.shop.pojo.Human;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TaskService {

    private static final Human[] HUMANS = new Human[]{
            new Human("Марк", 37),
            new Human("Джон", 16),
            new Human("Алекс", 43)
    };

    public List<Human> getHumans() {
        return new ArrayList<>(List.of(HUMANS));
    }

    public List<Human> getHumansByAge(int from, int to) {
        return Arrays
                .stream(HUMANS)
                .filter(human/*human берет в себя значение из массива*/ -> human.getAge() >= from && human.getAge() <= to)
                .toList();
    }
}

