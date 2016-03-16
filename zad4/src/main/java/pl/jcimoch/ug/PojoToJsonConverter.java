package pl.jcimoch.ug;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.function.BiConsumer;

/**
 * Created by Jarek on 13.03.2016.
 */
public class PojoToJsonConverter {

    private final StringBuilder mainStringBuilder = new StringBuilder();

    public PojoToJsonConverter() {
    }


    public String convert(Object objToConvert) throws NoSuchMethodException, InvocationTargetException {
        this.mainStringBuilder.append("{ \n");
        Field[] objectFieldsList = objToConvert.getClass().getDeclaredFields();
        int counter = objectFieldsList.length;

        for (Field f : objectFieldsList) {
            counter--;
            f.setAccessible(true);
            mainStringBuilder.append("\"" + f.getName() + "\": ");
            try {
                Object fieldValue = f.get(objToConvert);
                if (f.getType().getSimpleName().equals("ArrayList")) {
                    Method toArray = ArrayList.class.getDeclaredMethod("toArray");
                    traverseArray(toArray.invoke(fieldValue));
                } else if (fieldValue.getClass().isArray()) {
                    traverseArray(fieldValue);
                } else if (f.getType().isEnum()) {
                    mainStringBuilder.append("\"" + fieldValue + "\"");
                } else if (!SupportedTypes.isSupported(f.getType().getSimpleName())) {
                    convert(fieldValue);
                } else if (fieldValue == null) {
                    mainStringBuilder.append("null");
                } else if (fieldValue.getClass() == Character.class || fieldValue.getClass() == String.class) {
                    mainStringBuilder.append("\"" + fieldValue + "\"");
                } else {
                    mainStringBuilder.append(fieldValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (counter == 0) {
                mainStringBuilder.append("\n");
            } else {
                mainStringBuilder.append(",\n");
            }
        }

        this.mainStringBuilder.append("}");
        return mainStringBuilder.toString();

    }


    public void traverseArray(Object someArray) throws NoSuchMethodException, InvocationTargetException {

        BiConsumer<Integer, Object> commaAppender = (Integer x, Object y) -> {
            if (x != Array.getLength(someArray) - 1) {
                mainStringBuilder.append(",");
            }
        };

        mainStringBuilder.append("[");
        if (SupportedTypes.isSupported(someArray.getClass().getSimpleName().replace("[]", "")) && someArray != null) {
            for (int i = 0; i < Array.getLength(someArray); i++) {
                Object obj = Array.get(someArray, i);
                if (obj != null && obj.getClass().isArray()) {
                    traverseArray(obj);
                } else if (obj != null && obj.getClass() == Character.class || obj != null && obj.getClass() == String.class) {
                    mainStringBuilder.append("\"" + obj + "\"");
                } else {
                    mainStringBuilder.append(obj);
                }
                commaAppender.accept(i, obj);
            }
        } else {
            for (int i = 0; i < Array.getLength(someArray); i++) {
                Object o = Array.get(someArray, i);
                if (o != null && o.getClass().isArray()) {
                    traverseArray(someArray);
                } else if (o != null && SupportedTypes.isSupported(o.getClass().getSimpleName().replace("[]", ""))) {
                    mainStringBuilder.append(o);
                } else if (o != null) {
                    convert(o);
                } else {
                    mainStringBuilder.append("{}");
                }
                commaAppender.accept(i, o);
            }
        }
        mainStringBuilder.append("]");
    }


}
