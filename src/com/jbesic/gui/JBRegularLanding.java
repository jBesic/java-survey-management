package com.jbesic.gui;

import com.jbesic.components.JBCategory;
import com.jbesic.components.JBSurvey;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class JBRegularLanding extends JBPanel {

    private final JBPanel categoriesPanel = new JBPanel();
    private final JBPanel surveysPanel = new JBPanel();

    public JBRegularLanding() {
        super();

        buildCategories();
        buildSurveys();

        add(categoriesPanel, gridBagConstraints);
        gridBagConstraints.gridx++;
        add(surveysPanel, gridBagConstraints);
    }

    private void buildCategories() {
        JBHeadline categoriesHeader = new JBHeadline("List of created categories");
        JBPanel categoriesWrapper = new JBPanel();
        buildCategoriesRow(categoriesWrapper);

        categoriesPanel.add(categoriesHeader, categoriesPanel.gridBagConstraints);
        categoriesPanel.gridBagConstraints.gridy++;
        categoriesPanel.add(new JBScrollPane(categoriesWrapper), categoriesPanel.gridBagConstraints);
    }

    private void buildCategoriesRow(JBPanel categoriesWrapper) {
        List<Map<String, Object>> fetchedCategories = JBCategory.getCategories();
        for (final Map<String, Object> category : fetchedCategories) {
            JBHeadline categoryName = new JBHeadline((String) category.get("Name"));
            JBParagraph categoryDescription = new JBParagraph((String) category.get("Description"));
            JBButton categoryManage = new JBButton("Explore");

            categoryManage.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JBRegularCategory regularCategory = new JBRegularCategory();
                    regularCategory.setCategoryId((Integer) category.get("Id")).init();
                    JBFrame.getFrame().add(regularCategory);
                }
            });

            JBPanel categoryRow = new JBPanel();
            categoryRow.add(categoryName, categoryRow.gridBagConstraints);
            categoryRow.gridBagConstraints.gridy++;
            categoryRow.add(categoryDescription, categoryRow.gridBagConstraints);
            categoryRow.gridBagConstraints.gridy++;
            categoryRow.add(categoryManage, categoryRow.gridBagConstraints);

            categoriesWrapper.add(categoryRow, categoriesWrapper.gridBagConstraints);
            categoriesWrapper.gridBagConstraints.gridy++;
        }
    }

    private void buildSurveys() {
        JBHeadline surveysHeader = new JBHeadline("List of created surveys");
        JBPanel surveysWrapper = new JBPanel();
        buildSurveysRow(surveysWrapper);

        surveysPanel.add(surveysHeader, surveysPanel.gridBagConstraints);
        surveysPanel.gridBagConstraints.gridy++;
        surveysPanel.add(new JBScrollPane(surveysWrapper), surveysPanel.gridBagConstraints);
    }

    private void buildSurveysRow(JBPanel surveysWrapper) {
        List<Map<String, Object>> fetchedSurveys = JBSurvey.getSurveys();
        for (final Map<String, Object> survey : fetchedSurveys) {
            JBHeadline surveyName = new JBHeadline((String) survey.get("Name"));
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
