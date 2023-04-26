//**************************  SLL.java  *********************************
//           a generic singly linked list class
public class SLL<T>{

    private class SLLNode<T>{

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

    protected SLLNode<T> head;
    protected SLLNode<T> tail;
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


    /*    public void SmallestLargest(SLL<T> L) {
    // first, we check if the linked list is empty
    // or has one element
            if (L.isEmpty() || L.head==L.tail)
                return; // nothing needs to be done
    // now, we need to search for the smallest and largest
            SLLNode<T> r_temp; T smallest, largest;
    // we start by initializing smallest and largest to the
    // value in at the head of the linked list.
            r_temp = L.head; smallest = largest = r_temp.info;
            while (r_temp.next != null) {
                r_temp = r_temp.next;
                if (r_temp.info.compareTo(smallest)<0)
                    smallest = r_temp.info;
                else if (r_temp.info.compareTo(largest)>0)
                    largest = r_temp.info;
            }
    // Now, make sure that smallest and largest are different
    // If not, this means that all elements are equal and hence
    // nothing needs to be done.
            if (smallest != largest) {
    // delete smallest and insert it into the beginning of L
                L.delete(smallest);
                L.addToHead(smallest);
    // delete largest and insert it into the end of L
                L.delete(largest);
                L.addToTail(largest);
            }
        }*/
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
    public int getMin(SLLNode p){
        return getMin(p, (T) p.info);
    }
    private int getMin(SLLNode p , T e){
        Integer E = (Integer) e;
        T min = null;
        if (p == null)
            return (int) e;
        else {
            if(E.compareTo((Integer) p.info)>0)
                min = (T) p.info;
            return getMin(p.next,min);
        }
    }
}