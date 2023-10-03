import java.util.Scanner;
public class Client extends User {
    String address;
    int stratum;

    public String getAddress() {
        return address;
    }

    public void setAddress() {
        System.out.println("Address");
        Scanner sc = new Scanner(System.in);
        this.address = sc.nextLine();
    }

    public int getStratum() {
        return stratum;
    }

    public void setStratum() {
        System.out.println("Stratum");
        Scanner sc = new Scanner(System.in);
        this.stratum = sc.nextInt();
    }

    @Override
    public String toString() {
        return "{Name: " + name + "\n DocId: " + docId + "\n Position: " + position + "\n Address: " + address + "\n Stratum: " + stratum + "}";
    }
}
