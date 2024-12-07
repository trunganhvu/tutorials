package org.anhvt.springbootmasksensitivelogannotationsecurity.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MaskingDataSerializer extends StdSerializer<Object> implements ContextualSerializer {

	private static final long serialVersionUID = -92567324427014798L;

	private String[] allowedRoiles;

	protected MaskingDataSerializer(String[] allowedRoles) {
		this();
		this.allowedRoiles = allowedRoles;
	}

	public MaskingDataSerializer() {
		super(Object.class);
	}

	@Override
	public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
		Optional<MaskingData> annotation = Optional.ofNullable(property).map(prop -> prop.getAnnotation(MaskingData.class));
		return new MaskingDataSerializer(annotation.map(MaskingData::allowedRoles).orElse(new String[] { "ADMIN" }));
	}

	@Override
	public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		String data = value.toString();
		
		if (authentication != null) {
			List<String> roles = Arrays.asList(this.allowedRoiles);
			long count = authentication.getAuthorities().stream().filter(r -> roles.contains(r.getAuthority().substring(5))).count();
			
			if (count == 0) {
				data = data.replaceAll(".(?=.{4})", "X");
			}
		}
		
		gen.writeString(data);

	}

}
