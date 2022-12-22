import java.util.List;

public class Criterion {

  private List<Descriptor> descriptors;

  public List<Descriptor> getDescriptors() {
    return descriptors;
  }

  public void setDescriptors(List<Descriptor> descriptors) {
    this.descriptors = descriptors;
  }

  public void addDescriptor(Descriptor descriptor) {
    this.descriptors.add(descriptor);
  }
}
