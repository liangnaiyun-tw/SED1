import java.util.HashMap;
import java.util.Map;

public class Review {
  private Student student;
  private Student reviewer;
  private Assignment assignment;
  private Map<Criterion, Level> reviews = new HashMap<>();

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

  public void putReviews(Criterion c, Level l) {
    this.reviews.put(c, l);
  }

  public Map<Criterion, Level> getReviews() {
    return reviews;
  }

  public void setDescriptors(Map<Criterion, Level> reviews) {
    this.reviews = reviews;
  }
}
