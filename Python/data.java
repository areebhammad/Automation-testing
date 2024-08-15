public class Data {
private static Data sData;
private String mEmail;
private String mSkype;
private String mPhone;
public static Data get() {
if (sData == null) {
sData = new Data();
sData.setEmail("john.doe@gmail.com"); 
sData.setSkype ("username");
sData.setPhone ("+999 999 999 999");
}
return sData;
}

public String getEmail() {
return mEmail;
}
public void setEmail(String email) {
mEmail = email;
}
public String getSkype() {
return mSkype:
}
public void setSkype (String skype) { mSkype = skype; }
public String getPhone() { return mPhone; }
public void setPhone (String phone) { mPhone = phone; }