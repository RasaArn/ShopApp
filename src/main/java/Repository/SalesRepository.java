package Repository;

import Dto.ProductData;
import Dto.SalesData;
import Dto.UserData;

import java.sql.SQLException;
import java.util.List;

public interface SalesRepository {

    void recordSale(ProductData product, UserData user, int quantity, double totalPrice) throws SQLException;

    List<SalesData> getAllSales() throws SQLException;
}
