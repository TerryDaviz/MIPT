package com.example.lab12;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;

public class TIMsubmit extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        var authTag = "<input type='submit' value='Login'><br/>" +
                      "<input type='submit' value='Sign up'><br/>";
        try{
            var writer = pageContext.getOut();
            writer.write(authTag);
        }catch (IOException ex){
            throw new JspException(ex.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
