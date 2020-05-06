package yuansim;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class JsonUtils {

	public static final ObjectMapper JSON_MAPPER = newObjectMapper(), JSON_MAPPER_WEB = newObjectMapper();

	private static ObjectMapper newObjectMapper() {
		ObjectMapper result = new ObjectMapper();
		result.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		result.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		result.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return result;
	}

	public static ObjectMapper getObjectMapper() {
		return JSON_MAPPER;
	}

	/**
	 * 转化为json串
	 * @param value
	 * @return value == null ? null :
	 */
	public static String writerWithDefaultPrettyPrinter(Object value) {
		try {
			return value == null ? null : JSON_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(value);
		} catch (IOException e) {
			throw new IllegalArgumentException(e); // TIP: 原则上，不对异常包装，这里为什么要包装？因为正常情况不会发生IOException
		}
	}

	/**
	 * 转化为json bytes
	 * @param value
	 * @return value == null ? null :
	 */
	public static byte[] writeValueAsBytes(Object value) {
		try {
			return value == null ? new byte[0] : JSON_MAPPER.writeValueAsBytes(value);
		} catch (IOException e) {
			throw new IllegalArgumentException(e); // TIP: 原则上，不对异常包装，这里为什么要包装？因为正常情况不会发生IOException
		}
	}

	/**
	 * 转化为json串
	 * @param value
	 * @return value == null ? null :
	 */
	public static String writeValueAsString(Object value) {
		try {
			return value == null ? null : JSON_MAPPER.writeValueAsString(value);
		} catch (Throwable e) {
			// throw new IllegalArgumentException(e); // TIP: 原则上，不对异常包装，这里为什么要包装？因为正常情况不会发生IOException
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 复制上面的方法, 精简方法名
	 * @param value
	 * @return
	 */
	public static String string(Object value) {
		return writeValueAsString(value);
	}

	/** 对象转换为map，如果是字符串，先转成json对象再转为map */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(Object value) throws IllegalArgumentException {
		return convertValue(value, Map.class);
	}

	@SuppressWarnings("unchecked")
	public static <T> T readValue(String content) {
		if (StringUtils.isEmpty(content)) {
			return null;
		}

		char ch = content.charAt(0);
		try {
			if (ch == '[') {
				return (T) JSON_MAPPER.readValue(content, List.class);
			}
			return (T) JSON_MAPPER.readValue(content, Map.class);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public static <T> T readValue(String content, Class<T> type) {
		if (content == null) {
			return null;
		}
		try {
			return JSON_MAPPER.readValue(content, type);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	public static <T> T readValue(byte[] content, Class<T> type) {
		if (content == null) {
			return null;
		}
		try {
			return JSON_MAPPER.readValue(content, type);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public static <T> T readMap(String content, Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
		if (content == null) {
			return null;
		}
		try {
			return JSON_MAPPER.readValue(content, JSON_MAPPER.getTypeFactory().constructMapType(mapClass, keyClass, valueClass));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	@SuppressWarnings("rawtypes")
	public static <T> T readCollection(String content, Class<? extends Collection> collectionClass, Class<?> elementClass) {
		if (content == null) {
			return null;
		}
		try {
			return JSON_MAPPER.readValue(content, JSON_MAPPER.getTypeFactory().constructCollectionType(collectionClass, elementClass));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	public static <T> List<T> readListValue(String content, Class<T> elementClass) {
		if (StringUtils.isEmpty(content)) {
			return null;
		}
		try {
			return JSON_MAPPER.readValue(content, JSON_MAPPER.getTypeFactory().constructCollectionType(List.class, elementClass));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	public static <T> T[] readArrayValue(String content, Class<T> elementClass) {
		if (StringUtils.isEmpty(content)) {
			return null;
		}
		try {
			return JSON_MAPPER.readValue(content, JSON_MAPPER.getTypeFactory().constructArrayType(elementClass));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public static <T> T convertValue(Object value, Class<T> clazz) throws IllegalArgumentException {
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		try {
			if (value instanceof String) {
				value = JSON_MAPPER.readTree((String) value);
			}
			return JSON_MAPPER.convertValue(value, clazz);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static <T> T getCollectionType(String json, TypeReference<T> typeRef) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		try {
			return  JSON_MAPPER.readValue(json, typeRef);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
