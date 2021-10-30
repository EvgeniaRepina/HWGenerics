package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Trip;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ManagerTest {
    private ProductRepository repository = new ProductRepository();
    private Manager manager = new Manager(repository);

    Trip berlinMoscow = new Trip(1, 20000, "BER", "БКВ", 150);
    Trip amsterdamMoscow = new Trip(2, 27000, "AMS", "БКВ", 170);
    Trip parisBarcelona = new Trip(3, 1000, "LBG", "BCN", 40);
    Trip bangkokBogota = new Trip(4, 50000, "BKK", "BOG", 200);
    Trip amsterdamMoscow2 = new Trip(5, 25000, "AMS", "БКВ", 170);
    Trip amsterdamMoscow3 = new Trip(6, 26000, "AMS", "БКВ", 170);

    //  _________________ add________________________________
    @Test
    public void shouldSaveOneItem() {
        manager.add(berlinMoscow);
        manager.add(amsterdamMoscow);
        manager.add(parisBarcelona);
        manager.add(bangkokBogota);

        Trip[] expected = new Trip[]{berlinMoscow, amsterdamMoscow, parisBarcelona, bangkokBogota};
        Trip[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    //_________________ Match____________________________________
    @Test
    public void shouldMatchPositive() {
        manager.add(berlinMoscow);
        manager.add(amsterdamMoscow);
        manager.add(parisBarcelona);
        manager.add(bangkokBogota);

        boolean expected = true;
        boolean actual = manager.matches(amsterdamMoscow, "AMS");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldMatchNegative() {
        manager.add(berlinMoscow);
        manager.add(amsterdamMoscow);
        manager.add(parisBarcelona);
        manager.add(bangkokBogota);

        boolean expected = false;
        boolean actual = manager.matches(amsterdamMoscow, "Art");
        assertEquals(expected, actual);
    }


    //_________________ searchByAirports____________________________________

    @Test
    public void shouldSearchByAirportsLength4Result1First() {
        manager.add(berlinMoscow);
        manager.add(amsterdamMoscow);
        manager.add(parisBarcelona);
        manager.add(bangkokBogota);

        Trip[] expected = new Trip[]{parisBarcelona};
        Trip[] actual = manager.searchByAirports("LBG", "BCN");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAirportsLength4Result1Second() {
        manager.add(berlinMoscow);
        manager.add(amsterdamMoscow);
        manager.add(parisBarcelona);
        manager.add(bangkokBogota);

        Trip[] expected = new Trip[]{amsterdamMoscow};
        Trip[] actual = manager.searchByAirports("AMS", "БКВ");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAirportsLength4NoResultOneKeywordNotMatch() {
        manager.add(berlinMoscow);
        manager.add(amsterdamMoscow);
        manager.add(parisBarcelona);
        manager.add(bangkokBogota);

        Trip[] expected = new Trip[]{};
        Trip[] actual = manager.searchByAirports("AMS", "fgf");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAirportsLength4NoResultBothKeywordNotMatch() {
        manager.add(berlinMoscow);
        manager.add(amsterdamMoscow);
        manager.add(parisBarcelona);
        manager.add(bangkokBogota);

        Trip[] expected = new Trip[]{};
        Trip[] actual = manager.searchByAirports("555", "fgf");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAirportsLength6Result3() {
        manager.add(berlinMoscow);
        manager.add(amsterdamMoscow);
        manager.add(parisBarcelona);
        manager.add(bangkokBogota);
        manager.add(amsterdamMoscow2);
        manager.add(amsterdamMoscow3);

        Trip[] expected = new Trip[]{amsterdamMoscow2, amsterdamMoscow3, amsterdamMoscow};
        Trip[] actual = manager.searchByAirports("AMS", "БКВ");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAirportsLength5Result2Double() {
        manager.add(berlinMoscow);
        manager.add(amsterdamMoscow);
        manager.add(parisBarcelona);
        manager.add(bangkokBogota);
        manager.add(amsterdamMoscow);

        Trip[] expected = new Trip[]{amsterdamMoscow, amsterdamMoscow};
        Trip[] actual = manager.searchByAirports("AMS", "БКВ");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAirportsLength1Result1() {

        manager.add(amsterdamMoscow);


        Trip[] expected = new Trip[]{amsterdamMoscow};
        Trip[] actual = manager.searchByAirports("AMS", "БКВ");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAirportsLength1NoResult2() {

        manager.add(amsterdamMoscow);


        Trip[] expected = new Trip[]{};
        Trip[] actual = manager.searchByAirports("dd", "БКВ");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAirportsLength1NoResult1() {

        manager.add(amsterdamMoscow);


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