package lk.ijse.wildlifetourismmanagementsystem.model;

import javafx.fxml.Initializable;
import lk.ijse.wildlifetourismmanagementsystem.db.DbConnection;
import lk.ijse.wildlifetourismmanagementsystem.dto.CashierDto;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CashierModel  {


    public boolean addCashier(CashierDto dto) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="INSERT INTO cashier VALUES(?,?,?,?)";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1, dto.getCashierId());
        pstm.setString(2, dto.getCashierUsername());
        pstm.setString(3, dto.getCashierPassword());
        pstm.setString(4, dto.getAdminEmail());

        boolean isSaved=pstm.executeUpdate()>0;
        return isSaved;

    }


    public boolean isDelete(String cashierID) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="DELETE FROM cashier WHERE cashier_id=?";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1,cashierID);
        return pstm.executeUpdate()>0;
    }

    public boolean isUpdate(CashierDto dto) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="UPDATE cashier SET username=?,password=?,admin_email=? WHERE cashier_id=?";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1, dto.getCashierUsername());
        pstm.setString(2, dto.getCashierPassword());
        pstm.setString(3, dto.getAdminEmail());
        pstm.setString(4, dto.getCashierId());

       return pstm.executeUpdate()>0;
    }

}

