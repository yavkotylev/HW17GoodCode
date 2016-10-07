package ru.sbt.bit.ood.solid.homework;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Yaroslav on 07.10.16.
 */
public class Report {
    public static StringBuilder fillReport(ResultSet resultSet) {
        StringBuilder resultingHtml = new StringBuilder();
        resultingHtml.append("<html><body><table><tr><td>Employee</td><td>Salary</td></tr>");
        double totals = 0;
        try {
            while (resultSet.next()) {
                totals += fillEmployeesData(resultSet, resultingHtml);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resultingHtml.append("<tr><td>Total</td><td>").append(totals).append("</td></tr>");
        resultingHtml.append("</table></body></html>");
        return resultingHtml;
    }

    private static double fillEmployeesData(ResultSet resultSet, StringBuilder resultingHtml) {
        resultingHtml.append("<tr>");
        try {
            resultingHtml.append("<td>").append(resultSet.getString("emp_name")).append("</td>");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + " exception at the name");
        }
        try {
            resultingHtml.append("<td>").append(resultSet.getDouble("salary")).append("</td>");
        } catch (SQLException e) {
            throw new RuntimeException(e + " exception at the salary");
        }
        resultingHtml.append("</tr>");
        try {
            return resultSet.getDouble("salary");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
