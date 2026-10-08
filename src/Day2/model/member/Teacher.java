package Day2.model.member;

public class Teacher extends Member{
    public Teacher(String name, int age) {
        super(name, age);
    }

    @Override
    public int getMaxBorrowLimit() {
        return 10;
    }
}
