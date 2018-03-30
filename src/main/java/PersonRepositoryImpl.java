import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonRepositoryImpl implements PersonRepository{

    private NamedParameterJdbcTemplate jdbcTemplate;

    public PersonRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> selectAll() {
        String query = " select * from Persons";
        return  jdbcTemplate.query(query, (RowMapper) (resultSet, rowNumber) -> {
                    return new Person(
                            resultSet.getInt("PersonID"),
                            resultSet.getString("LastName"),
                            resultSet.getString("FirstName"),
                            resultSet.getInt("Address"),
                            resultSet.getString("City")
                    );
                }
        );

    }

    public List<Person> personsWithMaxId() {
        String query = "select * from PERSONS where PersonID = (select MAX(PersonID) from PERSONS) ";
        return  jdbcTemplate.query(query, (RowMapper) (resultSet, rowNumber) -> {
                    return new Person(
                            resultSet.getInt("PersonID"),
                            resultSet.getString("LastName"),
                            resultSet.getString("FirstName"),
                            resultSet.getInt("Address"),
                            resultSet.getString("City")
                    );
                }
        );
    }

    public void insertPerson(List<Person> people) {
        String query = "INSERT INTO PERSONS (PersonID, LastName, FirstName, Address, City) VALUES (:personID,:lastName,:firstName,:address, :city )";
        HashMap<String, Object> params = new HashMap<>();
        for (Person person : people) {
            params.put("personID", person.getPersonID());
            params.put("lastName", person.getLastName());
            params.put("firstName", person.getFirstName());
            params.put("address", person.getAddress());
            params.put("city", person.getCity());

            jdbcTemplate.update(query, params);
        }
    }

    public void deleteById(int personId) {
        String query = "DELETE FROM PERSONS where PersonID = :PersonID";
        Map namedParameters = new HashMap();
        namedParameters.put("PersonID", Integer.valueOf(personId));
        jdbcTemplate.update(query, namedParameters);
    }
}
