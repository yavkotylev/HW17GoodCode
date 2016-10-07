package ru.sbt.bit.ood.solid.homework;


import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class SalaryHtmlReportNotifier {

    private Connection connection;

    public SalaryHtmlReportNotifier(Connection databaseConnection) {
        this.connection = databaseConnection;
    }

    public void salaryReport(String departmentId, LocalDate dateFrom, LocalDate dateTo, String recipients) {
        try {
            PreparedStatement ps = prepareStatment(connection);
            injectParameters(ps, departmentId, dateFrom, dateTo);
            sendReport(recipients, Report.fillReport(ps.executeQuery()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PreparedStatement prepareStatment(Connection connection) {
        try {
            return connection.prepareStatement("select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary from employee emp left join" +
                    "salary_payments sp on emp.id = sp.employee_id where emp.department_id = ? and" +
                    " sp.date >= ? and sp.date <= ? group by emp.id, emp.name");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void injectParameters(PreparedStatement ps, String departmentId, LocalDate dateFrom, LocalDate dateTo) {
        try {
            ps.setString(0, departmentId);
            ps.setDate(1, new java.sql.Date(dateFrom.toEpochDay()));
            ps.setDate(2, new java.sql.Date(dateTo.toEpochDay()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendReport(String recipients, StringBuilder resultingHtml) {
        try {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("mail.google.com");
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipients);
            helper.setText(resultingHtml.toString(), true);
            helper.setSubject("Monthly department salary report");
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}