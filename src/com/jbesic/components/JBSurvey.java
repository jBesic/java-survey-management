package com.jbesic.components;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jbesic.helpers.JBDatabaseWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JBSurvey {

    private int Id;
    private int CatalogId;
    private String Name;
    private String Description;

    public JBSurvey setId(int id) {
        this.Id = id;
        return this;
    }

    public int getId() {
        return this.Id;
    }

    public JBSurvey setCatalogId(int catalogId) {
        this.CatalogId = catalogId;
        return this;
    }

    public int getCatalogId() {
        return this.CatalogId;
    }

    public String getName() {
        return this.Name;
    }

    public JBSurvey setName(String name) {
        this.Name = name;
        return this;
    }

    public String getDescription() {
        return this.Description;
    }

    public JBSurvey setDescription(String description) {
        this.Description = description;
        return this;
    }

    public void createSurvey() {
        String statement = String.format("INSERT INTO %s (CatalogId, Name, Description) VALUES (%s, '%s', '%s');",
                "surveys", this.CatalogId, this.Name, this.Description);
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

    public void updateSurvey() {
        String statement = String.format("UPDATE %s SET CatalogId = %s, Name = '%s', Description = '%s' WHERE Id = %s;",
                "surveys", this.CatalogId, this.Name, this.Description, this.Id);
        try {
            Statement newStatement = JBDatabaseWrapper.getConnection().createStatement();
            newStatement.executeUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static JBSurvey getSurveyById(int surveyId) {
        String statement = String.format("SELECT CatalogId, Name, Description FROM %s WHERE %s LIMIT 1", "surveys",
                surveyId);
        JBSurvey fetchedSurvey = null;
        try {
            Statement newStatement = JBDatabaseWrapper.getConnection().createStatement();
            ResultSet resultSurvey = newStatement.executeQuery(statement);
            if (resultSurvey.next()) {
                return new JBSurvey().setId(surveyId).setCatalogId(resultSurvey.getInt(1))
                        .setName(resultSurvey.getString(2)).setDescription(resultSurvey.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fetchedSurvey;
    }

    public static List<Map<String, Object>> getSurveys() {
        String statement = String.format("SELECT Id, Name, Description FROM %s", "surveys");

        List<Map<String, Object>> surveys = new ArrayList<Map<String, Object>>();
        try {
            Statement newStatement = JBDatabaseWrapper.getConnection().createStatement();
            ResultSet fetchedSurveys = newStatement.executeQuery(statement);

            while (fetchedSurveys.next()) {
                Map<String, Object> row = new HashMap<String, Object>();
                row.put("Id", fetchedSurveys.getInt("Id"));
                row.put("Name", fetchedSurveys.getString("Name"));
                row.put("Description", fetchedSurveys.getString("Description"));

                surveys.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return surveys;
    }

    public static List<Map<String, Object>> getSurveysByCategoryId(int categoryId) {
        String statement = String.format("SELECT Id, Name, Description FROM %s WHERE CatalogId = %s", "surveys", categoryId);

        List<Map<String, Object>> surveys = new ArrayList<Map<String, Object>>();
        try {
            Statement newStatement = JBDatabaseWrapper.getConnection().createStatement();
            ResultSet fetchedSurveys = newStatement.executeQuery(statement);

            while (fetchedSurveys.next()) {
                Map<String, Object> row = new HashMap<String, Object>();
                row.put("Id", fetchedSurveys.getInt("Id"));
                row.put("Name", fetchedSurveys.getString("Name"));
                row.put("Description", fetchedSurveys.getString("Description"));

                surveys.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return surveys;
    }
}
