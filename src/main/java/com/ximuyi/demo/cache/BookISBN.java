package com.ximuyi.demo.cache;

import java.io.Serializable;
import java.util.Objects;

public class BookISBN implements Serializable {

	private final String rawNumber;

	public BookISBN() {
		this("");
	}

	public BookISBN(String rawNumber) {
		this.rawNumber = rawNumber;
	}

	public String getRawNumber() {
		return rawNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BookISBN bookISBN = (BookISBN) o;
		return Objects.equals(rawNumber, bookISBN.rawNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(rawNumber);
	}

	@Override
	public String toString() {
		return "{" +
				"rawNumber='" + rawNumber + '\'' +
				'}';
	}

	public static int hash(BookISBN isbn){
		return 1;   //每一个的hash值都一样~
	}
}
