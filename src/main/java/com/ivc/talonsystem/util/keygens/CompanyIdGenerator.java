package com.ivc.talonsystem.util.keygens;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.ivc.talonsystem.entity.Rju;
import com.ivc.talonsystem.entity.Underrju;
import com.ivc.talonsystem.entity.Underunitdep;
import com.ivc.talonsystem.entity.UnitDepart;
import com.ivc.talonsystem.entity.Vstan;

public class CompanyIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		Connection connection = session.connection();
		try {
			Statement statement = connection.createStatement();
			if (object instanceof Rju) {
				ResultSet rs = statement.executeQuery("select max(id) as Id from Rju");

				if (rs.next()) {
					if (rs.getObject(1) == null) {
						return new Integer(1);
					} else {
						int id = rs.getInt(1) + 1;
						Integer generatedId = new Integer(id);
						return generatedId;
					}
				}
			} else if (object instanceof Underrju) {
				ResultSet rs = statement.executeQuery("select max(id) as Id from Underrju");
				if (rs.next()) {
					if (rs.getObject(1) == null) {
						return new Integer(1001);
					} else {
						int iden = rs.getInt(1) + 1;
						Integer generatedId = new Integer(iden);
						return generatedId;
					}
				}
			} else if (object instanceof Vstan) {
				return ((Vstan) object).getKod();

			} else if (object instanceof UnitDepart) {
				ResultSet rs = statement.executeQuery("select max(id) as Id from Unitdepart");

				if (rs.next()) {
					if (rs.getObject(1) == null) {
						return new Integer(101);
					} else {
						int id = rs.getInt(1) + 1;
						Integer generatedId = new Integer(id);
						return generatedId;
					}
				}
			} else if (object instanceof Underunitdep) {
				ResultSet rs = statement.executeQuery("select max(id) as Id from Underunitdep");

				if (rs.next()) {
					if (rs.getObject(1) == null) {
						return new Integer(10001);
					} else {
						int id = rs.getInt(1) + 1;
						Integer generatedId = new Integer(id);
						return generatedId;
					}
				}
			} else {
				ResultSet rs = statement.executeQuery("select max(id) as Id from AbstractCompany");

				if (rs.next()) {
					if (rs.getObject(1) == null) {
						return new Integer(100001);
					} else {
						int id = rs.getInt(1) + 1;
						Integer generatedId = new Integer(id);
						return generatedId;
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
