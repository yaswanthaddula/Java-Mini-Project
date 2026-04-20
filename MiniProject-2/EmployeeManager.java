import java.io.*;
import java.util.*;

public class EmployeeManager {
    private List<Employee> employees = new ArrayList<>();
    private final String FILE_NAME = "employees.dat";

    public EmployeeManager() {
        loadFromFile();
    }

    public void addEmployee(Employee emp) {
        employees.add(emp);
        saveToFile();
    }

    public void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found");
            return;
        }

        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    public void searchEmployee(int id) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                System.out.println("Employee Found: " + e);
                return;
            }
        }
        System.out.println("Employee not found");
    }

    public void deleteEmployee(int id) {
        Iterator<Employee> iterator = employees.iterator();

        while (iterator.hasNext()) {
            Employee e = iterator.next();
            if (e.getId() == id) {
                iterator.remove();
                saveToFile();
                System.out.println("Employee deleted");
                return;
            }
        }

        System.out.println("Employee not found");
    }

    public void updateSalary(int id, double salary) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                e.setSalary(salary);
                saveToFile();
                System.out.println("Salary updated");
                return;
            }
        }

        System.out.println("Employee not found");
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }

    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_NAME))) {
            employees = (List<Employee>) ois.readObject();
        } catch (Exception e) {
            employees = new ArrayList<>();
        }
    }
}