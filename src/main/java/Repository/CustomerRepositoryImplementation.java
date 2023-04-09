package Repository;
import Dto.UserData;
import Entities.Role;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImplementation implements CustomerRepository {


    private Connection connection;

    public CustomerRepositoryImplementation() throws SQLException {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getConnection();
    }


    public double checkBalance(UserData user) throws SQLException {
        String query = "SELECT balance FROM user_data WHERE userId=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, user.getUserId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                double balance = resultSet.getDouble("balance");
                return balance;
            } else {
                System.out.println("User not found!");
                return 0.0;
            }
        }
    }

    @Override
    public void addMoneyToBalance(UserData user, double amount) throws SQLException {
        String query = "UPDATE user_data SET balance=balance+? WHERE userId=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, amount);
            statement.setInt(2, user.getUserId());
            statement.executeUpdate();
        }
    }


    public boolean login(UserData user) throws SQLException {
        String query = "SELECT email FROM user_data WHERE email=? AND password=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.executeQuery();
       }return true;
    }

    public int registerUser(UserData user) throws SQLException {
        String query = "INSERT INTO user_data (userNameSurname, email, password, addressStreet, addressBuildingNumber, apartmentNumber, city, country, zipCode, balance, userRole) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        // Set default role to "customer" if userRole property is null
        if(user.getUserRole() == null) {
            user.setUserRole(Role.CUSTOMER);
        }

        int userId;
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUserNameSurname());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getAddressStreet());
            statement.setString(5, user.getAddressBuildingNumber());
            statement.setString(6, user.getApartmentNumber());
            statement.setString(7, user.getCity());
            statement.setString(8, user.getCountry());
            statement.setString(9, user.getZipCode());
            statement.setDouble(10, user.getBalance());
            statement.setString(11, user.getUserRole().toString());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                userId = rs.getInt(1);
                user.setUserId(userId);
                return userId;
            } else {
                throw new SQLException("Failed to retrieve the newly registered user's ID.");
            }
        }
    }
    public void updateUserInformation(UserData user) throws SQLException {
        String query = "UPDATE user_data SET userNameSurname=?, email=?, password=?, addressStreet=?, addressBuildingNumber=?, apartmentNumber=?, city=?, country=?, zipCode=?, balance=? WHERE userId=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUserNameSurname());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getAddressStreet());
            statement.setString(5, user.getAddressBuildingNumber());
            statement.setString(6, user.getApartmentNumber());
            statement.setString(7, user.getCity());
            statement.setString(8, user.getCountry());
            statement.setString(9, user.getZipCode());
            statement.setDouble(10, user.getBalance());
            statement.setInt(11, user.getUserId());
            statement.executeUpdate();

        }
    }

   /* @Override
    public void deleteUser(int userId) throws SQLException {
        String query = "DELETE FROM user_data WHERE userId=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            int numRowsAffected = statement.executeUpdate();
            if (numRowsAffected == 0) {
                throw new SQLException("User with userId=" + userId + " not found.");
            }
        }
    }*/

    @Override
    public UserData getUserById(int userId) throws SQLException {
        String query = "SELECT * FROM user_data WHERE userId=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            UserData user = null;
            if (resultSet.next()) {
                user = new UserData();
                user.setUserId(resultSet.getInt("userId"));
                user.setPassword(resultSet.getString("password"));
                user.setUserNameSurname(resultSet.getString("userNameSurname"));
                user.setEmail(resultSet.getString("email"));
                user.setAddressStreet(resultSet.getString("addressStreet"));
                user.setAddressBuildingNumber(resultSet.getString("addressBuildingNumber"));
                user.setApartmentNumber(resultSet.getString("apartmentNumber"));
                user.setCity(resultSet.getString("city"));
                user.setCountry(resultSet.getString("country"));
                user.setZipCode(resultSet.getString("zipCode"));
                user.setBalance(resultSet.getDouble("balance"));
                user.setUserRole(Role.valueOf(resultSet.getString("userRole")));
            } else {
                throw new SQLException("User not found for userId " + userId);
            }
            resultSet.close();
            return user;
        }
    }

    @Override
    public UserData getUserId(int userId) throws SQLException {
        return null;
    }

    public List<UserData> getAllUsers() throws SQLException {
        String query = "SELECT * FROM user_data";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<UserData> userList = new ArrayList<>();
        while (resultSet.next()) {
            int userId = resultSet.getInt("userId");
            String password = resultSet.getString("password");
            String userNameSurname = resultSet.getString("userNameSurname");
            String email = resultSet.getString("email");
            String addressStreet = resultSet.getString("addressStreet");
            String addressBuildingNumber = resultSet.getString("addressBuildingNumber");
            String apartmentNumber = resultSet.getString("apartmentNumber");
            String city = resultSet.getString("city");
            String country = resultSet.getString("country");
            String zipCode = resultSet.getString("zipCode");
            double balance = resultSet.getDouble("balance");
            Entities.Role userRole = Entities.Role.valueOf(resultSet.getString("userRole"));

            UserData userData = new UserData(userId, password, userNameSurname, email, addressStreet, addressBuildingNumber,
            apartmentNumber, city, country, zipCode, balance, userRole);

            userList.add(userData);
        }

        resultSet.close();
        statement.close();
        return userList;
    }

}
