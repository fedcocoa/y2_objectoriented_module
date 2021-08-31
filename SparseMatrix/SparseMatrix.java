//c1946553

import java.util.*;
import java.io.*;

// A class that represents a dense vector and allows you to read/write its elements
class DenseVector {
	private int[] elements;

	public DenseVector(int n) {
		elements = new int[n];
	}

	public DenseVector(String filename) {
		File file = new File(filename);
		ArrayList<Integer> values = new ArrayList<Integer>();

		try {
			Scanner sc = new Scanner(file);

			while (sc.hasNextInt()) {
				values.add(sc.nextInt());
			}

			sc.close();

			elements = new int[values.size()];
			for (int i = 0; i < values.size(); ++i) {
				elements[i] = values.get(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Read an element of the vector
	public int getElement(int idx) {
		return elements[idx];
	}

	// Modify an element of the vector
	public void setElement(int idx, int value) {
		elements[idx] = value;
	}

	// Return the number of elements
	public int size() {
		return (elements == null) ? 0 : (elements.length);
	}

	// Print all the elements
	public void print() {
		if (elements == null) {
			return;
		}

		for (int i = 0; i < elements.length; ++i) {
			System.out.println(elements[i]);
		}
	}
}

// A class that represents a sparse matrix
public class SparseMatrix { 
	// Auxiliary function that prints out the command syntax
	public static void printCommandError() {
		System.err.println("ERROR: use one of the following commands");
		System.err.println(" - Read a matrix and print information: java SparseMatrix -i <MatrixFile>");
		System.err.println(" - Read a matrix and print elements: java SparseMatrix -r <MatrixFile>");
		System.err.println(" - Transpose a matrix: java SparseMatrix -t <MatrixFile>");
		System.err.println(" - Add two matrices: java SparseMatrix -a <MatrixFile1> <MatrixFile2>");
		System.err.println(" - Matrix-vector multiplication: java SparseMatrix -v <MatrixFile> <VectorFile>");
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			printCommandError();
			System.exit(-1);
		}

		if (args[0].equals("-i")) {
			if (args.length != 2) {
				printCommandError();
				System.exit(-1);
			}

			SparseMatrix mat = new SparseMatrix();
			mat.loadEntries(args[1]);
			System.out.println("Read matrix from " + args[1]);
			System.out.println("The matrix has " + mat.getNumRows() + " rows and " + mat.getNumColumns() + " columns");
			System.out.println("It has " + mat.numNonZeros() + " non-zeros");
		} else if (args[0].equals("-r")) {
			if (args.length != 2) {
				printCommandError();
				System.exit(-1);
			}

			SparseMatrix mat = new SparseMatrix();
			mat.loadEntries(args[1]);
			System.out.println("Read matrix from " + args[1] + ":");
			mat.print();
		} else if (args[0].equals("-t")) {
			if (args.length != 2) {
				printCommandError();
				System.exit(-1);
			}

			SparseMatrix mat = new SparseMatrix();
			mat.loadEntries(args[1]);
			System.out.println("Read matrix from " + args[1]);
			SparseMatrix transpose_mat = mat.transpose();
			System.out.println();
			System.out.println("Matrix elements:");
			mat.print();
			System.out.println();
			System.out.println("Transposed matrix elements:");
			transpose_mat.print();
		} else if (args[0].equals("-a")) {
			if (args.length != 3) {
				printCommandError();
				System.exit(-1);
			}

			SparseMatrix mat1 = new SparseMatrix();
			mat1.loadEntries(args[1]);
			System.out.println("Read matrix 1 from " + args[1]);
			System.out.println("Matrix elements:");
			mat1.print();

			System.out.println();
			SparseMatrix mat2 = new SparseMatrix();
			mat2.loadEntries(args[2]);
			System.out.println("Read matrix 2 from " + args[2]);
			System.out.println("Matrix elements:");
			mat2.print();
			SparseMatrix mat_sum1 = mat1.add(mat2);

			System.out.println();
			mat1.multiplyBy(2);
			SparseMatrix mat_sum2 = mat1.add(mat2);

			mat1.multiplyBy(5);
			SparseMatrix mat_sum3 = mat1.add(mat2);

			System.out.println("Matrix1 + Matrix2 =");
			mat_sum1.print();
			System.out.println();

			System.out.println("Matrix1 * 2 + Matrix2 =");
			mat_sum2.print();
			System.out.println();

			System.out.println("Matrix1 * 10 + Matrix2 =");
			mat_sum3.print();
		} else if (args[0].equals("-v")) {
			if (args.length != 3) {
				printCommandError();
				System.exit(-1);
			}

			SparseMatrix mat = new SparseMatrix();
			mat.loadEntries(args[1]);
			DenseVector vec = new DenseVector(args[2]);
			DenseVector mv = mat.multiply(vec);

			System.out.println("Read matrix from " + args[1] + ":");
			mat.print();
			System.out.println();

			System.out.println("Read vector from " + args[2] + ":");
			vec.print();
			System.out.println();

			System.out.println("Matrix-vector multiplication:");
			mv.print();
		}
	}

	// Loading matrix entries from a text file
	// You need to complete this implementation
	public void loadEntries(String filename) {
		File file = new File(filename);

		try {
			Scanner sc = new Scanner(file);
			numRows = sc.nextInt();
			numCols = sc.nextInt();
			entries = new ArrayList<Entry>();

			while (sc.hasNextInt()) {
				// Read the row index, column index, and value of an element
				int row = sc.nextInt();
				int col = sc.nextInt();
				int val = sc.nextInt();

				// Add your code here to add the element into data member entries
				entries.add(new Entry((row*numCols)+col,val));
			}
			sc.close();

			// Add your code here for sorting non-zero elements
			entries = sort(entries);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			numRows = 0;
			numCols = 0;
			entries = null;
		}
	}

	public ArrayList<Entry> sort(ArrayList<Entry> toSort) {
		if(toSort.size() == 0) {
			return toSort;
		}
        ArrayList<ArrayList<Entry>> paired = pairs(split(toSort));
        while(paired.size() != 1) {
            paired = pairs(paired);
        }
        return paired.get(0);
    }

	public ArrayList<ArrayList<Entry>> pairs(ArrayList<ArrayList<Entry>> toPair) {
        if(toPair.size() == 1) {
            return toPair;
        }
        ArrayList<ArrayList<Entry>> returnPairs = new ArrayList<ArrayList<Entry>>();
        int pairs = Math.floorDiv(toPair.size(),2);
        boolean even = toPair.size() % 2 == 0;

        for(int i = 0; i < (pairs*2)-1; i+=2) {
            ArrayList<Entry> currentPair = sort_combine(toPair.get(i),toPair.get(i+1));
            returnPairs.add(currentPair);
        }
       
        if(!even) {
            ArrayList<Entry> triplet = sort_combine(toPair.get(toPair.size()-1),returnPairs.get(returnPairs.size()-1));
            returnPairs.set(returnPairs.size()-1,triplet);
        }

        return returnPairs;
    }

	public ArrayList<ArrayList<Entry>> split(ArrayList<Entry> toSplit) {
        ArrayList<ArrayList<Entry>> singles = new ArrayList<ArrayList<Entry>>();
        for(int i = 0; i < toSplit.size(); i++) {
            ArrayList<Entry> single = new ArrayList<Entry>();
            single.add(toSplit.get(i));
            singles.add(single);
        }
        return singles;
    }

	public ArrayList<Entry> sort_combine(ArrayList<Entry> a, ArrayList<Entry> b) {
        int a_pointer = 0;
        int b_pointer = 0;
		Entry max = new Entry(Integer.MAX_VALUE,0);
        Entry a_value = max;
        Entry b_value = max;
        ArrayList<Entry> combined = new ArrayList<Entry>();

        while(a.size() + b.size() != combined.size()) {
            if(a_pointer == a.size()) {
                a_value = max;
            } else {
                a_value = a.get(a_pointer);
            }
            if(b_pointer == b.size()) {
                b_value = max;
            } else {
                b_value = b.get(b_pointer);
            }
            if(a_value.getPosition() <= b_value.getPosition()) {
                combined.add(a_value);
                a_pointer++;
            } else {
                combined.add(b_value);
                b_pointer++;
            }
        }

        return combined;
    }

	// Default constructor
	public SparseMatrix() {
		numRows = 0;
		numCols = 0;
		entries = null;
	}

	// A class representing a pair of column index and elements
	private class Entry {
		private int position; // Position within row-major full array representation
		private int value; // Element value

		// Constructor using the column index and the element value
		public Entry(int pos, int val) {
			this.position = pos;
			this.value = val;
		}

		//remove this for later on
		public String toString() {
			return String.format("[%d]:%d", this.position,this.value);
		}

		// Copy constructor
		public Entry(Entry entry) {
			this(entry.position, entry.value);
		}

		// Read column index
		int getPosition() {
			return position;
		}

		// Set column index
		void setPosition(int pos) {
			this.position = pos;
		}

		// Read element value
		int getValue() {
			return value;
		}

		// Set element value
		void setValue(int val) {
			this.value = val;
		}
	}

	// Adding two matrices
	public SparseMatrix add(SparseMatrix M) {
		ArrayList<Entry> newEntries = new ArrayList<Entry>();
		
		int a_ptr = 0;
		int b_ptr = 0;

		Entry a_entry;
		Entry b_entry;

		while(a_ptr < this.entries.size() || b_ptr < M.entries.size()) {
			if(a_ptr >= this.entries.size()) {
				a_entry = new Entry(Integer.MAX_VALUE,0);
			} else {
				a_entry = new Entry(this.entries.get(a_ptr));
			}
			if(b_ptr >= M.entries.size()) {
				b_entry = new Entry(Integer.MAX_VALUE,0);
			} else {
				b_entry = new Entry(M.entries.get(b_ptr));
			}


			if(a_entry.getPosition() == b_entry.getPosition()) {
				Entry newEntry = new Entry(a_entry.getPosition(),a_entry.getValue()+b_entry.getValue());
				if(newEntry.getValue() != 0) {
					newEntries.add(newEntry);
				}
				a_ptr++;
				b_ptr++;
			} else if(a_entry.getPosition() < b_entry.getPosition()) {
				newEntries.add(a_entry);
				a_ptr++;
			} else {
				newEntries.add(b_entry);
				b_ptr++;
			}
		}

		SparseMatrix returnMatrix = new SparseMatrix();
		returnMatrix.entries = sort(newEntries);
		returnMatrix.numCols = this.getNumColumns();
		returnMatrix.numRows = this.getNumRows();

		return returnMatrix;
	}

	// Transposing a matrix
	public SparseMatrix transpose() {
		SparseMatrix returnMatrix = new SparseMatrix();
		returnMatrix.numRows = this.getNumColumns();
		returnMatrix.numCols = this.getNumRows();
		returnMatrix.entries = new ArrayList<Entry>();
		for(int i = 0; i < this.entries.size(); i++) {
			Entry currentEntry = this.entries.get(i);
			int row = Math.floorDiv(currentEntry.getPosition(),numCols);
			int column = currentEntry.position % numCols;
			returnMatrix.entries.add(new Entry((column*numRows)+row,currentEntry.getValue()));
		}
		returnMatrix.entries = sort(returnMatrix.entries);
		return returnMatrix;
	}

	public Entry getEntryWithPosition(int position) {
		for(int i = 0; i < this.entries.size(); i++) {
			Entry current = this.entries.get(i);
			if(current.getPosition() == position) {
				return current;
			}
		}
		return null;
	}

	// Matrix-vector multiplication
	public DenseVector multiply(DenseVector v) {
		DenseVector returnVector = new DenseVector(this.getNumRows());

		for(int row = 0; row < this.getNumRows(); row++) {
			int sum = 0;
			for(int column = 0; column < this.getNumColumns(); column++) {
				Entry currentEntry = getEntryWithPosition((row*this.numCols)+column);
				if(currentEntry != null) {
					currentEntry = new Entry(currentEntry);
					sum += currentEntry.getValue()*v.getElement(column);
				}
			}
			returnVector.setElement(row, sum);
		}
		
		return returnVector;
	}

	// Return the number of non-zeros
	public int numNonZeros() {
		// Add your code here
		return entries.size();
	}

	// Multiply the matrix by a scalar, and update the matrix elements
	public void multiplyBy(int scalar) {
		// System.out.println("USING MULTIPLY BY");
		for(int i = 0; i < this.entries.size(); i++) {
			int newValue = this.entries.get(i).getValue() * scalar;
			if(newValue != 0) {
				this.entries.get(i).setValue(newValue);
			}
			
		}
	}

	// Number of rows of the matrix
	public int getNumRows() {
		return this.numRows;
	}

	// Number of columns of the matrix
	public int getNumColumns() {
		return this.numCols;
	}

	// Output the elements of the matrix, including the zeros
	// Do not modify this method
	public void print() {
		int n_elem = numRows * numCols;
		int pos = 0;

		for (int i = 0; i < entries.size(); ++i) {
			int nonzero_pos = entries.get(i).getPosition();

			while (pos <= nonzero_pos) {
				if (pos < nonzero_pos) {
					System.out.print("0 ");
				} else {
					System.out.print(entries.get(i).getValue());
					System.out.print(" ");
				}

				if ((pos + 1) % this.numCols == 0) {
					System.out.println();
				}

				pos++;
			}
		}

		while (pos < n_elem) {
			System.out.print("0 ");
			if ((pos + 1) % this.numCols == 0) {
				System.out.println();
			}

			pos++;
		}
	}
	private int numRows; // Number of rows
	private int numCols; // Number of columns
	private ArrayList<Entry> entries; // Non-zero elements
}
