package io.nzbee.exceptions;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.nzbee.ErrorKeys;
import java.io.IOException;
import java.util.Map;

/**
 * Created by hardik on 03/11/17.
 */
public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {

    private static final long serialVersionUID = 1L;

	public CustomOauthExceptionSerializer() {
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("code", ErrorKeys.userIsDisabled);
        jsonGenerator.writeObjectField("description", value.getMessage());
        if (value.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                jsonGenerator.writeStringField(key, add);
            }
        }
        jsonGenerator.writeEndObject();
    }
}
