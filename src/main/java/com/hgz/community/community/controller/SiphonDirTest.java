package com.hgz.community.community.controller;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TODO: SiphonDirTest
 *
 * @author CHENG DU
 * @version 1.0
 * @date 2021/1/8 11:20
 */
public class SiphonDirTest {
   /* private List<String> dirStrHandler(String pathStr) {
        List<String> dirList = Arrays.asList(pathStr.split("/"));
        List<String> after = new ArrayList<>();
        dirList.forEach(e -> {
            if (!e.isEmpty() && !".".equals(e)) {
                if ("..".equals(e)) {
                    after.remove(after.size() - 1);
                } else {
                    after.add(e);
                }
            }
        });
        return after;
    }
    private List<String> extractBlanket(List<String> l1, List<String> l2) {
        for (int i = 0; i < (Math.min(l1.size(), l2.size())); i++) {
            if (l1.get(i).equals(l2.get(i))) {
                continue;
            }
            if (i > 0) {
                return l1.subList(0, i);
            }
        }
        return l1.size() < l2.size() ? l2 : l1;
    }
    @Test
    public void siphonDirTest() {
        String path1 = "/a/b/c/e/1/2/e";
        String path2 = "/a/b/c//e/1///2";
        String path3 = "/a/b/c/./e/../../3";
        List<String> result = this.extractBlanket(this.dirStrHandler(path1), this.dirStrHandler(path3));
        System.out.println("result = " + result);
    }*/
       public static void test(String path){
           String pathUsed = "";

           List<String> paths = new ArrayList<>();

           while (true){
               int a = path.lastIndexOf("/");

               int a2 = path.lastIndexOf("/..");
               int a3 = path.lastIndexOf("/.");
               int a4 = path.lastIndexOf("//");
               if (a!=0 && a-1==a4){
                   a=a4;
               }
               int max = Math.max(a4, Math.max(a3, Math.max(a2, a)));

               String b = path.substring(max, path.length());
               paths.add(b);
               path = path.substring(0,max);
               if (max==0) {
                   break;
               }
           }
           Collections.reverse(paths);
           System.out.println(paths);
           for (String s : paths) {
               if (s.contains("/..")) {
                   pathUsed=pathUsed.substring(0,pathUsed.lastIndexOf("/"));
                   continue;
               }
               if (s.contains("//")) {
                   pathUsed=pathUsed + s.substring(1,s.length());
                   continue;
               }
               if (s.contains("/.")) {
                   continue;
               }
               if (s.contains("/")) {
                   pathUsed=pathUsed + s;
               }
           }
           System.out.println(pathUsed);
       }

       public static void main(String[] args) {
           test("/aaa/bbb//ccc/../tt/./p");
           test("/aaa/bbb/ccc/../tt/.//2");
           test("/aaa/bbb/ccc/../../tt/.//2");


   }

}
