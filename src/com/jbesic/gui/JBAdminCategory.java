package com.jbesic.gui;

import com.jbesic.components.JBCategory;
import com.jbesic.components.JBSurvey;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class JBAdminCategory extends JBPanel {

    private final JBPanel categoryPanel = new JBPanel();
    private final JBPanel surveysPanel = new JBPanel();
    private int CategoryId;

    public JBAdminCategory() {
        super();
    }

    public JBAdminCategory setCategoryId(int categoryId) {
        this.CategoryId = categoryId;
        return this;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public JBAdminCategory init() {
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
        final JBCategory fetchedCategory = JBCategory.getCategoryById(CategoryId);
        final JBTextArea categoryName = new JBTextArea(fetchedCategory.getName());
        final JBTextArea categoryDescription = new JBTextArea(fetchedCategory.getDescription());
        JBButton categorySave = new JBButton("Save");

        categorySave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String categoryUpdatedName = categoryName.getText();
                String categoryUpdatedDescription = categoryDescription.getText();
                fetchedCategory.setName(categoryUpdatedName).setDescription(categoryUpdatedDescription).updateCategory();
            }
        });

        JBPanel categoryRow = new JBPanel();
        categoryRow.add(categoryName, categoryRow.gridBagConstraints);
        categoryRow.gridBagConstraints.gridy++;
        categoryRow.add(categoryDescription, categoryRow.gridBagConstraints);
        categoryRow.gridBagConstraints.gridy++;
        categoryRow.add(categorySave, categoryRow.gridBagConstraints);

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
            JBButton surveyManage = new JBButton("Manage");

            surveyManage.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JBAdminSurvey adminSurvey = new JBAdminSurvey();
                    adminSurvey.setSurveyId((Integer) survey.get("Id")).init();
                    JBFrame.getFrame().add(adminSurvey);
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
