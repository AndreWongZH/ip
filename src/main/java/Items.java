public class Items {
    private final String[] list;
    private int listCount;

    public Items() {
        list = new String[100];
        listCount = 0;
    }

    public void addToList(String item) {
        list[listCount] = item;
        listCount++;
        System.out.println("added: " + item + ", I said with a posed look.\n");
    }

    public void printList() {
        for (int i = 0; i < listCount; i++) {
            System.out.println(i+1 + ". " + list[i]);
        }
        System.out.println();
    }
}
