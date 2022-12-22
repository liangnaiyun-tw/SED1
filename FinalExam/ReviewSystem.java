import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewSystem {
    private List<Student> students;
    private List<Assignment> assignments;
    private RankingStrategy rankingStrategy;

    public Map<Criterion, Double> averageCriterion(String assignmentId) {
    }

    public Double calculateScore(String assignmentId, String studentId, RankingStrategy rankingStrategy) {
    }

    public List<Criterion> findStrength(String assignmentId, String studentId) {
    }

    public List<Criterion> findWeakness(String assignmentId, String studentId) {
    }

    public void addAssignment(String assignmentId, Rubric rubric) {
    }

    public void addStudent(String studentId) {
    }

    public void addReview(String assignmentId, String reviewer, String receiver, List<String> levels) {
    }

    private Assignment getAssignmentById(String assignmentId) {
    }

    private Student getStudentById(String studentId) {
    }
}
