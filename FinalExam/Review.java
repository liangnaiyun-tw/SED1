import java.util.HashMap;
import java.util.Map;

public class Review {
  private Student student;
  private Student reviewer;
  private Assignment assignment;
  private Map<Criterion,Level> descriptors = new HashMap<>();

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Student getReviewer() {
    return reviewer;
  }

  public void setReviewer(Student reviewer) {
    this.reviewer = reviewer;
  }

  public Assignment getAssignment() {
    return assignment;
  }

  public void setAssignment(Assignment assignment) {
    this.assignment = assignment;
  }

  public void putDescriptors(Criterion c, Level l){
    this.descriptors.put(c,l);
  }

  public Map<Criterion, Level> getDescriptors() {
    return descriptors;
  }

  public void setDescriptors(Map<Criterion, Level> descriptors) {
    this.descriptors = descriptors;
  }
}
