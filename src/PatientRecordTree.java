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
 * This class implements a binary search tree (BST) which stores a set of patient records. The left
 * subtree contains the patient records of all the patients who are older than the patient who's
 * PatientRecord is stored at a parent node. The right subtree contains the patient records of all
 * the patients who are younger than the patient who's PatientRecord is stored at a parent node.
 *
 */

/**
 * @author SaiRahulReddyKondlapudi
 * @Description This is the PatientRecordTree class.
 *
 */
public class PatientRecordTree {
  private PatientRecordNode root; // root of this binary search tree
  private int size; // total number of patient records stored in this tree.

  /**
   * Checks whether this binary search tree (BST) is empty
   * 
   * @return true if this PatientRecordTree is empty, false otherwise
   */
  public boolean isEmpty() {
    if (root == null || size == 0) {
      return true;
    }
    return false;
  }

  /**
   * Returns the number of patient records stored in this BST.
   * 
   * @return the size of this PatientRecordTree
   */
  public int size() {
    return size;
  }

  /**
   * Recursive helper method to add a new PatientRecord to a PatientRecordTree rooted at current.
   * 
   * @param current   The "root" of the subtree we are inserting newRecord into.
   * @param newRecord The PatientRecord to be added to a BST rooted at current.
   * @return true if the newRecord was successfully added to this PatientRecordTree, false otherwise
   */
  public static boolean addPatientRecordHelper(PatientRecord newRecord, PatientRecordNode current) {
    if (newRecord.getDateOfBirth().compareTo(current.getPatientRecord().getDateOfBirth()) < 0) {
      if (current.getLeftChild() == null) {
        current.setLeftChild(new PatientRecordNode(newRecord));
        return true;
      } else if (current.getLeftChild() != null) {
        addPatientRecordHelper(newRecord, current.getLeftChild());
        return true;
      }
    }
    if (newRecord.getDateOfBirth().compareTo(current.getPatientRecord().getDateOfBirth()) > 0) {
      if (current.getRightChild() == null) {
        current.setRightChild(new PatientRecordNode(newRecord));
        return true;
      } else if (current.getRightChild() != null) {
        addPatientRecordHelper(newRecord, current.getRightChild());
        return true;
      }
    }
    return false;
  }

  /**
   * Adds a new PatientRecord to this PatientRecordTree
   * 
   * @param newRecord a new PatientRecord to add to this BST.
   * @return true if the newRecord was successfully added to this BST, and returns false if there is
   *         a match with this PatientRecord already already stored in this BST.
   */
  public boolean addPatientRecord(PatientRecord newRecord) {
    if (isEmpty()) {
      root = new PatientRecordNode(newRecord);// Add newRecord to an empty PatientRecordTree
      ++size;
      return true;

    } else if (!isEmpty()) {
      addPatientRecordHelper(newRecord, root);
      ++size;
      return true;

    }
    return false;
  }

  /**
   * Recursive helper method which returns a String representation of the BST rooted at current. An
   * example of the String representation of the contents of a PatientRecordTree is provided in the
   * description of the above toString() method.
   * 
   * @param current reference to the current PatientRecordNode within this BST.
   * @return a String representation of all the PatientRecords stored in the sub-tree
   *         PatientRecordTree rooted at current in increasing order with respect to the patients
   *         dates of birth. Returns an empty String "" if current is null.
   */
  public static String toStringHelper(PatientRecordNode current) {
    String s = "";
    if (current == null) {
      return "";
    }
    if (current.getLeftChild() != null) {
      s += toStringHelper(current.getLeftChild());
    }
    s += current.getPatientRecord().toString() + "\n";
    if (current.getRightChild() != null) {
      s += toStringHelper(current.getRightChild());
    }

    return s;
  }

  /**
   * Returns a String representation of all the PatientRecords stored within this BST in the
   * increasing order, separated by a newline "\n". For instance: "Sarah(1/2/1935)" + "\n" +
   * "George(5/27/1943)" + "\n" + "Adam(8/12/1972)" + "\n" + "Norah(11/23/1985)" + "\n" +
   * "William(6/4/1998)" + "\n" + "Nancy(9/12/2003)" + "\n" + "Sam(4/20/2019)" + "\n"
   * 
   * @return a String representation of all the PatientRecords stored within this BST sorted in an
   *         increasing order with respect to the dates of birth of the patients (i.e. from the
   *         oldest patient to the youngest patient). Returns an empty string "" if this BST is
   *         empty.
   */
  public String toString() {
    String s = "";
    s += toStringHelper(root);
    return s;
  }

