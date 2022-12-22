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

    public List<Criterion> findStrength(String assignmentId, String studentId, RankingStrategy rankingStrategy) {
        Assignment assignment = getAssignmentById(assignmentId);
        Student student = getStudentById(studentId);
        Map<Criterion, Double> map = rankingStrategy.calculateScoreGroupByCriterion(
            assignment, student);
        List<Criterion> criteria = assignment.getRubric().getCriteria();
        List<Criterion> ret = new ArrayList<>();
        double maxScore = 0.0;
        for(Criterion c: criteria){
            maxScore = Math.max(map.get(c),maxScore);
        }
        System.out.print(String.format("Assignment: %s, Student: %s, Strength:",assignmentId,
            studentId));
        for(Criterion c: criteria){
            if(map.get(c)==maxScore){
                ret.add(c);
                System.out.print(" " + c.getName());
            }
        }
        System.out.println();
        return ret;
    }

    public List<Criterion> findWeakness(String assignmentId, String studentId,RankingStrategy rankingStrategy) {
        Assignment assignment = getAssignmentById(assignmentId);
        Student student = getStudentById(studentId);
        Map<Criterion, Double> map = rankingStrategy.calculateScoreGroupByCriterion(
            assignment, student);
        List<Criterion> criteria = assignment.getRubric().getCriteria();
        List<Criterion> ret = new ArrayList<>();
        double minScore = 100.0;
        for(Criterion c: criteria){
            minScore = Math.max(map.get(c),minScore);
        }
        System.out.print(String.format("Assignment: %s, Student: %s, Weakness:",assignmentId,
            studentId));
        for(Criterion c: criteria){
            if(map.get(c)==minScore){
                ret.add(c);
                System.out.print(" " + c.getName());
            }
        }
        System.out.println();
        return ret;
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
        Assignment ret = null;
        for(Assignment assignment: assignments){
            if(assignment.getAssignmentId().equals(assignmentId)){
                ret = assignment;
            }
        }
        return ret;
    }

    private Student getStudentById(String studentId) {
        Student ret = null;
        for(Student student: students){
            if(student.getStudentId().equals(studentId)){
                ret = student;
            }
        }
        return ret;
    }
}
