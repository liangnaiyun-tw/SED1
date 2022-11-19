import java.util.ArrayList;
import java.util.List;

public class FileViewer {

  private List<View> views = new ArrayList<>();

  public void attach(View view) throws Exception {
    for (View v : views) {
      if (v.equals(view)) {
        throw new Exception("The same view name was used");
      }
    }
    views.add(view);
  }

  public void detach(View view) {
    views.remove(view);
  }

  public void displayView(String viewName) throws Exception {
    views.stream()
        .filter(view -> view.getName().equals(viewName))
        .findFirst().orElseThrow(() -> new Exception("The TextViewName not found"))
        .display();
  }

  public void addElementToView(String viewName, String elementName) throws Exception {
    Element element;
    if (elementName.equals(ScrollBar.name)) {
      element = new ScrollBar();
    } else if (elementName.equals(ThickBlackBorder.name)) {
      element = new ThickBlackBorder();
    } else {
      throw new Exception("The element not found");
    }
    views.stream()
        .filter(view -> view.getName().equals(viewName))
        .findFirst().orElseThrow(() -> new Exception("The TextViewName not found"))
        .attach(element);
  }
}
