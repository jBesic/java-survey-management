package com.jbesic.gui;

import com.jbesic.components.JBQuestion;
import com.jbesic.components.JBSurvey;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class JBAdminSurvey extends JBPanel {

    private final JBPanel surveyPanel = new JBPanel();
    private final JBPanel questionsPanel = new JBPanel();
    private int SurveyId;

    public JBAdminSurvey() {
        super();
    }

    public JBAdminSurvey setSurveyId(int categoryId) {
        this.SurveyId = categoryId;
        return this;
    }

    public int getSurveyId() {
        return SurveyId;
    }

    public JBAdminSurvey init() {
        buildSurvey();
        buildQuestions();

        add(surveyPanel, gridBagConstraints);
        gridBagConstraints.gridx++;
        add(questionsPanel, gridBagConstraints);

        return this;
    }

    private void buildSurvey() {
        JBParagraph surveyHeader = new JBParagraph("Survey details");
        JBPanel surveyWrapper = new JBPanel();
        buildSurveyRow(surveyWrapper);

        surveyPanel.add(surveyHeader, surveyPanel.gridBagConstraints);
        surveyPanel.gridBagConstraints.gridy++;
        surveyPanel.add(surveyWrapper, surveyPanel.gridBagConstraints);
    }

    private void buildSurveyRow(JBPanel surveyWrapper) {
        final JBSurvey fetchedSurvey = JBSurvey.getSurveyById(SurveyId);
        final JBTextArea surveyName = new JBTextArea(fetchedSurvey.getName());
        final JBTextArea surveyDescription = new JBTextArea(fetchedSurvey.getDescription());
        JBButton surveySave = new JBButton("Save");

        surveySave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String surveyUpdatedName = surveyName.getText();
                String surveyUpdatedDescription = surveyDescription.getText();
                fetchedSurvey.setName(surveyUpdatedName).setDescription(surveyUpdatedDescription).updateSurvey();
            }
        });

        JBPanel surveyRow = new JBPanel();
        surveyRow.add(surveyName, surveyRow.gridBagConstraints);
        surveyRow.gridBagConstraints.gridy++;
        surveyRow.add(surveyDescription, surveyRow.gridBagConstraints);
        surveyRow.gridBagConstraints.gridy++;
        surveyRow.add(surveySave, surveyRow.gridBagConstraints);

        surveyWrapper.add(surveyRow, surveyWrapper.gridBagConstraints);
    }

    private void buildQuestions() {
        JBParagraph questionsHeader = new JBParagraph("List of questions");
        JBPanel questionsWrapper = new JBPanel();
        buildQuestionsRow(questionsWrapper);

        questionsPanel.add(questionsHeader, questionsPanel.gridBagConstraints);
        questionsPanel.gridBagConstraints.gridy++;
        questionsPanel.add(new JBScrollPane(questionsWrapper), questionsPanel.gridBagConstraints);
    }

    private void buildQuestionsRow(JBPanel questionsWrapper) {
        List<Map<String, Object>> fetchedQuestions = JBQuestion.getQuestions(SurveyId);
        for (final Map<String, Object> question : fetchedQuestions) {
            final JBTextArea questionQuestion = new JBTextArea((String) question.get("Question"));
            JBButton questionSave = new JBButton("Save");

            questionSave.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String questionUpdatedQuestion = questionQuestion.getText();
                    JBQuestion questionObject = new JBQuestion();
                    questionObject.setId((Integer) question.get("Id")).setQuestion(questionUpdatedQuestion).setTypeId((Integer) question.get("TypeId")).updateQuestion();
                }
            });

            JBPanel questionRow = new JBPanel();
            questionRow.add(questionQuestion, questionRow.gridBagConstraints);
            questionRow.gridBagConstraints.gridy++;
            questionRow.add(questionSave, questionRow.gridBagConstraints);

            questionsWrapper.add(questionRow, questionsWrapper.gridBagConstraints);
            questionsWrapper.gridBagConstraints.gridy++;
        }
    }
}
