package reflectionw10;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookSummarizer {

    BookReader reader = new BookReader();
    List<String> book = reader.readLines("reflectionw10/alice.txt");

    protected Long getNumberOfWords() {
        return book.stream()
                .count();
    }

    protected Long getNumberOfDistinctWords() {
        return book.stream()
                .distinct()
                .count();
    }

    Map<String, Long> wordAppearances = book.stream()
            .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

    protected List<String> getFiveMostAppearingWords() {
        return wordAppearances.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(e -> e.getKey() + " appears " + e.getValue() + " times")
                .limit(5)
                .collect(Collectors.toList());
    }

    protected List<String> getFiveMostAppearingLetters() {
        return book.stream()
                .map(word -> word.split(""))
                .flatMap(e -> Stream.of(e))
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(5)
                .map(e -> e.getKey() + " | " + e.getValue())
                .collect(Collectors.toList());
    }

    protected List<String> getLongestWord() {
        return book.stream()
                .collect(Collectors.groupingBy(e->e.length(), Collectors.toList()))
                .entrySet().stream()
                .sorted((e1,e2)->e2.getKey().compareTo(e1.getKey()))
                .map(e->e.getKey().toString())
                .limit(1)
                .collect(Collectors.toList());
    }
}
