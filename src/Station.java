public class Station {

    private String stationName;
    private int stationId;

    Station(int errorCode) {
        switch (errorCode) {
            case 0: {
                this.stationName = "Nie można uzyskać połączenia z API, możliwy brak połączenia sieciowego";
                break;
            }
            case 1: {
                this.stationName = "Stacja o podanej nazwie nie istnieje, nie udało się odnaleźć stacji o podobnej nazwie";
                break;
            }
            case 2: {
                this.stationName = "Podana nazwa lokacji zawiera niedozwolone znaki";
                break;
            }
            case 3: {
                this.stationName = "Nie wpisano nazwy lokacji";
                break;
            }
            case 4: {
                this.stationName = "Przed wcisieciem przycisku select prosimy o wyszukanie interesujacej lokacji";
                break;
            }
        }
    }

    Station(String data) {
        //    this.data=data;
        stationId = extractStationId(data);
        System.out.println(data);
        stationName = extractStationName(data);
    }

    // access changed to public for testing
    public int extractStationId(String data) {
        return Integer.parseInt((data.substring(5).split(",", 2))[0]);
    }

    // access changed to public for testing
    public String extractStationName(String data) {
        String tmp = data.substring(5).split(",", 2)[1];
        return tmp.substring(15, tmp.length() - 1);
    }

    public String getStationName() {
        return stationName;
    }

    public int getStationId() {
        return stationId;
    }

    public String toString() {
        return getStationName();
    }
}
