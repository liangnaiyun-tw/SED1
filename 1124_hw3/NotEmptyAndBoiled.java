public class NotEmptyAndBoiled implements Status {

  private ChocolateBoiler boiler;


  public NotEmptyAndBoiled(ChocolateBoiler boiler) {
    this.boiler = boiler;
  }

  public void execute(String command) throws Exception {
    if (command.equals("Drain")) {
      System.out.println("Drain the boiled chocolate");
      boiler.setStatus(new Empty(boiler));
      boiler.setChocolate(null);
    } else {
      throw new IllegalArgumentException("No command matched");
    }

  }

}
