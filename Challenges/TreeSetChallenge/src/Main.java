public class Main {
    public static void main(String[] args) {
        Theatre theatre = new Theatre("OrtaOyun", 5, 130);
        theatre.printSeatMap();
        theatre.reserveSeat('A',28);
        theatre.reserveSeat('D',12);
        theatre.reserveSeat('E',26);
        theatre.reserveSeat('E',26);

        System.out.println("");
        theatre.printSeatMap();
    }
}