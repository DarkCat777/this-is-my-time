package edu.app.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class ServerRestApplicationTests {
    @FunctionalInterface
    interface Mathematic {
        public double operation(double x, double y);

        default double operator(double x, double y) {
            return this.operation(x, y);
        }
    }

    @Test
    void functionalInterfaceTest() {
        /*
         * @FunctionalInterface
         * Solo permiten un metodo.
         * Aunque tambien admiten metodos por defecto.
         */
        Mathematic mathematic = (x, y) -> x * y;
        System.out.println("mathematic.operation(2, 3) = " + mathematic.operation(2, 3));
        System.out.println("mathematic.operator(4, 8) = " + mathematic.operator(4, 8));
    }

    @Test
    void consumerTest() {
        // Consumer to display a number
        Consumer<Integer> display = System.out::println;

        // Implement display using accept()
        display.accept(10);

        // Consumer to multiply 2 to every integer of a list
        Consumer<List<Integer>> modify = list ->
        {
            for (int i = 0; i < list.size(); i++)
                list.set(i, 2 * list.get(i));
        };

        // Consumer to display a list of numbers
        Consumer<List<Integer>>
                dispList = list -> list.forEach(a -> System.out.print(a + " "));

        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(3);

        // Implement modify using accept()
        modify.accept(list);

        // Implement dispList using accept()
        dispList.accept(list);
    }

    @Test
    void runnableTest() {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        new Thread(runnable).start();
    }

    @Test
    void functionTest() {
        Function<String, Integer> func = String::length;
        Integer apply = func.apply("mkyong");   // 6
        System.out.println(apply);
        Function<Integer, Integer> function = integer -> {
            return integer * 2;
        };
        System.out.println("func.andThen(function).apply(\"Hello World\") = "
                + func.andThen(function).apply("Hello World"));
    }

    @Test
    void predicateTest() {
        Predicate<Integer> noGreaterThan5 = x -> x > 5;
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> collect = list.stream()
                .filter(noGreaterThan5)
                .collect(Collectors.toList());
        collect.forEach(System.out::print);
    }

    @Test
    void contextLoads() throws IOException {
        /*
         * TESTING JAVA 8 NEWs
         */
        /*
         * Optional -> Para evitar los null pointer exception
         */
        Optional<String> stringOptional = Optional.empty();
        String s = stringOptional.orElse(null);
        /*
         * @FunctionalInterface -> X
         * Consumer -> X
         * Runnable -> X
         * Function -> X
         * Predicate ->
         */
        /*
         * JAVA 9 NEWS
         *
         * METODOS PRIVADOS EN LAS INTERFACES
         *
         */
        ProcessBuilder pb = new ProcessBuilder("notepad.exe");
        String np = "Not Present";
        Process p = pb.start();
        ProcessHandle.Info info = p.info();
        System.out.printf("Process ID : %s%n", p.pid());
        System.out.printf("Command name : %s%n", info.command().orElse(np));
        System.out.printf("Command line : %s%n", info.commandLine().orElse(np));

        System.out.printf("Start time: %s%n",
                info.startInstant().map(i -> i.atZone(ZoneId.systemDefault())
                        .toLocalDateTime().toString()).orElse(np));

        System.out.printf("Arguments : %s%n",
                info.arguments().map(a -> Stream.of(a).collect(
                        Collectors.joining(" "))).orElse(np));

        System.out.printf("User : %s%n", info.user().orElse(np));
        /*
        JAVA 10,11,12
        var
        annotations in lambda expresions
        Switch expresion
        ScriptEngine
        ParallelStream
        etc
         */
    }

}
