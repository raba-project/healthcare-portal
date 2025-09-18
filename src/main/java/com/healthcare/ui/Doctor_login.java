package com.healthcare.ui;

import java.sql.*;
import java.util.Scanner;

import com.healthcare.dao.DBConnection;

public class Doctor_login {
    private static final String url = "jdbc:mysql://localhost:3306/project";
    private static final String user = "root";
    private static final String pass = "Happy";

    static Connection con;
    static PreparedStatement prSt;
    static Statement stmt;

    public static  void Login(String name,String password,String mail,String mobile){


              String query="Insert into users(username,password,email,phone,role) values (?,?,?,?,'Doctor')";
              try (Connection con = DBConnection.getConnection();){
                  prSt = con.prepareStatement(query);
                  prSt.setString(1, name);
                  prSt.setString(2, password);
                  prSt.setString(3, mail);
                  prSt.setString(4, mobile);
                  int count = prSt.executeUpdate();
                  System.out.println(count + " doctor inserted successfully!");
                  stmt = con.createStatement();
                  String query1 = "Select * FROM users WHERE role='DOCTOR'";
                  ResultSet rs = stmt.executeQuery(query1);
                  while (rs.next()) {
                      int id = rs.getInt("user_id");
                      String uname = rs.getString("username");
                      String email = rs.getString("email");
                      String phone = rs.getString("phone");
                      System.out.println(id + "   " + uname + "   " + email + "   " + phone);
                  }

                  rs.close();
                  stmt.close();
                  prSt.close();
                  con.close();
              }
              catch (SQLException ex) {
                  System.out.println(ex.getMessage());
              }
    }



    public static void Appointments(int doc_id){
        //Select appointment_id,patient_id ,status from appointments where doctor_id=2 and DATE(appointment_date) = CURDATE();
        String query3=" Select appointment_id,patient_id ,status from appointments where doctor_id=? and DATE(appointment_date) = CURDATE()";
        try (Connection con = DBConnection.getConnection();){
            prSt = con.prepareStatement(query3);
            prSt.setInt(1,doc_id);
            ResultSet rs = prSt.executeQuery();
            stmt = con.createStatement();
           while (rs.next()) {
                int id = rs.getInt("appointment_id");
                int appointment = rs.getInt("patient_id");
                int doctor = rs.getInt("doctor_id");
                String phone = rs.getString("status");
                System.out.println(id + "   " + appointment + "   " + doctor + "   " + phone);
            } 

            //rs.close();
            //stmt.close();
            prSt.close();
            con.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public static void prescribe(int id){
        String query4="Update prescriptions set medicines =?,doctor_notes=? where prescription_id=?";
        try (Connection con = DBConnection.getConnection();){
            prSt = con.prepareStatement(query4);
            Scanner sc=new Scanner(System.in);
            System.out.print("Enter medicines");
            String med=sc.next();
            System.out.println("Enter notes");
            String note=sc.next();
            prSt.setString(1,med);
            prSt.setString(2,note);
            
            
            prSt.setInt(3,id);
            String query5="Select * from prescriptions";
            stmt = con.createStatement();
            int count=prSt.executeUpdate();
            ResultSet rs = stmt.executeQuery(query5);
            while (rs.next()) {
                String uname = rs.getString("appointment_id");
                String email = rs.getString("doctor_notes");
                String phone = rs.getString("medicines");
                System.out.println(id + "   " + uname + "   " + email + "   " + phone);
            }

            rs.close();
            stmt.close();
            prSt.close();
            con.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        }

    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
      System.out.println("1.Login as a doctor ");
      System.out.println("2.view today's appointments");
      System.out.println("3.update Diagnosis");
      System.out.println("4.check doctors availability");
      System.out.println("Enter your option->");
      int user_option=sc.nextInt();
      String username,password,email,phone;
      int prescription_id;
      int doctor_id;
      int doc_id;




        switch (user_option){
              case 1:
                  System.out.println("Enter username");
                  username=sc.next();
                  System.out.println("Enter password");
                  password=sc.next();
                  System.out.println("Enter email");
                  email=sc.next();
                  System.out.println("Enter phone");
                  phone=sc.next();
                  Login(username,password,email,phone);
                  break;

              case 2:
                  System.out.println("Today's Appointment");
                  System.out.println("Enter your id to view appointments");
                  doc_id=sc.nextInt();
                  Appointments(doc_id);
                  break;
              case 3:
                   System.out.println("Enter patient's prescription id->");
                   prescription_id=sc.nextInt();
                   prescribe(prescription_id);
                   break;
              case 4:
                  System.out.println("Enter Doctors id->");
                  doctor_id=sc.nextInt();
                  available(doctor_id);
                  break;
              default:
                  System.out.println("select appropriate option");
      }

    }

    private static void available(int doctorId) {
        try (Connection con = DBConnection.getConnection();){
            String query6 = "Select availability from doctors where doctor_id=?";
            PreparedStatement pst = con.prepareStatement(query6);
            pst.setInt(1, doctorId);
            stmt= con.createStatement();// pass doctor ID
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String availability = rs.getString("availability");
                if (availability != null && !availability.isEmpty()) {
                    System.out.println("Doctor is available: " + availability);
                } else {
                    System.out.println("Doctor is not available right now.");
                }
            } else {
                System.out.println("Doctor ID not found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }}

