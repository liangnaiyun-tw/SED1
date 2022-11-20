package coding;

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

  public void execute(String command) throws Exception {
//      if(command.equals("Boil")) {
//        NotEmptyAndNotBoiled.execute(command);
//      }
//    else if(command.equals("Drain")) {
//      NotEmptyAndBoiled.execute(command);
//    }
//    else if (command.equals("Fill")){
//        Empty.execute(command);
//      }
    status.execute(command);
  }
}

