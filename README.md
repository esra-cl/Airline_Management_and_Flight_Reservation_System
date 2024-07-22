# Airline_Management_and_Flight_Reservation_System

The Airline Management and Flight Reservation System developed using Java and NetBeans for coding and was designed to facilitate airline operations, including flight scheduling, airport management, aircraft management, and passenger reservations. The system ensures efficient handling of airline resources and passenger information.

## System Requirements
- System administrators can list and add airline companies.
- System administrators can list and add airports.
- System administrators can list and add aircraft.
- System administrators can list and add flights.
- System administrators can list information about passengers with reservations.
- Customers can search for flights by date and departure/arrival airports.
- Customers can make reservations for multiple passengers on a single flight.
- Customers can cancel their reservations.

## File Descriptions
1. **HavayoluSirketleri.txt:** Contains airline company codes and names.
2. **Havalimanlari.txt:** Contains airport names, countries, and cities.
3. **Ucaklar.txt:** Contains aircraft types, maximum passenger capacities, and production years.
4. **Ucuslar.txt:** Contains flight details, including aircraft type and airline company.
5. **Kisiler.txt:** Contains personal information of individuals, categorized as Manager, Passenger, or CabinCrew.
6. **Rezervasyonlar.txt:** Contains reservation details for customers, with information on the first line about what is stored.

## Execution and Testing
- Compile the code: `javac *.java` or `javac Main.java`
- Run the program: `java Main`
- Check the output file (rezervasyonlar.txt) and other relevant text files after operations.
- In the Main method, determine if the user is an administrator or a customer.
  - For administrators, allow operations with a predefined password.
  - For customers, allow flight search, listing, and reservation operations.

