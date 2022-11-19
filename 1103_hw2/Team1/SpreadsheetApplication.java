import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SpreadsheetApplication {

  private LinkedHashMap<String, Integer> data;
  private List<Presentation> presentations;


  public LinkedHashMap<String, Integer> getData() {
    return this.data;
  }

  public void setData(LinkedHashMap<String, Integer> data) {
    this.data = data;
  }

  public List<Presentation> getPresentation() {
    return this.presentations;
  }

  public void setPresentation(List<Presentation> presentations) {
    this.presentations = presentations;
  }

  public void addPresentation(String chartType) {
    if (this.presentations == null) {
      this.presentations = new ArrayList<>();
    }
    Presentation p = null;
    switch (chartType) {
      case "Spreadsheet":
        p = new SpreadSheet(this);
        break;
      case "BarChart":
        p = new BarChart(this);
        break;
      case "PieChart":
        p = new PieChart(this);
        break;
    }
    this.presentations.add(p);
  }

  public void addData(String newItem, int newValue) {
    if (this.data == null) {
      this.data = new LinkedHashMap<>();
    }

    this.data.put(newItem, newValue);
  }

  public void update(String newItem, int newValue) {
    this.addData(newItem, newValue);
    for (Presentation presentation : presentations) {
      presentation.depict();
    }
  }
}
