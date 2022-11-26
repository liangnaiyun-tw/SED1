public class ComplaintHandler extends EmailHandler{

  @Override
  public void handleEmail(Email email) {
    if(email instanceof ComplaintEmail){
      System.out.println("Forward to legal department.");
    }
    else{
      next.handleEmail(email);
    }
  }
}
