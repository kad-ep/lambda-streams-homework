package org.example;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Task2 {

    public static class Book{
        private String name;
        private Integer price;

        public Book(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public static List<Book> generateBooks(int quantity) {
            List<Book> booksList = new ArrayList<>();
            int nameBound;
            int priceBound;
            Random random = new Random();

            for (int i = 0; i < quantity; i++) {
                nameBound = random.nextInt(10 - 3) + 3;
                priceBound = random.nextInt(99 - 1) + 1;
                booksList.add(new Book(RandomStringUtils.random(nameBound, true, true), priceBound));
            }
            return booksList;
        }

        //subtask1 - print all books using method toString (information should be consistent, e.g. “name: Book1, price: 10”)
        @Override
        public String toString() {
            return String.format("name: %s, price: %d", this.name, this.price);
        }

        @Override public boolean equals(Object o) { // If the object is compared with itself then return true
             if (o == this) { return true; }
            /* Check if o is an instance of Complex or not "null instanceof [type]" also returns false */
            if (!(o instanceof Book)) {return false;} // typecast o to Complex so that we can compare data members
            Book c = (Book) o; // Compare the data members and return accordingly
            return name.equals(c.name) && Integer.compare(price, c.price) == 0; }
    }

    public static void main(String[] args) {
        List<Book> booksList = Book.generateBooks(10);

        System.out.println(String.format("books list: %s", booksList));

        //subtask1 - get List<String> of all book names and print them
        List<String> names = booksList
                .stream()
                .map(el -> el.getName())
                .collect(Collectors.toList());
        System.out.println(String.format("books names list: %s", names));

        //get total price of all books and print it

        int totalPrice = booksList
                .stream()
                .map(el -> el.getPrice())
                .reduce(0, (acc, el) -> acc + el);
        System.out.println(String.format("books total price: %d", totalPrice));

        //get any book with “A” or “a” character in name and print its name

        Function<List<Book>, String> getbookWithA = (list) -> {
            List<String> booksWithA = list
                    .stream()
                    .filter(el -> el.getName().toLowerCase().contains("a"))
                    .map(el -> el.getName())
                    .collect(Collectors.toList());

            if (booksWithA.size() == 0) {
                return "No books with \'a\' symbol";
            }
            else {
                return booksWithA.get(new Random().nextInt(booksWithA.size()));
            }
        };

        System.out.println(String.format("any book with “A” or “a” character in name: %s",getbookWithA.apply(booksList)));

        //print book name with highest price

        List highestPrice = booksList
                .stream()
                .max(Comparator.comparing(Book::getPrice))
                .map(el-> el.getName())
                .stream().collect(Collectors.toList());

        System.out.println(String.format("book name with highest price: %s", highestPrice.get(0)));

        //get number of books where name consists of 5 letters

        Function<List<String>, Integer> fivePlusLettersNumber = list -> {
            int result = 0;
            for (String el:list) {
                if (el.length() == 5 ){
                    result++;
                }

            }
            return result;
        };

        System.out.println(String.format("number of books where name consists of 5 letters: %d", fivePlusLettersNumber.apply(names)));

        //get all books except books with price higher then 30

        List higherThan30 = booksList
                .stream()
                .filter(el -> el.getPrice() <= 30)
                .collect(Collectors.toList());

        System.out.println(String.format("all books except books with price higher than 30: %s", higherThan30));

        //sort books by name in desc order, if name equals then sort by price

        Set<String> namesSet = new HashSet<>(names);
        List sortedList;
        if (namesSet.size() < names.size()){
            sortedList = booksList
                    .stream()
                    .sorted(Comparator.comparing(Book::getPrice))
                    .collect(Collectors.toList());
            System.out.println(String.format("sort books by price : %s", sortedList));
        }

        else {
            sortedList = booksList
                    .stream()
                    .sorted(Comparator.comparing(Book::getName))
                    .collect(Collectors.toList());
            System.out.println(String.format("sort books by name : %s", sortedList));
        }


        //Subtask 2
        Book book1 = new Book("myBook", 5);
        AtomicReference<Book> book2 = new AtomicReference<>(new Book("myBook", 5));
        Map<Book, String> mapWithBook = new HashMap<>();
        mapWithBook.put(book1, "Best Library");
        System.out.println(mapWithBook.entrySet());

//        List libName = mapWithBook
//                .entrySet()
//                .stream()
//                .peek(e -> {
//                    book2.equals(book1);
//
//
//                })
//                .collect(Collectors.toList());
//        System.out.println("libName: " + libName);





//        Set bookSet = mapWithBook.entrySet();
//        Set bookKeys = mapWithBook.keySet();
//        Collection valuesCol = mapWithBook.values();

        //el-> el.getName() == book2.getName()
//        Function<Book, Book> checkKey = el -> el.getName() == book2.getName() ? el : false;
//
//        String libName = mapWithBook.get(el-> el.getName() == book2.getName());
        //bookSet.forEach(entry -> entry.get);

//        System.out.println("EntrySet: " + bookSet);
//        System.out.println("KeySet: " + bookKeys);
//        System.out.println(".values: " + valuesCol);








    }

}