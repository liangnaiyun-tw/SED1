abstract class Window {

  protected WindowImplementor windowImplementor;

  public abstract void drawBorder();

  public abstract void drawCloseBox();

  public abstract void setWindowImplementor(WindowImplementor windowImplementor);
}

