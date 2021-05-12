package common;

import org.junit.Assert;
import testdataobjects.ExpectedResults;

import java.util.HashMap;
import java.util.List;

public class ReadCSVUtil {
    public static List<String> getExpectedResult(String Key){
        CSVReader csvReader = new CSVReader();
        HashMap<String, ExpectedResults> expectedResultsMap = csvReader.getExpectedResults();
        ExpectedResults expectedResults = expectedResultsMap.get(Key);
        Assert.assertFalse("Expected Result CSV is not configured",expectedResults.getColumn().isEmpty());
        return expectedResults.getColumn();
    }
}
