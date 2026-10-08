package Day2;

import Day2.model.Book;
import Day2.model.Loan;
import Day2.model.member.Member;
import Day2.model.member.Student;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Book book = new Book(
                "Clean Code",
                "Robert C. Martin",
                false
        );

        Member student = new Student(
                "Huy",
                20
        );

        Loan loan = new Loan(
                "L001",
                student,
                book,
                LocalDate.now(),
                LocalDate.now().plusDays(14)
        );

        loan.displayInfo();

        System.out.println(
                "Đã trả: " + loan.isReturned()
        );

        System.out.println(
                "Đang hoạt động: " + loan.isActive()
        );

        System.out.println(
                "Đã quá hạn: "
                        + loan.isOverdue(LocalDate.now())
        );

        loan.markAsReturned(LocalDate.now());

        loan.displayInfo();
    }
}