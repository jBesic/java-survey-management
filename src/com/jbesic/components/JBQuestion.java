package com.jbesic.components;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jbesic.helpers.JBDatabaseWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JBQuestion {

    private int Id;
    private int TypeId;
    private String Question;

    public JBQuestion setId(int id) {
        this.Id = id;
        return this;
    }

    public int getId() {
        return this.Id;
    }

    public JBQuestion setTypeId(int typeId) {
        this.TypeId = typeId;
        return this;
    }

    public int getTypeId() {
        return this.TypeId;
    }

    public JBQuestion setQuestion(String question) {
        this.Question = question;
        return this;
    }

    public String getQuestion() {
        return this.Question;
    }

    public void createQuestion(int surveyId) {
        String statement = String.format("INSERT INTO %s (Question, TypeId) VALUES ('%s', %s);", "questions",
                this.Question, this.TypeId);
        try {
            Statement newStatement = JBDatabaseWrapper.getConnection().createStatement();
            newStatement.executeUpdate(statement, Statement.RETURN_GENERATED_KEYS);

            ResultSet generatedKeys = newStatement.getGeneratedKeys();
            generatedKeys.next();
            this.setId(generatedKeys.getInt(1));
            this.createQuestionSurvey(surveyId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateQuestion() {
        String statement = String.format("UPDATE %s SET Question = '%s', TypeId = %s WHERE Id = %s;", "questions",
                this.Question, this.TypeId, this.Id);
        try {
            Statement newStatement = JBDatabaseWrapper.getConnection().createStatement();
            newStatement.executeUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createQuestionSurvey(int surveyId) {
        String statement = String.format("INSERT INTO %s (QuestionId, SurveyId) VALUES (%s, %s);", "questionssurveys",
                this.Id, surveyId);

        try {
            Statement newStatement = JBDatabaseWrapper.getConnection().createStatement();
            newStatement.executeUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static JBQuestion getQuestionById(int questionId) {
        String statement = String.format("SELECT Question, TypeId FROM %s WHERE %s LIMIT 1", "catalogs", questionId);
        JBQuestion fetchedSurvey = null;
        try {
            Statement newStatement = JBDatabaseWrapper.getConnection().createStatement();
            ResultSet resultQuestion = newStatement.executeQuery(statement);
            if (resultQuestion.next()) {
                return new JBQuestion().setId(questionId).setQuestion(resultQuestion.getString(1)).setTypeId(resultQuestion.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fetchedSurvey;
    }

    public static List<Map<String, Object>> getQuestions(int surveyId) {
        String statement = String.format("SELECT q.Id, q.Question, q.TypeId FROM %s AS q LEFT OUTER JOIN questionssurveys AS qs ON qs.QuestionId = q.Id WHERE qs.SurveyId = %s", "questions", surveyId);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        try {
            Statement newStatement = JBDatabaseWrapper.getConnection().createStatement();
            ResultSet fetchedQuestions = newStatement.executeQuery(statement);

            while (fetchedQuestions.next()) {
                Map<String, Object> row = new HashMap<String, Object>();
                row.put("Id", fetchedQuestions.getInt("Id"));
                row.put("Question", fetchedQuestions.getString("Question"));
                row.put("TypeId", fetchedQuestions.getInt("TypeId"));

                questions.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }
}
