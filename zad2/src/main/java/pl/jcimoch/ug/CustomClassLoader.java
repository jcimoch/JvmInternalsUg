package pl.jcimoch.ug;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Jarek on 02.03.2016.
 */
public class CustomClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.endsWith("Dog")) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = null;
        try {
            bytes = arrayClass();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class<?> result = defineClass(name, bytes, 0, bytes.length);
        return result;
    }

    private byte[] arrayClass() throws IOException {
        URLConnection connection = new URL("http://localhost:8080/Dog.class").openConnection();
        InputStream input = connection.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int data = input.read();
        while (data != -1) {
            buffer.write(data);
            data = input.read();
        }
        input.close();
        return buffer.toByteArray();
    }


}
