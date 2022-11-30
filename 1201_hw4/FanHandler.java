public class FanHandler extends EmailHandler {

  @Override
  public void handleEmail(Email email) {
    if(email instanceof FanEmail){
      System.out.println("Forward to CEO.");
    }
    else{
      next.handleEmail(email);
    }
  }
}
