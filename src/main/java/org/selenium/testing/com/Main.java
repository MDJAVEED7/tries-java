package org.selenium.testing.com;

import java.util.ArrayList;
import java.util.Scanner;

class Trie{
    Trie ch[];
    int wc;
    boolean ended;
    Trie()
    {
        ch= new Trie[26];
        wc=0;
        ended=false;
    }

}
public class Main {
    public static void insert(Trie root,String s)
    {
        Trie te=root;
        for(char ci:s.toCharArray())
        {
            int idx=ci-'a';
            if(te.ch[idx]==null)
            {
                te.ch[idx]=new Trie();
            }
            te.wc++;
            te=te.ch[idx];
        }
        te.ended=true;
    }
    public static boolean doesExist(Trie root,String s)
    {
        Trie te=root;
        for(char ci:s.toCharArray())
        {
            int idx=ci-'a';
            if(te.ch[idx]==null)
            {
                return false;
            }
            te=te.ch[idx];
        }
        return te.ended;
    }
    public static void print_all(Trie root,ArrayList<String> li,String curr)
    {
        if(root.ended)
        {
            li.add(curr);
        }
        for(int i=0;i<26;i++)
        {
            if(root.ch[i]!=null)
            {
                char ch=(char)(i+'a');
                print_all(root.ch[i],li,curr+ch);
            }
        }
    }
    public static void prefix_with_given_s(Trie root, ArrayList<String> li, String curr) {
        Trie te = root;

        for (char ci : curr.toCharArray()) {
            int idx = ci - 'a';
            if (te.ch[idx] == null) {
                return;
            }
            te = te.ch[idx];
        }

        collectWords(te, li, curr);
    }

    private static void collectWords(Trie node, ArrayList<String> li, String word) {
        if (node.ended) {
            li.add(word);
        }

        for (int i = 0; i < 26; i++) {
            if (node.ch[i] != null) {
                collectWords(node.ch[i], li, word + (char)(i + 'a'));
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int choise=-1;
        Trie root=new Trie();
        while(choise!=0) {

            System.out.println("enter 1 for insert");
            System.out.println("enter 2 for check existence");
            System.out.println("enter 3 prefix with given string");
            System.out.println("enter 4 to get all words");
            System.out.println("enter 5 exit");
            choise = sc.nextInt();
            if (choise == 1) {
               String s=sc.next();
               insert(root,s);
            }
            if(choise==2)
            {
                String s=sc.next();
                System.out.println(doesExist(root,s));
            }
            if(choise==3)
            {
                String s= sc.next();
                ArrayList<String> li=new ArrayList<>();
                prefix_with_given_s(root,li,s);
                System.out.println(li);
            }
            if(choise==4)
            {

                ArrayList<String> li=new ArrayList<>();
                print_all(root,li,"");
                System.out.println(li);
            }
            if(choise==5)
            {
                return ;
            }
        }
    }
}
