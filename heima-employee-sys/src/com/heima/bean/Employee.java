package com.heima.bean;
//"ID", "姓名","部门", "职位", "电话", "年龄", "入职时间","薪水"

public class Employee {
    private String id;
    private String name;
    private String department;
    private String position;
    private String phone;
    private String age;
    private String entryTime;
    private String salary;
    public Employee()
    {
    }
    public Employee(String id, String name, String department, String position, String phone, String age, String entryTime, String salary)
    {
        this.id = id;
        this.name = name;
        this.department = department;
        this.position = position;
        this.phone = phone;
        this.age = age;
        this.entryTime = entryTime;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getEntryTime() {
        return entryTime;
    }
    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }
    public String getSalary() {
        return salary;
    }
    public void setSalary(String salary) {
        this.salary = salary;
    }

}
