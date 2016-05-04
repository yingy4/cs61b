/* Date.java */

import java.io.*;

class Date {

  /* Put your private data fields here. */
	private int month;
	private int day;
	private int year;

  /** Constructs a date with the given month, day and year.   If the date is
   *  not valid, the entire program will halt with an error message.
   *  @param month is a month, numbered in the range 1...12.
   *  @param day is between 1 and the number of days in the given month.
   *  @param year is the year in question, with no digits omitted.
   */
  public Date(int month, int day, int year) {
	  if (!isValidDate(month,day,year)) {
		  System.exit(0);
	  } else {
		  this.month = month;
		  this.day = day;
		  this.year = year;
	  }
  }

  /** Constructs a Date object corresponding to the given string.
   *  @param s should be a string of the form "month/day/year" where month must
   *  be one or two digits, day must be one or two digits, and year must be
   *  between 1 and 4 digits.  If s does not match these requirements or is not
   *  a valid date, the program halts with an error message.
   */
  public Date(String s) {
	  String[] dateArray=s.split("/");
	  if (dateArray.length != 3) {
		  System.exit(0);
	  } else {
		  if (isValidDate(Integer.parseInt(dateArray[0]),Integer.parseInt(dateArray[1]),Integer.parseInt(dateArray[2]))){
			  this.month = Integer.parseInt(dateArray[0]);
			  this.day = Integer.parseInt(dateArray[1]);
			  this.year = Integer.parseInt(dateArray[2]);
		  } else {
			  System.exit(0);
		  }
	  }
  }

