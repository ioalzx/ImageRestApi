package com.ioalzx.animeimagerestapi.entity;

import com.mitchellbosecke.pebble.utils.Pair;
import org.springframework.boot.LazyInitializationExcludeFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ImageQueryParams {


    private String cat = "Anime";
    private final Pair<Integer, Integer> wp;
    private final Pair<Integer, Integer> hp;
    private final Pair<Integer, Integer> fp;
    private final List<Integer> ratingList;
    private final List<Pair<Integer, String>> tagList;
    private String shape = "";
    private Boolean trans = false;

    public ImageQueryParams(String cat, String w, String h, String fsize, String rating, String tags, String shape, Boolean trans) {
        this.cat = cat != null? cat:"Anime";
        this.shape = shape != null? shape.toLowerCase():"desktop";
        this.trans = trans != null;

        this.wp = w != null? getBoundary(w):null;
        this.hp = h != null? getBoundary(h):null;
        this.fp = fsize != null? getBoundary(fsize):null;

        this.ratingList = rating != null ? getNumberList(rating): List.of(1);
        this.tagList = tags != null ? getTagL(tags):null ;

    }

    private Pair<Integer, Integer> getBoundary(String range) {
        String[] boundaries = range.split(":");
        int left, right;


        if ( boundaries.length != 2 && ! range.endsWith(":")) {
            return null;
        }

        try {
            left = Integer.parseInt(boundaries[0]);
        }
        catch (Exception e) {
            left = -1;
        }

        try {
            right = Integer.parseInt(boundaries[1]);
        }
        catch (Exception e) {
            right = -1;
        }

        return new Pair<>(left,right);
    }

    private List<Integer> getNumberList (String str) {
        var nums = str.toCharArray() ;
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (char ch : nums) {
            Integer num;
            try {
                num = Integer.valueOf(String.valueOf(ch));
            }
            catch (Exception e) {
                continue;
            }

            if (  !(num <=5 && num >= 1) || result.contains(num) ) {
                continue;
            }

            result.add(num);

        }
        return result;
    }

    private List<Pair<Integer, String>> getTagL (String str) {

        str = str.toLowerCase();
        str = str.replace("*", "").replace("^", "");
        str = str.replace("(a)", " * ").replace("(o)", " ^ ").replace("(", " ( ").replace(")", " ) ");;

        var strElements = str.split(" ");

        List<Pair<Integer, String>> result = new ArrayList<Pair<Integer, String>>();

        for ( String s : strElements ) {
            if (s.equals("")) {
                continue;
            }
            else if ( s.equals("(") ) {
                result.add(new Pair<>(2, ""));
            }
            else if ( s.equals(")") ) {
                result.add(new Pair<>(3, ""));
            }
            else if ( s.equals("*") ) {
                result.add(new Pair<>(4, ""));
            }
            else if ( s.equals("^") ) {
                result.add(new Pair<>(5, ""));
            }
            else if (s.startsWith("!")) {
                result.add(new Pair<>( 6, s.replace("!", "") ));
            }
            else {
                result.add(new Pair<>( 1, s ));
            }
        }

        return result;
    }

    public String getCat() {
        return cat;
    }

    public Pair<Integer, Integer> getWp() {
        return wp;
    }

    public Pair<Integer, Integer> getHp() {
        return hp;
    }

    public Pair<Integer, Integer> getFp() {
        return fp;
    }

    public List<Integer> getRatingList() {
        return ratingList;
    }

    public List<Pair<Integer, String>> getTagList() {
        return tagList;
    }

    public String getShape() {
        return shape;
    }

}
