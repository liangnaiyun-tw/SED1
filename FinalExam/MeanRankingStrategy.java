import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MeanRankingStrategy implements RankingStrategy {

  @Override
  public double calculateScore(Assignment assignment, Student student) throws Exception {
    List<Review> receivedReviews = student.getReceivedReviews();
    double total = 0;
    int cnt = 0;
    for (Review r : receivedReviews) {
      if (r.getAssignment().getAssignmentId().equals(assignment.getAssignmentId())) {
        List<Criterion> criteria = r.getAssignment().getRubric().getCriteria();
        for (Criterion c : criteria) {
          total += r.getReviews().get(c).getRate();
          cnt++;
        }
      }
    }
    if(cnt==0)  throw new Exception("Error");
    return total/cnt;
  }

  @Override
  public Map<Criterion, Double> calculateScoreGroupByCriterion(Assignment assignment,
      Student student) throws Exception {
    List<Review> receivedReviews = student.getReceivedReviews();
    Map<Criterion, Double> ret = new LinkedHashMap<>();
    List<Criterion> criteria = assignment.getRubric().getCriteria();
    for (Criterion c : criteria) {
      ret.put(c, 0.0);
    }
    boolean flag = true;
    for (Review r : receivedReviews) {
      if (r.getAssignment().getAssignmentId().equals(assignment.getAssignmentId())) {
        if (flag)
          flag = false;
        for (Criterion c : criteria) {
          ret.put(c, ret.get(c) + r.getReviews().get(c).getRate());
        }
      }
    }
    if (flag)
      throw new Exception("Error");
    return ret;
  }
}
