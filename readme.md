###JVM Internals

##Zad1

How to run:

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

##Zad2

How to run:

```java
mvn compile
mvn exec:exec@runServer
mvn exec:exec
```
* Statyczny serwer plików z przykładową klasą został wykonany w node.js.

##Zad 3

How to run:

```java
mvn compile
mvn exec:exec
```
|    CPU              |    Intel Core i5 2.5Ghz    |
|---------------------|----------------------------|
|    RAM              |    16GB DDR3               |
|    DYSK             |    SSD 256GB               |
|    OS               |    OSX Yosemite 64bit      |
|    Java version     |    1.8.0_73                |

Dokonałem 10 niezależnych pomiarów z których wyciągnąłem średnią. Wykres „Fresh Run” zawiera czasy pierwszego wywołania natomiast „Several Runs” to dodatkowa średnia z kolejnych 10 uruchomień wszystkich testów.  Co ciekawe przy kolejnych uruchomieniach, dzięki optymalizacjom JIT’a czas wywoływania metody poprzez refeleksje zrównał się z typowym wywołaniem. Można przypuścić że kompilator obserwując jednakowe zachowanie tegoż kawałka kodu uznał że, może dokonać uproszczenia i zastosować podobny mechanizm do bezpośredniego wywołania metody. Czas jest mierzony w milisekundach. Referencyjny odczyt oraz prosty zapis wahał się w granicy 0-1ms dlatego jest niewidoczny na wykresie.

![alt text](https://dl.dropboxusercontent.com/u/15067146/freshrun.PNG "Fresh Run")
![alt text](https://dl.dropboxusercontent.com/u/15067146/after10.PNG "After 10")

Po nałożeniu nasię obu poprzednich wykresów widzimy efekt dynamicznej optymalizacji. W niektórych przypadkach czas się nieznacznie pogorszył jednak może to wynikać z faktu że wykresy pochodzą z obu różnych uruchomień programu. Możliwe też, że JIT nie dokonał poprawnej optymalizacji. 

JIT Optimalization:
![alt text](https://dl.dropboxusercontent.com/u/15067146/JITOptymalization.PNG "JIT Optymalization")

##Zad 4

How to run:

```java
mvn clean install
mvn test
```

Prosty konwerter obiektów POJO do formatu JSON. Konwerter obsuguje typy proste, tablice wielowymiarowe typów złożonych, oraz jednowymiarowe ArrayList'y. 

