import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day1 {
    public static void main(String[] args) throws IOException {
        int highest = 0;
        int sum = 0;
        for (String line : Files.readAllLines(Path.of("day1.txt"))) {
            if (line.isBlank()) {
                if (sum > highest) {
                    highest = sum;
                }
                sum = 0;
            } else {
                sum += Integer.parseInt(line);
            }
        }
        System.out.println(highest);
    }
}
