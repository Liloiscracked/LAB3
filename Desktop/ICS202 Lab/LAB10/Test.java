public class Test {
    public static void main(String[] args) {
        Entry [] testing = new Entry[13];
        HashTable mytable = new HashTable<>(testing);
        mytable.insert(18);
        mytable.insert(26);
        mytable.insert(35);
        mytable.insert(9);
        System.out.println("After inserting 18,26,35,9");
        mytable.tostring();
        mytable.find(15);
        mytable.find(48);
        mytable.delete(35);
        if(mytable.delete(35)){
            System.out.println(35 + " succesfully deleted");
        }
        else
            System.out.println(35 + " is not there");

        if(mytable.find(9) != -1){
            System.out.println(9 + " found at " + mytable.find(9));
        }
        else
            System.out.println(9 + " not found ");
        mytable.insert(64);
        mytable.insert(47);
        System.out.println("After deleting 35 and  inserting 64 and 47");
        mytable.tostring();

        if(mytable.find(35) != -1){
            System.out.println(35 + " found at " + mytable.find(35));
        }
        else
            System.out.println(35 + " not found ");

    }
}
