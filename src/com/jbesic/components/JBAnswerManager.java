package com.jbesic.components;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jbesic.helpers.JBDatabaseWrapper;

public class JBAnswerManager {

    private ArrayList<JBAnswer> answers = new ArrayList<JBAnswer>();

    public JBAnswerManager add(int QuestionId, String Answer, int UserId, String componentId) {
        Boolean answerIsUpdated = false;

        for (JBAnswer answer : answers) {
            if (answer.getComponentId().equals(componentId)) {
                answer.setAnswer(Answer);
                answerIsUpdated = true;
            }
        }

        if (!answerIsUpdated) {
            JBAnswer answer = new JBAnswer().setQuestionId(QuestionId).setAnswer(Answer).setUserId(UserId).setComponentId(componentId);
            this.answers.add(answer);
        }

        return this;
    }

    public void submit() {

        String values = "";
        for (JBAnswer answer : answers) {
            values += "(" + answer.getQuestionId() + ", '" + answer.getAnswer() + "', " + answer.getUserId() + "), ";
        }

        String statement = String.format("INSERT INTO %s (QuestionId, Answer, UserId) VALUES %s;",
                "answers", values.replaceAll(",\\s$", ""));

        try {
            Statement newStatement = JBDatabaseWrapper.getConnection().createStatement();
            newStatement.executeUpdate(statement, Statement.RETURN_GENERATED_KEYS);

            ResultSet generatedKeys = newStatement.getGeneratedKeys();
            generatedKeys.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
