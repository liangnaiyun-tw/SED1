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
        Assignment assignment = this.getAssignmentById(assignmentId);
        Student reviewerStudent = this.getStudentById(reviewer);
        Student receiverStudent = this.getStudentById(receiver);
        Rubric rubric = assignment.getRubric();
        Review newReview = new Review(receiverStudent, reviewerStudent, assignment);

        System.out.print(String.format("%s-%s was reviewed by %s]. Level:", assignmentId, receiver, reviewer));
        for (int i = 0; i < rubric.getCriteria().size(); i++) {
            System.out.print(String.format(" %s", levels.get(i)));
            Criterion criterion = rubric.getCriteria().get(i);
            Level level = criterion.getDescriptorByLevelName(levels.get(i)).getLevel();
            newReview.putReviews(criterion, level);
        }
        System.out.println("");
        assignment.addReview(newReview);
        receiverStudent.addReview(newReview);
    }

    private Assignment getAssignmentById(String assignmentId) {
    }

    private Student getStudentById(String studentId) {
    }
}
