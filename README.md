# Static implementation of a minHeap using Java
A minHeap is a complete tree structure that is always partially ordered, which means that the child nodes will always be bigger or equal to the parent nodes.  This behaviour is ideal for implementing priority queues, as it provides easy acces to the element on the top of the heap and guarantees it is always the one with the lower value.\
This implementation is built upon a static array and the diferent elements are disposed following three rules:
* The position of right child of a node is always parent_position * 2
* The position of the left child of a node is always (parent_position * 2) + 1
* The position of the parent node of a node is always (node_position / 2)

As for the minHeap class itself, it only contains two variables:
* `heap`: an array containing the heap.
* `length`: the total number of elements in the heap.

## Methods
The methods implemented are:
* `Constructor(maxLength)`: creates an empty minHeap with the given maxLength value.
* `insert(value)`: inserts the value in the minHeap, always mantaining the priority order.
* `popRoot()`: removes and returns the root element and reorganizes the heap to maintain the priority order withd the private `heapify(position)` method.
* `isEmpty()`: returns true if the minHeap is empty, false if it is not.
* `root()`: returns the root element without removing it.
* `toString()`: displays the heap as a tree structure.

## Exceptions
The exceptions implemented are:
* `HeapIsFull`: in case the `insert(value)` method cannot insert the value because the heap is already full.
* `HeapIsEmpty`: in case the `popRoot()` or `root()` methods cannot return the root value because the heap is empty.

