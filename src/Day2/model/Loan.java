package Day2.model;

import Day2.model.member.Member;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {

    private String id;
    private Member borrower;
    private Book book;

    private LoanStatus loanStatus = LoanStatus.ACTIVE;

    private LocalDate borrowDay;
    private LocalDate overdueDay;
    private LocalDate returnDay;

    public Loan(
            String id,
            Member borrower,
            Book book,
            LocalDate borrowDay,
            LocalDate overdueDay
    ) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(
                    "ID phiếu mượn không được để trống"
            );
        }

        if (borrower == null) {
            throw new IllegalArgumentException(
                    "Người mượn không được null"
            );
        }

        if (book == null) {
            throw new IllegalArgumentException(
                    "Sách không được null"
            );
        }

        if (borrowDay == null || overdueDay == null) {
            throw new IllegalArgumentException(
                    "Ngày mượn và hạn trả không được null"
            );
        }

        if (overdueDay.isBefore(borrowDay)) {
            throw new IllegalArgumentException(
                    "Hạn trả không được trước ngày mượn"
            );
        }

        this.id = id;
        this.borrower = borrower;
        this.book = book;
        this.borrowDay = borrowDay;
        this.overdueDay = overdueDay;
    }

    public String getId() {
        return id;
    }

    public Member getBorrower() {
        return borrower;
    }

    public Book getBook() {
        return book;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public LocalDate getBorrowDay() {
        return borrowDay;
    }

    public LocalDate getOverdueDay() {
        return overdueDay;
    }

    public LocalDate getReturnDay() {
        return returnDay;
    }

    /*
     * Kiểm tra phiếu mượn đã được trả chưa.
     */
    public boolean isReturned() {
        return loanStatus == LoanStatus.RETURNED;
    }

    /*
     * Kiểm tra phiếu mượn còn đang hoạt động không.
     */
    public boolean isActive() {
        return loanStatus == LoanStatus.ACTIVE;
    }

    /*
     * Kiểm tra phiếu mượn có quá hạn
     * tại một ngày cụ thể không.
     */
    public boolean isOverdue(LocalDate currentDay) {
        if (currentDay == null) {
            throw new IllegalArgumentException(
                    "Ngày kiểm tra không được null"
            );
        }

        return !isReturned()
                && currentDay.isAfter(overdueDay);
    }

    /*
     * Cập nhật trạng thái phiếu mượn
     * dựa trên ngày hiện tại.
     */
    public void updateStatus(LocalDate currentDay) {
        if (isOverdue(currentDay)) {
            loanStatus = LoanStatus.OVERDUE;
        }
    }

    /*
     * Đánh dấu sách đã được trả.
     */
    public void markAsReturned(LocalDate returnDay) {
        if (isReturned()) {
            throw new IllegalStateException(
                    "Sách này đã được trả trước đó"
            );
        }

        if (returnDay == null) {
            throw new IllegalArgumentException(
                    "Ngày trả không được null"
            );
        }

        if (returnDay.isBefore(borrowDay)) {
            throw new IllegalArgumentException(
                    "Ngày trả không được trước ngày mượn"
            );
        }

        this.returnDay = returnDay;
        this.loanStatus = LoanStatus.RETURNED;

        // Đưa sách về trạng thái có thể mượn.
        book.setAvailable(true);
    }

    /*
     * Tính số ngày trễ hạn.
     */
    public long calculateOverdueDays(LocalDate currentDay) {
        if (!isOverdue(currentDay)) {
            return 0;
        }

        return ChronoUnit.DAYS.between(
                overdueDay,
                currentDay
        );
    }

    /*
     * Hiển thị thông tin phiếu mượn.
     */
    public void displayInfo() {
        System.out.println("===== THÔNG TIN PHIẾU MƯỢN =====");
        System.out.println("Mã phiếu: " + id);
        System.out.println(
                "Người mượn: " + borrower.getName()
        );
        System.out.println(
                "Tên sách: " + book.getName()
        );
        System.out.println("Ngày mượn: " + borrowDay);
        System.out.println("Hạn trả: " + overdueDay);

        if (returnDay == null) {
            System.out.println("Ngày trả: Chưa trả");
        } else {
            System.out.println("Ngày trả: " + returnDay);
        }

        System.out.println("Trạng thái: " + loanStatus);
        System.out.println("===============================");
    }
}