/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.manager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sample.utils.DBUtils;

/**
 *
 * @author nguye
 */
public class ManagerDAO {
    public ManagerDTO checkLogin (String email, String password) throws SQLException {
        ManagerDTO manager= null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if(conn!=null) {
                String sql=" SELECT lecturerID, email, name, birthday, gender, phone, address, createDate, status, subject "
                        + "FROM tblLecturers " 
                        + "WHERE email = ? AND password = ? ";
                stm= conn.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                rs=stm.executeQuery();
                if (rs.next()){
                    String lecturerID= rs.getString("lecturerID");
                    String name= rs.getString("name");
                    Date birthday= rs.getDate("birthday");
                    boolean gender= rs.getBoolean("gender");
                    String phone= rs.getString("phone");
                    String address= rs.getString("address");
                    Date createDate= rs.getDate("createDate");
                    boolean status= rs.getBoolean("status");
                    String subject= rs.getString("subject");
                    manager = new ManagerDTO(lecturerID, email, password, name, birthday, gender, phone, address, createDate, status, subject);
                }
            }
        } catch (Exception e) {          
        } finally {
            if(rs!=null) rs.close();
            if(stm!=null) stm.close();
            if(conn!=null) conn.close();
        }
        return manager;
    }
}
