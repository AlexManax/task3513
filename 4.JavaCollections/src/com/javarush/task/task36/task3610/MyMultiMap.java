package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        return values().size();
    }

    @Override
    public V put(K key, V value) {
//        int count;
//        if (map.containsKey(key)) {
//            if (map.get(key).size() < repeatCount)
//                map.get(key).add(value);
//
//            if (map.get(key).size() == repeatCount){
//                map.get(key).remove(0);
//                map.get(key).add(value);}
//        } else return null;
//        return map.get(key).get(map.get(key).size()-1);

        List<V> values = map.get(key);
        V oldValue = null;

        if (values == null) {
            values = new ArrayList<>();

        } else {
            oldValue = values.get(values.size()-1);
            if (values.size() == repeatCount)
                values.remove(0);
        }

        values.add(value);
        map.put(key, values);
        return oldValue;
    }

    @Override
    public V remove(Object key) {
//        V o = null;
//        if (!map.containsKey(key)) return null;
//        if (!map.get(key).isEmpty())
//            o = map.get(key).remove(0);
//        if (map.get(key).size()==0)
//         map.remove(key);
//        else return o;
//
//        return null;

        //напишите тут ваш код
        List<V> values = map.get(key);
        if (values == null)
            return null;

        V storeValue = values.get(0);
        values.remove(0);

        if (values.size() == 0)
            map.remove(key);

        return storeValue;
    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
//        return (ArrayList<V>) map.values();
        ArrayList<V> values = new ArrayList<>();
        for (List<V> value : map.values()) {
            values.addAll(value);
        }
        return values;
    }

    @Override
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        return keySet().contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //напишите тут ваш код
        return values().contains(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}