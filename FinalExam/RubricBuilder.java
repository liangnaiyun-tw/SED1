import java.util.List;

public class RubricBuilder {

  private List<Level> levels;
  private List<Criterion> criterions;

  public List<Level> getLevels() {
    return levels;
  }

  public void setLevels(List<Level> levels) {
    this.levels = levels;
  }

  public List<Criterion> getCriterions() {
    return criterions;
  }

  public void setCriterions(List<Criterion> criterions) {
    this.criterions = criterions;
  }

  public void addLevel(String name, int rate) {}

  public void addCriterion(
    String criterion,
    String level,
    String description
  ) {}

  public Rubric build() {
    return new Rubric();
  }
}
