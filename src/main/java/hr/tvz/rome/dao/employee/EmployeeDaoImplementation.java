package hr.tvz.rome.dao.employee;

import hr.tvz.rome.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Marko on 22.5.2016..
 */
@Repository
public class EmployeeDaoImplementation extends JdbcDaoSupport implements EmployeeDao {

    @Autowired
    public EmployeeDaoImplementation(DataSource dataSource) {
        super();
        setDataSource(dataSource);
    }

    @Override
    public List<Employee> fetchAllWorkers() {
        String employeeQuery = "SELECT * FROM user_auth";
        return getJdbcTemplate().query(employeeQuery, new EmployeeMapper());

    }

    private class EmployeeMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String authority = resultSet.getString("authority");
            return new Employee(id, firstName, lastName, username, password, authority);
        }
    }


}
