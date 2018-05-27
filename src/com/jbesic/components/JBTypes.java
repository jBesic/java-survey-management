/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbesic.components;

import com.jbesic.helpers.JBDatabaseWrapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jasmin
 */
public class JBTypes {

    private int Id;
    private String Type;

    public int getId() {
        return Id;
    }

    public String getType() {
        return Type;
    }

    public static Map<Integer, String> getTypes() {
        Map<Integer, String> types = new HashMap<Integer, String>();
        String statement = String.format("SELECT Id, Type FROM %s", "types");

        try {
            Statement newStatement = JBDatabaseWrapper.getConnection().createStatement();
            ResultSet fetchedSurveys = newStatement.executeQuery(statement);

            while (fetchedSurveys.next()) {
                types.put(fetchedSurveys.getInt("Id"), fetchedSurveys.getString("Type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return types;
    }
}
