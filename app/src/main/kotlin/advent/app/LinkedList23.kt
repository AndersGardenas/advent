package advent.app

class LinkedList23 {
    var head: Node? = null
    var tail: Node? = null
    var length: Int = 0

    inner class Node(var value: Int) {
        var next: Node? = null
    }

    /* Add a node before the first element of the linked list */
    fun addAtHead(value: Int) {
        val h = this.head
        val newNode = Node(value)
        newNode.next = this.head
        head = newNode
        if (h == null) tail = newNode
        this.length++
    }




    /* Append a node to the last element of the linked list. */
    fun addAtTail(value: Int) {
        var h = head
        val newNode = Node(value)
        newNode.next = null
        while (h!!.next != null) h = h.next
        h.next = newNode
        tail = newNode
        this.length++
    }

    /* Add a node before the index-th node in the linked list */
    fun addAtIndex(index: Int, value: Int) {
        var h = head
        var newNode = Node(value)
        var counter = 0
        if (index < 0 || index > this.length) return
        if (index == 0) {
            addAtHead(value)
            return
        }
        if (index == this.length) {
            addAtTail(value)
            return
        }
        while (counter != index - 1) {
            h = h!!.next
            counter++
        }
        newNode.next = h!!.next
        h.next = newNode
        this.length++
    }

    /* Delete the index-th node in the linked list, if the index is valid. */
    fun deleteAtIndex(index: Int) {
        var curr = this.head
        var prev: Node? = null
        var counter = 0
        if (index < 0 || index >= this.length) return
        if (index == 0) {
            head = curr!!.next
            this.length--
            return
        }
        while (counter != index) {
            prev = curr
            curr = prev!!.next
            counter++
        }
        prev!!.next = curr!!.next
        if (index == length - 1) tail = prev
        this.length--
    }

    /*  Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    fun get(index: Int): Int {
        var h = head
        var counter = 0
        if (index < 0 || index >= this.length) return -1
        while (counter != index) {
            h = h!!.next
            counter++
        }
        return h!!.value
    }

    fun indexOf(value: Int): Int {
        var h = head
        var counter = 0
        if (value < 0 || value >= this.length) return -1
        while (h!!.value != value) {
            h = h.next
            counter++
        }
        return counter
    }

}