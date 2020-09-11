public class Teacher {
    private String tid;
    private String teacherName;
    private String status;

    public Teacher(String tid, String teacherName, String status) {
        this.tid = tid;
        this.teacherName = teacherName;
        this.status = status;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
