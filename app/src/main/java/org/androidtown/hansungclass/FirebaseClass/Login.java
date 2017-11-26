package org.androidtown.hansungclass.FirebaseClass;

/**
 * Created by hscom-019 on 2017-11-26.
 */

public class Login {

    private String email;
    private String password;
    private String s_grade;
    private String s_name;
    private String student_id;

    public Login(){

    }

    public Login(String email,String password,String s_grade,String s_name, String student_id){
        this.email = email;
        this.password = password;
        this.s_grade = s_grade;
        this.s_name = s_name;
        this.student_id = student_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setS_grade(String s_grade) {
        this.s_grade = s_grade;
    }

    public String getS_grade() {
        return s_grade;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_name() {
        return s_name;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_id() {
        return student_id;
    }
}
