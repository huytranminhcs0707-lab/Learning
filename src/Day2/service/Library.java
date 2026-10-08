package Day2.service;

import Day2.model.Book;
import Day2.model.Loan;
import Day2.model.member.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library implements LibraryService{
    private List<Book> books;
    private List<Member> members;
    private List<Loan> loans;
    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public void addBook(Book book){
        if (books.contains(book)){
            throw new IllegalArgumentException("Book has been already in library");
        }
        if (book == null){
            throw new IllegalArgumentException("Book cannot be null");
        }
        books.add(book);
    }
    public void addMember(Member member){
        if (member == null){
            throw new IllegalArgumentException("Member cannot be null");
        }
        if (members.contains(member)){
            throw new IllegalArgumentException("Member has already in library");
        }
        members.add(member);
    }
    @Override
    public Loan borrowBook(String loanId, Member member, Book book) {
        if (loanId == null || loanId.isBlank()) {
            throw new IllegalArgumentException(
                    "Mã phiếu mượn không được để trống"
            );
        }

        if (member == null) {
            throw new IllegalArgumentException(
                    "Thành viên không được null"
            );
        }

        if (book == null) {
            throw new IllegalArgumentException(
                    "Sách không được null"
            );
        }
        int currentBorrowedBooks =
                countActiveLoansOfMember(member);

        if (currentBorrowedBooks
                >= member.getMaxBorrowLimit()) {
            throw new IllegalStateException(
                    "Thành viên đã đạt giới hạn mượn sách"
            );
        }

        LocalDate borrowDay = LocalDate.now();
        LocalDate overdueDay = borrowDay.plusDays(14);

        Loan loan = new Loan(
                loanId,
                member,
                book,
                borrowDay,
                overdueDay
        );

        book.setAvailable(false);
        loans.add(loan);

        return loan;
    }

    public int countActiveLoansOfMember(Member member){
        int count = 0;
        for (Loan loan : loans){
            if (loan.getBorrower() == member && !loan.isReturned()){
                count++;
            }
        }
        return count;
    }

    public Loan findLoanById(String id){
        for (Loan loan : loans){
            if (loan.getId().equals(id)){
                return loan;
            }
        }
        return null;
    }
    public List<Book> getBooks() {
        return books;
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    @Override
    public void returnBook(Loan loan) {
        loan.markAsReturned(LocalDate.now());
    }

}
