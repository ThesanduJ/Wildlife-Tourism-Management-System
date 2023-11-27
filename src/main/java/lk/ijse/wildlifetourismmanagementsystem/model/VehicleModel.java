package lk.ijse.wildlifetourismmanagementsystem.model;

import lk.ijse.wildlifetourismmanagementsystem.db.DbConnection;
import lk.ijse.wildlifetourismmanagementsystem.dto.VehicleDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VehicleModel {
    public boolean isAdd(VehicleDto dto) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql="INSERT INTO vehicle VALUES (?,?,?,?,?,?)";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1, dto.getRegistration());
        pstm.setString(2, dto.getPackageId());
        pstm.setString(3, dto.getAdminEmail());
        pstm.setString(4, dto.getPermitNo());
        pstm.setString(5, dto.getPermitD());
        pstm.setString(6, dto.getLicenceD());

        return pstm.executeUpdate()>0;
    }

    public boolean isDelete(String registration) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="DELETE FROM vehicle WHERE registration_id = ?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,registration);

        return pstm.executeUpdate()>0;
    }

    public boolean isUpdate(VehicleDto dto) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="UPDATE vehicle SET  package_id=?,admin_email=?,permit_no=?,permit_expair_date=?,licence_expair_date=? WHERE registration_id=?";
        PreparedStatement pstm=connection.prepareStatement(sql);


        pstm.setString(1, dto.getPackageId());
        pstm.setString(2, dto.getAdminEmail());
        pstm.setString(3, dto.getPermitNo());
        pstm.setString(4, dto.getPermitD());
        pstm.setString(5, dto.getLicenceD());
        pstm.setString(6, dto.getRegistration());

        return pstm.executeUpdate()>0;
    }
}
