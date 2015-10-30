package com.blog.signup;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//http://stackoverflow.com/questions/25030857/java-code-for-regex-of-email-validation

public class EmailValidate {

	private Pattern emailPattern;
    private Matcher emailMatcher;

    private String EMAIL_REGEX = 
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidate() {
    	emailPattern = Pattern.compile(EMAIL_REGEX);
    }

    public boolean validate(String email) {
    	emailMatcher = emailPattern.matcher(email);
        return emailMatcher.matches();

    }
}
