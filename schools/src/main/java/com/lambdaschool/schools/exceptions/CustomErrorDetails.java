package com.lambdaschool.schools.exceptions;

import com.lambdaschool.schools.services.HelperFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;

public class CustomErrorDetails extends DefaultErrorAttributes
{
    @Autowired
    HelperFunction helperFunction;

    @Override
    public Map<String, Object> getErrorAttributes(
        WebRequest webRequest,
        boolean includeStackTrace)
    {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        Map<String, Object> errorDetails = new LinkedHashMap<>();
        errorDetails.put("title", errorAttributes.get("error"));
        errorDetails.put("status", errorAttributes.get("status"));
        errorDetails.put("detail", errorAttributes.get("message"));
        errorDetails.put("timestamp", errorAttributes.get("timestamp"));
        errorDetails.put("developerMessage", "path: " + errorAttributes.get("path"));

        errorDetails.put("errors", helperFunction.getConstraintViolation(this.getError(webRequest)));

        return errorDetails;
    }
}
