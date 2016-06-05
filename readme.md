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
mvn compile
mvn test
```

Prosty konwerter obiektów POJO do formatu JSON. Konwerter obsuguje typy proste, tablice wielowymiarowe typów złożonych, oraz jednowymiarowe ArrayList'y. 

##Zad 6

How to run:
```java
mvn compile
mvn exec:exec
```

Przykładowy program pokazujący, że SimpleDateFormat nie jest bezpieczny względem operacji wielowątkowych. Najprostszym rozwiązaniem problemu a zaraz chyba najszybszym (wydajnościowo) jest użycie zmiennej ThreadLocal, która spowoduje, że każdy powstały wątek będzie miał swoją instancje pożądanej klasy, tym samym nie doprowadzamy do synchronizacji i wszystkie wątki mogą działać równolegle. Wadą tego rozwiązania jest fakt, że bardzo często chcemy operować na jednej instacji w sposób wielowątkowy, wtedy instacja per wątek nie rozwiąże naszych problemów i najłatwiej a zarazm najmniej wydajnie będzie użyć synchronizacji.

Do zarządzania wątkami użyłem java.util.concurrent.ExecutorService; oraz  java.util.concurrent.Executors;

##Zad 7

W tym zadaniu do stworzenia prostego serwisu REST posłużyłem się frameworkiem http://sparkjava.com/download.html wzorowanym na express.js znanym z node.js

How to run:
```java
mvn compile
mvn assembly:assembly
mvn exec:exec
```

Wchodzimy na http://localhost:4567/hello/100
zmieniając końcowy parametr wpływamy na długość uśpienia wątku.
Odpowiednie logi powinny się pojawić w konsoli. 

##Zad 9

|    CPU              |    Intel Core i7 4770K @ 4.5ghz    |
|---------------------|----------------------------|
|    RAM              |    16GB DDR3               |
|    DYSK             |    SSD 128GB            |
|    OS               |    Windows 10 64      |
|    Java version     |    1.8.0_73                |


Argumenty przekazywane dla każdego testu 
```shell
-Xms1024m -Xmx1024m -Xloggc:gc.log -XX:+PrintGCDateStamps -XX:+PrintGCApplicationStoppedTime
```

Każdy GC został przetestowany na 4 różnych niezależnych testach.
Pierwsze 2 testy zawsze dotyczą sytuacji jednowątkowej. Kolejne wielowątkowej.
Alokacja statyczna oznacza stałą próbę rezerwacji pamięci wielkości 1Mb.
Alokacja dynamiczna różni się losowym zakresem w granicach 1 - 99Mb.
Mimo losowości, wiemy że średni rozkład będzie oscylował w połowie tych wartości, tym samym ilość przerwań jest
większa a końcowa ilość alokacji mniejsza gdyż próbujemy rezerować duże obszary pamieci z dużą częstotliwością.
Ilość przerwań oraz ich czas został przygotowany na podstawie logów ręczną metodą z pomocą edytora.

### Wnioski 
Ciężko zaobserwować szczególne różnice pomiędzy zastosowanymi algorytami na tak prostym teście,
jednak możemy wyróżnić  G1GC oraz ParallelOldGC, które charakteryzują się najmniejszą sumaryczną ilością przerwań programu.
G1GC zdecydowanie gorzej radzi sobie gdy używany jest intensywnie tylko jeden wątek.
Jego przewaga pojawia się przy maksymalnym wykorzystaniu wielu wątków jednocześnie.

| GC Type         |Thread Count|Time |Allocation Count | Allocation Type |Intervals Count | Intervals Total Time - ms | 
|-----------------|--------------|------|------------------|-----------------|-----------------|---------------------------| 
| SerialGC        | 1            | 60s  | 1125735          | static          | 4139            | 1.2403030000000022        | 
| SerialGC        | 1            | 60s  | 23506            | dynamic         | 5037            | 1.234610900000004         | 
| SerialGC        | 8            | 60s  | 1077978          | static          | 4029            | 4.447214100000007         | 
| SerialGC        | 8            | 60s  | 5413             | dynamic         | 1098            | 59.46893719999999         | 

| GC Type         |Thread Count|Time |Allocation Count | Allocation Type |Intervals Count | Intervals Total Time - ms | 
|-----------------|--------------|------|------------------|-----------------|-----------------|---------------------------| 
| ParallelOldGC   | 1            | 60s  | 1231852          | static          | 3636            | 1.035664999999998         | 
| ParallelOldGC   | 1            | 60s  | 22938            | dynamic         | 3853            | 1.3397927999999972        | 
| ParallelOldGC   | 8            | 60s  | 1031772          | static          | 3098            | 6.716371200000009         | 
| ParallelOldGC   | 8            | 60s  | 6798             | dynamic         | 1475            | 58.599057300000084        | 

| GC Type         |Thread Count|Time |Allocation Count | Allocation Type |Intervals Count | Intervals Total Time - ms | 
|-----------------|--------------|------|------------------|-----------------|-----------------|---------------------------| 
| ConcMarkSweepGC | 1            | 60s  | 1145202          | static          | 4211            | 3.225004400000015         | 
| ConcMarkSweepGC | 1            | 60s  | 22710            | dynamic         | 4896            | 3.4961221999999967        | 
| ConcMarkSweepGC | 8            | 60s  | 1056285          | static          | 3947            | 5.568603499999988         | 
| ConcMarkSweepGC | 8            | 60s  | 6641             | dynamic         | 1806            | 59.09474670000003         | 

| GC Type         |Thread Count|Time |Allocation Count | Allocation Type |Intervals Count | Intervals Total Time - ms | 
|-----------------|--------------|------|------------------|-----------------|-----------------|---------------------------| 
| G1GC            | 1            | 60s  | 566833           | static          | 3067            | 26.189230500000026        | 
| G1GC            | 1            | 60s  | 14680            | dynamic         | 2156            | 15.725686399999983        | 
| G1GC            | 8            | 60s  | 596374           | static          | 4700            | 27.4719264                | 
| G1GC            | 8            | 60s  | 13628            | dynamic         | 2465            | 30.364052000000058        | 

##Zad 11

Uwaga: Dane testowe są generowane bez zachowania ich poprawności (np. imię 0abc)

How to run:
```java
mvn test
```

