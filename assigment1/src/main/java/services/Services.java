package services;

import domain.Bilet;
import domain.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Services {
     private  Map<User,List<Bilet>> list;
     private List<Bilet> listaBilete;

    public Services() {
         list = new HashMap<User,List<Bilet>>();
         listaBilete = new LinkedList<Bilet>();
    }

    public void buyTicket(User user, Bilet bilet){
        listaBilete.add(bilet);
        list.put(user,listaBilete);
    }

    public void getList() {
        Set<Entry<User,List<Bilet>>> hashSet=list.entrySet();
        for(Entry entry:hashSet ) {

            System.out.println("Key="+entry.getKey()+", Value="+entry.getValue());
        }
    }

    public void setList(Map<User, List<Bilet>> list) {
        this.list = list;
    }


}
