package com.jbesic;

import com.jbesic.components.JBCategory;
import com.jbesic.components.JBPossibleAnswer;
import com.jbesic.components.JBQuestion;
import com.jbesic.components.JBSurvey;

public class JBDummyData {

    private JBDummyData() {
    }

    public static void generateTables() {

        // Generate categories
        for (int i = 1; i <= 3; i++) {
            JBCategory category = new JBCategory();
            category.setName("Category #" + i + " name").setDescription("Category #" + i + " description.").createCategory();

            // Generate surveys
            for (int j = 1; j <= 3; j++) {
                JBSurvey survey = new JBSurvey();
                survey.setCatalogId(category.getId()).setName("Survey #" + j + " [Category #" + i + "]").setDescription("Survey #" + j + " description.").createSurvey();

                // Generate questions
                // Text field
                for (int k = 1; k <= 2; k++) {
                    JBQuestion question = new JBQuestion();
                    question.setQuestion("TEXT FIELD: Question #" + k + " [Survey #" + j + "] [Category #" + i + "]").setTypeId(1).createQuestion(survey.getId());
                }
                // Text area
                for (int k = 1; k <= 2; k++) {
                    JBQuestion question = new JBQuestion();
                    question.setQuestion("TEXTAREA FIELD:  Question #" + k + " [Survey #" + j + "] [Category #" + i + "]").setTypeId(2).createQuestion(survey.getId());
                }
                // Radio
                for (int k = 1; k <= 2; k++) {
                    JBQuestion question = new JBQuestion();
                    question.setQuestion("RADIO FIELD:  Question #" + k + " [Survey #" + j + "] [Category #" + i + "]").setTypeId(3).createQuestion(survey.getId());

                    // Possible answers
                    for (int z = 1; z <= 4; z++) {
                        JBPossibleAnswer possibleAnswer = new JBPossibleAnswer();
                        possibleAnswer.setAnswer("Answer #" + z + " [Question #" + k + "]").setQuestionId(question.getId()).createPossibleAnswer();
                    }
                }
                // Checkbox
                for (int k = 1; k <= 2; k++) {
                    JBQuestion question = new JBQuestion();
                    question.setQuestion("CHECKBOX FIELD: Question #" + k + " [Survey #" + j + "] [Category #" + i + "]").setTypeId(4).createQuestion(survey.getId());

                    // Possible answers
                    for (int z = 1; z <= 4; z++) {
                        JBPossibleAnswer possibleAnswer = new JBPossibleAnswer();
                        possibleAnswer.setAnswer("Answer #" + z + " [Question #" + k + "]").setQuestionId(question.getId()).createPossibleAnswer();
                    }
                }
            }
        }
    }
}
