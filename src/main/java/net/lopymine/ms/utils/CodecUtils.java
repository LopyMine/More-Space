package net.lopymine.ms.utils;

import com.google.gson.*;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.lopymine.ms.client.MoreSpaceClient;

import java.util.*;
import java.util.function.*;

@SuppressWarnings("unused")
public final class CodecUtils {

	public static <A, B> RecordCodecBuilder<A, B> option(String optionId, B defValue, Codec<B> codec, Function<A, B> getter) {
		return codec.optionalFieldOf(optionId).xmap((o) -> o.orElse(defValue), Optional::ofNullable).forGetter(getter);
	}

	public static <A, B> RecordCodecBuilder<A, HashSet<B>> option(String optionId, HashSet<B> defValue, Codec<B> codec, Function<A, HashSet<B>> getter) {
		return codec.listOf().xmap(HashSet::new, ArrayList::new).optionalFieldOf(optionId).xmap((o) -> o.orElse(defValue), Optional::ofNullable).forGetter(getter);
	}

	public static <T> void decode(Codec<T> codec, JsonElement o, Consumer<T> consumer) {
		try {
			T value = codec.decode(JsonOps.INSTANCE, o)/*? if >=1.20.5 {*/.getOrThrow()/*?} else {*//*.getOrThrow(false, LOGGER::error)*//*?}*/.getFirst();
			consumer.accept(value);
		} catch (Exception e) {
			MoreSpaceClient.LOGGER.warn("Failed to decode JsonElement:", e);
		}
	}

	public static <T> T decode(String id, Codec<T> codec, JsonObject o) {
		if (o.has(id)) {
			try {
				return codec.decode(JsonOps.INSTANCE, o.get(id))/*? if >=1.20.5 {*/.getOrThrow()/*?} else {*//*.getOrThrow(false, LOGGER::error)*//*?}*/.getFirst();
			} catch (Exception e) {
				MoreSpaceClient.LOGGER.warn("Failed to decode \"%s\" from JsonObject:".formatted(id), e);
			}
		}
		return null;
	}

	public static <T> T decode(String id, T fallback, Codec<T> codec, JsonObject o) {
		if (o.has(id)) {
			try {
				return codec.decode(JsonOps.INSTANCE, o.get(id))/*? if >=1.20.5 {*/.getOrThrow()/*?} else {*//*.getOrThrow(false, LOGGER::error)*//*?}*/.getFirst();
			} catch (Exception e) {
				MoreSpaceClient.LOGGER.warn("Failed to decode \"%s\" from JsonObject:".formatted(id), e);
			}
		}
		return fallback;
	}

}
