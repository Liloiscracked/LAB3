public class HashTable <T>{
    Entry [] table;

    public HashTable(Entry [] table) {
        this.table = table;
        for(int i = 0 ; i<this.table.length;i++) // initialize an empty table
            this.table[i] = new Entry<>(null,"E");
    }

    public boolean insert(T dataObject){
        if(find(dataObject) !=-1 ) //  if the object is in the Hashtable, therefore we will prevent duplicates
            return false;

        int place = (Integer) dataObject % this.table.length; // regular insertion if the key
        if(this.table[place].getStatus().equals("O"))
            place = findNextAvailableSlot(place);
        this.table[place] = new Entry<>(dataObject,"O");
        return true;
    }

    public int findNextAvailableSlot(int currentSlot){
        if(this.table[currentSlot].getStatus().equals("E") || this.table[currentSlot].getStatus().equals("D"))
            return currentSlot % table.length;
        return findNextAvailableSlot(currentSlot+1);
    }

    public boolean delete(T dataObject){
    if(find(dataObject) == -1)
        return false;
    int hashfunc = (Integer) dataObject % this.table.length;
    while(!this.table[hashfunc].getDataObject().equals(dataObject));{
        hashfunc++;
        }
        this.table[hashfunc-1].setStatus("D");
    return true;
    }
    public int find(T dataObject){
        int hashfunc = (Integer) dataObject % this.table.length;
        while (!this.table[hashfunc].getStatus().equals("E")){
            if(this.table[hashfunc].getDataObject().equals(dataObject) && !this.table[hashfunc].getStatus().equals("D"))
                return hashfunc;
            hashfunc++;
        }
        return  -1;
    }
    public void tostring(){
    for(int i = 0 ; i< this.table.length;i++){
        System.out.println(i+" : " + "[ " + this.table[i].getDataObject() + ", " + this.table[i].getStatus()+" ]" );
    }
    }
}
