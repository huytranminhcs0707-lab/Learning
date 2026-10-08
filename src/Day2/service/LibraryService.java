package Day2.service;

import Day2.model.Book;
import Day2.model.Loan;
import Day2.model.member.Member;

public interface LibraryService {
    Loan borrowBook(String loanId, Member member, Book book);
    void returnBook(Loan loan);
}