  /** Checks whether the given year is a leap year.
   *  @return true if and only if the input year is a leap year.
   */
  public static boolean isLeapYear(int year) {
	  
	  if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)){
		  return true;
	  } else {
		  return false;
	  }
    
  }

  /** Returns the number of days in a given month.
   *  @param month is a month, numbered in the range 1...12.
   *  @param year is the year in question, with no digits omitted.
   *  @return the number of days in the given month.
   */
  public static int daysInMonth(int month, int year) {

	  switch (month){
	  	case 1:
	  	case 3:
	  	case 5:
	  	case 7:
	  	case 8:
	  	case 10:
	  	case 12:
	  		return 31;
	  	case 2:
	  		if (isLeapYear(year)) {
	  			return 29;
	  		} else {
	  			return 28;
	  		}
	  	default:
	  		return 30;		  
	  }
  }

  /** Checks whether the given date is valid.
   *  @return true if and only if month/day/year constitute a valid date.
   *
   *  Years prior to A.D. 1 are NOT valid.
   */
  public static boolean isValidDate(int month, int day, int year) {
	  
	  if (year < 1) {
		  return false;
	  }
	  if ((month < 1) || (month > 12)) {
		  return false;
	  }
	  if ((day < 1) || (day > daysInMonth(month,year))){
		  return false;
	  }
	  return true;
  }

  /** Returns a string representation of this date in the form month/day/year.
   *  The month, day, and year are expressed in full as integers; for example,
   *  12/7/2006 or 3/21/407.
   *  @return a String representation of this date.
   */
  public String toString() {
    return month+"/"+day+"/"+year;
  }

  /** Determines whether this Date is before the Date d.
   *  @return true if and only if this Date is before d. 
   */
  public boolean isBefore(Date d) {

	  if (d.year > this.year) {
		  return true;
	  } else if ((d.year == this.year) && (d.month > this.month)) {
		  return true;
	  } else if ((d.month == this.month) && (d.day > this.day)) {
		  return true;
	  } else {
		  return false;
	  }
  }

  /** Determines whether this Date is after the Date d.
   *  @return true if and only if this Date is after d. 
   */
  public boolean isAfter(Date d) {

//	  if (d.year < this.year) {
//		  return true;
//	  } else if ((d.year == this.year) && (d.month < this.month)) {
//		  return true;
//	  } else if ((d.month == this.month) && (d.day < this.day)) {
//		  return true;
//	  } else {
//		  return false;
//	  }
	  
	  return (!isBefore(d)) && (!this.toString().equals(d.toString()));
  }

  /** Returns the number of this Date in the year.
   *  @return a number n in the range 1...366, inclusive, such that this Date
   *  is the nth day of its year.  (366 is used only for December 31 in a leap
   *  year.)
   */
  public int dayInYear() {
	  int count=this.day;
	  for (int i=1;i<this.month;i++){
		  count = count + daysInMonth(i,this.year);
	  }
	  return count;
  }

  public int dayFromVeryFirst(Date d) {
	  int count=d.day;
	  for (int i=1;i<d.month;i++){
		  count = count + daysInMonth(i,d.year);
	  }
	  for (int j=1;j<d.year;j++){
		  if (isLeapYear(j)){
			  count = count + 366;
		  } else {
			  count = count + 365;
		  }
	  }
	  return count;
  }
  
  /** Determines the difference in days between d and this Date.  For example,
   *  if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
   *  If this Date occurs before d, the result is negative.
   *  @return the difference in days between d and this date.
   */
  public int difference(Date d) {
	  //if (isBefore(d)) {
		  return dayFromVeryFirst(this)-dayFromVeryFirst(d);
	  //}
  }

  public static void main(String[] argv) {
    System.out.println("\nTesting constructors.");
    Date d1 = new Date(1, 1, 1);
    System.out.println("Date should be 1/1/1: " + d1);
    d1 = new Date("2/4/2");
    System.out.println("Date should be 2/4/2: " + d1);
    d1 = new Date("2/29/2000");
    System.out.println("Date should be 2/29/2000: " + d1);
    d1 = new Date("2/29/1904");
    System.out.println("Date should be 2/29/1904: " + d1);

    d1 = new Date(12, 31, 1975);
    System.out.println("Date should be 12/31/1975: " + d1);
    Date d2 = new Date("1/1/1976");
    System.out.println("Date should be 1/1/1976: " + d2);
    Date d3 = new Date("1/2/1976");
    System.out.println("Date should be 1/2/1976: " + d3);

    Date d4 = new Date("2/27/1977");
    Date d5 = new Date("8/31/2110");


    System.out.println("\nTesting isLeapYear.");
    System.out.println(2000 + " is a leap year should be true: " + 
            isLeapYear(2000));    
    System.out.println(2004 + " is a leap year should be true: " + 
            isLeapYear(2004));    
    System.out.println(2100 + " is a leap year should be false: " + 
            isLeapYear(2100));    
    System.out.println(2001 + " is a leap year should be false: " + 
            isLeapYear(20001));    
    
    

    System.out.println("\nTesting before and after.");
    System.out.println(d2 + " after " + d1 + " should be true: " + 
                       d2.isAfter(d1));
    System.out.println(d3 + " after " + d2 + " should be true: " + 
                       d3.isAfter(d2));
    System.out.println(d1 + " after " + d1 + " should be false: " + 
                       d1.isAfter(d1));
    System.out.println(d1 + " after " + d2 + " should be false: " + 
                       d1.isAfter(d2));
    System.out.println(d2 + " after " + d3 + " should be false: " + 
                       d2.isAfter(d3));

    System.out.println(d1 + " before " + d2 + " should be true: " + 
                       d1.isBefore(d2));
    System.out.println(d2 + " before " + d3 + " should be true: " + 
                       d2.isBefore(d3));
    System.out.println(d1 + " before " + d1 + " should be false: " + 
                       d1.isBefore(d1));
    System.out.println(d2 + " before " + d1 + " should be false: " + 
                       d2.isBefore(d1));
    System.out.println(d3 + " before " + d2 + " should be false: " + 
                       d3.isBefore(d2));

    System.out.println("\nTesting difference.");
    System.out.println(d1 + " - " + d1  + " should be 0: " + 
                       d1.difference(d1));
    System.out.println(d2 + " - " + d1  + " should be 1: " + 
                       d2.difference(d1));
    System.out.println(d3 + " - " + d1  + " should be 2: " + 
                       d3.difference(d1));
    System.out.println(d3 + " - " + d4  + " should be -422: " + 
                       d3.difference(d4));
    System.out.println(d5 + " - " + d4  + " should be 48762: " + 
                       d5.difference(d4));
  }
}
