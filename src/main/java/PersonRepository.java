import java.util.List;

public interface PersonRepository {
    List<Person> selectAll();
    List<Person> personsWithMaxId();
    void insertPerson(List<Person> person);
    void deleteById(int name);
}
