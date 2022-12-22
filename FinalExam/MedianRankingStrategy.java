import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MedianRankingStrategy implements RankingStrategy {

  @Override
  public double calculateScore(Assignment assignment, Student student) {
    Map<Criterion, Double> rates = calculateScoreGroupByCriterion(assignment, student);
    return rates.values().stream().reduce(0.0, (a, b) -> a + b) / rates.size();
  }

  @Override
  public Map<Criterion, Double> calculateScoreGroupByCriterion(Assignment assignment,
      Student student) {
    Map<Criterion, List<Integer>> criterionRates = new LinkedHashMap<>();
    for (Criterion criterion : assignment.getRubric().getCriteria()) {
      criterionRates.put(criterion, new ArrayList<>());
    }

    List<Review> reviews = student.getReceivedReviews();
    reviews.stream()
        .filter(r -> r.getAssignment().getAssignmentId().equals(assignment.getAssignmentId()))
        .forEach(r -> {
          r.getReviews().entrySet().forEach(criterionRate -> criterionRates
              .get(criterionRate.getKey()).add(criterionRate.getValue().getRate()));
        });
    criterionRates.values().forEach(list -> Collections.sort(list));

    Map<Criterion, Double> results = new LinkedHashMap<>();
    criterionRates.entrySet().forEach(entry -> {
      Double res;
      int size = entry.getValue().size();
      if (size % 2 == 0) {
        res = (double) (entry.getValue().get(size / 2) + entry.getValue().get(size / 2 - 1)) / 2;
      } else {
        res = (double) entry.getValue().get(size / 2);
      }
      results.put(entry.getKey(), res);
    });

    return results;
  }
}
