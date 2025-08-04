package com.demo;

public class JD implements Switch{

    private String name;
    private boolean status = false; //默认为关闭
    public JD(String name, boolean status) {
        this.name = name;
        this.status = status;
    }
    public String getName() {
        return name;
    }
    public boolean getStatus() {
        return status;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void press() {
        if (status) {
            System.out.println(this.name + "已关闭");
           this.status = false;
        } else {
            System.out.println(this.name + "已打开");
            this.status = true;
        }
    }


}
