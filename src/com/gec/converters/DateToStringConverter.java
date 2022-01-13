package com.gec.converters;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateToStringConverter implements Converter<Date, String> {

	@Override
	public String convert(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		String dateStr = format.format(date);
		return dateStr;
	}

}
