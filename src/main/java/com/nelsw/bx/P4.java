package com.nelsw.bx;

import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
public class P4 {

    static List<String>         result  = new ArrayList<>();
    static Map<Integer, String> dollars = new HashMap<>();
    static Map<Integer, String> cents   = new HashMap<>();

    public static void main(String[] args) {

        var p = "15.94;16.00";
        var a = MakeChange(p);
        var e = "NICKEL,PENNY";
        System.out.println(a.equals(e));
        System.out.println(a);
        System.out.println(e);
    }

    public static String MakeChange(String purchaseInfo) {

        cents.put(1, "PENNY");
        cents.put(5, "NICKEL");
        cents.put(10, "DIME");
        cents.put(25, "QUARTER");
        cents.put(50, "HALF DOLLAR");

        dollars.put(1, "ONE");
        dollars.put(2, "TWO");
        dollars.put(5, "FIVE");
        dollars.put(10, "TEN");
        dollars.put(20, "TWENTY");
        dollars.put(50, "FIFTY");
        dollars.put(100, "ONE HUNDRED");


        String[]   sp    = purchaseInfo.split(";");
        String     price = sp[0];
        String     cash  = sp[1];
        BigDecimal d1    = new BigDecimal(price);
        BigDecimal d2    = new BigDecimal(cash);

        int c = d2.compareTo(d1);
        if (c < 0) {
            return "ERROR";
        }

        if (c == 0) {
            return "ZERO";
        }

        String[] priceChunks  = price.split("\\.");
        String   priceDollars = priceChunks[0];
        String   priceCents   = priceChunks[1];

        String[] cashChunks  = cash.split("\\.");
        String   cashDollars = cashChunks[0];
        String   cashCents   = cashChunks[1];

        int pd = Integer.parseInt(priceDollars);
        int pc = Integer.parseInt(priceCents);

        int cd = Integer.parseInt(cashDollars);
        int cc = Integer.parseInt(cashCents);


        int rc;
        if (cc == 0) {
            rc = 100 - pc;
            cd--;
        } else {
            rc = cc - pc;
        }

        while (rc > 0) {
            rc -= makeCentChange(rc);
        }

        int rd = cd - pd;
        while (rd > 0) {
            rd -= makeDollarChange(rd);
        }

        return result
                .stream()
                .sorted()
                .collect(Collectors.joining(","));
    }

    private static int makeDollarChange(int r) {

        if (r >= 100) {
            return makeDollarChange(r, 100);
        }

        if (r >= 50) {
            return makeDollarChange(r, 50);
        }

        if (r >= 20) {
            return makeDollarChange(r, 20);
        }

        if (r >= 10) {
            return makeDollarChange(r, 10);
        }

        if (r >= 5) {
            return makeDollarChange(r, 5);
        }

        if (r >= 2) {
            return makeDollarChange(r, 2);
        }

        if (r >= 1) {
            return makeDollarChange(r, 1);
        }

        return 0;
    }

    private static int makeCentChange(int r) {
        if (r >= 50) {
            return makeCentChange(r, 50);
        }
        if (r >= 25) {
            return makeCentChange(r, 25);
        }
        if (r >= 10) {
            return makeCentChange(r, 10);
        }
        if (r >= 5) {
            return makeCentChange(r, 5);
        }
        if (r >= 1) {
            return makeCentChange(r, 1);
        }
        return 0;
    }


    private static int makeDollarChange(int r, int k) {
        int p = 0;
        r /= k;
        while (r-- > 0) {
            result.add(dollars.get(k));
            p += k;
        }
        return p;
    }

    private static int makeCentChange(int r, int k) {
        int p = 0;
        r /= k;
        while (r-- > 0) {
            result.add(cents.get(k));
            p += k;
        }
        return p;
    }


}
