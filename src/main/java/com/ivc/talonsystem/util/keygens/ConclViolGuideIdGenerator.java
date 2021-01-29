package com.ivc.talonsystem.util.keygens;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class ConclViolGuideIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		Connection connection = session.connection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select max(id) as Id from Conclusionviolguide");

			if (rs.next()) {
				if (rs.getObject(1) == null) {
					return new Integer(1);
				} else {
					int id = rs.getInt(1) + 1;
					Integer generatedId = new Integer(id);
					return generatedId;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
