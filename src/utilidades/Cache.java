/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.HashMap;

/**
 *
 * @author fernando_mota
 */
public class Cache {
    private HashMap<Object, Object> data;
    public static Cache instance;
    public Cache(){
        this.data = new HashMap<Object,Object>();
    }
    public static Cache getInstance(){
        if(Cache.instance==null){
            Cache.instance = new Cache();
        }
        return Cache.instance;
    }
    public Object get(Object key){
        return data.get(key);
    }
    public Object set(Object key, Object value){
        return data.put(key,value);
    }
}
