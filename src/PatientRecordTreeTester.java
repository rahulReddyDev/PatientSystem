//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Patient Record System
// Files: PatientRecord.java, PatientRecordNode.java, PatientRecordTree.java,
// PatientRecordTreeTester.java
// Course: (CS 300, Spring, and 2020)
//
// Author: Sai Rahul Reddy Kondlapudi
// Email: kondlapudi@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////// PAIR PROGRAMMING (MAY SKIP WHEN WORKING INDIVIDUALLY) ////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understood the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Students who get help from sources other than their partner and the course
// staff must fully acknowledge and credit those sources here. If you did not
// receive any help of any kind from outside sources, explicitly indicate NONE
// next to each of the labels below.
//
// Persons: none
// Online Sources: none
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * PatientRecordTree.
 *
 */

/**
 * @author SaiRahulReddyKondlapudi
 * @Description This is the PatientRecordTreeTester
 *
 */
public class PatientRecordTreeTester {

  /**
   * Checks the correctness of the implementation of both addPatientRecord() and toString() methods
   * implemented in the PatientRecordTree class. This unit test considers at least the following
   * scenarios. (1) Create a new empty PatientRecordTree, and check that its size is 0, it is empty,
   * and that its string representation is an empty string "". (2) try adding one patient record and
   * then check that the addPatientRecord() method call returns true, the tree is not empty, its
   * size is 1, and the .toString() called on the tree returns the expected output. (3) Try adding
   * another patientRecord which is older that the one at the root, (4) Try adding a third patient
   * Record which is younger than the one at the root, (5) Try adding at least two further patient
   * records such that one must be added at the left subtree, and the other at the right subtree.
   * For all the above scenarios, and more, double check each time that size() method returns the
   * expected value, the add method call returns true, and that the .toString() method returns the
   * expected string representation of the contents of the binary search tree in an ascendant order
   * from the oldest patient to the youngest one. (6) Try adding a patient whose date of birth was
   * used as a key for a patient record already stored in the tree. Make sure that the
   * addPatientRecord() method call returned false, and that the size of the tree did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddPatientRecordToStringSize() {
    int count = 0;
    PatientRecordTree test = new PatientRecordTree();
    if (test.size() == 0 && test.isEmpty() == true && test.toString().equals(""))
      ++count;

    PatientRecord test1 = new PatientRecord("Sarah", "1/2/1935");
    if (test.addPatientRecord(test1) == true && test.size() == 1 && test.isEmpty() == false
            && test.toString().equals("Sarah(1/2/1935)" + "\n"))
      return true;

    PatientRecord test2 = new PatientRecord("George", "5/27/1943");
    if (test.addPatientRecord(test2) == true && test.size() == 2
            && test.toString().equals("Sarah(1/2/1935)" + "\n" + "George(5/27/1943)" + "\n"))
      ++count;

    PatientRecord test3 = new PatientRecord("Anna", "3/18/1928");
    if (test.addPatientRecord(test3) == true && test.size() == 3 && test.toString().equals(
            "Anna(3/18/1928)" + "\n" + "Sarah(1/2/1935)" + "\n" + "George(5/27/1943)" + "\n"))
      ++count;

    PatientRecord test4 = new PatientRecord("Tom", "5/14/1926");
    if (test.addPatientRecord(test4) == true && test.size() == 4
            && test.toString().equals("Tom(5/14/1926)" + "\n" + "Anna(3/18/1928)" + "\n"
                    + "Sarah(1/2/1935)" + "\n" + "George(5/27/1943)" + "\n"))
      ++count;


    // test 6
    PatientRecord test5 = new PatientRecord("Han", "7/21/1951");
    if (test.addPatientRecord(test5) == true && test.size() == 5
            && test.toString()
                    .equals("Tom(5/14/1926)" + "\n" + "Anna(3/18/1928)" + "\n" + "Sarah(1/2/1935)"
                            + "\n" + "George(5/27/1943)" + "\n" + "Han(7/21/1951)" + "\n"))
      ++count;

    if (test.addPatientRecord(test1) != false && test.size() != 5)
      ++count;

    if (count == 7) // if all tests return true
      return true;
    else
      return false;
  }


  /**
   * This method checks mainly for the correctness of the PatientRecordTree.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new PatientRecordTree. Then, check
   * that calling the lookup() method with any valid date must throw a NoSuchElementException. (2)
   * Consider a PatientRecordTree of height 3 which consists of at least 5 PatientRecordNodes. Then,
   * try to call lookup() method to search for the patient record at the root of the tree, then a
   * patient records at the right and left subtrees at different levels. Make sure that the lookup()
   * method returns the expected output for every method call. (3) Consider calling .lookup() method
   * on a non-empty PatientRecordTree with a date of birth not stored in the tree, and ensure that
   * the method call throws a NoSuchElementException.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddPatientRecordAndLookup() {

    int count = 0;

    PatientRecordTree test = new PatientRecordTree();
    try {
      test.lookup("04/14/2001");
    } catch (NoSuchElementException e) {
      if (e.getMessage().equals("No search results."))
        count++;
    }


    PatientRecord test1 = new PatientRecord("Sarah", "1/2/1935");
    test.addPatientRecord(test1);
    PatientRecord test2 = new PatientRecord("George", "5/27/1943");
    test.addPatientRecord(test2);
    PatientRecord test3 = new PatientRecord("Anna", "3/18/1928");
    test.addPatientRecord(test3);
    PatientRecord test4 = new PatientRecord("Tom", "5/14/1926");
    test.addPatientRecord(test4);
    PatientRecord test5 = new PatientRecord("Han", "7/21/1951");
    test.addPatientRecord(test5);

    if (test.lookup("1/2/1935").compareTo(test1) == 0
            && test.lookup("5/27/1943").compareTo(test2) == 0
            && test.lookup("5/14/1926").compareTo(test4) == 0)
      count++;


    try {
      test.lookup("04/14/2001");
    } catch (Exception e) {
      if (e.getMessage().equals("No search results."))
        count++;
    }

    if (count == 3)
      return true;
    else
      return false;
  }

  /**
   * Checks for the correctness of PatientRecordTree.height() method. This test must consider
   * several scenarios such as, (1) ensures that the height of an empty patient record tree is zero.
   * (2) ensures that the height of a tree which consists of only one node is 1. (3) ensures that
   * the height of a PatientRecordTree with the following structure for instance, is 4. (*) / \ (*)
   * (*) \ / \ (*) (*) (*) / (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    PatientRecordTree patientRecord = new PatientRecordTree();
    if (patientRecord.height() != 0) {
      return false;
    }
    patientRecord.addPatientRecord(new PatientRecord("William", "6/4/1998"));
    if (patientRecord.height() != 1) {
      return false;
    }
    patientRecord.addPatientRecord(new PatientRecord("Adam", "8/12/1972"));
    patientRecord.addPatientRecord(new PatientRecord("Norah", "11/23/1985"));
    patientRecord.addPatientRecord(new PatientRecord("Sam", "9/12/2003"));
    patientRecord.addPatientRecord(new PatientRecord("Rahul", "6/6/2001"));
    patientRecord.addPatientRecord(new PatientRecord("Andrew", "4/20/2019"));
    patientRecord.addPatientRecord(new PatientRecord("Emily", "2/28/2020"));

    if (patientRecord.height() != 4) {
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of PatientRecordTree.getRecordOfYoungestPatient() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetRecordOfYoungestPatient() {
    PatientRecordTree patientRecord = new PatientRecordTree();
    patientRecord.addPatientRecord(new PatientRecord("Adam", "8/12/1972"));
    patientRecord.addPatientRecord(new PatientRecord("Norah", "11/23/1985"));
    if (patientRecord.getRecordOfYoungestPatient().toString().compareTo("Norah(11/23/1985)") != 0) {
      return false;
    }
    patientRecord.addPatientRecord(new PatientRecord("Sam", "9/12/2003"));
    if (patientRecord.getRecordOfYoungestPatient().toString().compareTo("Sam(9/12/2003)") != 0) {
      return false;
    }
    patientRecord.addPatientRecord(new PatientRecord("Emily", "2/28/2020"));
    if (patientRecord.getRecordOfYoungestPatient().toString().compareTo("Emily(2/28/2020)") != 0) {
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of PatientRecordTree.getRecordOfOldestPatient() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetRecordOfOldestPatient() {
    PatientRecordTree patientRecord = new PatientRecordTree();
    patientRecord.addPatientRecord(new PatientRecord("Adam", "8/12/1972"));
    patientRecord.addPatientRecord(new PatientRecord("Norah", "11/23/1985"));
    if (patientRecord.getRecordOfOldestPatient().toString().compareTo("Adam(8/12/1972)") != 0) {
      return false;
    }
    patientRecord.addPatientRecord(new PatientRecord("Sam", "9/12/2003"));
    if (patientRecord.getRecordOfOldestPatient().toString().compareTo("Adam(8/12/1972)") != 0) {
      return false;
    }
    patientRecord.addPatientRecord(new PatientRecord("George", "5/27/1943"));
    if (patientRecord.getRecordOfOldestPatient().toString().compareTo("George(5/27/1943)") != 0) {
      return false;
    }
    return true;
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testAddPatientRecordToStringSize: " + testAddPatientRecordToStringSize());
    System.out.println("testAddPatientRecordAndLookup: " + testAddPatientRecordAndLookup());
    System.out.println("testHeight: " + testHeight());
    System.out.println("testGetRecordOfYoungestPatient: " + testGetRecordOfYoungestPatient());
    System.out.println("testGetRecordOfOldestPatient: " + testGetRecordOfOldestPatient());
  }
}