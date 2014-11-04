//************************************************************************
//    File: MyWork.java
//    Author: Kelvin Sung
//    Date: 4/20/2005
// 
//  My (Pretend) Work class, pretends to be a workload, 
//  we will call the DoMyWork method to increment a nubmer
// 
///////////////////////////////////////////////////////////

import java.io.*;
import java.util.*;

public class MyWork {
    private int    MyPretendWork[];
    MyWork() { 
        MyPretendWork = new int[1];
        MyPretendWork[0] = 0;
    }

    // synchronized void DoMyWork() {
  void DoMyWork() {
        //synchronized(MyPretendWork) {
            MyPretendWork[0]++;
        //}
    }
    void ShowMyWork() {
        System.err.println("**** From MyWork: PretendWork=" + MyPretendWork[0]);
    }
}
