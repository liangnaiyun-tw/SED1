import java.util.List;

public class Descriptor {

  private Criterion criterion;
  private Level level;
  private String description;

  public Criterion getCriterion() {
    return criterion;
  }

  public void setCriterion(Criterion criterion) {
    this.criterion = criterion;
  }

  public Level getLevel() {
    return level;
  }

  public void setLevel(Level level) {
    this.level = level;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
