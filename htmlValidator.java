package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;


public class HTMLValidator {
    private String htmlString="";
    private HashSet<String> selfClosingTag = new HashSet<>();
    private HashSet<String> ValidTags = new HashSet<>();
    private int htmlLength;
    
    Stack<String> tagStack = new Stack<>();
    // default construcutor
    public HTMLValidator(){
        fillValidTags();
        fillSelfClosingTags();
    }
    // parameterized constructor
    public HTMLValidator(String input){
        this();
        htmlString = input;
        htmlLength = htmlString.length();

    }
    // setter for HTMLString
    public void setHTMLString(String input){
        htmlString = input;
        htmlLength = input.length();
    }
    // getter for HTMLString
    public String getHTMLString(){
        return htmlString;
    }
    // conversion function of start tag into end tag
    private String startTagintoEndTag(String startTag){
        String endTag = "</";
        for(int i=1;i<startTag.length();i++){
            endTag+=startTag.charAt(i);

        }
        return endTag;
    }
    private boolean StartTagPush(int i){
        String tagGenerated = "<";
        for(int j=i+1;j<htmlLength;j++){
            char c = htmlString.charAt(j);
            if(c=='>' || c==' '){
                String startTag = tagGenerated+'>';
                if(!ValidTags.contains(startTag))
                return false;

                if(selfClosingTag.contains(startTag))
                break;
                String endTag = startTagintoEndTag(startTag);
                tagStack.push(endTag);
                break;
            }
            tagGenerated+=c;

        }
        return true;

    }
    private boolean EndTagPop(int i) {
        String tagGenerated = "<";
        for (int j = i + 1; j < htmlLength; j++) {
            char c = htmlString.charAt(j);
            tagGenerated += c;
            if (c == '>') {
                String endTag = tagGenerated;
                String checkTag = tagStack.pop();
                if (!checkTag.equals(endTag))
                    return false;
                break;

            }
        }

        return true;
    }


    // logic for checking validity
    public boolean isValid(){
        for(int i=0;i<htmlLength;i++){
            char currentCharacter = htmlString.charAt(i);
            char nextCharacter = (i+1<htmlLength?htmlString.charAt(i+1):'c');

            if(currentCharacter=='<' ) {

                if(nextCharacter=='/')
                {
                    if(EndTagPop(i)==false)
                        return false;
                    else
                        continue;
                }
                else {
                 if(StartTagPush(i)== false)
                  return false;
                  else
                      continue;
                    }
            }
        }
        if(!tagStack.isEmpty())
            return false;
        return true;
    }


    private void fillValidTags(){
        String arr[]={"<html>","<head>","<title>","<script>","<body>","<div>","<p>","<ul>","<li>","<b>","<i>","<br>","<img>","<span>","<link>"};
        for(String element:arr)
            ValidTags.add(element);
    }
    private void fillSelfClosingTags(){
        String arr[]={"<img>","<br>","<link>"};
        for(String element:arr)
            selfClosingTag.add(element);
    }

}
