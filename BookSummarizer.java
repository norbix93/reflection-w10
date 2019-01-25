package reflectionw10;


import java.util.List;
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


    protected List<String> getFiveMostAppearingWords() {
        return book.stream()
                .filter(e->!e.isEmpty())
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(e -> e.getKey() + " | " + e.getValue())
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
                .limit(1)
                .map(e->e.getValue())
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
    protected Long getNumberOfTimesAliceAppears(){
        return book.stream()
                .filter(e->e.equalsIgnoreCase("Alice"))
                .count();
    }
}
