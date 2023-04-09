package Repository;
import Dto.UserData;
import java.sql.SQLException;
import java.util.List;

public interface CustomerRepository {

    boolean login (UserData user) throws SQLException;
    int registerUser(UserData user) throws SQLException;
    double checkBalance(UserData user) throws SQLException;
    void addMoneyToBalance(UserData user, double amount) throws SQLException;

    void updateUserInformation(UserData user) throws SQLException;

    UserData getUserById(int userId) throws SQLException;
    UserData getUserId(int userId) throws SQLException;
    List<UserData> getAllUsers() throws SQLException;


}
