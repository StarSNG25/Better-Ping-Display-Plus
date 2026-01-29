package com.starsng.betterpingdisplayplus.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Locale;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.Expose;
import net.minecraft.util.Formatting;

public class Config
{
	public static final String DEFAULT_NULL_PING_PLACEHOLDER = "N/A";
	public static final Formatting DEFAULT_NULL_PING_PLACEHOLDER_COLOR = Formatting.GRAY;
	
	private static final Gson gson = new GsonBuilder()
			.setPrettyPrinting()
			.registerTypeAdapter(Formatting.class, new JsonSerializer<Formatting>()
			{
				@Override
				public JsonElement serialize(Formatting src, Type typeOfSrc, JsonSerializationContext context)
				{
					return new JsonPrimitive(src.getName());
				}
			})
			.registerTypeAdapter(Formatting.class, new JsonDeserializer<Formatting>()
			{
				@Override
				public Formatting deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
				{
					String colorName = json.getAsString();
					Formatting formatting = Formatting.byName(colorName.toLowerCase(Locale.ROOT));
					
					if (formatting == null || !formatting.isColor())
						return DEFAULT_NULL_PING_PLACEHOLDER_COLOR;
					
					return formatting;
				}
			})
			.create();
	
	@Expose
	private String nullPingPlaceholder = DEFAULT_NULL_PING_PLACEHOLDER;
	@Expose
	private Formatting nullPingPlaceholderColor = DEFAULT_NULL_PING_PLACEHOLDER_COLOR;
	
	public String getNullPingPlaceholder()
	{
		return nullPingPlaceholder;
	}

	public void setNullPingPlaceholder(String nullPingPlaceholder)
	{
		this.nullPingPlaceholder = nullPingPlaceholder;
	}
	
	public Formatting getNullPingPlaceholderColor()
	{
		return nullPingPlaceholderColor;
	}
	
	public void setNullPingPlaceholderColor(Formatting nullPingPlaceholderColor)
	{
		this.nullPingPlaceholderColor = nullPingPlaceholderColor;
	}
	
	public void writeToFile(File file) throws IOException
	{
		try (FileWriter fileWriter = new FileWriter(file))
		{
			fileWriter.write(gson.toJson(this));
		}
	}
	
	public static Config fromFile(File file) throws IOException
	{
		try (FileReader fileReader = new FileReader(file))
		{
			return gson.fromJson(fileReader, Config.class);
		}
	}
	
	public static Config fromDefault()
	{
		return new Config();
	}
}
