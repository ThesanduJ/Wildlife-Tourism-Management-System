package lk.ijse.wildlifetourismmanagementsystem.model;

import lk.ijse.wildlifetourismmanagementsystem.db.DbConnection;
import lk.ijse.wildlifetourismmanagementsystem.dto.PackageDto;
import lk.ijse.wildlifetourismmanagementsystem.dto.TouristDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackageModel {
    public boolean IsAdd(PackageDto dto) throws SQLException {

        Connection connection= DbConnection.getInstance().getConnection();
        String sql="INSERT INTO package VALUES(?,?,?,?,?)";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1, dto.getPackageId());
        pstm.setString(2,dto.getDescription());
        pstm.setDouble(3,dto.getPrice());
        pstm.setString(4, dto.getFeatures());
        pstm.setString(5, dto.getPackageType());

        return pstm.executeUpdate()>0;
    }

    public List<PackageDto> loadAllTourist() throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM package";
            PreparedStatement pstm = connection.prepareStatement(sql);

            List<PackageDto> packageList = new ArrayList<>();

            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                packageList.add(new PackageDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }

            return packageList;
    }

    public PackageDto search(String packageID) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
         String sql="SELECT * FROM package WHERE package_id = ?";
         PreparedStatement pstm=connection.prepareStatement(sql);
         pstm.setString(1,packageID);
         ResultSet resultSet=pstm.executeQuery();
         PackageDto dto=null;

        if(resultSet.next()) {
            String packageId = resultSet.getString(1);
            String description = resultSet.getString(2);
            double packagePrice = resultSet.getDouble(3);
            String packageFeatures = resultSet.getString(4);
            String packageType=resultSet.getString(5);

            dto = new PackageDto(packageId, description, packagePrice, packageFeatures,packageType);
        }
        return dto;
    }

    public boolean isDelete(String packageId) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="DELETE FROM package WHERE package_id=?";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1,packageId);
        return pstm.executeUpdate()>0;
    }

    public boolean isUpdate(PackageDto dto) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="UPDATE package SET description=?,package_price=?,package_features=?,package_type=? WHERE package_id=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1, dto.getDescription());
        pstm.setString(2, String.valueOf(dto.getPrice()));
        pstm.setString(3, dto.getFeatures());
        pstm.setString(4, dto.getPackageType());
        pstm.setString(5, dto.getPackageId());

        return pstm.executeUpdate()>0;
    }
}
