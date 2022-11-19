import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

abstract class View {

  private String name;
  List<Element> elements = new ArrayList<>();

  public View(String name) {
    this.name = name;
  }

  abstract void display();

  public String getName() {
    return name;
  }

  public void attach(Element element) throws Exception {
    for (Element e : elements) {
      if (e.getName().equals(element.getName())) {
        throw new Exception("The element has existed.");
      }
    }
    elements.add(element);
  }

  public void detach(Element element) {
    elements.remove(element);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    View view = (View) o;
    return Objects.equals(name, view.name);
  }
}
