package ru.netology.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TripTest {


    @Test
    public void shouldCompareTo() {
        Trip BerlinMoscow = new Trip(1, 20000, "BER", "БКВ", 150);
        Trip AmsterdamMoscow = new Trip(2, 25000, "AMS", "БКВ", 160);
        Trip ParisBarcelona = new Trip(3, 1000, "LBG", "BCN", 40);
        Trip BangkokBogota = new Trip(4, 50000, "BKK", "BOG", 200);

        Trip[] expected = new Trip[]{ParisBarcelona, BerlinMoscow, AmsterdamMoscow, BangkokBogota};
        Trip[] actual = new Trip[]{BerlinMoscow, AmsterdamMoscow, ParisBarcelona, BangkokBogota};

        Arrays.sort(actual);

        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldCompareTo2() {
        Trip BerlinMoscow = new Trip(1, 1, "BER", "БКВ", 150);
        Trip AmsterdamMoscow = new Trip(2, 2, "AMS", "БКВ", 160);
        Trip ParisBarcelona = new Trip(3, 3, "LBG", "BCN", 40);
        Trip BangkokBogota = new Trip(4, 4, "BKK", "BOG", 200);

        Trip[] expected = new Trip[]{BerlinMoscow, AmsterdamMoscow, ParisBarcelona, BangkokBogota};
        Trip[] actual = new Trip[]{BerlinMoscow, AmsterdamMoscow, ParisBarcelona, BangkokBogota};

        Arrays.sort(actual);

        assertArrayEquals(expected, actual);

    }
}