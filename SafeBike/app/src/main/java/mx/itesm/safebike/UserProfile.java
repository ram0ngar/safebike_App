package mx.itesm.safebike;

/**
 * Created by francisco on 22/02/18.
 */

public class UserProfile {

    public String userBike;
    public String userEmail;
    public String userName;

    public UserProfile(){
    }

    public UserProfile(String userBike, String userEmail, String userName) {
        this.userBike = userBike;
        this.userEmail = userEmail;
        this.userName = userName;
    }


    public String getUserBike() {
        return userBike;
    }

    public void setUserBike(String userBike) {
        this.userBike = userBike;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
