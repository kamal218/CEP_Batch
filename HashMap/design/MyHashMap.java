package design;
import java.util.*;

public class MyHashMap<Key, Value> implements Iterable {
    @Override
    public Iterator iterator() {
        travel it = new travel();
        return it;
    }

    int i = 0;
    int j = 0;

    public class travel implements Iterator {
        @Override
        public boolean hasNext() {
            if (i == buckets.length) {
                return false;
            }
            return true;
        }

        @Override
        public int next() {
            while (i < buckets.length) {
                if (bucketsp[i].size() == 0 || j == buckets[i].size()) {
                    i++;
                }else{
                    j++;
                }
            }
            return i==buckets?null:buckets[i].get(j);
        }
    }

    public class HMNode {
        Key key = null;
        Value value = null;

        public HMNode(Key k, Value v) {
            this.key = k;
            this.value = v;
        }
    }

    // actual hashmap is array of linked list
    LinkedList<HMNode>[] buckets;
    int size = 0;

    // constructor
    public MyHashMap() {
        initialize(10);
    }

    public void initialize(int size) {
        buckets = new LinkedList[size];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    // put
    public Value put(Key key, Value value) {
        // bucket index
        int idx = getHashCode(key);
        // does exist
        LinkedList<HMNode> group = buckets[idx];
        boolean contains = containsKey(key);
        if (contains == true) {
            group.getFirst().value = value;
        } else {
            HMNode node = new HMNode(key, value);
            group.addLast(node);
            size++;
        }
        return value;
    }

    // get
    public Value get(Key key) {
        int idx = getHashCode(key);
        LinkedList<HMNode> group = buckets[idx];
        boolean contains = containsKey(key);
        if (contains) {
            return group.getFirst().value;
        } else {
            return null;
        }
    }

    // get orr default
    public Value getOrDefault(Key key, Value dvalue) {
        int idx = getHashCode(key);
        LinkedList<HMNode> group = buckets[idx];
        boolean contains = containsKey(key);
        if (contains) {
            return group.getFirst().value;
        } else {
            return dvalue;
        }
    }

    // remove
    public Value remove(Key key) {
        int idx = getHashCode(key);
        LinkedList<HMNode> group = buckets[idx];
        boolean contains = containsKey(key);
        if (contains) {
            Value ans = group.getFirst().value;
            group.removeFirst();
            size--;
            return ans;
        } else {
            return null;
        }
    }

    // containskey
    public boolean containsKey(Key key) {
        int idx = getHashCode(key);
        LinkedList<HMNode> group = buckets[idx];
        int size = group.size();
        while (size-- > 0) {
            if (group.getFirst().key.equals(key)) {
                return true;
            }
            group.addLast(group.removeFirst());
        }
        return false;
    }

    // size
    public Integer size() {
        return size;
    }

    // keyset
    public ArrayList<Key> keySet() {
        ArrayList<Key> ans = new ArrayList<>();
        for (int i = 0; i < buckets.length; i++) {
            LinkedList<HMNode> group = buckets[i];
            int size = group.size();
            while (size-- > 0) {
                ans.add(group.getFirst().key);
                group.addLast(group.removeFirst());
            }
        }
        return ans;
    }

    public int getHashCode(Key key) {
        int hc = Math.abs(key.hashCode()) % buckets.length;
        return hc;
    }

    // REHASHING

    public void rehash() {
        LinkedList<HMNode>[] temp = buckets;
        initialize(temp.length * 2);
        for (int i = 0; i < temp.length; i++) {
            LinkedList<HMNode> group = temp[i];
            int size = group.size();
            while (size-- > 0) {
                HMNode first = group.getFirst();
                put(first.key, first.value);
                group.removeFirst();
            }
        }
    }
}