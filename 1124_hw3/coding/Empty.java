package coding;

public class Empty implements Status {

  private ChocolateBoiler boiler;

  public Empty(ChocolateBoiler boiler) {
    this.boiler = boiler;
  }

  public void execute(String command) throws Exception {
    if (command.equals("Fill")) {
      System.out.println("Fill chocolate");
      boiler.setStatus(new NotEmptyAndNotBoiled(boiler));
    } else {
      throw new Exception("");
    }
  }

}

