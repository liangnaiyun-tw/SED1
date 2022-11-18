public abstract class Presentation {

  private SpreadsheetApplication spreadsheetApplication;

  public Presentation(SpreadsheetApplication spreadsheetApplication) {
    this.setSpreadsheetApplication(spreadsheetApplication);
  }

  public SpreadsheetApplication getSpreadsheetApplication() {
    return this.spreadsheetApplication;
  }

  public void setSpreadsheetApplication(SpreadsheetApplication spreadsheetApplication) {
    this.spreadsheetApplication = spreadsheetApplication;
  }

  public abstract void depict();

  public void change(String newItem, int newValue) {
    this.spreadsheetApplication.update(newItem, newValue);
  }

}