  /**
   * Search for a patient record (PatientRecord) given the date of birth as lookup key.
   * 
   * @param date a String representation of the date of birth of a patient in the format mm/dd/yyyy
   * @return the PatientRecord of the patient born on date.
   * @throws a NoSuchElementException with a descriptive error message if there is no PatientRecord
   *           found in this BST having the provided date of birth
   */
  public PatientRecord lookup(String date) {
    PatientRecord findRecord = new PatientRecord("", date);
    return this.lookupHelper(findRecord, root);
  }

  /**
   * Recursive helper method to lookup a PatientRecord given a reference PatientRecord with the same
   * date of birth in the subtree rooted at current
   * 
   * @param findRecord a reference to a PatientRecord target we are lookup for a match in the BST
   *                   rooted at current.
   * @param current    "root" of the subtree we are looking for a match to findRecord within it.
   * @return reference to the PatientRecord stored stored in this BST which matches findRecord.
   * @throws NoSuchElementException with a descriptive error message if there is no patient record
   *                                whose date of birth matches date, stored in this BST.
   */
  private PatientRecord lookupHelper(PatientRecord findRecord, PatientRecordNode current) {
    if (current == null)
      throw new NoSuchElementException("No search results.");
    else {
      if (findRecord.compareTo(current.getPatientRecord()) == 0)
        return current.getPatientRecord();

      if (findRecord.compareTo(current.getPatientRecord()) < 0)
        if (current.getLeftChild() != null)
          return lookupHelper(findRecord, current.getLeftChild());
        else
          throw new NoSuchElementException("No search results.");

      if (findRecord.compareTo(current.getPatientRecord()) > 0)
        if (current.getRightChild() != null)
          return lookupHelper(findRecord, current.getRightChild());
        else
          throw new NoSuchElementException("No search results.");
      throw new NoSuchElementException("No search results.");

    }
  }


  /**
   * Computes and returns the height of this BST, counting the number of nodes (PatientRecordNodes)
   * from root to the deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  public int height() {
    return heightHelper(root);
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current
   * 
   * @param current pointer to the current PatientRecordNode within a PatientRecordTree
   * @return height of the subtree rooted at current, counting the number of PatientRecordNodes
   */
  public static int heightHelper(PatientRecordNode current) {
    if (current == null) {
      return 0;
    } else {
      int leftDepth = heightHelper(current.getLeftChild());
      int rightDepth = heightHelper(current.getRightChild());

      if (leftDepth > rightDepth) {
        return leftDepth + 1;
      } else {
        return rightDepth + 1;
      }
    }
  }


  /**
   * Returns the PatientRecord of the youngest patient in this BST.
   * 
   * @return the PatientRecord of the youngest patient in this BST and null if this tree is empty.
   */
  public PatientRecord getRecordOfYoungestPatient() {
    return getRecordOfYoungestPatientHelper(root);
  }

  /**
   * This is a Recursive helper method that helps compute the Youngest Person in the Binary Search
   * Tree
   * 
   * @param current current pointer to the current PatientRecordNode within a PatientRecordTree
   * @return the PatientRecord of the youngest patient in this BST and null if this tree is empty.
   */
  private PatientRecord getRecordOfYoungestPatientHelper(PatientRecordNode current) {
    if (current == null) {
      return null;
    }

    if (current.getRightChild() == null)
      return current.getPatientRecord();
    return getRecordOfYoungestPatientHelper(current.getRightChild());
  }

  /**
   * Returns the PatientRecord of the oldest patient in this BST.
   * 
   * @return the PatientRecord of the oldest patient in this BST, and null if this tree is empty.
   */
  public PatientRecord getRecordOfOldestPatient() {
    return getRecordOfOldestPatientHelper(root);
  }

  /**
   * This is a Recursive helper method that helps compute the oldest Person in the Binary Search
   * Tree
   * 
   * @param current current current pointer to the current PatientRecordNode within a
   *                PatientRecordTree
   * @return the PatientRecord of the oldest patient in this BST, and null if this tree is empty.
   */
  private PatientRecord getRecordOfOldestPatientHelper(PatientRecordNode current) {
    if (current == null) {
      return null;
    }
    if (current.getLeftChild() == null)
      return current.getPatientRecord();
    return getRecordOfOldestPatientHelper(current.getLeftChild());
  }
}