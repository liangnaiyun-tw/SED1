public class SkipList extends Traverse {

    public SkipList(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    private SkipNode[] skipNodes = new SkipNode[0];

    public SkipNode[] getSkipNodes() {
        return skipNodes;
    }

    public void setSkipNodes(SkipNode[] skipNodes) {
        this.skipNodes = skipNodes;
    }

    @Override
    public int getLength() {
        return size();
    }

    @Override
    public Object getObject(int index) {
        return getNode(index);
    }

    public SkipNode getNode(int index) {
        return skipNodes[index];
    }

    public int size() {
        return skipNodes.length;
    }

    @Override
    public Object[] getList() {
        return this.skipNodes;
    }

    @Override
    public void setList(Object[] skipNodes) {
        this.skipNodes = new SkipNode[skipNodes.length];
        this.skipNodes = (SkipNode[]) skipNodes;

    }

}
