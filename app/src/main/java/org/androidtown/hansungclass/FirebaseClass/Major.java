package org.androidtown.hansungclass.FirebaseClass;

import android.util.Log;

/**
 * Created by LG on 2017-11-12.
 */

public class Major {
    public String divide;
    public String nclass;
    public String ntime;
    public String professor;
    public String subject;
    public Major(){

    }
    public Major(String divide, String nclass, String ntime, String professor, String subject) {
        this.divide = divide;
        this.nclass = nclass;
        this.ntime = ntime;
        this.professor = professor;
        this.subject = subject;
    }

    public String getDivide() {
        return divide;
    }

    public void setDivide(String divide) {
        this.divide = divide;
    }

    public String getNclass() {
        return nclass;
    }

    public void setNclass(String nclass) {
        this.nclass = nclass;
    }

    public String getNtime() {
        return ntime;
    }

    public void setNtime(String ntime) {
        this.ntime = ntime;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
