package com.agni.demo.util;

import java.lang.reflect.Type;

import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


public class OutputMapper {
	static GsonBuilder builder;

	public OutputMapper() {
		if (builder == null) {
			builder = new GsonBuilder();
			builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			builder.excludeFieldsWithoutExposeAnnotation();
			builder.serializeNulls();
			builder.registerTypeAdapter(ObjectId.class, new JsonSerializer<ObjectId>() {
                @Override
                public JsonElement serialize(ObjectId src, Type typeOfSrc, JsonSerializationContext context) {
                    return new JsonPrimitive(src.toHexString());
                }
            }).registerTypeAdapter(ObjectId.class, new JsonDeserializer<ObjectId>() {
                @Override
                public ObjectId deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    return new ObjectId(json.getAsString());
                }
            });
		}
	}

	public static Gson gson() {
		return builder.create();
	}
}