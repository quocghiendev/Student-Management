/*
 * StudentController.java
 *
 * Phiên bản: 1.3
 * Ngày cập nhật: 30/09/2026
 * Tác giả: [Họ tên sinh viên] - [Mã sinh viên]
 *
 * Copyright (c) 2026 [Họ tên sinh viên]. All rights reserved.
 *
 * Mô tả: Thành phần Controller của chương trình Quản lý sinh viên.
 * Lớp điều phối luồng chương trình và xử lý nghiệp vụ trên danh sách
 * sinh viên.
 */
package controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import model.Student;
import view.StudentView;


/**
 * Điều phối giữa View và Model của chương trình Quản lý sinh viên.
 * Đây là thành phần Controller trong kiến trúc MVC: nhận lựa chọn từ
 * View, thực hiện nghiệp vụ (thêm, kiểm tra trùng mã, tìm kiếm, sắp xếp)
 * trên danh sách Student, rồi yêu cầu View hiển thị kết quả.
 *
 * @version 1.3 30 Sep 2026
 * @author [Họ tên sinh viên]
 */
public class StudentController {

    /** Danh sách sinh viên đang được quản lý (lưu trong bộ nhớ). */
    private final List<Student> students;

    /** View dùng để nhập và xuất dữ liệu. */
    private final StudentView view;

    /**
     * Tạo Controller với danh sách rỗng và View mặc định.
     */
    public StudentController() {
        students = new ArrayList<>();
        view = new StudentView();
    }

    /**
     * Chạy chương trình cho tới khi người dùng chọn thoát hoặc luồng nhập
     * bị đóng.
     */
    public void run() {
        try {
            runMenu();
        } catch (IllegalStateException e) {

            /*
             * Luồng nhập đã đóng (Ctrl+D / Ctrl+Z hoặc file input hết dữ
             * liệu): không thể đọc tiếp nên thông báo và kết thúc.
             */
            view.showMessage("\n" + e.getMessage()
                    + " Program terminated.");
        }
    }

    /**
     * Lặp hiển thị menu, đọc lựa chọn và gọi chức năng tương ứng cho tới
     * khi người dùng chọn thoát.
     */
    private void runMenu() {
        while (true) {
            view.showMenu();

            /*
             * Menu có 5 chức năng đánh số từ 1 đến 5; View đọc và kiểm tra
             * để lựa chọn luôn nằm trong khoảng này.
             */
            int choice = view.inputChoice(1, 5);

            switch (choice) {
            case 1:
                addStudents();
                break;
            case 2:
                displayStudents();
                break;
            case 3:
                searchStudent();
                break;
            case 4:
                sortStudentsByGpa();
                break;
            case 5:
                view.showMessage("Program terminated.");
                return;
            default:

                /* Không xảy ra vì View đã kiểm tra lựa chọn hợp lệ. */
                view.showMessage("Invalid choice.");
                break;
            }
        }
    }

    /**
     * Thêm nhiều sinh viên vào danh sách.
     * Hỏi số lượng, sau đó nhập lần lượt từng sinh viên. Mã sinh viên bị
     * trùng sẽ được yêu cầu nhập lại.
     */
    private void addStudents() {
        int n = view.inputNumberOfStudents();

        for (int i = 0; i < n; i++) {
            view.showMessage("\nStudent " + (i + 1));

            String id = inputUniqueId();
            Student student = view.inputStudentDetails(id);
            students.add(student);
        }
        view.showMessage(n + " student(s) added successfully.");
    }

    /**
     * Yêu cầu nhập mã sinh viên cho tới khi mã chưa tồn tại trong danh sách.
     *
     * @return mã sinh viên chưa được sử dụng
     */
    private String inputUniqueId() {
        while (true) {
            String id = view.inputStudentId();

            if (!isIdExisted(id)) {
                return id;
            }
            view.showMessage("ID " + id + " already exists. Try again.");
        }
    }

    /**
     * Kiểm tra mã sinh viên đã có trong danh sách hay chưa.
     * <p>
     * Phép so sánh không phân biệt chữ hoa, chữ thường.
     *
     * @param id mã cần kiểm tra
     * @return true nếu mã đã tồn tại, ngược lại false
     */
    private boolean isIdExisted(String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Hiển thị toàn bộ danh sách sinh viên.
     */
    private void displayStudents() {
        view.displayStudents(students);
    }

    /**
     * Tìm sinh viên có tên chứa từ khóa (không phân biệt hoa thường)
     * và hiển thị kết quả.
     */
    private void searchStudent() {
        String keyword = view.inputSearchName().toLowerCase();
        List<Student> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getName().toLowerCase().contains(keyword)) {
                result.add(student);
            }
        }

        if (result.isEmpty()) {
            view.showMessage("No student found.");
        } else {
            view.showMessage("\nSearch result:");
            view.displayStudents(result);
        }
    }

    /**
     * Sắp xếp danh sách theo điểm trung bình tăng dần và hiển thị kết quả.
     * Thứ tự sau sắp xếp được giữ lại cho các lần hiển thị tiếp theo.
     */
    private void sortStudentsByGpa() {
        if (students.isEmpty()) {
            view.showMessage("Student list is empty.");
            return;
        }

        students.sort(Comparator.comparingDouble(Student::getGpa));
        view.showMessage("Students sorted by GPA (ascending).");
        view.displayStudents(students);
    }
}
