package testutils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.TimeZone;

public class InvoiceManagerTestUtility {
    private static final ObjectMapper OBJECT_MAPPER;
    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        OBJECT_MAPPER.setTimeZone(TimeZone.getTimeZone("GMT+2:00"));
    }

    /**
     * Defined private constructor.
     */
    private InvoiceManagerTestUtility() {
    }

    /**
     * Can convert a json string to an object specified.
     *
     * @param jsonString       a JSON string.
     * @param resultObjectType specified return object type.
     * @param <T>              return object.
     * @return java object.
     */
    public static <T> T jsonStringToObject(String jsonString, Class<T> resultObjectType) throws Exception {
        try {
            return OBJECT_MAPPER.readValue(jsonString, resultObjectType);
        } catch (Exception e) {
            String errorMessage = String.format("Unable to convert json String to object [%s]. Caught a %s. %s", resultObjectType.getName(), e.getClass().getCanonicalName(), e.getMessage());
            throw new Exception(errorMessage, e);
        }
    }

    public static String objectToJsonString(Object object) throws Exception {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException var3) {
            String errorMessage = String.format("Unable to convert class [%s] to json String. Caught a %s. %s", object.getClass().getCanonicalName(), var3.getClass().getCanonicalName(), var3.getMessage());
            throw new Exception(errorMessage, var3);
        }
    }

    public static <T> T jsonFileToObject(String pathToJsonFile, Class<T> resultObjectType) throws Exception {
        String json = readFile(pathToJsonFile);
        return jsonStringToObject(json, resultObjectType);
    }

    public static String readFile(String path) throws Exception {
        InputStream inputStream = null;
        try {
            inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(path);

            /*Added this check to improve the error message, a NullPointer from here is not a great way to convey a resource not found error*/
            if (inputStream == null) {
                String errorMessage = String.format("Resource could not be found in path [%s]. InputStream is null. Please validate the path. Validate that the file is in the resources folder.", path);
                throw new Exception(errorMessage);
            }
            return IOUtils.toString(inputStream);
        } catch (IOException e) {
            String errorMessage = String.format("Unable to readFile from path [%s]. Caught a %s. %s", path, e.getClass().getCanonicalName(), e.getMessage());
            throw new Exception(errorMessage, e);
        } finally {
            closeInputStream(inputStream);
        }
    }

    private static void closeInputStream(final InputStream aInputStream) {
        try {
            if (aInputStream != null) {
                aInputStream.close();
            }
        } catch (IOException ignored) {
            //ignore...
        }
    }
}
