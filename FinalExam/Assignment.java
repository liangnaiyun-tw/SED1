import java.util.ArrayList;
import java.util.List;

public class Assignment {

  private String assignmentId;
  private Rubric rubric;
  private List<Review> reviews;

  public Assignment(String assignmentId, Rubric rubric) {
    this.assignmentId = assignmentId;
    this.rubric = rubric;
    this.reviews = new ArrayList<>();
  }

  public String getAssignmentId() {
    return assignmentId;
  }

  public void setAssignmentId(String assignmentId) {
    this.assignmentId = assignmentId;
  }

  public Rubric getRubric() {
    return rubric;
  }

  public void setRubric(Rubric rubric) {
    this.rubric = rubric;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  public void addReview(Review review) {
    this.reviews.add(review);
  }
}
