package dsaLinked;

public class LinkedList {
	Node head;
	Node tail;
	
	Node reservationHead;
	Node reservationTail;
	
	public LinkedList() {
		this.head = null;
		this.tail = null;
	}

    public boolean hasNext(Node data) {
        if (data.next != null) return true;
        return false;
    }

    public void append(int data){
        Node newNode = new Node(data);
        if (head != null) {
            Node current = head;
            while (hasNext(current)) {
                current = current.next;
            }

            current.next = newNode;
        } 
        else {
            head = newNode;
            return;
        }    
    }
	// Method to create a defined amount of seats.
	public void createSeat(int amount) {
		for (int i = 1; i <= amount; i++) {
			Node newNode = new Node(i);
			if (head == null) {
				head = newNode;
				tail = newNode;
				continue;
			}
			
			Node current = tail;
			current.next = newNode;
			newNode.prev = current;
			tail = newNode;
		}
	}
	
	// Method to reserve seats
	public boolean reserveSeat(int seatNumber) {
		// While loop used to iterate throughout the entire linked list to find and select a specific seat.
		// That specific seat is then defined as "current",
		// and given the reservation value of "true".
		Node current = head;
		while (current != null) {
			if (current.seat != seatNumber) {
				current = current.next;
                continue;
			} 
            else if (current.seat == seatNumber) {
				if (current.reservation) {
					System.out.println("Error: Cannot reserve an already reserved seat");
                    return false;
				} else {
					current.reservation = true;
                    break;
				}
			}
		}

		// Used to create the linked list for the reservation.
		if (reservationHead == null) {
            // for some reason { reservationHead = current } changes the original list, nawawala si current sa original
            Node newNode = new Node(current.seat);
			reservationHead = newNode;
			reservationTail = newNode;
			return true;
		}
		
		Node newReservation = new Node(current.seat);
		Node currentReservation = reservationTail;
		currentReservation.next = newReservation;
		newReservation.prev = currentReservation;
		reservationTail = newReservation;
		
		return true;
	}
	
	// Method to cancel reservations.
	public boolean cancelReservation(int seatNumber) {
		// While loop used to iterate throughout the entire linked list to find and select a specific seat.
		// That specific seat is then defined as "current",
		// and given the reservation value of "false".
		Node current = head;
		while (current != null) {
			if (current.seat < seatNumber) {
				current = current.next;
			} else if (current.seat == seatNumber) {
				if (current.reservation) {
					current.reservation = false;
                    break;
				} else {
					System.out.println("Error: Cannot cancel reservation on available seat");
                    return false;
				}
			}
		}
		
		// While loop used to iterate throughout the entire reservation linked list to find and select a specific reserved seat.
		// That specific reserved seat is then defined as "currentReservation",
		Node currentReservation = reservationHead;
		while (currentReservation != null) {
			if (currentReservation.seat != seatNumber) {
				currentReservation = currentReservation.next;
			} 
            else if (currentReservation.seat == seatNumber) {
				
				// The program proceeds here if the user is trying to cancel the tail of reservation list
				if (currentReservation.next == null) {
					currentReservation.prev.next = null;
                    reservationTail = current.prev;
					currentReservation = null;
					return true;
				}
				
				// The program proceeds here if the user is trying to cancel the head of the reservation list
				if (currentReservation.prev == null) {
					currentReservation.next.prev = null;
                    reservationHead = currentReservation.next;
					currentReservation = null;
					return true;
				}
				
				// The program proceeds here if the user is trying to cancel neither the head or tail of the reservation list
				currentReservation.prev.next = currentReservation.next;
				currentReservation.next.prev = currentReservation.prev;
				currentReservation = null;
				return true;
			}
		}
		
		return true;
	}
	
	public boolean checkSeatAvailability(int seatNumber) {
		Node current = head;
		while (current != null) {
			if (current.seat < seatNumber) {
				current = current.next;
			} else if (current.seat == seatNumber) break;
		}
		return current.reservation;
	}
	
	public String[] reservedSeats() {
		Node currentReservation = reservationHead;
        int size = resSize();
        String[] reservSeats = new String[size];
        int i = 0;
		while (currentReservation != null) {
			// System.out.println("Seat Number: " + currentReservation.seat);
            reservSeats[i] = "Seat Number: " + currentReservation.seat;
			currentReservation = currentReservation.next;
            i++;
		}

        return reservSeats;
	}
	
	public String[] availableSeats() {
		Node current = head;

        int size = size();
        int size2 = resSize();
        size -= size2;
        String[] availSeats = new String[size];
        
        int i = 0;
		while (current != null) {
			if (current.reservation == false) {
                availSeats[i] = "Seat Number: " + current.seat;
				current = current.next;
                i++;
			}
            else {
                current = current.next;
            }
		}
        return availSeats;
	}

    public int resSize() {
        int totalNodes = 0;
        Node current = reservationHead;
        while (current != null) {
            totalNodes++;
            current = current.next;
        }
        return totalNodes;
    }

    public int size() {
        int totalNodes = 0;
        Node current = head;
        while (current != null) {
            totalNodes++;
            current = current.next;
        }
        return totalNodes;
    }
}
