package com.jbesic.components;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jbesic.helpers.JBDatabaseWrapper;

public class JBCategory {
	private int Id;
	private String Name;
	private String Description;

	public JBCategory setId(int id) {
		this.Id = id;
		return this;
	}

	public int getId() {
		return this.Id;
	}
	
	public String getName() {
		return this.Name;
	}

	public JBCategory setName(String name) {
		this.Name = name;
		return this;
	}

	public String getDescription() {
		return this.Description;
	}

	public JBCategory setDescription(String description) {
		this.Description = description;
		return this;
	}

	public void createCategory() {
		String statement = String.format("INSERT INTO %s (Name, Description) VALUES ('%s', '%s');", "catalogs",
				this.Name, this.Description);
		try {
			Statement newStatement = JBDatabaseWrapper.getConnection().createStatement();
			newStatement.executeUpdate(statement, Statement.RETURN_GENERATED_KEYS);
			
			ResultSet generatedKeys = newStatement.getGeneratedKeys();
			generatedKeys.next();
			this.setId(generatedKeys.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateCategory() {
		String statement = String.format("UPDATE %s SET Name = '%s', Description = '%s' WHERE Id = %s;", "catalogs",
				this.Name, this.Description, this.Id);
		try {
			Statement newStatement = JBDatabaseWrapper.getConnection().createStatement();
			newStatement.executeUpdate(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static JBCategory getCategoryById(int categoryId) {
		String statement = String.format("SELECT Name, Description FROM %s WHERE %s LIMIT 1", "catalogs", categoryId);
		JBCategory fetchedCategory = null;
		try {
			Statement newStatement = JBDatabaseWrapper.getConnection().createStatement();
			ResultSet resultCategory = newStatement.executeQuery(statement);
			if (resultCategory.next()) {
				return new JBCategory().setId(categoryId).setName(resultCategory.getString(1)).setDescription(resultCategory.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return fetchedCategory;
	}
}
