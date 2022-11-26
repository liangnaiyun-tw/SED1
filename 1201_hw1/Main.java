import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    static BufferedReader bufferedReader;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Input Error");
        }

        try {
            String inputLine;
            String[] inputTokens;
            Traverse traverse = null;

            bufferedReader = new BufferedReader(new FileReader(args[0]));
            while ((inputLine = bufferedReader.readLine()) != null) {
                try {
                    inputTokens = inputLine.split("\\s+");
                    if (inputTokens.length == 3 && inputTokens[0].equals("Create")
                            && inputTokens[2].equals("SkipList")) {
                        traverse = new SkipList(inputTokens[1]);
                    } else if (inputTokens.length == 3 && inputTokens[0].equals("Create")
                            && inputTokens[2].equals("List")) {
                        traverse = new ListDataStructure(inputTokens[1]);
                    } else {
                        parseCommandActions(traverse, inputLine);
                    }
                } catch (Exception e) {
                    System.out.println("Input Error");
                }
            }
        } catch (Exception e) {
            System.out.println("Input Error");
        }

    }

    private static void parseCommandActions(Traverse traverse, String inpuString) {
        try {
            String[] tokeStrings = inpuString.split("\\s+");
            if (tokeStrings.length == 3 && tokeStrings[0].equals("Add")
                    && traverse.getName().equals(tokeStrings[1])) {
                parseCommandAddObject(traverse, tokeStrings[2]);
            } else if (tokeStrings.length == 2 && tokeStrings[0].equals("Length")
                    && traverse.getName().equals(tokeStrings[1])) {
                parseCommandGetListLength(traverse);
            } else if (tokeStrings.length == 2 && tokeStrings[0].equals("Size")
                    && traverse.getName().equals(tokeStrings[1])) {
                parseCommandGetListSize(traverse);
            } else if (tokeStrings.length == 3 && tokeStrings[0].equals("Get")
                    && tokeStrings[1].equals(traverse.getName())) {
                parseCommandGetStringByIndex(traverse, Integer.parseInt(tokeStrings[2]));
            } else if (tokeStrings.length == 3 && tokeStrings[0].equals("GetNode")
                    && tokeStrings[1].equals(traverse.getName())) {
                parseCommandGetNodeByIndex(traverse, Integer.parseInt(tokeStrings[2]));
            } else if (tokeStrings.length == 2 && tokeStrings[0].equals("PrintOutList")
                    && traverse.getName().equals(tokeStrings[1])) {
                parseCommandPrintOutList(traverse);
            } else {
                System.out.println("No command matched");
            }
        } catch (Exception e) {
            System.out.println("No command matched");
        }
    }

    private static void parseCommandAddObject(Traverse traverse, String content) {
        int length = traverse.getLength() + 1;

        if (traverse instanceof ListDataStructure) {
            String[] newArray = new String[length];
            String[] oldArray = (String[]) traverse.getList();
            System.arraycopy(oldArray, 0, newArray, 0, traverse.getLength());
            newArray[length - 1] = content;
            traverse.setList(newArray);
        }
        if (traverse instanceof SkipList) {
            SkipNode[] newArray = new SkipNode[length];
            SkipNode[] oldArray = (SkipNode[]) traverse.getList();
            System.arraycopy(oldArray, 0, newArray, 0, traverse.getLength());
            newArray[length - 1] = new SkipNode(content);
            traverse.setList(newArray);
        }

    }

    private static void parseCommandGetListLength(Traverse traverse) {

        if (traverse instanceof ListDataStructure) {
            System.out.println(traverse.getLength());
        } else {
            System.out.println("SkipList can not access length");
        }
    }

    private static void parseCommandGetListSize(Traverse traverse) {
        if (traverse instanceof SkipList) {
            System.out.println(traverse.getLength());
        } else {
            System.out.println("List do not have method size");
        }
    }

    private static void parseCommandGetStringByIndex(Traverse traverse, int index) {
        if (traverse instanceof ListDataStructure) {
            System.out.println(traverse.getObject(index));
        } else {
            System.out.println("SkipList do not have method get");
        }

    }

    private static void parseCommandGetNodeByIndex(Traverse traverse, int index) {
        if (traverse instanceof SkipList) {
            System.out.println(traverse.getObject(index));
        } else {
            System.out.println("List do not have method getNode");
        }
    }

    private static void parseCommandPrintOutList(Traverse traverse) {
        traverse.traverse();
    }
}