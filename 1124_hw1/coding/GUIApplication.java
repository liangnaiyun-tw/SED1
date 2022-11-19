package coding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GUIApplication {

  private List<Widget> widgets = new ArrayList<>();

  public void display() {
    Collections.sort(widgets);
    for (Widget widget : widgets) {
      widget.display();
    }
  }

  public void setStyle(Style newStyle) throws Exception {
    for (Widget widget : widgets) {
      widget.setStyle(newStyle);
    }
  }

  public void addWidget(Widget widget) throws Exception {
    widgets.add(widget);
  }
}

