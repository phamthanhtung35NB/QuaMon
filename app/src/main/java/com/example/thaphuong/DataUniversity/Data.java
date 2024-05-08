package com.example.thaphuong.DataUniversity;

import java.util.ArrayList;
import java.util.List;

public class Data {
public static List<University> getListUniversity(){
    List<University> list = new ArrayList<>();

//    Trường Đại học Công nghệ, ĐHQGHN
//    Trường Đại học Y Dược, ĐHQGHN
//    Trường Đại học Giáo dục, ĐHQGHN
//    Trường Khoa học liên ngành và Nghệ thuật, ĐHQGHN
//    Trường Đại học Khoa học Xã hội và Nhân văn, ĐHQGHN
//    Trường Đại học Kinh tế, ĐHQGHN
//    Trường Quản trị và Kinh doanh, ĐHQGHN
//    Trường Đại học Luật, ĐHQGHN
//    Trường Đại học Ngoại ngữ, ĐHQGHN
//    Trường Quốc tế, ĐHQGHN
//    Khoa Quốc tế Pháp ngữ, ĐHQGHN
//    Trường Đại học Khoa học Tự nhiên, ĐHQGHN
//    Trường Đại học Việt - Nhật, ĐHQGHN
//    Trường Khác

    University cNghe = new University(R.drawable.uet, "Trường Đại học Công Nghệ, ĐHQGHN");
    list.add(cNghe);
    University yDuoc = new University(R.drawable.yduoc, "Trường Đại học Y Dược, ĐHQGHN");
    list.add(yDuoc);
    University giaoDuc = new University(R.drawable.giaoduc, "Trường Đại học Giáo dục, ĐHQGHN");
    list.add(giaoDuc);
    University khoaHocLienNganh = new University(R.drawable.khoahoclienganh, "Trường Khoa học liên ngành và Nghệ thuật, ĐHQGHN");
    list.add(khoaHocLienNganh);
    University khoaHocXaHoi = new University(R.drawable.khoahocxahoi, "Trường Đại học Khoa học Xã hội và Nhân văn, ĐHQGHN");
    list.add(khoaHocXaHoi);
    University kinhTe = new University(R.drawable.kinhte, "Trường Đại học Kinh tế, ĐHQGHN");
    list.add(kinhTe);
    University quanTri = new University(R.drawable.quantri, "Trường Quản trị và Kinh doanh, ĐHQGHN");
    list.add(quanTri);
    University luat = new University(R.drawable.luat, "Trường Đại học Luật, ĐHQGHN");
    list.add(luat);
    University ngoaiNgu = new University(R.drawable.ngoaingu, "Trường Đại học Ngoại ngữ, ĐHQGHN");
    list.add(ngoaiNgu);
    University quocTe = new University(R.drawable.quocte, "Trường Quốc tế, ĐHQGHN");
    list.add(quocTe);
    University phapNgu = new University(R.drawable.phapngu, "Khoa Quốc tế Pháp Ngữ, ĐHQGHN");
    list.add(phapNgu);
    University khoaHocTuNhien = new University(R.drawable.khoahoctunhien, "Trường Đại học Khoa học Tự Nhiên, ĐHQGHN");
    list.add(khoaHocTuNhien);
    University vietNhat = new University(R.drawable.vietnhat, "Trường Đại học Việt - Nhật, ĐHQGHN");
    list.add(vietNhat);
    University khac = new University(R.drawable.khac, "Trường Khác");
    list.add(khac);

    return list;

}

}
