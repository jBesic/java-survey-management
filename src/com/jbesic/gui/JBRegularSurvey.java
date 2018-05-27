package com.jbesic.gui;

import com.jbesic.components.JBAnswerManager;
import com.jbesic.components.JBPossibleAnswer;
import com.jbesic.components.JBQuestion;
import com.jbesic.components.JBSurvey;
import com.jbesic.components.JBTypes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import java.util.Map;

public class JBRegularSurvey extends JBPanel {

    private final JBPanel surveyPanel = new JBPanel();
    private final JBPanel questionsPanel = new JBPanel();
    private final JBAnswerManager answersManager = new JBAnswerManager();
    private int SurveyId;

    public JBRegularSurvey() {
        super();
    }

    public JBRegularSurvey setSurveyId(int categoryId) {
        this.SurveyId = categoryId;
        return this;
    }

    public int getSurveyId() {
        return SurveyId;
    }

    public JBRegularSurvey init() {
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
        JBSurvey fetchedSurvey = JBSurvey.getSurveyById(SurveyId);
        JBParagraph surveyName = new JBParagraph(fetchedSurvey.getName());
        JBParagraph surveyDescription = new JBParagraph(fetchedSurvey.getDescription());

        JBPanel surveyRow = new JBPanel();
        surveyRow.add(surveyName, surveyRow.gridBagConstraints);
        surveyRow.gridBagConstraints.gridy++;
        surveyRow.add(surveyDescription, surveyRow.gridBagConstraints);

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

    private void buildQuestionsRow(final JBPanel questionsWrapper) {
        List<Map<String, Object>> fetchedQuestions = JBQuestion.getQuestions(SurveyId);
        for (final Map<String, Object> question : fetchedQuestions) {
            final JBParagraph questionQuestion = new JBParagraph((String) question.get("Question"));

            JBPanel questionRow = new JBPanel();
            questionRow.add(questionQuestion, questionRow.gridBagConstraints);
            questionRow.gridBagConstraints.gridy++;

            Map<Integer, String> definedTypes = JBTypes.getTypes();
            if ("Text".equals(definedTypes.get((Integer) question.get("TypeId")))) {
                final JBTextArea textArea = new JBTextArea();

                textArea.addFocusListener(new FocusListener() {
                    public void focusGained(FocusEvent e) {
                    }

                    public void focusLost(FocusEvent e) {
                        answersManager.add((Integer) question.get("Id"), textArea.getText(), 1);
                    }
                });

                questionRow.add(textArea, questionRow.gridBagConstraints);
            } else if ("Textarea".equals(definedTypes.get((Integer) question.get("TypeId")))) {
                final JBTextArea textArea = new JBTextArea();

                textArea.addFocusListener(new FocusListener() {
                    public void focusGained(FocusEvent e) {
                    }

                    public void focusLost(FocusEvent e) {
                        answersManager.add((Integer) question.get("Id"), textArea.getText(), 1);
                    }
                });

                questionRow.add(textArea, questionRow.gridBagConstraints);
            } else if ("Radio".equals(definedTypes.get((Integer) question.get("TypeId")))) {
                JBPanel radioPanel = new JBPanel();
                JBButtonGroup questionAnswer = new JBButtonGroup();
                List<Map<String, Object>> possibleAnswers = JBPossibleAnswer.getAnswerByQuestionId((Integer) question.get("Id"));
                for (Map<String, Object> possibleAnswer : possibleAnswers) {
                    final JBRadioButton radioButton = new JBRadioButton((String) possibleAnswer.get("Answer"));

                    radioButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            answersManager.add((Integer) question.get("Id"), radioButton.getText(), 1);
                        }
                    });

                    questionAnswer.add(radioButton);
                    radioPanel.add(radioButton, radioPanel.gridBagConstraints);
                    radioPanel.gridBagConstraints.gridy++;
                }

                questionRow.add(radioPanel, questionRow.gridBagConstraints);
            } else if ("Checkbox".equals(definedTypes.get((Integer) question.get("TypeId")))) {
                JBPanel checkboxPanel = new JBPanel();
                List<Map<String, Object>> possibleAnswers = JBPossibleAnswer.getAnswerByQuestionId((Integer) question.get("Id"));
                for (Map<String, Object> possibleAnswer : possibleAnswers) {
                    final JBCheckBox checkboxButton = new JBCheckBox((String) possibleAnswer.get("Answer"));

                    checkboxButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            answersManager.add((Integer) question.get("Id"), checkboxButton.getText(), 1);
                        }
                    });

                    checkboxPanel.add(checkboxButton, checkboxPanel.gridBagConstraints);
                    checkboxPanel.gridBagConstraints.gridy++;
                }

                questionRow.add(checkboxPanel, questionRow.gridBagConstraints);
            }

            questionsWrapper.add(questionRow, questionsWrapper.gridBagConstraints);
            questionsWrapper.gridBagConstraints.gridy++;
        }

        JBButton questionAnswersSave = new JBButton("Save");
        questionAnswersSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                answersManager.submit();
            }
        });

        questionsWrapper.add(questionAnswersSave, questionsWrapper.gridBagConstraints);
    }
}
