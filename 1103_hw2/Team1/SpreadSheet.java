import java.util.Map;

public class SpreadSheet extends Presentation {

  public SpreadSheet(SpreadsheetApplication spreadsheetApplication) {
    super(spreadsheetApplication);
  }

  @Override
  public void depict() {
    for (Map.Entry<String, Integer> entry : getSpreadsheetApplication().getData().entrySet()) {
      System.out.println(String.format("%s %d", entry.getKey(), entry.getValue()));
    }
  }

}
