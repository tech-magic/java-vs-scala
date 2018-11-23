package hello;

import java.util.*;
import java.util.stream.Collectors;

abstract class Student {
    private String name;
    private Date dateOfBirth;

    private static int studentCount = 0;

    public Student(Date dateOfBirth) {
        this(null, dateOfBirth);
    }

    public Student(String name, Date dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        studentCount++;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected String getName() {
        return this.name;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public abstract void doWork();

    public static int getStudentCount() {
        return studentCount;
    }
}

interface GuitarPlayer {
    void playGuitar();
}

interface PianoPlayer {
    void playPiano();
}

class MusicStudent extends Student implements GuitarPlayer, PianoPlayer {

    public MusicStudent(String name, Date dateOfBirth) {
        super(dateOfBirth);
        super.setName(name);
    }

    public void playGuitar() {
        System.out.println("Playing Guitar");
    }

    public void playPiano() {
        System.out.println("Playing Piano");
    }

    public void doWork() {
        this.playGuitar();
        this.playPiano();
    }

    @Override
    public String getName() {
        return "Maestro" + super.getName();
    }
}

class HelloWorldApp {
    public static void main(String[] args) throws IllegalArgumentException, NullPointerException {
        try {
            Student student = new MusicStudent("wimal", new Date());
            student.doWork();

            // Lists
            List<Student> students = new ArrayList();
            students.add(student);

            Student firstStudent = students.get(0);
            if (firstStudent == null) {
                throw new IllegalArgumentException("Should have at least one student ...");
            }

            firstStudent.doWork();

            // Iterators
            Iterator<Student> it = students.iterator();
            while (it.hasNext()) {
                Student next = it.next();
                next.doWork();
            }

            // Maps
            Map<String, List<Student>> studentMap = new HashMap();
            studentMap.put("musicStudents", students);

            Student firstMusicStudent = studentMap.get("musicStudents").get(0);
            firstMusicStudent.doWork();

            // Arrays
            Student[] studentsArray = new Student[1];
            studentsArray[0] = student;

            studentsArray[0].doWork();

            // 2D Arrays
            Student[][] students2DArray = new Student[1][1];
            students2DArray[0][0] = student;

            System.out.println(students2DArray[0][0].getName());

            // Lambda
            List<Student> result = students.stream()
                    .filter(lmbStudent -> ("wimal".equals(lmbStudent.getName())))
                    .collect(Collectors.toList());

            System.out.println(result.size());

            System.out.println(Student.getStudentCount());
        } catch (IllegalArgumentException illex) {
            illex.printStackTrace();
            throw illex;
        } catch (NullPointerException nex) {
            nex.printStackTrace();
            throw nex;
        } finally {
            System.out.println("In the finally block");
        }
    }
}

