import java.util.Map;
import java.util.stream.Collectors;

public class PieChart extends Presentation {

  public PieChart(SpreadsheetApplication spreadsheetApplication) {
    super(spreadsheetApplication);
  }

  @Override
  public void depict() {
    int sum = getSpreadsheetApplication().getData().values().stream().reduce(0, Integer::sum);

    for (Map.Entry<String, Integer> entry : getSpreadsheetApplication().getData().entrySet()) {

      System.out.println(String.format("%s %.1f%%", entry.getKey(),
          sum > 0 ? 100 * (float) entry.getValue() / sum : 0));
    }
  }
}
