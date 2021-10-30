package ru.netology.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TripTest {


    @Test
    public void shouldCompareTo() {
        Trip berlinMoscow = new Trip(1, 20000, "BER", "БКВ", 150);
        Trip amsterdamMoscow = new Trip(2, 25000, "AMS", "БКВ", 160);
        Trip parisBarcelona = new Trip(3, 1000, "LBG", "BCN", 40);
        Trip bangkokBogota = new Trip(4, 50000, "BKK", "BOG", 200);

        Trip[] expected = new Trip[]{parisBarcelona, berlinMoscow, amsterdamMoscow, bangkokBogota};
        Trip[] actual = new Trip[]{berlinMoscow, amsterdamMoscow, parisBarcelona, bangkokBogota};

        Arrays.sort(actual);

        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldCompareTo2() {
        Trip berlinMoscow = new Trip(1, 1, "BER", "БКВ", 150);
        Trip amsterdamMoscow = new Trip(2, 2, "AMS", "БКВ", 160);
        Trip parisBarcelona = new Trip(3, 3, "LBG", "BCN", 40);
        Trip bangkokBogota = new Trip(4, 4, "BKK", "BOG", 200);

        Trip[] expected = new Trip[]{berlinMoscow, amsterdamMoscow, parisBarcelona, bangkokBogota};
        Trip[] actual = new Trip[]{berlinMoscow, amsterdamMoscow, parisBarcelona, bangkokBogota};

        Arrays.sort(actual);

        assertArrayEquals(expected, actual);

    }
}