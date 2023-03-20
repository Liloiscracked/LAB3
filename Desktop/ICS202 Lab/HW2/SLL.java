//**************************  SLL.java  *********************************
//           a generic singly linked list class 
public class SLL<T>{

    private static class SLLNode<T>{

      private T info;
      private SLLNode<T> next;
      public SLLNode() {
         this(null,null);
      }
      public SLLNode(T el) {
        this(el,null);
      }
      public SLLNode(T el, SLLNode<T> ptr) {
         info = el; next = ptr;
      }
    }

    protected SLLNode<T> head, tail;
    public SLL() {
        head = tail = null;
    }
    public boolean isEmpty() {
        return head == null;
    }
    public void addToHead(T el) {
        head = new SLLNode<T>(el,head);
        if (tail == null)
            tail = head;
    }
    public void addToTail(T el) {
        if (!isEmpty()) {
            tail.next = new SLLNode<T>(el);
            tail = tail.next;
        }
        else head = tail = new SLLNode<T>(el);
    }
    public T deleteFromHead() { // delete the head and return its info; 
        if (isEmpty()) 
             return null;
        T el = head.info;
        if (head == tail)       // if only one node on the list;
             head = tail = null;
        else head = head.next;
        return el;
    }
    public T deleteFromTail() { // delete the tail and return its info;
        if (isEmpty()) 
             return null;
        T el = tail.info;
        if (head == tail)       // if only one node in the list;
             head = tail = null;
        else {                  // if more than one node in the list,
             SLLNode<T> tmp;    // find the predecessor of tail;
             for (tmp = head; tmp.next != tail; tmp = tmp.next);
             tail = tmp;        // the predecessor of tail becomes tail;
             tail.next = null;
        }
        return el;
    }
    public void delete(T el) {  // delete the node with an element el;
        if (!isEmpty())
            if (head == tail && el.equals(head.info)) // if only one
                 head = tail = null;       // node on the list;
            else if (el.equals(head.info)) // if more than one node on the list;
                 head = head.next;    // and el is in the head node;
            else {                    // if more than one node in the list
                 SLLNode<T> pred, tmp;// and el is in a nonhead node;
                 for (pred = head, tmp = head.next;  
                      tmp != null && !tmp.info.equals(el); 
                      pred = pred.next, tmp = tmp.next);
                 if (tmp != null) {   // if el was found;
                     pred.next = tmp.next;
                     if (tmp == tail) // if el is in the last node;
                        tail = pred;
                 }
            }
    }
    
    @Override
    public String toString() {
       if(head == null)
          return "[ ]";
       String str = "[ ";   
       SLLNode<T> tmp = head;
       while(tmp != null){
         str += tmp.info + " ";
         tmp = tmp.next;
       }
       return str+"]";             
    }
    
    public boolean contains(T el) {
        if(head == null)
            return false;
        SLLNode<T> tmp = head;
        while(tmp != null){
           if(tmp.info.equals(el))
              return true;
           tmp = tmp.next;
        }
        return false;
    }
    
    public int size(){
        if(head == null)
          return 0;
          
        int count = 0;
        SLLNode<T> p = head;
        while(p != null) {
           count++;
           p = p.next;
        }
           
        return count;
    }
    
