import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day1 {
    public static void main(String[] args) throws IOException {
        List<Integer> elves = new ArrayList<>();
        int sum = 0;
        for (String line : Files.readAllLines(Path.of("day1.txt"))) {
            if (line.isBlank()) {
                elves.add(sum);
                sum = 0;
            } else {
                sum += Integer.parseInt(line);
            }
        }
        elves.stream()
                .sorted()
                .skip(elves.size() - 3)
                .reduce(Integer::sum)
                .ifPresentOrElse(System.out::println, () -> System.out.println(0));
    }
}
