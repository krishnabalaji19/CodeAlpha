import java.util.*;

class Room {
    private int roomNumber;
    private String roomType;
    private double price;
    private boolean isAvailable;

    public Room(int roomNumber, String roomType, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", roomType='" + roomType + '\'' +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                '}';
    }
}

class Reservation {
    private int reservationId;
    private String guestName;
    private Room room;
    private int duration;
    private double totalCost;

    public Reservation(int reservationId, String guestName, Room room, int duration) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.room = room;
        this.duration = duration;
        this.totalCost = room.getPrice() * duration;
    }

    public int getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public Room getRoom() {
        return room;
    }

    public int getDuration() {
        return duration;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", guestName='" + guestName + '\'' +
                ", room=" + room +
                ", duration=" + duration +
                ", totalCost=" + totalCost +
                '}';
    }
}

class Hotel {
	Scanner scanner = new Scanner(System.in);
    private List<Room> rooms;
    private List<Reservation> reservations;
    private int reservationCounter;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        reservationCounter = 1;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> searchAvailableRooms(String roomType) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getRoomType().equalsIgnoreCase(roomType) && room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Reservation makeReservation(String guestName, int roomNumber, int duration) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                room.setAvailable(false);
                Reservation reservation = new Reservation(reservationCounter++, guestName, room, duration);
                reservations.add(reservation);
                return reservation;
            }
        }
        return null;
    }

    public Reservation viewBookingDetails(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }

    public boolean processPayment(int reservationId) {
        Reservation reservation = viewBookingDetails(reservationId);
        if (reservation != null) {
            System.out.println("Processing payment of $" + reservation.getTotalCost() + " for reservation ID " + reservationId);
            System.out.print("Payment method : \n1.UPI\n 2.Card\n3.Cash\n");
            System.out.print("Enter your choice : ");
            int n = scanner.nextInt();
            if(n>0 && n<=3)
            	return true;
            else
            	System.out.println("Invalid Payment method");
        }
        return false;
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);
        hotel.addRoom(new Room(101, "Single", 1000.0));
        hotel.addRoom(new Room(102, "Single", 1000.0));
        hotel.addRoom(new Room(103, "Single", 1000.0));
        hotel.addRoom(new Room(104, "Single", 1000.0));
        hotel.addRoom(new Room(105, "Single", 1000.0));
        hotel.addRoom(new Room(106, "Single", 1000.0));
        hotel.addRoom(new Room(107, "Double", 1500.0));
        hotel.addRoom(new Room(108, "Double", 1500.0));
        hotel.addRoom(new Room(109, "Double", 1500.0));
        hotel.addRoom(new Room(110, "Double", 1500.0));
        hotel.addRoom(new Room(111, "Double", 1500.0));
        hotel.addRoom(new Room(112, "Double", 1500.0));
        hotel.addRoom(new Room(113, "Deluxe", 2500.0));
        hotel.addRoom(new Room(114, "Deluxe", 2500.0));
        hotel.addRoom(new Room(115, "Deluxe", 2500.0));
        hotel.addRoom(new Room(116, "Deluxe", 2500.0));
        hotel.addRoom(new Room(117, "Deluxe", 2500.0));
        hotel.addRoom(new Room(118, "Deluxe", 2500.0));
        hotel.addRoom(new Room(119, "Deluxe", 2500.0));
        hotel.addRoom(new Room(120, "Deluxe", 2500.0));
        
        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Process payment");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter room type (Single/Double/Deluxe): ");
                    String roomType = scanner.nextLine();
                    List<Room> availableRooms = hotel.searchAvailableRooms(roomType);
                    if (availableRooms.isEmpty()) {
                        System.out.println("No available rooms of type " + roomType);
                    } else {
                        System.out.println("Available " + roomType + " rooms:");
                        for (Room room : availableRooms) {
                            System.out.println(room);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter guest name: ");
                    String guestName = scanner.nextLine();
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter duration (in days): ");
                    int duration = scanner.nextInt();
                    Reservation reservation = hotel.makeReservation(guestName, roomNumber, duration);
                    if (reservation != null) {
                        System.out.println("Reservation successful: " + reservation);
                    } else {
                        System.out.println("Reservation failed. Room not available.");
                    }
                    break;

                case 3:
                    System.out.print("Enter reservation ID: ");
                    int reservationId = scanner.nextInt();
                    Reservation bookingDetails = hotel.viewBookingDetails(reservationId);
                    if (bookingDetails != null) {
                        System.out.println("Booking details: " + bookingDetails);
                    } else {
                        System.out.println("Booking not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter reservation ID: ");
                    int paymentReservationId = scanner.nextInt();
                    boolean paymentStatus = hotel.processPayment(paymentReservationId);
                    if (paymentStatus) {
                        System.out.println("Payment processed successfully.");
                    } else {
                        System.out.println("Payment failed. Reservation not found");
                    }
                    break;

                case 5:
                    System.out.println("Exiting the system. Thank you!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
