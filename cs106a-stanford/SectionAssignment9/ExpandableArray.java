/**
 * This class provides methods for working with an array that expands to include
 * any positive index value supplied by the caller.
 */
public class ExpandableArray {
    private Object[] backingArray;

    public static void main(String[] args) {
        ExpandableArray myList = new ExpandableArray();
        myList.set(14, "Bob");
        myList.set(21, "Sally");

        System.out.println(myList.get(14));
        System.out.println(myList.get(21));
        System.out.println(myList.get(10));
        System.out.println(myList.get(100));
    }

    /**
     * Creates a new expandable array with no elements.
     */
    public ExpandableArray() {
        backingArray = new Object[0];
    }

    /**
     * Sets the element at the given index position to the specified. value. If
     * the internal array is not large enough to contain that element, the
     * implementation expands the array to make room.
     */
    public void set(int index, Object value) {
        if (index >= backingArray.length) {
            Object[] newBackingArray = new Object[index + 1];
            System.arraycopy(backingArray, 0, newBackingArray, 0, backingArray.length);

            backingArray = newBackingArray;
        }

        backingArray[index] = value;
    }

    /**
     * Returns the element at the specified index position, or null if no such
     * element exists. Note that this method never throws an out-of-bounds
     * exception; if the index is outside the bounds of the array, the return
     * value is simply null.
     */
    public Object get(int index) {
        if (index >= backingArray.length) {
            return null;
        } else {
            return backingArray[index];
        }
    }
}