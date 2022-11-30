public class SpamHandler extends EmailHandler{

  @Override
  public void handleEmail(Email email) {
    if(email instanceof SpamEmail){
      System.out.println("Put mail to the spam box.");
    }
    else{
      next.handleEmail(email);
    }
  }
}
