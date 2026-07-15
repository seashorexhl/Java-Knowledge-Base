package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.Statistics;

/**
 * @Author: xhl
 * @Date: 2026-06-24 18:35
 * @Description: User 实体类
 */
import java.math.BigDecimal;

public class User {
    private Long id;
    private String name;
    private Integer age;
    private BigDecimal salary;
    private String department;

    public User() {}

    public User(Long id, String name, Integer age, BigDecimal salary, String department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
