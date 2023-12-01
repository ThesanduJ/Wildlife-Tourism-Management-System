package lk.ijse.wildlifetourismmanagementsystem.model;

import lk.ijse.wildlifetourismmanagementsystem.db.DbConnection;
import lk.ijse.wildlifetourismmanagementsystem.dto.TouristDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TouristModel {
    public boolean isAdd(TouristDto dto) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql="INSERT INTO tourist VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1, dto.getTouristId());
        pstm.setString(2, dto.getNIC());
        pstm.setString(3, dto.getName());
        pstm.setString(4, dto.getAgeLevel());
        pstm.setString(5, dto.getEmail());
        pstm.setString(6, dto.getMobileNumber());
        pstm.setString(7, dto.getRegion());
        pstm.setString(8, dto.getCashierId());
        pstm.setString(9, dto.getAddress());

        boolean isAdd=pstm.executeUpdate()>0;
        return isAdd;
    }

    public boolean isDelete(String touristId) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="DELETE FROM tourist WHERE tourist_id=?";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1,touristId);

        return pstm.executeUpdate()>0;
    }

    public boolean isUpdate(TouristDto dto) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="UPDATE tourist SET  nic=?,name=?,adult_or_child=?,tourist_email=?,phone_number=?,local_or_not=?,cashier_id=?,address=? WHERE tourist_id=?";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1, dto.getNIC());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAgeLevel());
        pstm.setString(4, dto.getEmail());
        pstm.setString(5, dto.getMobileNumber());
        pstm.setString(6, dto.getRegion());
        pstm.setString(7, dto.getCashierId());
        pstm.setString(8, dto.getAddress());
        pstm.setString(9, dto.getTouristId());

        return pstm.executeUpdate()>0;
    }

    public static List<TouristDto> getTourist() throws SQLException, ClassNotFoundException {
        List<TouristDto> list = new ArrayList<>();
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM tourist");
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            list.add(new TouristDto(
                    rs.getString("touristId"),
                    rs.getString("NIC"),
                    rs.getString("name"),
                    rs.getString("ageLevel"),
                    rs.getString("email"),
                    rs.getString("mobileNumber"),
                    rs.getString("region"),
                    rs.getString("cashierId"),
                    rs.getString("address")

            ));
        }
        return list;
    }
}
