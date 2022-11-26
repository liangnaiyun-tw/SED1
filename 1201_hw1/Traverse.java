public abstract class Traverse implements DataStructure {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Traverse(String name) {
        this.name = name;
    }

    @Override
    public void traverse() {
        for (Object object : getList()) {
            System.out.println(object);
        }

    }

    public abstract int getLength();

    public abstract Object getObject(int index);

    public abstract void setList(Object[] newArray);

    public abstract Object[] getList();

}
