package Day4;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;
    private LocalDate hireDate;

    public Employee(int id, String name, String department, double salary, LocalDate hireDate) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public LocalDate getHireDate() { return hireDate; }

    @Override
    public String toString() {
        return String.format("Emp[ID=%d, Name='%s', Dept='%s', Salary=%.0f, Hired=%s]",
                id, name, department, salary, hireDate);
    }
}

public class Day4Assignment {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(1, "An", "IT", 20000000, LocalDate.of(2021, 3, 15)),
                new Employee(2, "Bình", "HR", 12000000, LocalDate.of(2019, 5, 10)),
                new Employee(3, "Cường", "IT", 25000000, LocalDate.of(2022, 1, 20)),
                new Employee(4, "Dũng", "Finance", 18000000, LocalDate.of(2018, 11, 5)),
                new Employee(5, "Giang", "HR", 16000000, LocalDate.of(2023, 8, 12))
        );

        List<Employee> filtered = employees.stream().filter(employee -> employee.getSalary() > 15000000 && employee.getHireDate().getYear() >2020).toList();
        Map<String, List<Employee>> byDept = employees.stream().collect(Collectors.groupingBy(e -> e.getDepartment()));
        double avgSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);

                // TODO 4: Tìm NV lương cao nhất (Trả về Optional<Employee>)
        Optional<Employee> highestPaid = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));

        // IN KẾT QUẢ TẠI ĐÂY...
        filtered.forEach(e -> System.out.println("   - " + e));
        byDept.forEach((dept, empList) -> {
            System.out.println("   + Phòng: " + dept);
            empList.forEach(e -> System.out.println("     * " + e.getName()));
        });
        System.out.printf("\n3. Lương trung bình toàn công ty: %,.0f VNĐ\n", avgSalary);
        highestPaid.ifPresentOrElse(
                e -> System.out.println(e.getName() + " (" + e.getDepartment() + ") với mức lương " + e.getSalary() + " VNĐ"),
                () -> System.out.println("Không có nhân viên nào trong danh sách.")
        );
    }
}