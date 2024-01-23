package HospitalTest;

public class Patient1 {
    private String name;
    private String disease;
    private String doctorName;
    private Double fees;
    private long contact;
    private String gender;
    private int age;
    public Patient1(){}
    public Patient1(String name,int age,String gender,long contact, String disease, String doctorName, Double fees) {
        this.name = name;
        this.age=age;
        this.gender=gender;
        this.contact=contact;
        this.disease = disease;
        this.doctorName = doctorName;
        this.fees = fees;
    }

    public Patient1(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Double getFees() {
        return fees;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }
    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", disease='" + disease + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", fees=" + fees +
                ", contact=" + contact +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
}
