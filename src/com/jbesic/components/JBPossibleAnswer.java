package com.jbesic.components;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jbesic.helpers.JBDatabaseWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JBPossibleAnswer {

    private int Id;
    private int QuestionId;
    private String Answer;

    public int getId() {
        return this.Id;
    }

    public JBPossibleAnswer setId(int id) {
        this.Id = id;
        return this;
    }

    public int getQuestionId() {
        return this.QuestionId;
    }

    public JBPossibleAnswer setQuestionId(int questionId) {
        this.QuestionId = questionId;
        return this;
    }

    public String getAnswer() {
        return this.Answer;
    }

    public JBPossibleAnswer setAnswer(String answer) {
        this.Answer = answer;
        return this;
    }

    public void createPossibleAnswer() {
        String statement = String.format("INSERT INTO %s (QuestionId, Answer) VALUES (%s, '%s');",
                "predefinedanswers", this.QuestionId, this.Answer);
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

    public void updatePossibleAnswer() {
        String statement = String.format("UPDATE %s SET QuestionId = %s, Answer = '%s' WHERE Id = %s;",
                "predefinedanswers", this.QuestionId, this.Answer, this.Id);
        try {
            Statement newStatement = JBDatabaseWrapper.getConnection().createStatement();
            newStatement.executeUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static JBPossibleAnswer getAnswerById(int answerId) {
        String statement = String.format("SELECT QuestionId, Answer FROM %s WHERE Id = %s LIMIT 1", "predefinedanswers",
                answerId);
        JBPossibleAnswer fetchedAnswer = null;
        try {
            Statement newStatement = JBDatabaseWrapper.getConnection().createStatement();
            ResultSet resultAnswer = newStatement.executeQuery(statement);
            if (resultAnswer.next()) {
                return new JBPossibleAnswer().setId(answerId).setQuestionId(resultAnswer.getInt(1)).setAnswer(resultAnswer.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fetchedAnswer;
    }

    public static List<Map<String, Object>> getAnswerByQuestionId(int questionId) {
        String statement = String.format("SELECT QuestionId, Answer FROM %s WHERE QuestionId = %s", "predefinedanswers",
                questionId);
        List<Map<String, Object>> possibleAnswers = new ArrayList<Map<String, Object>>();
        try {
            Statement newStatement = JBDatabaseWrapper.getConnection().createStatement();
            ResultSet fetchedPossibleAnswers = newStatement.executeQuery(statement);
            while (fetchedPossibleAnswers.next()) {
                Map<String, Object> row = new HashMap<String, Object>();
                row.put("QuestionId", fetchedPossibleAnswers.getInt("QuestionId"));
                row.put("Answer", fetchedPossibleAnswers.getString("Answer"));

                possibleAnswers.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return possibleAnswers;
    }
}
