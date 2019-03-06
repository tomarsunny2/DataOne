package com.json.transformer.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author sgtomar This Class will Serialize Data in Sorting order
 */
public class SortingSerializer extends StdSerializer<Request> {

    public SortingSerializer(Class<Request> t) {
        super(t);
    }

    @Override
    public void serialize(Request request, JsonGenerator gen, SerializerProvider sp) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(request);
        Map<String, Object> map = new TreeMap<String, Object>();
        map = mapper.readValue(json, new TypeReference<TreeMap<String, Object>>() {
        });
        json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);

        gen.writeRaw(json);

    }

}
