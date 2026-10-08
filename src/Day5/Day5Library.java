package Day5;

import java.util.List;


sealed interface LibraryItem permits Book, DigitalMedia {
    String title();
    double getFee();
}

record Book(String title, String author, double price) implements LibraryItem {
    @Override
    public double getFee() {
        return price * 0.05;
    }
}

record DigitalMedia(String title, String format, double sizeMB) implements LibraryItem {
    @Override
    public double getFee() {
        return 10000;
    }
}

public class Day5Library {

    // 3. PATTERN MATCHING FOR SWITCH: Xử lý đa hình mà không cần dùng 'if-else' hay 'instanceof' rườm rà
    public static void printItemDetails(LibraryItem item) {
        String details = switch (item) {
            case Book b -> String.format("Sách giấy: '%s' | Tác giả: %s | Phí mượn: %,.0f VNĐ",
                    b.title(), b.author(), b.getFee());

            case DigitalMedia d -> String.format("Tài liệu số: '%s' | Định dạng: %s (%.1f MB) | Phí mượn: %,.0f VNĐ",
                    d.title(), d.format(), d.sizeMB(), d.getFee());
        };

        System.out.println(details);
    }

    public static void main(String[] args) {
        // 4. TEXT BLOCKS & VAR: In banner bằng Text Block ngắn gọn
        var banner = """
            ===========================================
            === HỆ THỐNG QUẢN LÝ THƯ VIỆN (JAVA 17) ===
            ===========================================
            """;
        System.out.println(banner);

        // Khởi tạo danh sách bằng var và Record
        var items = List.of(
                new Book("Clean Code", "Robert C. Martin", 350000),
                new DigitalMedia("Design Patterns PDF", "PDF", 15.5),
                new Book("Effective Java", "Joshua Bloch", 450000),
                new DigitalMedia("Spring Boot Masterclass Video", "MP4", 1200.0)
        );

        // Duyệt danh sách và in thông tin
        items.forEach(Day5Library::printItemDetails);

        // Tính tổng phí mượn bằng Stream API kết hợp với Method Reference
        double totalFee = items.stream()
                .mapToDouble(LibraryItem::getFee)
                .sum();

        System.out.printf("\nTổng phí mượn tất cả tài liệu: %,.0f VNĐ\n", totalFee);
    }
}