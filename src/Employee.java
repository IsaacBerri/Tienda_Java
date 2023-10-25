import java.io.IOException;

public class Employee extends User{
  AdoptionProcess adoption;

  {
    try {
      adoption = new AdoptionProcess();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  public void createProcess(){
    adoption.addProcess();
  }
  public void update(){
    adoption.updateProcess();
  }
  public void read(){
    adoption.readProcess();
  }

}
