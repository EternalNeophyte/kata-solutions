package kata;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.*;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

/**
 * <a href="https://www.codewars.com/kata/52b7ed099cdc285c300001cd/java">Kata details</a>
 */
public final class Interval {

    public static int sumIntervals(int[][] intervals) {
        return (intervals != null)
            ? Arrays.stream(intervals)
                    .map(ints -> new Section(ints[0], ints[1]))
                    .collect(collectingAndThen(toList(), Interval::mergeAll))
                    .stream()
                    .mapToInt(Section::length)
                    .sum()
            : 0;
    }

    private static List<Section> mergeAll(List<Section> sections) {
        Section first, second;
        boolean merging = true;
        while(merging) {
            merging = false;
            for(int i = 0; i < sections.size(); i++) {
                for(int j = i + 1; j < sections.size(); j++) {
                    first = sections.get(i);
                    second = sections.get(j);
                    if(first.overlaps(second) || second.overlaps(first)) {
                        sections.add(first.merge(second));
                        merging = sections.remove(first);
                        sections.remove(second);
                    }
                }
            }
        }
        return sections;
    }

    static record Section(int start, int end, int length) {

        public Section(int start, int end) {
            this(start, end, abs(end - start));
        }

        public boolean overlaps(Section other) {
            return this.start <= other.end && other.start <= this.end;
        }

        public Section merge(Section other) {
            return new Section(min(this.start, other.start), max(this.end, other.end));
        }
    }
}
