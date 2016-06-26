package hr.tvz.rome.model.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import hr.tvz.rome.utilities.DateTimeBuilder;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Marko on 26.6.2016..
 */
public class JsonDateDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jsonparser,
                            DeserializationContext deserializationcontext) throws IOException {
        return DateTimeBuilder.fromDateTimeString(jsonparser.getText()).buildDate();

    }

}
