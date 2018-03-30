import org.springframework.context.support.ClassPathXmlApplicationContext;

import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/context.xml");

        PersonRepository repository = (PersonRepository) ctx.getBean("repository");

        Person dmitry = new Person(22, "Dmitry", "Nikiforov", 1234, "Cheboksary");
        Person ivan = new Person(23, "Ivan", "Azanov", 3211, "Ufa");
        Person naum = new Person(21, "Dmitry", "Naumov", 2345, "Cheboksary");
        Person max = new Person(23, "Max", "Yalunin", 54654, "Cheboksary");

        repository.insertPerson(asList(dmitry, ivan, naum, max));

        repository.selectAll().forEach(System.out::println);
        System.out.println("Person with Max ID is "+ repository.personsWithMaxId().get(0).getPersonID());

        repository.deleteById(23);
        repository.selectAll().forEach(System.out::println);
    }
}