package com.devskiller.anonymizer;

public class Anonymizer {

    public static String anonymizeEmail(String email, char replacement) {
        int atIndex = email.indexOf('@');
        if (atIndex > 0) {
            String prefix = email.substring(0, atIndex);
            String domain = email.substring(atIndex);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < prefix.length(); i++) {
                sb.append(replacement);
            }
            sb.append(domain);
            return sb.toString();
        }
        return email;
    }

    public static String anonymizeSkype(String skype, char replacement) {
        return skype.replaceAll(".", String.valueOf(replacement));
    }

    public static String anonymizePhone(String phone, char replacement, int digits) {
        if (phone.length() >= digits) {
            return phone.substring(0, phone.length() - digits).replaceAll("\\d", String.valueOf(replacement))
                    + phone.substring(phone.length() - digits);
        }
        return phone;
    }
}
