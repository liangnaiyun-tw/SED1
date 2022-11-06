public class TextView extends View{

    private String text;

    public TextView(String name,String text) {
        super(name);
        this.text = text;
    }

    @Override
    void display() {
        System.out.print(text);
        for(Element element: elements){
            System.out.print(" "+element.getName());
        }
        System.out.println();
    }
}
