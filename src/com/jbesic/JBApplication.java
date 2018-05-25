package com.jbesic;

import com.jbesic.components.JBCategory;
import com.jbesic.components.JBQuestion;
import com.jbesic.components.JBSurvey;

public class JBApplication {

	JBApplication() {
		// Generate categories
		JBCategory category1 = new JBCategory();
		category1.setName("Category 1").setDescription("Category 1 description").createCategory();
		
		JBCategory category2 = new JBCategory();
		category2.setName("Category 2").setDescription("Category 2 description").createCategory();
		
		JBCategory category3 = new JBCategory();
		category3.setName("Category 3").setDescription("Category 3 description").createCategory();
		
		// Generate surveys
		JBSurvey survey1 = new JBSurvey();
		survey1.setCatalogId(category1.getId()).setName("Survey 1").setDescription("Survey 1 description").createSurvey();
		
		JBSurvey survey2 = new JBSurvey();
		survey2.setCatalogId(category1.getId()).setName("Survey 2").setDescription("Survey 2 description").createSurvey();
		
		JBSurvey survey3 = new JBSurvey();
		survey3.setCatalogId(category2.getId()).setName("Survey 3").setDescription("Survey 3 description").createSurvey();
		
		// Generate questions
		JBQuestion question1 = new JBQuestion();
		question1.setQuestion("Question 1").setTypeId(1).createQuestion(survey1.getId());
		
		// Generate questions
		JBQuestion question2 = new JBQuestion();
		question2.setQuestion("Question 2").setTypeId(1).createQuestion(survey1.getId());
		
		// Generate questions
		JBQuestion question3 = new JBQuestion();
		question3.setQuestion("Question 3").setTypeId(1).createQuestion(survey1.getId());
	}
}
