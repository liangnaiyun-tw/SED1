public class IconWindow extends Window {


  public void setWindowImplementor(WindowImplementor windowImplementor) {
    super.windowImplementor = windowImplementor;
  }

  @Override
  public void drawCloseBox() {

  }

  @Override
  public void drawBorder() {
    windowImplementor.drawText();
    windowImplementor.drawRectangle();
  }
}
