package kata;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ConwayLife {

    private final boolean[][] grid;

    private ConwayLife(boolean[][] grid) {
        this.grid = grid;
    }

    public static GridBuilder builder() {
        return new GridBuilder();
    }

    public static class GridBuilder {

        private boolean[][] grid;

        public GridBuilder withGrid(int height, int width) {
            grid = new boolean[height][width];
            return this;
        }

        public CellBuilder withPopulatedCells() {
            return new CellBuilder(grid);
        }
    }

    public static class CellBuilder {

        private boolean[][] grid;

        public CellBuilder(boolean[][] grid) {
            this.grid = grid;
        }

        public CellBuilder at(int x, int y) {
            if(x < 0 || x > grid[0].length || y < 0 || y > grid.length)
                throw new IllegalArgumentException("Cell out of grid!");
            grid[y][x] = true;
            return this;
        }

        public ConwayLife build() {
            return new ConwayLife(grid);
        }
    }

    public static record Cell(int x, int y) { }

    private void computeSteps(int amount) {
        for(int i = 0; i < amount; i++)
            computeStep();
    }

    public void computeStep() {
        var cellsToDie = new LinkedList<Cell>();
        var cellsToBorn = new LinkedList<Cell>();
        forEachInRange((x, y) -> {
                switch(neighboursAliveAt(x, y)) {
                    case 2 -> { }
                    case 3 -> {
                        if(!grid[y][x])
                            cellsToBorn.add(new Cell(x, y));
                    }
                    default -> {
                        if(grid[y][x])
                            cellsToDie.add(new Cell(x, y));
                    }
                }
            },
            0, grid[0].length, 0, grid.length);
        cellsToDie.forEach(cell -> grid[cell.y()][cell.x()] = false);
        cellsToBorn.forEach(cell -> grid[cell.y()][cell.x()] = true);
    }

    private int neighboursAliveAt(int xPos, int yPos) {
        AtomicInteger counter = new AtomicInteger();
        int xStart = max(0, xPos - 1), xEnd = min(grid[0].length, xPos + 2),
            yStart = max(0, yPos - 1), yEnd = min(grid.length, yPos + 2);
        forEachInRange((x, y) -> {
                if(grid[y][x] && !(x == xPos && y == yPos))
                    counter.incrementAndGet();
            },
            xStart, xEnd, yStart, yEnd
        );
        return counter.get();
    }

    private void forEachInRange(BiConsumer<Integer, Integer> action, int xStart, int xEnd, int yStart, int yEnd) {
        for(int y = yStart; y < yEnd; y++) {
            for(int x = xStart; x < xEnd; x++) {
                action.accept(x, y);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(boolean[] row : grid) {
            sb.append('|');
            for(boolean cell : row) {
                sb.append(cell ? 'O' : '.');
            }
            sb.append("|\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        var game = ConwayLife.builder()
                       .withGrid(20, 25)
                       .withPopulatedCells()
                       .at(1, 2)
                       .at(2, 4)
                       .at(5, 7)
                       .at(8, 9)
                       .build();
        //???
    }
}
