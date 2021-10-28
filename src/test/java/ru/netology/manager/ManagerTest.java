package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Trip;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ManagerTest {
    private ProductRepository repository = new ProductRepository();
    private Manager manager = new Manager(repository);

    Trip BerlinMoscow = new Trip(1, 20000, "BER", "БКВ", 150);
    Trip AmsterdamMoscow = new Trip(2, 27000, "AMS", "БКВ", 170);
    Trip ParisBarcelona = new Trip(3, 1000, "LBG", "BCN", 40);
    Trip BangkokBogota = new Trip(4, 50000, "BKK", "BOG", 200);
    Trip AmsterdamMoscow2 = new Trip(5, 25000, "AMS", "БКВ", 170);
    Trip AmsterdamMoscow3 = new Trip(6, 26000, "AMS", "БКВ", 170);

    //  _________________ add________________________________
    @Test
    public void shouldSaveOneItem() {
        manager.add(BerlinMoscow);
        manager.add(AmsterdamMoscow);
        manager.add(ParisBarcelona);
        manager.add(BangkokBogota);

        Trip[] expected = new Trip[]{BerlinMoscow, AmsterdamMoscow, ParisBarcelona, BangkokBogota};
        Trip[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    //_________________ Match____________________________________
    @Test
    public void shouldMatchPositive() {
        manager.add(BerlinMoscow);
        manager.add(AmsterdamMoscow);
        manager.add(ParisBarcelona);
        manager.add(BangkokBogota);

        boolean expected = true;
        boolean actual = manager.matches(AmsterdamMoscow, "AMS");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldMatchNegative() {
        manager.add(BerlinMoscow);
        manager.add(AmsterdamMoscow);
        manager.add(ParisBarcelona);
        manager.add(BangkokBogota);

        boolean expected = false;
        boolean actual = manager.matches(AmsterdamMoscow, "Art");
        assertEquals(expected, actual);
    }


    //_________________ searchByAirports____________________________________

    @Test
    public void shouldSearchByAirportsLength4Result1First() {
        manager.add(BerlinMoscow);
        manager.add(AmsterdamMoscow);
        manager.add(ParisBarcelona);
        manager.add(BangkokBogota);

        Trip[] expected = new Trip[]{ParisBarcelona};
        Trip[] actual = manager.searchByAirports("LBG", "BCN");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAirportsLength4Result1Second() {
        manager.add(BerlinMoscow);
        manager.add(AmsterdamMoscow);
        manager.add(ParisBarcelona);
        manager.add(BangkokBogota);

        Trip[] expected = new Trip[]{AmsterdamMoscow};
        Trip[] actual = manager.searchByAirports("AMS", "БКВ");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAirportsLength4NoResultOneKeywordNotMatch() {
        manager.add(BerlinMoscow);
        manager.add(AmsterdamMoscow);
        manager.add(ParisBarcelona);
        manager.add(BangkokBogota);

        Trip[] expected = new Trip[]{};
        Trip[] actual = manager.searchByAirports("AMS", "fgf");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAirportsLength4NoResultBothKeywordNotMatch() {
        manager.add(BerlinMoscow);
        manager.add(AmsterdamMoscow);
        manager.add(ParisBarcelona);
        manager.add(BangkokBogota);

        Trip[] expected = new Trip[]{};
        Trip[] actual = manager.searchByAirports("555", "fgf");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAirportsLength6Result3() {
        manager.add(BerlinMoscow);
        manager.add(AmsterdamMoscow);
        manager.add(ParisBarcelona);
        manager.add(BangkokBogota);
        manager.add(AmsterdamMoscow2);
        manager.add(AmsterdamMoscow3);

        Trip[] expected = new Trip[]{AmsterdamMoscow2, AmsterdamMoscow3, AmsterdamMoscow};
        Trip[] actual = manager.searchByAirports("AMS", "БКВ");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAirportsLength5Result2Double() {
        manager.add(BerlinMoscow);
        manager.add(AmsterdamMoscow);
        manager.add(ParisBarcelona);
        manager.add(BangkokBogota);
        manager.add(AmsterdamMoscow);

        Trip[] expected = new Trip[]{AmsterdamMoscow, AmsterdamMoscow};
        Trip[] actual = manager.searchByAirports("AMS", "БКВ");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAirportsLength1Result1() {

        manager.add(AmsterdamMoscow);


        Trip[] expected = new Trip[]{AmsterdamMoscow};
        Trip[] actual = manager.searchByAirports("AMS", "БКВ");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAirportsLength1NoResult2() {

        manager.add(AmsterdamMoscow);


        Trip[] expected = new Trip[]{};
        Trip[] actual = manager.searchByAirports("dd", "БКВ");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAirportsLength1NoResult1() {

        manager.add(AmsterdamMoscow);


        Trip[] expected = new Trip[]{};
        Trip[] actual = manager.searchByAirports("dd", "ss");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAirportsLength0NoResult() {

        Trip[] expected = new Trip[]{};
        Trip[] actual = manager.searchByAirports("dd", "ss");
        assertArrayEquals(expected, actual);
    }
}