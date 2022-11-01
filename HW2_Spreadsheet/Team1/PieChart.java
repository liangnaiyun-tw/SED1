import java.util.Map;

public class PieChart extends Presentation{
    public PieChart(SpreadsheetApplication spreadsheetApplication) {
        super(spreadsheetApplication);
    }

    @Override
    public void depict() {
        for(Map.Entry<String, Integer> entry: getSpreadsheetApplication().getData().entrySet()){
            if(SpreadsheetApplication.totalItemValue <= 0){
                System.out.println(String.format("%s %.1f%%", entry.getKey(), 0));
            } else{
                System.out.println(String.format("%s %.1f%%", entry.getKey(), 100*(float)entry.getValue()/SpreadsheetApplication.totalItemValue));
            }
        }
    }
}
