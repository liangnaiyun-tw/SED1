import java.util.ArrayList;
import java.util.List;

public class Student {
  private String studentId;
  private List<Review> receivedReviews = new ArrayList<>();

  public Student(String studentId) {
    this.studentId = studentId;
    this.receivedReviews = new ArrayList<>();
  }

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public void addReview(Review review) {
    this.receivedReviews.add(review);
  }

  public List<Review> getReceivedReviews() {
    return receivedReviews;
  }

  public void setReceivedReviews(List<Review> receivedReviews) {
    this.receivedReviews = receivedReviews;
  }
}
