import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReviewSystem {

  private List<Student> students;
  private List<Assignment> assignments;

  public ReviewSystem() {
    students = new ArrayList<>();
    assignments = new ArrayList<>();
  }

  public Map<Criterion, Double> averageCriterion(String assignmentId) throws Exception {
    Assignment assignment = getAssignmentById(assignmentId);
    Map<Criterion, Double> map = new LinkedHashMap<>();
    int totalItem = 0;
    for (Review review : assignment.getReviews()) {
      for (Map.Entry<Criterion, Level> entry : review.getReviews().entrySet()) {
        totalItem++;
        if (!map.containsKey(entry.getKey())) {
          map.put(entry.getKey(), Double.valueOf(entry.getValue().getRate()));
        } else {
          double currentRate = map.get(entry.getKey());
          currentRate += entry.getValue().getRate();
          map.put(entry.getKey(), currentRate);
        }
      }
    }

    if (totalItem == 0) {
      System.out.println("Error");
    }
    // output result
    for (Map.Entry<Criterion, Double> entry : map.entrySet()) {
      // output like: Assignment: A1, Criterion: Thinking/Inquiry, AvgScore: 2.7
      System.out.println(String.format("Assignment: %s, Criterion: %s, AvgScore: %.1f",
          assignmentId, entry.getKey().getName(), (entry.getValue() / totalItem)));
    }

    return map;
  }

  public Double calculateScore(String assignmentId, String studentId,
      RankingStrategy rankingStrategy) throws Exception {
    Double score =
        rankingStrategy.calculateScore(getAssignmentById(assignmentId), getStudentById(studentId));
    System.out.println(
        String.format("Assignment: %s, Student: %s, Score: %.1f", assignmentId, studentId, score));
    return score;
  }

  public List<Criterion> findStrength(String assignmentId, String studentId,
      RankingStrategy rankingStrategy) throws Exception {
    Assignment assignment = getAssignmentById(assignmentId);
    Student student = getStudentById(studentId);
    Map<Criterion, Double> map =
        rankingStrategy.calculateScoreGroupByCriterion(assignment, student);
    List<Criterion> criteria = assignment.getRubric().getCriteria();
    List<Criterion> ret = new ArrayList<>();
    double maxScore = 0.0;
    for (Criterion c : criteria) {
      maxScore = Math.max(map.get(c), maxScore);
    }
    System.out
        .print(String.format("Assignment: %s, Student: %s, Strength:", assignmentId, studentId));
    for (Criterion c : criteria) {
      if (map.get(c) == maxScore) {
        ret.add(c);
        System.out.print(" " + c.getName());
      }
    }
    System.out.println();
    return ret;
  }

  public List<Criterion> findWeakness(String assignmentId, String studentId,
      RankingStrategy rankingStrategy) throws Exception {
    Assignment assignment = getAssignmentById(assignmentId);
    Student student = getStudentById(studentId);
    Map<Criterion, Double> map =
        rankingStrategy.calculateScoreGroupByCriterion(assignment, student);
    List<Criterion> criteria = assignment.getRubric().getCriteria();
    List<Criterion> ret = new ArrayList<>();
    double minScore = 100.0;
    for (Criterion c : criteria) {
      minScore = Math.min(map.get(c), minScore);
    }
    System.out
        .print(String.format("Assignment: %s, Student: %s, Weakness:", assignmentId, studentId));
    for (Criterion c : criteria) {
      if (map.get(c) == minScore) {
        ret.add(c);
        System.out.print(" " + c.getName());
      }
    }
    System.out.println();
    return ret;
  }

  public void addAssignment(String assignmentId, Rubric rubric) {
    Assignment newAssignment = new Assignment(assignmentId, rubric);
    this.assignments.add(newAssignment);
  }

  public void addStudent(String studentId) {
    Student newStudent = new Student(studentId);
    this.students.add(newStudent);
  }

  public void addReview(String assignmentId, String reviewer, String receiver, List<String> levels)
      throws Exception {
    Assignment assignment = this.getAssignmentById(assignmentId);
    Student reviewerStudent = this.getStudentById(reviewer);
    Student receiverStudent = this.getStudentById(receiver);
    Rubric rubric = assignment.getRubric();
    Review newReview = new Review(receiverStudent, reviewerStudent, assignment);

    System.out
        .print(String.format("%s-%s was reviewed by %s. Level:", assignmentId, receiver, reviewer));
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

  public void printRubric(String assignmentId) throws Exception {
    Assignment assignment = getAssignmentById(assignmentId);
    List<Criterion> criteria = assignment.getRubric().getCriteria();
    for (Criterion c : criteria) {
      List<Descriptor> descriptors = c.getDescriptors();
      for (Descriptor d : descriptors) {
        System.out.println(
            String.format("(%s,%s) %s", c.getName(), d.getLevel().getName(), d.getDescription()));
      }
    }
  }

  private Assignment getAssignmentById(String assignmentId) throws Exception {
    for (Assignment assignment : assignments) {
      if (assignment.getAssignmentId().equals(assignmentId)) {
        return assignment;
      }
    }
    throw new Exception("Error");
  }

  private Student getStudentById(String studentId) throws Exception {
    for (Student student : students) {
      if (student.getStudentId().equals(studentId)) {
        return student;
      }
    }
    throw new Exception("Error");
  }
}
