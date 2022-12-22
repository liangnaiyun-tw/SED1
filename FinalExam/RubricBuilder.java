import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RubricBuilder {

  private Map<String, Integer> levelToRate;
  private List<Criterion> criterions;

  public RubricBuilder() {
    this.levelToRate = new LinkedHashMap<>();
  }

  public List<Criterion> getCriterions() {
    return criterions;
  }

  public void setCriterions(List<Criterion> criterions) {
    this.criterions = criterions;
  }

  private Criterion getCriterionByName(String criterionName) {
    for (Criterion criterion : this.criterions) {
      if (criterion.getName().equals(criterionName))
        return criterion;
    }
    Criterion newCriterion = new Criterion(criterionName);
    this.criterions.add(newCriterion);
    return newCriterion;
  }

  public void addLevel(String name, int rate) {
    this.levelToRate.put(name, Integer.valueOf(rate));
  }

  public void addCriterion(String criterion, String level, String description) {
    Criterion targetCriterion = this.getCriterionByName(criterion);
    Level newLevel = new Level(level, this.levelToRate.get(level));
    Descriptor newDescriptor = new Descriptor(targetCriterion, newLevel, description);
    targetCriterion.addDescriptor(newDescriptor);
    newLevel.addDescriptor(newDescriptor);
  }

  public Rubric build() {
    Rubric newRubric = new Rubric();
    newRubric.setCriteria(this.criterions);
    return newRubric;
  }
}
