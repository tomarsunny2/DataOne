package com.json.transformer.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 *
 * @author sgtomar This Class will Serialize Flatten Data
 */
public class FlattenSerializer extends StdSerializer<Request> {

    public FlattenSerializer(Class<Request> t) {
        super(t);
    }

    @Override
    public void serialize(Request request, JsonGenerator gen, SerializerProvider sp) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("fruit", request.getFruit());
        gen.writeStringField("animal", request.getAnimal());
        String collect = request.getCityList().stream().collect(Collectors.joining(","));
        gen.writeStringField("city-list", collect);
        gen.writeEndObject();
    }

}
