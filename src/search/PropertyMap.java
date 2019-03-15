package search;

public class PropertyMap<Key,Value> {

    private Key key;
    private Value value;

    public PropertyMap(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Key getKey(){return this.key;}

    public Value getValue(){return this.value;}

    @Override
    public String toString(){
        return key + ": " + value;
    }
}
