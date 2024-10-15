import java.io.*;

public class Main {
    public static void main(String[] args) {

        Person person = new Person("Joan", 30);

        String file = "Person.ser";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(person);
            System.out.println("The Object has been successfully serialized " + file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Person deserializedPerson = (Person) ois.readObject();
            System.out.println("The object deserialized: " + deserializedPerson);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}