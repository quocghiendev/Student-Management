/*
 * Student.java
 *
 * Phiên bản: 1.1
 * Ngày cập nhật: 30/09/2026
 * Tác giả: [Họ tên sinh viên] - [Mã sinh viên]
 *
 * Copyright (c) 2026 [Họ tên sinh viên]. All rights reserved.
 *
 * Mô tả: Thành phần Model của chương trình Quản lý sinh viên.
 * Lớp biểu diễn thực thể Sinh viên và các ràng buộc dữ liệu của nó.
 */
package model;


/**
 * Biểu diễn một sinh viên gồm mã, họ tên, tuổi và điểm trung bình (GPA).
 * Đây là thành phần Model trong kiến trúc MVC: lớp chỉ chứa dữ liệu và
 * các phương thức liên quan trực tiếp tới dữ liệu của sinh viên.
 *
 * @version 1.1 30 Sep 2026
 * @author [Họ tên sinh viên]
 */
public class Student {

    /*
     * Không đặt Scanner, lệnh in màn hình, menu hay thuật toán tìm kiếm,
     * sắp xếp vào lớp này. Nhập/xuất thuộc về View, điều phối và nghiệp
     * vụ thuộc về Controller.
     */

    /** Tuổi nhỏ nhất hợp lệ của một sinh viên. */
    public static final int MIN_AGE = 18;

    /** Tuổi lớn nhất hợp lệ của một sinh viên. */
    public static final int MAX_AGE = 100;

    /** Điểm trung bình nhỏ nhất (thang điểm 10). */
    public static final double MIN_GPA = 0.0;

    /** Điểm trung bình lớn nhất (thang điểm 10). */
    public static final double MAX_GPA = 10.0;

    /** Mã sinh viên, không được trùng trong danh sách. */
    private String id;

    /** Họ và tên sinh viên. */
    private String name;

    /** Tuổi của sinh viên, trong khoảng [MIN_AGE, MAX_AGE]. */
    private int age;

    /** Điểm trung bình, trong khoảng [MIN_GPA, MAX_GPA]. */
    private double gpa;

    /**
     * Tạo một sinh viên rỗng, các thuộc tính mang giá trị mặc định.
     */
    public Student() {}

    /**
     * Tạo một sinh viên với đầy đủ thông tin.
     *
     * @param id   mã sinh viên
     * @param name họ tên sinh viên
     * @param age  tuổi sinh viên
     * @param gpa  điểm trung bình
     */
    public Student(String id, String name, int age, double gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    /**
     * Lấy mã sinh viên.
     *
     * @return mã sinh viên
     */
    public String getId() {
        return id;
    }

    /**
     * Cập nhật mã sinh viên.
     *
     * @param id mã sinh viên mới
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Lấy họ tên sinh viên.
     *
     * @return họ tên sinh viên
     */
    public String getName() {
        return name;
    }

    /**
     * Cập nhật họ tên sinh viên.
     *
     * @param name họ tên mới
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Lấy tuổi sinh viên.
     *
     * @return tuổi sinh viên
     */
    public int getAge() {
        return age;
    }

    /**
     * Cập nhật tuổi sinh viên.
     *
     * @param age tuổi mới
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Lấy điểm trung bình của sinh viên.
     *
     * @return điểm trung bình
     */
    public double getGpa() {
        return gpa;
    }

    /**
     * Cập nhật điểm trung bình của sinh viên.
     *
     * @param gpa điểm trung bình mới
     */
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    /**
     * Trả về chuỗi mô tả sinh viên, dùng cho ghi log hoặc gỡ lỗi.
     * Việc định dạng bảng hiển thị cho người dùng do View đảm nhận.
     *
     * @return chuỗi dạng Student[id=..., name=..., age=..., gpa=...]
     */
    @Override
    public String toString() {
        return "Student[id=" + id + ", name=" + name
                + ", age=" + age + ", gpa=" + gpa + "]";
    }
}
