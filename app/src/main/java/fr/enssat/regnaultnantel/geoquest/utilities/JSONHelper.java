package fr.enssat.regnaultnantel.geoquest.utilities;

import android.util.Log;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.enssat.regnaultnantel.geoquest.exceptions.JSONProcessingException;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.IOException;
import java.io.InputStream;

public class JSONHelper {

    private static final String LOGGER_TAG = JSONHelper.class.getName();

    private static ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setSerializationInclusion(Include.NON_NULL);
        MAPPER.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
    }

    public static String toJSON(Object object) throws JSONProcessingException {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            Log.e(LOGGER_TAG, ExceptionUtils.getStackTrace(e));
            throw new JSONProcessingException("Error while marshalling " + object.getClass() + " to JSON string", e);
        }
    }

    public static <T> T fromJSON(String jsonString, Class<T> valueType) throws JSONProcessingException {
        try {
            return MAPPER.readValue(jsonString, valueType);
        } catch (IOException e) {
            Log.e(LOGGER_TAG, ExceptionUtils.getStackTrace(e));
            throw new JSONProcessingException("Error while unmarshalling " + valueType, e);
        }
    }

    public static <T> T fromJSON(InputStream jsonStream, Class<T> valueType) throws JSONProcessingException {
        try {
            return MAPPER.readValue(jsonStream, valueType);
        } catch (IOException e) {
            Log.e(LOGGER_TAG, ExceptionUtils.getStackTrace(e));
            throw new JSONProcessingException("Error while unmarshalling " + valueType, e);
        }
    }
}
