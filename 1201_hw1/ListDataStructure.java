public class ListDataStructure extends Traverse {
    public ListDataStructure(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    private StringArray stringArray = new StringArray();
    public int length = 0;

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public Object getObject(int index) {
        return get(index);
    }

    public String get(int index) {
        return stringArray.getStrings()[index];
    }

    @Override
    public Object[] getList() {
        return stringArray.getStrings();
    }

    @Override
    public void setList(Object[] newArray) {
        this.stringArray.setStrings((String[]) newArray);
        this.length = this.stringArray.getStrings().length;
    }

}
