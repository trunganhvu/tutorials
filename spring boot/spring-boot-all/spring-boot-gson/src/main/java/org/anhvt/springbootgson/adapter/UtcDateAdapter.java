package org.anhvt.springbootgson.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.anhvt.springbootgson.context.RequestTimeZoneHolder;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class UtcDateAdapter extends TypeAdapter<Date> {
    private static final DateTimeFormatter ISO_FMT =
            DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneOffset.UTC);

    @Override
    public void write(JsonWriter out, Date value) throws IOException {
        System.out.println("[DEBUG] Gson writing date: " + value);
        if (value == null) {
            out.nullValue();
            return;
        }
        // Luôn serialize theo UTC chuẩn ISO 8601
        out.value(ISO_FMT.format(value.toInstant()));
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        String str = in.nextString();
        if (str == null || str.isEmpty()) return null;

        String tz = RequestTimeZoneHolder.get();
        ZoneId zone = ZoneId.of(tz);

        try {
            // Có offset sẵn
            OffsetDateTime odt = OffsetDateTime.parse(str, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            return Date.from(odt.toInstant());
        } catch (DateTimeParseException e1) {
            try {
                // Không có offset → gắn timezone từ request
                LocalDateTime ldt = LocalDateTime.parse(str, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                Instant instant = ldt.atZone(zone).toInstant();
                return Date.from(instant);
            } catch (DateTimeParseException e2) {
                // Chỉ có yyyy-MM-dd
                LocalDate ld = LocalDate.parse(str, DateTimeFormatter.ISO_LOCAL_DATE);
                Instant instant = ld.atStartOfDay(zone).toInstant();
                return Date.from(instant);
            }
        }
    }
}
