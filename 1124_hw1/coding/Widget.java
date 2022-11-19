package coding;

abstract class Widget implements Comparable<Widget> {

  private Style style = new Motif();
  private String name;
  private int order;

  public Widget(String name) {
    this.name = name;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public String getName() {
    return name;
  }

  public Style getStyle() {
    return style;
  }

  public void setStyle(Style style) {
    this.style = style;
  }

  abstract void display();

  @Override
  public int compareTo(Widget other) {
    return this.order - other.order;
  }
}