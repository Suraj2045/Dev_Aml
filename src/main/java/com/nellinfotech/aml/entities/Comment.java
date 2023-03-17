package com.nellinfotech.aml.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Comment")
public class Comment {
    private String content;
    
    private String DeltaAction;
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getDeltaAction() {
        return DeltaAction;
    }
    
    public void setDeltaAction(String DeltaAction) {
        this.DeltaAction = DeltaAction;
    }
    
    @Override
    public String toString() {
        return "ClassPojo [content = " + content + ", DeltaAction = " + DeltaAction + "]";
    }
}
