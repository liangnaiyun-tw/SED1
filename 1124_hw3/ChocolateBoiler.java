public class ChocolateBoiler {

  private Chocolate chocolate;
  private static ChocolateBoiler chocolateBoiler;
  private Status status;


  public static ChocolateBoiler getInstance() {
    if (chocolateBoiler == null) {
      chocolateBoiler = new ChocolateBoiler();
    }

    return chocolateBoiler;
  }

  private ChocolateBoiler() {
    this.status = new Empty(this);
  }

  ;

  public void setStatus(Status status) {
    this.status = status;
  }

  public Status getStatus() {
    return status;
  }

  public Chocolate getChocolate() {
    return chocolate;
  }

  public void setChocolate(Chocolate chocolate) {
    this.chocolate = chocolate;
  }

  public void execute(String command) throws Exception {

    status.execute(command);
  }
}
