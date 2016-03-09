package pl.jcimoch.ug;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Benchmark {


    private Person testPerson;

    private final int repetitions = 20000000;
    private long startTime;
    private long endTime;
    private int tempIntRead;
    private Double tempDoubleRead;

    public Benchmark() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        this.testPerson = new Person();
        for (int i = 0; i <= 10; i++) {
            for (MethodType t : MethodType.values()) {
                this.runBenchmark(t);
            }
        }
    }

    /**
     * @param type
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private void runBenchmark(MethodType type) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        this.startTime = System.nanoTime();
        switch (type) {
            case SimpleRead: {
                for (int i = 0; i < this.repetitions; i++) {
                    this.tempIntRead = this.testPerson.age;
                }
            }
            break;
            case SimpleReferenceRead: {
                for (int i = 0; i < this.repetitions; i++) {
                    this.tempDoubleRead = this.testPerson.height;
                }
            }
            break;
            case SimpleWrite: {
                this.startTime = System.nanoTime();
                for (int i = 0; i < this.repetitions; i++) {
                    this.testPerson.age = i;
                }
            }
            break;
            case SimpleReferenceWrite: {
                for (int i = 0; i < this.repetitions; i++) {
                    this.testPerson.height = new Double(182);
                }
            }
            break;
            case SimpleReflectionRead: {
                Field f = this.testPerson.getClass().getField("age");
                for (int i = 0; i < this.repetitions; i++) {
                    tempIntRead = f.getInt(this.testPerson);
                }
            }
            break;
            case SimpleReflectionReferenceRead: {
                Field f = this.testPerson.getClass().getField("height");
                for (int i = 0; i < this.repetitions; i++) {
                    tempDoubleRead = (Double) f.get(this.testPerson);
                }
            }
            case SimpleReflectionWrite: {
                Field f = this.testPerson.getClass().getField("age");
                for (int i = 0; i < this.repetitions; i++) {
                    f.setInt(this.testPerson, 10);
                }
            }
            break;
            case SimpleReflectionReferenceWrite: {
                Field f = this.testPerson.getClass().getField("height");
                for (int i = 0; i < this.repetitions; i++) {
                    f.set(this.testPerson, 182.0);
                }
            }
            break;
            case SimpleMethod: {
                for (int i = 0; i < this.repetitions; i++) {
                    testPerson.grow(10);
                }
            }
            break;
            case SimpleReflectionMethod: {
                Method m = testPerson.getClass().getMethod("grow", int.class);
                for (int i = 0; i < this.repetitions; i++) {
                    m.invoke(testPerson, 10);
                }
            }
            break;
        }
        endTime = System.nanoTime() - startTime;

        BenchmarkResults.results.get(type).add(endTime);

    }

}
