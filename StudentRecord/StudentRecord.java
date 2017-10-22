package StudentRecord;


import java.io.Serializable;
import java.util.Calendar;

public class StudentRecord implements Serializable {
    private String lastName;    // Required
    private String firstName;   // Required
    private String id;          // Optional
    private String dateOfBirth; // Optional
    private int age;            // Optional

    public static class StudentRecordBuilder {
        private String firstName;
        private String lastName;
        private String id = "";
        private String dateOfBirth = "";
        private int age = 0;

        public StudentRecordBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public StudentRecordBuilder id(String s) {
            this.id = s;
            return this;
        }

        public StudentRecordBuilder dateOfBirth(String s) {
            this.dateOfBirth = s;
            return this;
        }

        public StudentRecord build() {
            return new StudentRecord(this);
        }

    }

    private StudentRecord(StudentRecordBuilder b) {
        this.id = b.id;
        this.lastName = b.lastName;
        this.firstName = b.firstName;
        this.dateOfBirth = b.dateOfBirth;
        this.age = b.age;
    }

    public boolean isIDZero()
    {
        if (this.id.equals("0"))
            return true;
        else
            return false;
    }

    public void ComputeAge()
    {
        this.age = Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(this.dateOfBirth.substring(0,4));
        int i = (Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        if ((Calendar.getInstance().get(Calendar.MONTH)+1) == Integer.parseInt(this.dateOfBirth.substring(5,7)))//month + 1 due to month starting at 0
        {
            if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) < Integer.parseInt(this.dateOfBirth.substring(8,10)))
            {
                this.age -= 1;
            }
        }
        else if ((Calendar.getInstance().get(Calendar.MONTH)+1) < Integer.parseInt(this.dateOfBirth.substring(5,7)))//month + 1 due to month starting at 0
        {
            this.age -= 1;
        }

    }

    public int getAge()
    {
        return this.age;
    }

    public int getID()
    {
        return Integer.parseInt(this.id);
    }

    public void setNotFound(int id)
    {
        this.id = String.valueOf(id);
        this.lastName = "Not Found";
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public String getFirstName()
    {
        return this.firstName;
    }
    @Override
    public String toString() {
        String newLine = System.getProperty("line.separator");
        return (lastName + newLine +
                firstName + newLine +
                id + newLine +
                age);
    }
}

