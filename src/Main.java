import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Павел", "Лидия", "Юлия", "Александр", "Ольга", "Алексей");
        List<String> families = Arrays.asList("Смирнов", "Орешкина", "Петрова", "Фаер", "Терентьева", "Воронин");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long count = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .count();

        List<String> conscripts = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= 27)
                .filter(person -> person.getSex() == Sex.MAN)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());

        List<String> workers = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> {
                    if ((person.getSex() == Sex.MAN) && person.getAge() <= 65) {
                        return true;
                    } else if ((person.getSex() == Sex.WOMAN) && person.getAge() <= 60) {
                        return true;
                    }
                    return false;
                })
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
    }
}