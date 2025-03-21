package com.project.broccoli.pot.util;

import java.util.*;

public class ClassLoader {

    public static <T> T load(String classPath) throws ClassNotFoundException{
        return (T) Class.forName(classPath);
    }

    public static <T> List<T> loadList(){
        return new ArrayList<T>();
    }
    public static <T> List<T> loadVector() {
        return new Vector<T>();
    }
    public static <K,V> Map<K,V> loadMap(){
        return new HashMap<K, V>();
    }
    public static <K,V> LinkedHashMap<K,V> loadLinkedMap(){
        return new LinkedHashMap<K,V>();
    }
    public static <T> Set<T> loadSet(){
        return new HashSet<T>();
    }


}
