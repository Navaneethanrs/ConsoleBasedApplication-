import java.util.*;

class Hr {
    static Scanner sc = new Scanner(System.in);

    static class Employee {
        int id;
        String name, department, email, password;
        int salary;
        int attendanceDays = 0;
        int leaveBalance = 10;
        ArrayList<String> leaveRequests = new ArrayList<>();

        Employee(int id, String name, String department, String email, String password) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.email = email;
            this.password = password;
            this.salary = 30000;
        }
    }

    static ArrayList<Employee> employees = new ArrayList<>();

    static void admin(String name, String pass) {
        String aname = "HRManager";
        String apass = "HR1234HR";
        if ((name.equals(aname)) && (pass.equals(apass))) {
            System.out.println("Login successfully...");
            adminMenu();
        } else {
            System.out.println("Invalid Credential...");
        }
    }

    static void adminMenu() {
        while (true) {
            System.out.println("\n   Admin Dashboard   ");
            System.out.println("1. Add Employee\n2. View Employees\n3. Edit Employee\n4. Delete Employee");
            System.out.println("5. View Attendance\n6. Approve/Reject Leaves\n7. Generate Payroll\n8. Logout");
            System.out.print("Enter choice: ");
            switch (sc.nextInt()) {
                case 1: addEmployee(); break;
                case 2: viewEmployees(); break;
                case 3: editEmployee(); break;
                case 4: deleteEmployee(); break;
                case 5: viewAttendance(); break;
                case 6: approveLeave(); break;
                case 7: generatePayroll(); break;
                case 8: return;
                default: System.out.println("Invalid choice!..");
            }
        }
    }

    static void addEmployee() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Department: "); String dept = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Password : "); String pass = sc.nextLine();
        employees.add(new Employee(id, name, dept, email, pass));
        System.out.println("Employee added!");
    }

    static void viewEmployees() {
        System.out.println("\nEmployee List:");
        for (Employee e : employees) {
            System.out.println(e.id + " | " + e.name + " | " + e.department + " | " + e.email);
        }
    }

    static void editEmployee() {
        System.out.print("Enter Employee ID to edit: ");
        int id = sc.nextInt(); sc.nextLine();
        for (Employee e : employees) {
            if (e.id == id) {
                System.out.print("New Name: "); e.name = sc.nextLine();
                System.out.print("New Department: "); e.department = sc.nextLine();
                System.out.print("New Email: "); e.email = sc.nextLine();
                System.out.println("Employee Updated!");
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    static void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();
        employees.removeIf(e -> e.id == id);
        System.out.println("Employee removed.");
    }

    static void viewAttendance() {
        for (Employee e : employees) {
            System.out.println(e.name + " (" + e.id + ") - Attendance Days: " + e.attendanceDays);
        }
    }

    static void approveLeave() {
        for (Employee e : employees) {
            if (!e.leaveRequests.isEmpty()) {
                System.out.println("Leave request from " + e.name + ": " + e.leaveRequests.get(0));
                System.out.print("Approve? (y/n): ");
                String choice = sc.next();
                if (choice.equalsIgnoreCase("y")) {
                    e.leaveBalance--;
                    System.out.println("Leave Approved.");
                } else {
                    System.out.println("Leave Rejected.");
                }
                e.leaveRequests.remove(0);
            }
        }
    }

    static void generatePayroll() {
        System.out.println("\nPayroll Report:");
        for (Employee e : employees) {
            int salaryEarned = e.salary + (e.attendanceDays * 200);
            System.out.println(e.name + " (" + e.id + ") - Salary: " + salaryEarned);
        }
    }

    static void Esignin(int id, String password) {
        for (Employee e : employees) {
            if (e.id == id && e.password.equals(password)) {
                System.out.println("Successfully Logined....");
                employeeMenu(e);
                return;
            }
        }
        System.out.println("Invalid id and password....");
    }

    static void employeeMenu(Employee e) {
        while (true) {
            System.out.println("\nEmployee Dashboard");
            System.out.println("1. View Profile\n2. Mark Attendance\n3. Apply Leave\n4. View Leave Balance\n5. Logout");
            System.out.print("Enter choice: ");
            switch (sc.nextInt()) {
                case 1:
                    System.out.println("ID: " + e.id + ", Name: " + e.name + ", Dept: " + e.department +
                            ", Email: " + e.email + ", Salary: " + e.salary);
                    break;
                case 2:
                    e.attendanceDays++;
                    System.out.println("Attendance marked. Total days: " + e.attendanceDays);
                    break;
                case 3:
                    sc.nextLine();
                    System.out.print("Enter leave reason: ");
                    String reason = sc.nextLine();
                    e.leaveRequests.add(reason);
                    System.out.println("Leave request submitted.");
                    break;
                case 4:
                    System.out.println("Remaining Leave Balance: " + e.leaveBalance);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice....");
            }
        }
    }

    static void Esignup(int eid, String ename, String edept, String email, String epass) {
        employees.add(new Employee(eid, ename, edept, email, epass));
        System.out.println("Successfully Registered....");
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n  HR MANAGEMENT SYSTEM   ");
            System.out.println("1. Admin Login \n2. Employee SignUp  \n3. Employee SignIn \n4. Exit");
            System.out.print("Enter your choice : ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("   Admin Login   ");
                    System.out.print("Admin Name : ");
                    String name = sc.nextLine();
                    System.out.print("Admin password : ");
                    String pass = sc.nextLine();
                    admin(name, pass);
                    break;
                case 2:
                    System.out.println("   Employee SignUp   ");
                    System.out.print("Employee Name : ");
                    String ename = sc.nextLine();
                    System.out.print("Employee Id : ");
                    int eid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Department :  ");
                    String edept = sc.nextLine();
                    System.out.print("Employee email : ");
                    String email = sc.nextLine();
                    System.out.print("Employee Password : ");
                    String epass = sc.nextLine();
                    Esignup(eid, ename, edept, email, epass);
                    break;
                case 3:
                    System.out.println("   Employee SignIn   ");
                    System.out.print("Enter Employee ID : ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Employee pass : ");
                    String password = sc.nextLine();
                    Esignin(id, password);
                    break;
                case 4:
                    System.out.println("Exit...");
                    return;
                default:
                    System.out.println("Invalid choice....");
            }
        }
    }
}
