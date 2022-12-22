import java.util.ArrayList;
import java.util.List;

public class Level {

  private List<Descriptor> descriptors;
  private String name;
  private int rate;

  public Level(String name, int rate) {
    this.name = name;
    this.rate = rate;
    descriptors = new ArrayList<>();
  }

  public List<Descriptor> getDescriptors() {
    return descriptors;
  }

  public void setDescriptors(List<Descriptor> descriptors) {
    this.descriptors = descriptors;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getRate() {
    return rate;
  }

  public void setRate(int rate) {
    this.rate = rate;
  }

  public void addDescriptor(Descriptor descriptor) {
    this.descriptors.add(descriptor);
  }
}
