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
            category.setName("Category " + i).setDescription("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta.").createCategory();

            // Generate surveys
            for (int j = 1; j <= 3; j++) {
                JBSurvey survey = new JBSurvey();
                survey.setCatalogId(category.getId()).setName("Survey " + j).setDescription("Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large").createSurvey();

                // Generate questions
                // Text field
                for (int k = 1; k <= 2; k++) {
                    JBQuestion question = new JBQuestion();
                    question.setQuestion("A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was.").setTypeId(1).createQuestion(survey.getId());
                }
                // Text area
                for (int k = 1; k <= 2; k++) {
                    JBQuestion question = new JBQuestion();
                    question.setQuestion("A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was.").setTypeId(2).createQuestion(survey.getId());
                }
                // Radio
                for (int k = 1; k <= 2; k++) {
                    JBQuestion question = new JBQuestion();
                    question.setQuestion("A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was.").setTypeId(3).createQuestion(survey.getId());

                    // Possible answers
                    for (int z = 1; z <= 4; z++) {
                        JBPossibleAnswer possibleAnswer = new JBPossibleAnswer();
                        possibleAnswer.setAnswer("Possible " + z).setQuestionId(question.getId()).createPossibleAnswer();
                    }
                }
                // Checkbox
                for (int k = 1; k <= 2; k++) {
                    JBQuestion question = new JBQuestion();
                    question.setQuestion("A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was.").setTypeId(4).createQuestion(survey.getId());

                    // Possible answers
                    for (int z = 1; z <= 4; z++) {
                        JBPossibleAnswer possibleAnswer = new JBPossibleAnswer();
                        possibleAnswer.setAnswer("Possible " + z).setQuestionId(question.getId()).createPossibleAnswer();
                    }
                }
            }
        }
    }
}
