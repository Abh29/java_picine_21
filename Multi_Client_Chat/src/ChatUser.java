import java.util.Date;
import java.util.Objects;

public class ChatUser {
    private int     id;
    private String userName;
    private String phone;
    private String passwd;
    private int     last_thread_id;
    private Date    last_seen;


    public ChatUser(int id, String userName, String phone, String passwd) {
        this.id = id;
        this.userName = userName;
        this.phone = phone;
        this.passwd = passwd;
    }

    public ChatUser(String userName, String phone, String passwd)
    {
        this.userName = userName;
        this.phone = phone;
        this.passwd = passwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getLast_thread_id() {
        return last_thread_id;
    }

    public void setLast_thread_id(int last_thread_id) {
        this.last_thread_id = last_thread_id;
    }

    public Date getLast_seen() {
        return last_seen;
    }

    public void setLast_seen(Date last_seen) {
        this.last_seen = last_seen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatUser chatUser = (ChatUser) o;
        return (Objects.equals(userName, chatUser.userName) && Objects.equals(passwd, chatUser.passwd));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, phone, passwd);
    }
}
