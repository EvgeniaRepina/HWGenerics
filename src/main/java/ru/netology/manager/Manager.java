package ru.netology.manager;

import ru.netology.domain.Trip;
import ru.netology.repository.ProductRepository;

import java.util.Arrays;

public class Manager {

    private ProductRepository repository = new ProductRepository();


    //    ____________ конструктор________________________
    public Manager(ProductRepository repository) {
        this.repository = repository;
    }

//____________________добавление товаров_______

    public void add(Trip item) {
        repository.save(item);
    }

// ______________ поиск______________________________


    public Trip[] searchByAirports(String arrivalAirport, String departureAirport) {
        Trip[] result = new Trip[0];
        for (Trip trip : repository.findAll()) {
            if (matches(trip, arrivalAirport) && matches(trip, departureAirport)) {
                Trip[] tmp = new Trip[result.length + 1];

                System.arraycopy(result, 0, tmp, 0, result.length);

                tmp[tmp.length - 1] = trip;
                result = tmp;
            }
        }
        Arrays.sort(result); // лучше отсортировать один раз перед return чем на каждой итерации цикла
        return result;
    }

    public boolean matches(Trip trip, String search) {

//        if (product instanceof Book) { // если в параметре product лежит объект класса Book
//            Trip trip = new Trip(); // положим его в переменную типа Book чтобы пользоваться методами класса Book
        if (trip.getArrivalAirport().contains(search)) { // проверим есть ли поисковое слово в данных
            return true;
        }
        if (trip.getDepartureAirport().contains(search)) {
            return true;
        }
        return false;


    }
}
