public class NotEmptyAndNotBoiled implements Status {

  private ChocolateBoiler boiler;

  public NotEmptyAndNotBoiled(ChocolateBoiler boiler) {
    this.boiler = boiler;
  }

  public void execute(String command) throws Exception {
    if (command.equals("Boil")) {
      System.out.println("Boil chocolate");
      boiler.setStatus(new NotEmptyAndBoiled(boiler));
      boiler.getChocolate().setBoiled(true);
    } else {
      throw new IllegalArgumentException("No command matched");
    }
  }

}
