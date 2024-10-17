/**
 * Copyright 2024
 * Name: Employees
 */
package PrototypePattern.cloneable;

import java.util.ArrayList;
import java.util.List;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/17/2024
 */
public class Employees implements Cloneable{

    private List<String> empList;

    public Employees(){
        empList = new ArrayList<>();
    }

    public Employees(List<String> list){
        this.empList=list;
    }
    public void loadData(){
        empList.add("A");
        empList.add("B");
        empList.add("C");
        empList.add("D");
    }

    public List<String> getEmpList() {
        return empList;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        List<String> temp = new ArrayList<String>();
        for(String s : this.getEmpList()){
            temp.add(s);
        }
        return new Employees(temp);
    }

}
