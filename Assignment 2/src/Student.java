public class Student {

    private int SID;
    private int STUD_ID;
    private String FNAME;
    private String SNAME;

    public Student(int SID, int STUD_ID, String FNAME, String SNAME) {
        this.SID = SID;
        this.STUD_ID = STUD_ID;
        this.FNAME = FNAME;
        this.SNAME = SNAME;
    }

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public int getSTUD_ID() {
        return STUD_ID;
    }

    public void setSTUD_ID(int STUD_ID) {
        this.STUD_ID = STUD_ID;
    }

    public String getFNAME() {
        return FNAME;
    }

    public void setFNAME(String FNAME) {
        this.FNAME = FNAME;
    }

    public String getSNAME() {
        return SNAME;
    }

    public void setSNAME(String SNAME) {
        this.SNAME = SNAME;
    }
}
