import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day2 {
    public static void main(String[] args) throws IOException {
        System.out.println(Files.readAllLines(Path.of("day2.txt")).stream()
                .map(s -> new Game(s, true))
                .mapToInt(Game::score)
                .reduce(Integer::sum)
                .orElse(0));
    }

    enum Hand {
        ROCK,
        PAPER,
        SCISSORS;

        static Hand of(char s) {
            switch (s) {
                case 'A', 'X' -> {
                    return ROCK;
                }
                case 'B', 'Y' -> {
                    return PAPER;
                }
                case 'C', 'Z' -> {
                    return SCISSORS;
                }
            }
            throw new IllegalArgumentException("Bad value " + s);
        }
    }

    static class Game {
        private Hand me;
        private final Hand them;

        public Game(String line, boolean lastColumnIndicatesResult) {
            if (lastColumnIndicatesResult) {
                Hand me1;
                // this is kinda hacky
                them = Hand.of(line.charAt(0));
                switch (line.charAt(2)) {
                    case 'X' -> {
                        if (them == Hand.PAPER) me1 = Hand.ROCK;
                        else if (them == Hand.ROCK) me1 = Hand.SCISSORS;
                        else if (them == Hand.SCISSORS) me1 = Hand.PAPER;
                    }
                    case 'Y' -> {
                        me1 = them;
                    }
                    case 'Z' -> {
                        if (them == Hand.PAPER) me1 = Hand.SCISSORS;
                        else if (them == Hand.ROCK) me1 = Hand.PAPER;
                        else if (them == Hand.SCISSORS) me1 = Hand.ROCK;
                    }
                }
                // base case, should never be reached
            } else {
                // assuming all the lines are the same
                me = Hand.of(line.charAt(2));
                them = Hand.of(line.charAt(0));
            }
        }

        public int score() {
            int score = 0;
            switch (me) {
                case ROCK -> score += 1;
                case PAPER -> score += 2;
                case SCISSORS -> score += 3;
            }
            if (me == them) score += 3;
            if (me == Hand.ROCK && them == Hand.SCISSORS)       score += 6;
            else if (me == Hand.PAPER && them == Hand.ROCK)     score += 6;
            else if (me == Hand.SCISSORS && them == Hand.PAPER) score += 6;
            return score;
        }
    }
}
