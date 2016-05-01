/* Nuke2.java */

import java.io.*;

  /** A class whose main method reads a string from the keyboard and prints the same string, with its
   *  second character removed.
   */

class Nuke2 {
  
  /** reads a string from the keyboard and prints the same string, with its
   *  second character removed.
   *  @param arg is not used.
   *  @exception Exception thrown if there are any problems parsing the 
   *             user's input.
   */
  
  public static void main(String[] arg) throws Exception {
    
    BufferedReader keyboard;
    String inputLine;
    keyboard = new BufferedReader(new InputStreamReader(System.in));
    inputLine = keyboard.readLine();
    System.out.println(inputLine.substring(0,1)+inputLine.substring(2));

  }
}
