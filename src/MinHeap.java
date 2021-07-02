import Exceptions.HeapIsEmpty;
import Exceptions.HeapIsFull;

/**
 * Static MinHeap implementation using an array:
 *          right child -> position * 2
 *          left child -> (position * 2) +1
 *          parent node -> position/2
 *
 * @author Mireia Gasco Agorreta
 * @version 1.0
 */
public class MinHeap {

    //Attributes
    private int[] heap;
    private int length;

    private static final int root = 1;

    //Constructor
    public MinHeap(int maxLength){
        this.heap = new int[maxLength + 1];
        this.length = 0;
    }

    //Methods
    private int getParentPos(int position){
        return position/2;
    }

    private int getLeftChildPos(int position){
        return (position * 2)+1;
    }

    private int getRightChildPos(int position){
        return position * 2;
    }

    /**
     * Insert Method.  Inserts a new element to the heap and places it correctly to maintain the structure partially ordered.
     * @param newInt value to insert.
     * @throws HeapIsFull in case the heap is already full.
     */
    public void insert(int newInt) throws HeapIsFull{


        if (this.length + 1 == heap.length) throw new HeapIsFull();     //if the heap is full, throws the exception
        this.length++;

        heap[this.length] = newInt;     //adds the new value tho the end of the heap
        int currentNode = this.length;  //obtain the position of the new element

        //if the heap has more than 1 node
        if (this.length > 1){
            while (hasParent(currentNode) && heap[currentNode] < heap[getParentPos(currentNode)]){      //while we do not get to the root and the parent node has a bigger element
                swapNodes(currentNode, getParentPos(currentNode));  //swap nodes to maintain the partial order
                currentNode = getParentPos(currentNode);            //check parent node
            }
        }
    }

    private boolean hasParent(int position){
        return heap[getParentPos(position)] > -1;
    }

    private void swapNodes(int current, int parent){
        int aux = heap[parent];
        heap[parent] = heap[current];
        heap[current] = aux;
    }

    /**
     * PopRoot Method.  Removes and returns the root element while making sure the heap stays partially ordered.
     * @return the root element.
     * @throws HeapIsEmpty in case the heap is empty.
     */
    public int popRoot() throws HeapIsEmpty {
        if (this.length < 1) throw new HeapIsEmpty();

        int aux = heap[root];      //store the root element
        heap[root] = heap[length]; //move the last element to the root position
        this.length--;          //decrease the heap length

        if (length > 1){
            heapify(root);     //if the heap has more than 1 element, order it
        }

        return aux;
    }

    private void heapify(int position){

        int parent = heap[position];
        int lower = position;
        int aux;
        int auxPos;

        //if the node has childs
        if (!isLeaf(position)){

            //if it does not have a right child
            if (getRightChildPos(position) > this.length){
                if (heap[getLeftChildPos(position)] < parent){
                    lower = getLeftChildPos(position);
                }
            }

            //if it does not have a left child
            if (getLeftChildPos(position) > this.length){
                if (heap[getRightChildPos(position)] < parent){
                    lower = getRightChildPos(position);
                }
            }

            //if it has both childs
            else{
                int right = heap[getRightChildPos(position)];
                int left = heap[getLeftChildPos(position)];

                aux = left;
                auxPos = getLeftChildPos(position);

                //get the child with the lower value
                if (right < left){
                    aux = right;
                    auxPos = getRightChildPos(position);
                }

                //check if the value of the lower child is lower than the parent value
                if (aux < parent){
                    lower = auxPos;
                }

                if (lower != position){
                    swapNodes(position, lower);
                    heapify(lower);
                }
            }
        }
    }

    private boolean isLeaf(int position){
        return (getRightChildPos(position) > this.length) && (getLeftChildPos(position) > this.length);
    }

    /**
     * Empty method.  Indicates if the heap is empty.
     * @return true if the heap is empty, false if it is not.
     */
    public boolean isEmpty(){
        return this.length < 1;
    }

    /**
     * Root method. Returns the root value without removing it
     * @return the root value.
     */
    public int root(){
        return heap[root];
    }


    //to String methods

    @Override
    public String toString(){
        return writeRoot(root);
    }

    private String writeRoot(int position){
        StringBuilder sb = new StringBuilder();
        sb.append("--------------MinHeap--------------\n");

        if (position < this.length){
            sb.append(heap[position]);

            String pointerRight = "└──";
            String pointerLeft = (hasRightChild(position)) ? "├──" : "└──";

            writeNode(getLeftChildPos(position), sb, "", pointerLeft, hasRightChild(position));
            writeNode(getRightChildPos(position), sb, "", pointerRight, false);
        }

        return sb.toString();
    }

    private void writeNode(int position, StringBuilder sb, String padding, String pointer, boolean hasRightNode){
        if (position < this.length){
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(heap[position]);

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightNode){
                paddingBuilder.append("│  ");
            }
            else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "└──";
            String pointerLeft = (hasRightChild(position)) ? "├──" : "└──";

            writeNode(getLeftChildPos(position), sb, paddingForBoth, pointerLeft, hasRightChild(position));
            writeNode(getRightChildPos(position), sb, paddingForBoth, pointerRight, false);
        }
    }

    private boolean hasRightChild(int position){
        return getRightChildPos(position) < this.length;
    }
}
