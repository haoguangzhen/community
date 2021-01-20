package com.hgz.community.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class CommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
        /*File ctoFile = new File("D:\\DemoPerjoct\\community\\readme.txt");
        InputStreamReader rdCto = null;
        try {
            rdCto = new InputStreamReader(new FileInputStream(ctoFile));
            BufferedReader bfReader = new BufferedReader(rdCto);
            String txtline = null;
            while ((txtline = bfReader.readLine()) != null) {
                System.out.println(txtline);
            }
            bfReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        System.out.println("******************* community Has Started ************************");

        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("loaded...."+i+"%.......");
            if(i == 100){
                System.out.println(".................. off the stock ..................");
            }
        }

    };


}
