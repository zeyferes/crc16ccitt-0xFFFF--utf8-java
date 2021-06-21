package com.kocesat;

import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        String input = "00020101021229300012D156000000000510A93FO3230Q31280012D15600000001030812345678520441115802CN5914BEST TRANSPORT6007BEIJING64200002ZH0104最佳运输0202北京540523.7253031565502016233030412340603***0708A60086670902ME91320016A0112233449988770708123456786304";
        System.out.println(calculateCRC(input)); // prints A13A
    }

    private static String calculateCRC(String input)
    {
        int crc = 0xFFFF;
        int polynomial = 0x1021;
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        for(byte b : bytes)
        {
            for (int i = 0; i < 8; i++)
            {
                boolean bit = (b >> (7 - i) & 1) == 1;
                boolean c15 = (crc >> 15 & 1) == 1;
                crc <<= 1;
                if (c15 ^ bit)
                {
                    crc ^= polynomial;
                }
            }
        }
        crc &= 0xFFFF;
        return String.format("%04x", crc).toUpperCase();
    }
}
