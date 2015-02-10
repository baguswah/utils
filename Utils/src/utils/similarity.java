/*
 * SPDXVersion: SPDX-1.1
 * Creator: Person: Nuno Brito (nuno.brito@triplecheck.de)
 * Creator: Organization: TripleCheck (contact@triplecheck.de)
 * Created: 2014-10-23T23:53:20Z
 * LicenseName: EUPL-1.1-without-appendix
 * FileName: TokenizerJava.java  
 * FileCopyrightText: <text> Copyright 2014 Nuno Brito, TripleCheck </text>
 * FileComment: <text> 
    Use this class for generating an outputs that we later use for
    comparing similarities between two files or text
</text> 
 */

package utils;

import org.apache.commons.lang3.StringUtils;


/**
 *
 * @author Nuno Brito, 23rd of October 2014 in Tettnang, Germany
 */
public class similarity {

    /**
     * Compute the similarity between two strings and provide a percentage,
     * doesn't really matter in which order they are compared
     * @param s0    String 1
     * @param s1    String 2
     * @return  A value ranging from 0 to 100%
     */
    public static int levenshteinPercentage(final String s0, final String s1) {
        final int value = StringUtils.getLevenshteinDistance(s0, s1);
        int percentage =(int) (100 - (float) value * 100 / (float) (s0.length() + s1.length()));
        return percentage;
    }

  /**
   * Enjoy life, keep similarity matching as simple as possible.
   * @param c1 The string in your archive
   * @param c2 The string that might be modified
   * @return A percentage of how closely related both strings might be
   */
  public static int britoshteinPercentage(final char[] c1, final char[] c2) {
         // get the smallest of the two arrays to compare
        final int size = Math.min(c1.length, c2.length);
        int points = 0;
        int pointer = 0;
        // compare each case
        for(int i = 0; i < size; i++){
            if(c1[pointer] == c2[i]){
                points++;
                pointer++;
            }
        }
        // get the percentual value
        return (points * 100) / size;
    }

  
    
    public static void main(String[] args){
        String a1  = "abcdefghijklmnopqrstuvwxyz0123456789";
        String a2 = "ab..-cdefghij..klmnopqrstuvwxyz0..123456789";
        
        String b1 = "TR{IV=V;IF(V>V){V=VM(V[0]);}TestHandlerV=NM();V.ProcessorV=NVM(V);TServerSocketV=NM(V);TProtocolFactoryV=NVM();TServerV;V=NM(V,V,V);VM(##+V+##);VM();}CA(EVceptionV){VM();}VM(##);";
        String b2 = "TR{IV=V;IF(V>V){V=VM(V[0]);}TestHandlerV=NM();V.ProcessorV=NVM(V);,,,V,TServerSocketV=NM(V);TProtocolFactoryV=NVM();TServerV;V=NM(V,V,V);VM(##+fdfdfsV+##);VM();}CA(EVceptionV){VM();}VM(##);";
        int result = britoshteinPercentage(b1.toCharArray(), b2.toCharArray());
        System.out.println(result);
    }
    
}
