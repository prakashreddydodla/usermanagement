package com.otsi.retail.authservice.utils;




public enum DataTypesEnum {

	INTEGER("int", 1), STRING("String", 2), DATE("Date", 3), BOOLEAN("boolean", 4);

	public String getKey() {
		return key;
	}

	public Integer getValue() {
		return value;
	}

	private final String key;
	private final Integer value;

	DataTypesEnum(String key, int value) {
		this.key = key;
		this.value = value;
	}

}
