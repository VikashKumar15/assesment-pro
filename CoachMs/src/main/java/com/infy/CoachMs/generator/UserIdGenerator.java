package com.infy.CoachMs.generator;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class UserIdGenerator implements IdentifierGenerator {
	private static int counter = 1001;
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		int id = counter++;
		LocalDateTime now = LocalDateTime.now();
		String value = "U" + now.getDayOfMonth() + now.getMonthValue() + 
				now.getYear() + now.getHour() + now.getMinute();
		return value + id;
	}
}
