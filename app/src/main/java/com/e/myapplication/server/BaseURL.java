package com.e.myapplication.server;

public class BaseURL {

    public static String baseURL = "http://192.168.43.105:5050/";

    public static  String login = baseURL + "pengguna/login";
    public static  String register = baseURL + "pengguna/registrasi";

    //jam
    public static String datajam = baseURL + "jam/datajam";
    public static String editDataJam = baseURL + "jam/ubah/";
    public static String hapusData= baseURL + "jam/hapus/";
    public static String inputBuku= baseURL + "jam/input/";

}
