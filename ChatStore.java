package com.chatting;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChatStore
  extends HttpServlet
{
  public ChatStore() {}
  
  public void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException
  {
    PrintWriter localPrintWriter = paramHttpServletResponse.getWriter();
    try
    {
      paramHttpServletResponse.setContentType("text/html");
      
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection localConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chat_bot_ai","root","root");
      Statement localStatement = localConnection.createStatement();
      

      String str1 = paramHttpServletRequest.getParameter("uname");
      String str2 = paramHttpServletRequest.getParameter("msg");
      
      SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
      SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("hh:mm:ss a");
      Date localDate = new Date();
      String str3 = localSimpleDateFormat1.format(localDate);
      String str4 = localSimpleDateFormat2.format(localDate);
      
      String query = "INSERT INTO chatting_data VALUES (?, ?, ?, ?, ?";
      PreparedStatement preparedStatement = localConnection.prepareStatement(query);
      preparedStatement.setString(1, str1);
      preparedStatement.setString(2, str2);
      preparedStatement.setString(3, str3);
      preparedStatement.setString(4, str4);
      preparedStatement.executeUpdate();

      localConnection.close();
    }
    catch (Exception localException) {
      localException.printStackTrace();
      System.out.println("Invalid User");
    }
  }
  
  public void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException
  {
    doGet(paramHttpServletRequest, paramHttpServletResponse);
  }
}