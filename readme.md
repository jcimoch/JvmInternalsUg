###JVM Internals

##Zad1

How to run
```java
mvn clean install
mvn exec:java
```

* Aplikacje javy przy starcie mają ustalony lecz ograniczony dostęp do pamięci,
która jest podzielona na dwie strefy
    - Heap - do przechowywania aktualnych zasobów
    - Permgen - do trzymania załadowanych klas

* Przepełnienie pamięci powoduje wyczerpanie dostępnej strefy Heap poprzez
nieskończone dorzucanie nowych obiektów do ArrayList nie dając tym samym możliwości zadziałania GC.
