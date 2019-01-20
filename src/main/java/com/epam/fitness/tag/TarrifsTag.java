package com.epam.fitness.tag;

import com.epam.fitness.utils.PeriodCost;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class TarrifsTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        Map<String,BigDecimal> tariffs = PeriodCost.getAllCosts();
        JspWriter out = pageContext.getOut();
        try {
        out.write("<option value=\"Choose tariff\"></option>");
            for (Map.Entry<String, BigDecimal> tariff : tariffs.entrySet()) {
                out.write("<option value=\"" + tariff.getValue() + " \">" + tariff.getKey() + "</option>");
            }
        }catch (IOException exception){
            throw new JspException(exception.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
