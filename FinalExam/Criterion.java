import java.util.ArrayList;
import java.util.List;

public class Criterion {

  private String name;
  private List<Descriptor> descriptors;

  public Criterion(String name) {
    this.name = name;
    this.descriptors = new ArrayList<>();
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Descriptor> getDescriptors() {
    return descriptors;
  }

  public void setDescriptors(List<Descriptor> descriptors) {
    this.descriptors = descriptors;
  }

  public void addDescriptor(Descriptor descriptor) {
    this.descriptors.add(descriptor);
  }

  public Descriptor getDescriptorByLevelName(String levelName) {
    for (Descriptor des : this.descriptors) {
      if (des.getLevel().getName().equals(levelName)) {
        return des;
      }
    }
    return null;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Criterion))
      return false;
    Criterion objCri = (Criterion) obj;
    return (objCri.name == this.name);
  }
}
