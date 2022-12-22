import java.util.LinkedHashMap;
import java.util.Map;

public class Review {
  public Review(Student student, Student reviewer, Assignment assignment) {
    this.student = student;
    this.reviewer = reviewer;
    this.assignment = assignment;
  }

  private Student student;
  private Student reviewer;
  private Assignment assignment;
  private Map<Criterion, Level> reviews = new LinkedHashMap<>();

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
