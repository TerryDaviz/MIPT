package com.example.lab12;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;

public class TIMprintTable extends TagSupport {

    private String tableName;


    private int itemsCount;
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            var writer = pageContext.getOut();
            writer.write("<h1>"+ tableName + "</h1>");
            writer.write("<table border='1'>");
            writer.write("<tr><td>");
        } catch (IOException e) {
            throw new JspException(e);
        }
       return EVAL_BODY_INCLUDE;
    }
    @Override
    public int doAfterBody() throws JspException {
        if (itemsCount-- > 1) {
            try {
                pageContext.getOut().write("</td></tr><tr><td>");
            } catch (IOException e) {
                throw new JspException(e);
            }
            return EVAL_BODY_AGAIN;
        }else {
            return SKIP_BODY;
        }
    }

    @Override
    public int doEndTag() throws JspException {
        var writer = pageContext.getOut();
        try {
            writer.write("</td></tr>");
            writer.write("<table>");
        } catch (IOException e) {
            throw new JspException(e);
        }
        return EVAL_PAGE;
    }
}
