package logginApp;

public enum userType {
    Admin, Client;

    userType(){}

    public String value(){
        return this.name();
    }

    public static userType fromValue(String v){
        return valueOf(v);
    }

}
