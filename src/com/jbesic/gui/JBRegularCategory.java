package com.jbesic.gui;

import com.jbesic.components.JBCategory;
import com.jbesic.components.JBSurvey;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class JBRegularCategory extends JBPanel {

    private final JBPanel categoryPanel = new JBPanel();
    private final JBPanel surveysPanel = new JBPanel();
    private int CategoryId;

    public JBRegularCategory() {
        super();
    }

    public JBRegularCategory setCategoryId(int categoryId) {
        this.CategoryId = categoryId;
        return this;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public JBRegularCategory init() {
        buildCategory();
        buildSurveys();

        add(categoryPanel, gridBagConstraints);
        gridBagConstraints.gridx++;
        add(surveysPanel, gridBagConstraints);

        return this;
    }

    private void buildCategory() {
        JBParagraph categoryHeader = new JBParagraph("Category details");
        JBPanel categoryWrapper = new JBPanel();
        buildCategoryRow(categoryWrapper);

        categoryPanel.add(categoryHeader, categoryPanel.gridBagConstraints);
        categoryPanel.gridBagConstraints.gridy++;
        categoryPanel.add(categoryWrapper, categoryPanel.gridBagConstraints);
    }

    private void buildCategoryRow(JBPanel categoryWrapper) {
        JBCategory fetchedCategory = JBCategory.getCategoryById(CategoryId);
        JBParagraph categoryName = new JBParagraph(fetchedCategory.getName());
        JBParagraph categoryDescription = new JBParagraph(fetchedCategory.getDescription());

        JBPanel categoryRow = new JBPanel();
        categoryRow.add(categoryName, categoryRow.gridBagConstraints);
        categoryRow.gridBagConstraints.gridy++;
        categoryRow.add(categoryDescription, categoryRow.gridBagConstraints);

        categoryWrapper.add(categoryRow, categoryWrapper.gridBagConstraints);
    }

    private void buildSurveys() {
        JBParagraph surveysHeader = new JBParagraph("List of created surveys");
        JBPanel surveysWrapper = new JBPanel();
        buildSurveysRow(surveysWrapper);

        surveysPanel.add(surveysHeader, surveysPanel.gridBagConstraints);
        surveysPanel.gridBagConstraints.gridy++;
        surveysPanel.add(new JBScrollPane(surveysWrapper), surveysPanel.gridBagConstraints);
    }

    private void buildSurveysRow(JBPanel surveysWrapper) {
        List<Map<String, Object>> fetchedSurveys = JBSurvey.getSurveysByCategoryId(CategoryId);
        for (final Map<String, Object> survey : fetchedSurveys) {
            JBParagraph surveyName = new JBParagraph((String) survey.get("Name"));
            JBParagraph surveyDescription = new JBParagraph((String) survey.get("Description"));
            JBButton surveyManage = new JBButton("Start");

            surveyManage.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JBRegularSurvey regularSurvey = new JBRegularSurvey();
                    regularSurvey.setSurveyId((Integer) survey.get("Id")).init();
                    JBFrame.getFrame().add(regularSurvey);
                }
            });

            JBPanel surveyRow = new JBPanel();
            surveyRow.add(surveyName, surveyRow.gridBagConstraints);
            surveyRow.gridBagConstraints.gridy++;
            surveyRow.add(surveyDescription, surveyRow.gridBagConstraints);
            surveyRow.gridBagConstraints.gridy++;
            surveyRow.add(surveyManage, surveyRow.gridBagConstraints);

            surveysWrapper.add(surveyRow, surveysWrapper.gridBagConstraints);
            surveysWrapper.gridBagConstraints.gridy++;
        }
    }
}
