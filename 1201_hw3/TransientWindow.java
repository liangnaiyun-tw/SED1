public class TransientWindow extends Window {


  public void setWindowImplementor(WindowImplementor windowImplementor) {
    super.windowImplementor = windowImplementor;
  }

  @Override
  public void drawBorder() {

  }

  @Override
  public void drawCloseBox() {
    windowImplementor.drawText();
  }
}