    //  Please write the methods of Task02 here:
    //A
    public void insertBefore(int index, T newElem) throws Exception{
        int counter = 0; // used as a counter variable
        SLLNode<T> currentNode = head; // an iterator
        // in the case of the first element
        if(index == 0){
            head = new SLLNode<T>(newElem,currentNode);
        }
        // the exception:
        if(size()<= index)
            throw new Exception("Not valid due to the not found index");
        // in the case of neither in the beginning nor ending :
            while(currentNode.next != null){
                if(counter == index - 1){
                   SLLNode<T> comingnode = new SLLNode<T>(newElem,currentNode.next);
                   currentNode.next = comingnode;
                }
                counter++;
                currentNode = currentNode.next;
            }
    }
    //B
    public T  delete(int  index) throws Exception{
        int counter = 0; // used as a counter variable
        SLLNode<T> currentNode = head; // an iterator
        SLLNode<T> previousNode = null;
        T val = null;
        // handling error
        if(isEmpty())
            throw new Exception("there is no elements to Delete!!!!");
        // if the first element case
        if(index == 0){
           head = head.next;
           val = head.info;
           return val;
        }
        // if it is not in the beginning
        while (currentNode != null){
            if(counter == index){
                val = currentNode.info;
                previousNode.next = currentNode.next;
                //return val;
            }
            counter++;
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        return val;
    }
    //C
    public  void  insertAfterSecondOccurrence(T  e1,  T   e2) throws Exception{
        int counter = 0;//to count the occurrences
        SLLNode<T> currentNode = head; // the current node (used like an iterator)
        while (currentNode.next != null){ //searching
            if(currentNode.info.equals(e2)){
                counter++;
            }
            if(counter == 2)
                break;
            currentNode = currentNode.next;
        }
        if(counter == 2){ // the insertion process
            currentNode.next = new SLLNode<T>(e1,currentNode.next);
        }
        else // the exception handling
            throw new Exception("the element does not repeat twice!!!");
    }
public void mergeSorted(SLL<T> list1, SLL<T> list2) {
    SLLNode<T> tmp1 = list1.head;
    SLLNode<T> tmp2 = list2.head;
    while(tmp1!=null && tmp2!=null) {
        if (((Integer)tmp1.info).compareTo((Integer)tmp2.info) <0) {
            this.addToTail(tmp1.info);
            tmp1 = tmp1.next;
        }
        else {
            this.addToTail(tmp2.info);
            tmp2 = tmp2.next;
        }
    }// end while
    if(tmp1 == null) {
        while(tmp2!=null){
            this.addToTail(tmp2.info);
            tmp2 = tmp2.next;
        }
    }
    if(tmp2 == null) {
        while(tmp1!=null){
            this.addToTail(tmp1.info);
            tmp1 = tmp1.next;
        }
    }

}// end method mergeSorted

    public void deleteAllNodes(T e1){
        // empty list
        if(head == null)
            return;
        // if the item to be deleted is at the beginning of the list
        while(head != null && e1.equals(head.info))
            head = head.next;
        // if all items in the list are deleted
        if(head == null){
            tail = null;
            return;
        }
        // save the head of the list // traverse the list
        SLLNode<T> current = head; while(current.next != tail){
            if(e1.equals(current.next.info))
                current.next = current.next.next;
            else
                current = current.next; }
        if(e1.equals(tail.info)){ tail = current; tail.next = null;
        }
    }


    public void del(T el){
        while (head.info.equals(el)){
            head = head.next;
        }
        SLLNode current = head , coming = current.next;
        while (coming != null){
            if(coming.info.equals(el)){
                current.next = coming.next;
                coming = coming.next;
            }
            else {
                current = current.next;
                coming = coming.next;
            }
        }

    }



    // HW2 Question1:
    public static String Operation(int num1 , int num2 , String operation){
        SLL<Integer> n1 = new SLL<>();
        SLL<Integer> n2 = new SLL<>();
        SLL<Integer> result = new SLL<>();
        int carry = 0;
        while (num1 != 0){
            n1.addToTail(num1%10);
            num1 /= 10;
        }
        while (num2 != 0){
            n2.addToTail(num2%10);
            num2 /= 10;
        }
        SLLNode<Integer> node1 = n1.head;
        SLLNode<Integer> node2 = n2.head;
        if(operation.equals("+")){
            return Add(n1,n2);
        }
        else if (operation.equals("-")) {
            return Subtract(n1,n2);
        }
        else if (operation.equals("*")) {
            return String.valueOf(multiplyTwoLists(n1,n2));
        }
        else
            return "Invalid!!!";
    }
    //Add two linked lists
    static String Add(SLL<Integer> n1 , SLL<Integer> n2) {
        SLLNode<Integer> node1 = n1.head;
        SLLNode<Integer> node2 = n2.head;
        SLL<Integer> result = new SLL<>();
        int carry = 0;
        while (node1 != null & node2 != null){
            if(node1.info + node2.info + carry > 9){
                result.addToHead((node1.info + node2.info + carry)%10);
                carry = 1;
            }
            else
                result.addToHead(node1.info + node2.info + carry);

            node1 = node1.next;
            node2 = node2.next;
        }
        if(node1 == null){
            while(node2 != null){
                if (carry == 1){
                    result.addToHead(carry + node2.info);
                    carry = 0;
                }
                else
                    result.addToHead(node2.info+ carry);
                node2 = node2.next;
            }
        }
        if(node2 == null){
            while(node1 != null){
                if (carry == 1){
                    result.addToHead(carry + node1.info);
                    carry = 0;
                }
                else
                    result.addToHead(node1.info+ carry);
                node1 = node1.next;
            }
        }
        if(carry ==1)
            result.addToHead(carry);
        return result.toString();
    }
    // Multiply contents of two linked lists
    static long multiplyTwoLists(SLL<Integer>  First, SLL<Integer> Second){
        int multiplying_Factor = 1;
        int multiplying_Factor2 ;
        SLLNode<Integer> temp = First.head;
        SLLNode<Integer> temp2;
        long result = 0;
        while (temp != null) {
            temp2 = Second.head;
            multiplying_Factor2 = 1;
            while (temp2 != null) {
                result += (multiplying_Factor * multiplying_Factor2*((temp.info * temp2.info)));
                temp2 = temp2.next;
                multiplying_Factor2*=10;
            }
            multiplying_Factor *= 10;
            temp = temp.next;
        }
        return result;
    }

    // Subtract two linked lists
    static int findMAX(SLL<Integer> n1 , SLL<Integer> n2){
        LabQueue<Integer> q1 = new LabQueue<>();
        LabQueue<Integer> q2 = new LabQueue<>();
        int MAX=0 ;
        while (!n1.isEmpty()){
            q1.enqueue(n1.deleteFromTail());
            q2.enqueue(n2.deleteFromTail());
        }
        while (!q1.isEmpty() && !q2.isEmpty()){
            if(q1.firstEl() > q2.firstEl()){
                MAX = 1;
            } else if (q1.firstEl() < q2.firstEl()) {
                MAX = 2;
            }
            n1.addToHead(q1.dequeue());
            n2.addToHead(q2.dequeue());
        }
        if(MAX == 1)
            return 1;
        else
            return 2;
    }
    static String Subtract(SLL<Integer> n1 , SLL<Integer> n2){
        SLL<Integer> result = new SLL<>();
        if(n1.size()>n2.size())
            return sub(n1,n2,result,0);
        else if (n1.size()<n2.size()) {
            return "-" + sub(n2,n1,result,0);
        }else {
            if(findMAX(n1,n2) == 1)
                return sub(n1,n2,result,0);
            else
                return "-" + sub(n2,n1,result,0);
        }

    }
    static String sub(SLL<Integer> n1 , SLL<Integer> n2, SLL<Integer> result , int carry){
        if(n1.head==null && n2.head == null){
         while (result.head.info == 0)
             result.deleteFromHead();
         return result.toString();
        }

        else if(n1.head!=null && n2.head != null){

            if(n1.head.info-n2.head.info + carry <0){
                result.addToHead(n1.deleteFromHead()- n2.deleteFromHead() + carry + 10);
                carry = -1;
            }

            else
                result.addToHead(n1.deleteFromHead()- n2.deleteFromHead() + carry);
        }
        else if (n2.head == null) {
            if(n1.head.info + carry <0){
                result.addToHead(n1.deleteFromHead() + carry + 10);
                carry = -1;
            }

            else
                result.addToHead(n1.deleteFromHead() + carry);
        } else if (n1.head == null) {
            if(-n2.head.info + carry <0){
                result.addToHead(-n2.deleteFromHead() + carry + 10);
                carry = -1;
            }

            else
                result.addToHead(-n2.deleteFromHead() + carry);
        }
        return sub(n1,n2,result,carry);
    }
    }
