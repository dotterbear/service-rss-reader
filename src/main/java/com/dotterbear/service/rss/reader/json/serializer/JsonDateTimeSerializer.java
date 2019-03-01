package com.dotterbear.service.rss.reader.json.serializer;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonDateTimeSerializer extends JsonSerializer<Date> {

  @Value("${com.dotterbear.rss.reader.serializer.date-time}")
  private String dateFormat;

  @Override
  public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider arg2)
      throws IOException {
    String format = DateTimeFormatter.ofPattern(dateFormat).withZone(ZoneOffset.UTC)
        .format(date.toInstant());
    jsonGenerator.writeString(format);
  }

}
