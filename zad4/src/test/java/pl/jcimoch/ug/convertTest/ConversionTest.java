package pl.jcimoch.ug.convertTest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.glassfish.gmbal.Description;
import org.junit.Test;
import pl.jcimoch.ug.PojoToJsonConverter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Jarek on 16.03.2016.
 */
public class ConversionTest {
    @Test
    @Description("JSON should have a valid format")
    public void validJsonTest() throws NoSuchMethodException, InvocationTargetException {
        PojoToJsonConverter jsonConverter = new PojoToJsonConverter();
        TestClassToConvert testObject = new TestClassToConvert();
        assert (isValidJSON(jsonConverter.convert(testObject)));
    }

    public boolean isValidJSON(final String json) {
        boolean valid = false;
        try {
            final JsonParser parser = new ObjectMapper().getJsonFactory()
                    .createJsonParser(json);
            while (parser.nextToken() != null) {
            }
            valid = true;
        } catch (JsonParseException jpe) {
            jpe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return valid;
    }

    @Test
    @Description("Produced JSON after parsing again to Java object should be a correct instance of a specyfic Class")
    public void isCorrectlyParsedTest() throws IOException, NoSuchMethodException, InvocationTargetException {
        PojoToJsonConverter jsonConverter = new PojoToJsonConverter();
        TestClassToConvert testObject = new TestClassToConvert();


        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = jsonConverter.convert(testObject);
        System.out.println(jsonInString);
        try {
            TestClassToConvert t = mapper.readValue(jsonInString, TestClassToConvert.class);
            assert (t instanceof TestClassToConvert);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
