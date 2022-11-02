import java.util.Map;

public class BarChart extends Presentation {
    public BarChart(SpreadsheetApplication spreadsheetApplication) {
        super(spreadsheetApplication);
    }

    @Override
    public void depict() {
        for(Map.Entry<String, Integer> entry: getSpreadsheetApplication().getData().entrySet()){
            for(int i = 0; i< entry.getValue(); i++){
                System.out.print("=");
            }
            System.out.println(String.format(" %s", entry.getKey()));
        }
    }
}
