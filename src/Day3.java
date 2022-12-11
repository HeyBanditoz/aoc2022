import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.TreeSet;

public class Day3 {
    public static void main(String[] args) throws IOException {
        System.out.println(Files.readAllLines(Path.of("day3.txt")).stream()
                .map(Rucksack::new)
                .mapToInt(Rucksack::score)
                .reduce(Integer::sum)
                .orElse(0));

    }

    static class Rucksack {
        private final String compartmentOne;
        private final String compartmentTwo;

        public Rucksack(String s) {
            int i = s.length() / 2;
            compartmentOne = s.substring(0, i);
            compartmentTwo = s.substring(i);
        }

        public int score() {
            Set<Integer> lettersInFirst = new TreeSet<>();
            Set<Integer> lettersInSecond = new TreeSet<>();
            compartmentOne.chars().forEach(lettersInFirst::add);
            compartmentTwo.chars().forEach(lettersInSecond::add);
            Set<Integer> lol = new TreeSet<>(lettersInFirst);
            for (Integer i : lettersInSecond) {
                if (!lol.add(i)) {
                    if (65 <= i && i <= 90) {
                        return i - 38; // ascii table offset
                    } else {
                        return i - 96;
                    }
                }
            }
            throw new IllegalArgumentException("should never happen");
        }
    }
}
