import java.util.Map;

public interface RankingStrategy {
  public double calculateScore(Assignment assignment, Student student) throws Exception;

  public Map<Criterion, Double> calculateScoreGroupByCriterion(
    Assignment assignment,
    Student student
  ) throws Exception;
}
