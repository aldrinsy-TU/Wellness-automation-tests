package testdataobjects;

import java.util.ArrayList;
import java.util.List;

public class ExpectedResults {
    List<String> column = new ArrayList<>();

    public ExpectedResults(List<String> column) {
        this.column = column;
    }

    public ExpectedResults() {

    }

    public List<String> getColumn() {
        return column;
    }

    public void setColumn(List<String> column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "ExpectedResults{" +
                "column=" + column +
                '}';
    }
}