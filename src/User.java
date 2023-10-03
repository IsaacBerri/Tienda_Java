import java.util.Scanner;

public abstract class User{
    String name;
    int docId;
    String position;

    public void logging() {
        System.out.println("logging in ");
    }

    public String getName() {
        return name;
    }

    public void setName() {
        System.out.println("Name");
        Scanner sc = new Scanner(System.in);
        this.name = sc.next();
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId() {
        System.out.println("Doc");
        Scanner sc = new Scanner(System.in);
        this.docId = sc.nextInt();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition() {
        System.out.println("Pocition");
        Scanner sc = new Scanner(System.in);
        this.position = sc.next();
    }
}
