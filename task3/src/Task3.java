import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Person {
    private final String id;
    private final String name;
    private final int age;

    public Person(final String id, final String name, final int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

class Mapper implements Function<Person, Integer> {

    @Override
    public Integer apply(final Person person) {
        return person.getAge();
    }
}

public class Task3 {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("1", "Alice", 28),
                new Person("2", "Trudy", 32),
                new Person("3", "Anna", 24),
                new Person("4", "Bob", 40),
                new Person("5", "James", 18)
        );

        String s = people.stream().map(p -> p.getId()).reduce("", (x, y) -> {
            if(x.isBlank())
                return y;
            return x + " " + y;
        });
        System.out.println("Ids: " + s);

        // a) Apresente a contagem de pessoas cuja idade é igual ou superior à média na coleção.
        double average = people.stream().map(p -> p.getAge()).reduce(0, (x, y) -> x + y) / people.size();
        //mapToInt(p -> p.getAge()).sum() / people.size();
        System.out.println("Média: " + average);
        System.out.println(people.stream().filter(p -> p.getAge() >= average).count());

        // b) Apresente o desvio padrão das idades na coleção.
        double stddev = Math.sqrt(people.stream().map(p -> Math.pow(p.getAge() - average, 2)).reduce(0d, (x, y) -> x + y) / people.size());
        System.out.println("Desvio padrão: " + stddev);

        // c) Construa um mapa cuja chave é o `id` de cada pessoa, e o valor é o objeto pessoa. Mostre o conteúdo do
        // mapa no terminal com o seguinte formato para cada registo: `id=Person(id, name, age)`
        people.stream().collect(Collectors.toMap(p -> p.getId(), p -> p)).forEach((k,v) -> System.out.println(k+"=Person("+v.getId()+","+v.getName()+","+v.getAge()+")"));

//        people.stream().collect(Collectors.toMap(p -> p.getId(), p -> p)).entrySet().forEach(System.out::println);
//
//        Map<String, Person> m = people.stream().collect(Collectors.toMap(p -> p.getId(), p -> p));
//        for(Map.Entry<String, Person> e : m.entrySet()) {
//            System.out.println(e.getKey() + " -- " + e.getValue());
//        }
    }
}
